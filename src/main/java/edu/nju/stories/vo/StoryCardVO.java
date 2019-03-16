package edu.nju.stories.vo;


import edu.nju.stories.models.StoryCardModel;
import edu.nju.stories.models.UserModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class StoryCardVO {

    private String id;
    private String title;
    private String content;
    private int xAxis;
    private int yAxis;
    private int innerIndex;
    private String creatorId;
    private int state;
    private UserVO ownerUser;

    public StoryCardVO(StoryCardModel model, UserModel userModel){
        this.id = model.get_id();
        this.title = model.getTitle();
        this.content = model.getContent();
        this.xAxis = model.getXAxis();
        this.yAxis = model.getYAxis();
        this.innerIndex = model.getInnerIndex();
        this.creatorId = model.getCreatorId();
        this.state = model.getState();
        this.ownerUser = new UserVO(userModel);
    }

    public StoryCardVO(){

    }

}
