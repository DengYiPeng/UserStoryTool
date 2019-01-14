package edu.nju.stories.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class CreateListForm {

    private String mapId;
    private int axis;
    private int numberOfCards;

}
