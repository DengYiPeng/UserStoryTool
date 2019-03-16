package edu.nju.stories.dao.impl;

import edu.nju.stories.dao.StoryMapDao;
import edu.nju.stories.models.StoryMapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StoryMapDaoImpl implements StoryMapDao {

    @Autowired
    MongoTemplate template;

    @Override
    public StoryMapModel save(StoryMapModel storyMapModel) {
        return template.save(storyMapModel);
    }

    @Override
    public StoryMapModel findById(String id) {
        return template.findById(id, StoryMapModel.class);
    }

    @Override
    public List<StoryMapModel> findByUserId(String userId) {
        Criteria criteria = Criteria.where(StoryMapModel.MEMBER_IDS).all(userId);
        return template.find(new Query(criteria), StoryMapModel.class);
    }

    @Override
    public boolean modifyState(String id, int state){
        Criteria criteria =Criteria.where(StoryMapModel._ID).is(id);
        Update update = new Update();
        update.set(StoryMapModel.STATE, state);
        return template.updateFirst(new Query(criteria), update, StoryMapModel.class).wasAcknowledged();
    }

    @Override
    public boolean modifyName(String id, String name) {
        Criteria criteria =Criteria.where(StoryMapModel._ID).is(id);
        Update update = new Update();
        update.set(StoryMapModel.NAME, name);
        return template.updateFirst(new Query(criteria), update, StoryMapModel.class).wasAcknowledged();    }

    @Override
    public boolean addMember(String id, String beAddedUserId) {
        Criteria criteria = Criteria.where(StoryMapModel._ID).is(id);
        Update update = new Update();
        update.addToSet(StoryMapModel.MEMBER_IDS, beAddedUserId);
        return template.updateFirst(new Query(criteria), update, StoryMapModel.class).wasAcknowledged();
    }

    @Override
    public boolean removeMember(String id, String beRemovedUserId) {
        Criteria criteria = Criteria.where(StoryMapModel._ID).is(id);
        Update update = new Update();
        update.pull(StoryMapModel.MEMBER_IDS, beRemovedUserId);
        return template.updateFirst(new Query(criteria), update, StoryMapModel.class).wasAcknowledged();
    }
}
