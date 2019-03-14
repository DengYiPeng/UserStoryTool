package edu.nju.stories.form;

import lombok.Data;

@Data
public class ModifyPasswordForm {

    private String oldPassword;
    private String newPassword;
}
