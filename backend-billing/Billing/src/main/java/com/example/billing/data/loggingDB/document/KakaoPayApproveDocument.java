package com.example.billing.data.loggingDB.document;

import com.example.billing.data.billingDB.entity.KakaoOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@NoArgsConstructor
@Document(collection = "kakao_pay_approvement")
@AllArgsConstructor
@Builder
public class KakaoPayApproveDocument {
    @Id
    private String _id;
    private int userId;
    private String serviceName;
    private int serviceUserId;
    private String status;
    private int orderId;
    private String tid;
    private String sid;
    private String aid;
    private LocalDateTime createdDate;


}
