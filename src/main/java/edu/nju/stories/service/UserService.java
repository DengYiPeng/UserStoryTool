package edu.nju.stories.service;

import edu.nju.stories.models.UserModel;

public interface UserService {

    boolean getRegisterCode(String email);

    String register(String email, String registerCode, String password);

    String login(String email, String password);

    boolean logout(String token);

    UserModel checkToken(String token);

}
