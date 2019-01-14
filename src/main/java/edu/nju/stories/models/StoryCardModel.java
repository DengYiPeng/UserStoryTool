package edu.nju.stories.models;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "storycard")
public class StoryCardModel {

    @Id
    private String _id;
    private String title;
    private String content;
    private String mapId;
    private int xAxis;
    private int yAxis;
    private int innerIndex;
    private String creatorId;
    private int state;

    public StoryCardModel(){

    }


    public StoryCardModel(String id, String mapId, int xAxis, int yAxis, int innerIndex, String creatorId, int state){
        this._id = id;
        this.mapId = mapId;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.innerIndex = innerIndex;
        this.creatorId = creatorId;
        this.state = state;
    }

    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String MAP_Id = "mapId";
    public static final String X_AXIS = "xAxis";
    public static final String Y_AXIS = "yAxis";
    public static final String INNER_INDEX = "innerIndex";
    public static final String STATE = "state";

}
