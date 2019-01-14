package edu.nju.stories.form;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class ModifyCardTitleForm {

    private String cardId;
    private String title;

}
