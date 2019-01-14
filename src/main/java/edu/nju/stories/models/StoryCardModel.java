package edu.nju.stories.models;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "storycard")
@Builder
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

    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String MAP_Id = "mapId";
    public static final String X_AXIS = "xAxis";
    public static final String Y_AXIS = "yAxis";
    public static final String INNER_INDEX = "innerIndex";
    public static final String STATE = "state";

}
