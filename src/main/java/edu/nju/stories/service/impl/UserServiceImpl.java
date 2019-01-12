package edu.nju.stories.service.impl;

import edu.nju.stories.dao.UserDao;
import edu.nju.stories.models.UserModel;
import edu.nju.stories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean getRegisterCode(String email) {
        return false;
    }

    @Override
    public String register(String email, String registerCode, String password) {
        UserModel userModel = new UserModel();
        String userId = UUID.randomUUID().toString();
        userModel.set_id(userId);
        userModel.setEmail(email);
        userModel.setPassword(password);
        String token = generateToken(userId);
        userModel.setToken(token);
        userDao.save(userModel);
        return token;
    }

    @Override
    public String login(String email, String password) {
        UserModel userModel = userDao.findByEmail(email);
        if (userModel.getPassword().equals(password)){
            return null;
        }else{
            String token = generateToken(userModel.get_id());
            userDao.signToken(userModel.get_id(), token);
            return token;
        }
    }

    @Override
    public boolean logout(String token) {
        userDao.clearToken(token);
        return true;
    }

    @Override
    public UserModel checkToken(String token) {
        UserModel user = userDao.findByToken(token);
        return user;
    }

    private String generateToken(String userId){
        return UUID.randomUUID().toString();
    }


}
