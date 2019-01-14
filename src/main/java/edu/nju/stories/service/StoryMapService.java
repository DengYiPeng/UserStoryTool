package edu.nju.stories.service;

import edu.nju.stories.models.StoryMapModel;
import edu.nju.stories.vo.StoryMapVO;

import java.util.List;

public interface StoryMapService {

    StoryMapVO createStoryMap(String userId, String name);

    List<StoryMapVO> getStoryMapList(String userId);

    boolean deleteStoryMap(String id, String userId);

    boolean modifyStoryMapName(String id, String userId, String newName);

}
