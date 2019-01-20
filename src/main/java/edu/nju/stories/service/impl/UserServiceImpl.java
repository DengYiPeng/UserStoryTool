package edu.nju.stories.service.impl;

import edu.nju.stories.constants.ErrorCode;
import edu.nju.stories.dao.UserDao;
import edu.nju.stories.exception.LogicException;
import edu.nju.stories.models.UserModel;
import edu.nju.stories.service.UserService;
import edu.nju.stories.vo.LoginResult;
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
    public LoginResult register(String username, String email, String registerCode, String password) {
        UserModel userModel = new UserModel();
        String userId = UUID.randomUUID().toString();
        userModel.set_id(userId);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setUsername(username);
        String token = generateToken(userId);
        userModel.setToken(token);
        userDao.save(userModel);
        LoginResult result = new LoginResult(token, userId);
        return result;
    }

    @Override
    public LoginResult login(String email, String password) {
        UserModel userModel = userDao.findByEmail(email);
        if (userModel.getPassword().equals(password)){
            String token = generateToken(userModel.get_id());
            userDao.signToken(userModel.get_id(), token);
            LoginResult result = new LoginResult(token, userModel.get_id());
            return result;
        }else{
            throw new LogicException(ErrorCode.ERROR_PASSWORD, "用户名密码错误");
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
