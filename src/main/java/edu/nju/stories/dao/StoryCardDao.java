package edu.nju.stories.dao;

import edu.nju.stories.models.StoryCardModel;

import java.util.List;

public interface StoryCardDao {

    StoryCardModel save(StoryCardModel model);

    boolean save(List<StoryCardModel> models);

    StoryCardModel findById(String id);

    List<StoryCardModel> findByMapId(String mapId);

    List<StoryCardModel> findByMapIdAndAxis(String mapId, int xAxis, int yAxis);

    List<StoryCardModel> findCardWithSpecificXAndBiggerY(String mapId, int xAxis, int yAxis);

    boolean set(String id, String prop, Object value);

    boolean update(List<StoryCardModel> models);
}
