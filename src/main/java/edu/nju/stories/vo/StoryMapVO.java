package edu.nju.stories.vo;

import edu.nju.stories.models.StoryMapModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class StoryMapVO {

    private String id;
    private String name;
    private int state;

    public StoryMapVO(StoryMapModel model){
        this.id = model.get_id();
        this.name = model.getName();
        this.state = model.getState();
    }

    public StoryMapVO(){

    }

}
