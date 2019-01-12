package edu.nju.stories.dao.impl;

import edu.nju.stories.dao.UserDao;
import edu.nju.stories.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class UserDaoImpl implements UserDao {

    @Autowired
    MongoTemplate template;

    @Override
    public UserModel findById(String id) {
        return template.findById(id, UserModel.class);
    }

    @Override
    public UserModel findByToken(String token) {
        Criteria criteria = Criteria.where(UserModel.TOKEN).is(token);
        return template.findOne(new Query(criteria), UserModel.class);
    }

    @Override
    public void save(UserModel userModel) {
        template.save(userModel);
    }

    @Override
    public void clearToken(String token) {
        Criteria criteria = Criteria.where(UserModel.TOKEN).is(token);
        Update update = new Update();
        update.set(UserModel.TOKEN, "");
        template.updateFirst(new Query(criteria), update, UserModel.class);
    }

    @Override
    public void signToken(String id, String token) {

    }
}
