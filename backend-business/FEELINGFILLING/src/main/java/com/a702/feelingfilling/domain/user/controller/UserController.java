package com.a702.feelingfilling.domain.user.controller;

import com.a702.feelingfilling.domain.user.model.dto.UserDTO;
import com.a702.feelingfilling.domain.user.model.dto.UserJoinDTO;
import com.a702.feelingfilling.domain.user.model.dto.UserKakaoRequestDTO;
import com.a702.feelingfilling.domain.user.model.dto.UserKakaoResponseDTO;
import com.a702.feelingfilling.domain.user.service.UserService;
import com.a702.feelingfilling.global.jwt.JwtTokenService;
import com.a702.feelingfilling.global.jwt.JwtTokens;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@Api(tags = {"회원 관리 API"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ALREADY_EXIST = "already exists";

    private final JwtTokenService jwtTokenService;
    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody UserKakaoRequestDTO userKakaoDTO) {
        log.info("카카오 로그인 : " + userKakaoDTO);
        UserKakaoResponseDTO responseDTO = userService.kakaoLogin(userKakaoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //2. 회원가입
    @PostMapping
    public ResponseEntity<?> join(@RequestBody UserJoinDTO userJoinDTO) {
        log.info("회원가입 요청 : " + userJoinDTO);
        Map<String, Object> resultMap;
        try {
            resultMap = new HashMap<>();
            JwtTokens tokens = userService.join(userJoinDTO);
            resultMap.put("message", "SUCCESS");
            resultMap.put("access-token", tokens.getAccessToken());
            resultMap.put("refresh-token", tokens.getRefreshToken());
            return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
        } catch (Exception e) {
            resultMap = new HashMap<>();
            resultMap.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resultMap);
        }
    }//join

    //3. 정보조회
    @GetMapping("/{userId}")
//	@PreAuthorize("hasRole('ROLE_AMDIN') or hasRole('ROLE_USER')" )
    public ResponseEntity<?> getUser(@PathVariable int userId) {
        log.info("회원 정보 조회 요청");
        Map<String, Object> resultMap;
        try {
            resultMap = new HashMap<>();
//			resultMap.put("user", userService.getUser());
            resultMap.put("user", userService.getUser(userId));
            resultMap.put("message", "SUCCESS");
            return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
        } catch (Exception e) {
            resultMap = new HashMap<>();
            resultMap.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resultMap);
        }
    }

    //4. 정보수정
    @PutMapping
//	@PreAuthorize("hasRole('ROLE_AMDIN') or hasRole('ROLE_USER')" )
    public ResponseEntity<?> modifyUser(UserDTO userDTO) {
        log.info("회원 정보 수정 요청");
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("user", userService.modifyUser(userDTO));
            resultMap.put("message", "SUCCESS");
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resultMap);
        }
    }

    //5. 뱃지 조회
    @GetMapping("/badge/{userId}")
//	@PreAuthorize(("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')"))
    public ResponseEntity<?> getUserBadge(@PathVariable int userId) {
        log.info("회원 뱃지 조회 요청");
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();

        try {
            List<Integer> badges = userService.getUserBadge(userId);
            resultMap.put("badges", badges);
            log.debug("유저 뱃지 조회 ,", badges);

            resultMap.put("message", SUCCESS);
        } catch (Exception e) {
            log.error("유저 뱃지 조회 에러 : {}", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    //6. 뱃지 조회
    @GetMapping("/user/token")
    public ResponseEntity<?> tokenRefresh(@RequestHeader(value = "RefreshToken")String refreshToken) {
        log.info("Token Refresh Request");
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            jwtTokenService.verifyToken(refreshToken);
            log.info("유효한 refresh 토큰");
            //AccessToken 새로 생성하기
            JwtTokens newTokens = jwtTokenService.refreshAccessToken(refreshToken);
            resultMap.put("access-token", newTokens.getAccessToken());
            resultMap.put("refresh-token", newTokens.getRefreshToken());
            resultMap.put("message", SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(resultMap, status);
    }





}
