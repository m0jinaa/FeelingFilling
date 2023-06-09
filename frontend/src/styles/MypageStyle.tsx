import styled from 'styled-components/native';
import {Common} from '../components/Common';

export const Container = styled.View`
  flex: 1;
  background-color: ${Common.colors.white01};
  padding: 12px;
`;

export const BadgePageContainer = styled.View`
  flex: 1;
  width: 100%;
  background-color: ${Common.colors.backgroundColor01};
`;

export const BadgePageWrapper = styled.ScrollView`
  flex: 1;
  width: 100%;
`;

export const BadgePageContent = styled.View`
  flex-direction: row;
  flex-wrap: wrap;
  padding-top: 10px;
  padding-bottom: 30px;
`;

export const Heading = styled.Text`
  font-family: 'NotoSansKR-Bold';
  font-size: 20px;
  margin: 5px 0px;
  margin-left: 15px;
  color: ${Common.colors.deepGrey};
`;

export const ProfileContainer = styled.TouchableOpacity`
  flex-direction: row;
  background-color: ${Common.colors.subColor04};
  border-radius: 20px;
  padding: 20px;
  justify-content: space-between;
  align-items: center;
  elevation: 5;
`;

export const ProfileWrapper = styled.View`
  flex-direction: row;
`;

export const EmotionImage = styled.Image`
  width: 60px;
  height: 60px;
`;

export const HeadingWrapper = styled.View`
  flex-direction: row;
  justify-content: space-between;
  margin-top: 20px;
  align-items: center;
`;

export const SubHeading = styled.Text`
  font-family: 'NotoSansKR-Bold';
  margin-right: 15px;
  font-size: 15px;
  color: ${Common.colors.selectGrey};
`;

export const BadgeContainer = styled.View`
  flex-direction: row;
  height: 80px;
  padding: 0px 10px;
  /* justify-content: space-between; */
  align-items: center;
`;

export const SingleBadgeWrapper = styled.View`
  margin: 0px 28px;
`;

export const NoBadge = styled.Text`
  font-family: 'NotoSansKR-Regular';
  margin-left: 15px;
  font-size: 15px;
  color: ${Common.colors.selectGrey};
`;

export const PlainText = styled.Text`
  font-family: 'NotoSansKR-Regular';
  margin-left: 15px;
  font-size: 15px;
  color: ${Common.colors.deepGrey};
`;

export const MenuList = styled.View`
  margin: 0px 15px;
  margin-top: 15px;
  padding-top: 25px;
  border-top-width: 1px;
  border-top-color: ${Common.colors.basicGrey};
`;

export const SingleMenu = styled.TouchableOpacity`
  padding: 5px 0px;
`;

export const SingleBadgeContainer = styled.View`
  width: 33.3333%;
  height: 230px;
  padding: 5px;
  padding-top: 20px;
  align-items: center;
`;

export const BadgeImage = styled.Image`
  width: 70px;
  height: 70px;
`;

export const BadgeTitle = styled.Text`
  font-family: 'NotoSansKR-Bold';
  color: ${Common.colors.deepGrey};
  text-align: center;
  margin: 5px 0px;
`;

export const BadgeDescription = styled.Text`
  text-align: center;
`;
