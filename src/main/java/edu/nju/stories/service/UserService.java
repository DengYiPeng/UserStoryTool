package edu.nju.stories.service;

public interface UserService {

    boolean getRegisterCode(String email);

    String register(String email, String registerCode, String password);

    String login(String email, String password);

    boolean logout(String token);

    boolean checkToken(String token);

}
