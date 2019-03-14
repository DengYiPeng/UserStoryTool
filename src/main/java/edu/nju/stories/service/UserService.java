package edu.nju.stories.service;

import edu.nju.stories.models.UserModel;
import edu.nju.stories.vo.LoginResult;

public interface UserService {

    boolean getRegisterCode(String email);

    LoginResult register(String username, String email, String registerCode, String password);

    LoginResult login(String email, String password);

    boolean logout(String token);

    UserModel checkToken(String token);

    boolean modifyPassword(String userId, String oldPassword, String newPassword);

}
