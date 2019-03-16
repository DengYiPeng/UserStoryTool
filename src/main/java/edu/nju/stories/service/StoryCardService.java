package edu.nju.stories.service;

import edu.nju.stories.vo.StoryCardListVO;

import java.util.List;

public interface StoryCardService {

    boolean createLane(String mapId, int yAxis, int numberOfList, String userId);

    boolean createList(String mapId, int xAxis, int numberOfLane, String userId);

    boolean modifyTitle(String cardId, String title);

    boolean modifyContent(String cardId, String content);

    boolean modifyState(String cardId, int state);

    List<StoryCardListVO> getStoryCardList(String mapId);

    boolean modifyOwnerOfChar(String cardId, String operatorId, String ownerRoleId);



}
