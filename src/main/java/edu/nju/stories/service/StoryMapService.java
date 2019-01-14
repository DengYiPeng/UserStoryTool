package edu.nju.stories.service;

import edu.nju.stories.models.StoryMapModel;
import edu.nju.stories.vo.StoryMapVO;

public interface StoryMapService {

    StoryMapVO createStoryMap(String userId, String name);

    boolean deleteStoryMap(String id, String userId);

    boolean modifyStoryMapName(String id, String userId, String newName);

}
