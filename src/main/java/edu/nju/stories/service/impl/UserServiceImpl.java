package edu.nju.stories.service.impl;

import edu.nju.stories.service.UserService;

public class UserServiceImpl implements UserService {


    @Override
    public boolean getRegisterCode(String email) {
        return false;
    }

    @Override
    public String register(String email, String registerCode, String password) {
        return null;
    }

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public boolean logout(String token) {
        return false;
    }

    @Override
    public boolean checkToken(String token) {
        return false;
    }


}
