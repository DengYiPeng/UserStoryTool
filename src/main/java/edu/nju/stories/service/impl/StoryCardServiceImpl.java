package edu.nju.stories.service.impl;

import edu.nju.stories.constants.StoryCardState;
import edu.nju.stories.dao.StoryCardDao;
import edu.nju.stories.models.StoryCardModel;
import edu.nju.stories.service.StoryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StoryCardServiceImpl implements StoryCardService {

    @Autowired
    StoryCardDao storyCardDao;

    @Override
    public boolean createLane(String mapId, int yAxis, int numberOfList, String userId) {
        List<StoryCardModel> storyCardModelList = new ArrayList<>();
        for (int xAxis = 0; xAxis < numberOfList; xAxis++){
            StoryCardModel storyCardModel = StoryCardModel.builder()._id(UUID.randomUUID().toString())
                    .innerIndex(0)
                    .creatorId(userId)
                    .mapId(mapId)
                    .state(StoryCardState.TODO)
                    .xAxis(xAxis)
                    .yAxis(yAxis)
                    .build();
            storyCardModelList.add(storyCardModel);
        }
        boolean result = storyCardDao.update(storyCardModelList);
        return result;
    }

    @Override
    public boolean createList(String mapId, int xAxis, int numberOfLane, String userId) {
        List<StoryCardModel> storyCardModelList = new ArrayList<>();
        for (int yAxis = 0; yAxis < numberOfLane; yAxis++){
            StoryCardModel storyCardModel = StoryCardModel.builder()._id(UUID.randomUUID().toString())
                    .innerIndex(0)
                    .creatorId(userId)
                    .mapId(mapId)
                    .state(StoryCardState.TODO)
                    .xAxis(xAxis)
                    .yAxis(yAxis)
                    .build();
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
}
