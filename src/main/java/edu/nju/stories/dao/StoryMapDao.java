package edu.nju.stories.dao;

import edu.nju.stories.models.StoryMapModel;

import java.util.List;

public interface StoryMapDao {

    StoryMapModel save(StoryMapModel storyMapModel);

    StoryMapModel findById(String id);

    List<StoryMapModel> findByUserId(String userId);

    boolean modifyState(String id, int state);

    boolean modifyName(String id, String name);

    boolean addMember(String id, String beAddedUserId);

    boolean removeMember(String id ,String beRemovedUserId);
}
