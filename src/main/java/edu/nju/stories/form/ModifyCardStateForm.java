package edu.nju.stories.form;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class ModifyCardStateForm {

    private String cardId;
    private int state;
}
