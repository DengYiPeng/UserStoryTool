package edu.nju.stories.service.impl;

import edu.nju.stories.constants.ErrorCode;
import edu.nju.stories.constants.StoryCardState;
import edu.nju.stories.dao.StoryCardDao;
import edu.nju.stories.dao.StoryMapDao;
import edu.nju.stories.dao.UserDao;
import edu.nju.stories.exception.LogicException;
import edu.nju.stories.models.StoryCardModel;
import edu.nju.stories.models.StoryMapModel;
import edu.nju.stories.models.UserModel;
import edu.nju.stories.service.StoryCardService;
import edu.nju.stories.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoryCardServiceImpl implements StoryCardService {

    @Autowired
    StoryCardDao storyCardDao;

    @Autowired
    UserDao userDao;

    @Autowired
    StoryMapDao storyMapDao;

    @Override
    public boolean createLane(String mapId, int yAxis, int numberOfList, String userId) {
        List<StoryCardModel> storyCardModelList = new ArrayList<>();
        for (int xAxis = 0; xAxis < numberOfList; xAxis++){
            StoryCardModel storyCardModel = new StoryCardModel(UUID.randomUUID().toString(), mapId, xAxis, yAxis,
                    0, userId, StoryCardState.TODO);
            storyCardModelList.add(storyCardModel);
        }
        boolean result = storyCardDao.update(storyCardModelList);
        return result;
    }

    @Override
    public boolean createList(String mapId, int xAxis, int numberOfLane, String userId) {
        List<StoryCardModel> storyCardModelList = new ArrayList<>();
        for (int yAxis = 0; yAxis < numberOfLane; yAxis++){
            StoryCardModel storyCardModel = new StoryCardModel(UUID.randomUUID().toString(), mapId, xAxis, yAxis,
                    0, userId, StoryCardState.TODO);
            storyCardModelList.add(storyCardModel);
        }
        boolean result = storyCardDao.update(storyCardModelList);
        return result;
    }

    @Override
    public boolean modifyTitle(String cardId, String title) {
        return storyCardDao.set(cardId, StoryCardModel.TITLE, title);
    }

    @Override
    public boolean modifyContent(String cardId, String content) {
        return storyCardDao.set(cardId, StoryCardModel.CONTENT, content);
    }

    @Override
    public boolean modifyState(String cardId, int state) {
        return storyCardDao.set(cardId, StoryCardModel.STATE, state);
    }

    @Override
    public List<StoryCardListVO> getStoryCardList(String mapId) {
        List<StoryCardModel> models = storyCardDao.findByMapId(mapId);
        List<String> allUserIds = models.stream().map(StoryCardModel::getOwnerId)
                .filter(Objects::nonNull).collect(Collectors.toList());
        allUserIds.addAll(models.stream().map(StoryCardModel::getCreatorId).collect(Collectors.toList()));
        List<UserModel> userModels = userDao.findByIds(allUserIds);
        Map<String, UserModel> userModelMap = userModels.stream().collect(Collectors.toMap(i->i.get_id(),i->i));
        Map<Integer, List<StoryCardModel>> modelMapByXAxis = models.stream()
                .collect(Collectors.groupingBy(StoryCardModel::getXAxis));

        List<StoryCardListVO> result = new ArrayList<>();

        Set<Map.Entry<Integer, List<StoryCardModel>>> entries = modelMapByXAxis.entrySet();
        for (Map.Entry<Integer, List<StoryCardModel>> entry : entries){
            List<StoryCardModel> cards = entry.getValue().stream()
                    .sorted(Comparator.comparingInt(StoryCardModel::getInnerIndex)).collect(Collectors.toList());
            StoryCardListVO tempListVO = new StoryCardListVO();
            tempListVO.setXAxis(entry.getKey());
            tempListVO.setTitle(cards.get(0).getTitle());
            tempListVO.setVos(cards.stream().map(i->new StoryCardVO(i, userModelMap.getOrDefault(i.getOwnerId(), userModelMap.get(i.getCreatorId())))).collect(Collectors.toList()));
            result.add(tempListVO);
        }
        return result;
    }

    @Override
    public boolean modifyOwnerOfChar(String cardId, String operatorId, String ownerRoleId) {
        storyCardDao.set(cardId, StoryCardModel.OWNER_ID, ownerRoleId);
        return true;
    }

    @Override
    public boolean modifyPosition(String cardId, String operatorId, int targetXAxis, int targetYAxis) {
        StoryCardModel cardModel = storyCardDao.findById(cardId);
        if (cardModel == null) return false;
        cardModel.setXAxis(targetXAxis);
        cardModel.setYAxis(targetYAxis);
        storyCardDao.save(cardModel);
        return true;
    }

    @Override
    public boolean addCard(String mapId, String operatorId, int xAxis, int yAxis, String content) {
        StoryCardModel cardModel = new StoryCardModel(UUID.randomUUID().toString(), mapId, xAxis, yAxis,
                0, operatorId, StoryCardState.TODO);
        cardModel.setContent(content);
        storyCardDao.save(cardModel);
        return true;
    }

    @Override
    public List<MemberCardVO> getMemberCards(String mapId, String operatorId) {
        StoryMapModel storyMapModel = storyMapDao.findById(mapId);
        if (!storyMapModel.getMemberIds().contains(operatorId)){
            throw new LogicException(ErrorCode.NO_PERMISSION, "没有对故事地图的操作权限");
        }else{
            List<String> userIds = storyMapModel.getMemberIds();
            List<UserVO> userVOS = userDao.findByIds(userIds).stream().map(UserVO::new).collect(Collectors.toList());
            List<SimpleStoryCardVO> storyCardVOS = storyCardDao.findByMapId(mapId).stream().map(SimpleStoryCardVO::new).collect(Collectors.toList());
            Map<String, List<SimpleStoryCardVO>> listMap = storyCardVOS.stream().collect(Collectors.groupingBy(SimpleStoryCardVO::getOwnerId));
            List<MemberCardVO> result = new ArrayList<>();
            for(UserVO userVO : userVOS){
                MemberCardVO tempMemberCardVO = new MemberCardVO();
                tempMemberCardVO.setCards(listMap.get(userVO.getUserId()));
                tempMemberCardVO.setUserVO(userVO);
                result.add(tempMemberCardVO);
            }
            return result;
        }
    }
}
