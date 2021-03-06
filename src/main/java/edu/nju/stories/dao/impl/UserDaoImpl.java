package edu.nju.stories.dao.impl;

import edu.nju.stories.dao.UserDao;
import edu.nju.stories.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
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
    public UserModel findByEmail(String email) {
        Criteria criteria = Criteria.where(UserModel.EMAIL).is(email);
        return template.findOne(new Query(criteria), UserModel.class);    }

    @Override
    public List<UserModel> findByIds(List<String> ids) {
        Criteria criteria = Criteria.where(UserModel._Id).in(ids);
        return template.find(new Query(criteria), UserModel.class);
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
        Criteria criteria = Criteria.where(UserModel._Id).is(id);
        Update update = new Update();
        update.set(UserModel.TOKEN, token);
        template.updateFirst(new Query(criteria), update, UserModel.class);
    }
}
