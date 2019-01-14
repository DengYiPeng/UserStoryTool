package edu.nju.stories.service.impl;

import edu.nju.stories.constants.StoryCardState;
import edu.nju.stories.dao.StoryCardDao;
import edu.nju.stories.models.StoryCardModel;
import edu.nju.stories.service.StoryCardService;
import edu.nju.stories.vo.StoryCardListVO;
import edu.nju.stories.vo.StoryCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoryCardServiceImpl implements StoryCardService {

    @Autowired
    StoryCardDao storyCardDao;

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
            tempListVO.setVos(cards.stream().map(StoryCardVO::new).collect(Collectors.toList()));
            result.add(tempListVO);
        }
        return result;
    }
}
