package edu.nju.stories.service;

import edu.nju.stories.vo.UserVO;

import java.util.List;

public interface StoryMapMemberService {

    boolean addMember(String operatorId, String beOperatedEmail, String mapId);

    boolean removeMember(String operatorId, String beOperatedEmail, String mapId);

    List<UserVO> getMapMemberUsers(String operatorUserId, String mapId);

}
