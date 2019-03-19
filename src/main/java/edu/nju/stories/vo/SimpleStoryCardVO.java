package edu.nju.stories.vo;

import edu.nju.stories.models.StoryCardModel;
import lombok.Data;

@Data
public class SimpleStoryCardVO {

    private String id;
    private String title;
    private String content;
    private int xAxis;
    private int yAxis;
    private int innerIndex;
    private String creatorId;
    private int state;
    private String ownerId;

    public SimpleStoryCardVO(StoryCardModel model){
        this.id = model.get_id();
        this.title = model.getTitle();
        this.content = model.getContent();
        this.xAxis = model.getXAxis();
        this.yAxis = model.getYAxis();
        this.innerIndex = model.getInnerIndex();
        this.creatorId = model.getCreatorId();
        this.state = model.getState();
        this.ownerId = model.getOwnerId();
    }

    public SimpleStoryCardVO(){

    }
}
