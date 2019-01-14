package edu.nju.stories.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class ModifyStoryMapForm {

    private String mapId;
    private String name;
}
