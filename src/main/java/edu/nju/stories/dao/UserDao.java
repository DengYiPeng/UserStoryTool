package edu.nju.stories.dao;

import edu.nju.stories.models.UserModel;

public interface UserDao {

    UserModel findById(String id);

    UserModel findByToken(String token);

    void save(UserModel userModel);

    void clearToken(String token);

    void signToken(String id, String token);

}
