package edu.nju.stories.dao.impl;

import edu.nju.stories.dao.StoryCardDao;
import edu.nju.stories.models.StoryCardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoryCardDaoImpl implements StoryCardDao {

    @Autowired
    MongoTemplate template;

    @Override
    public StoryCardModel save(StoryCardModel model) {
        return template.save(model);
    }

    @Override
    public StoryCardModel findById(String id) {
        return template.findById(id, StoryCardModel.class);
    }

    @Override
    public List<StoryCardModel> findByMapId(String mapId) {
        Criteria criteria = Criteria.where(StoryCardModel.MAP_Id).is(mapId);
        return template.find(new Query(criteria), StoryCardModel.class);
    }

    @Override
    public List<StoryCardModel> findByMapIdAndAxis(String mapId, int xAxis, int yAxis) {
        Criteria criteria = Criteria.where(StoryCardModel.MAP_Id).is(mapId);
        criteria.and(StoryCardModel.X_AXIS).is(xAxis);
        criteria.and(StoryCardModel.Y_AXIS).is(yAxis);
        return template.find(new Query(criteria), StoryCardModel.class);
    }


    @Override
    public boolean set(String id, String prop, Object value) {
        Criteria criteria = Criteria.where(StoryCardModel._ID).is(id);
        Update update = new Update();
        update.set(prop, value);
        return template.updateFirst(new Query(criteria), update, StoryCardModel.class).wasAcknowledged();
    }

    @Override
    public boolean update(List<StoryCardModel> models) {
        for (StoryCardModel model : models){
            template.save(model);
        }
        return true;
    }
}
