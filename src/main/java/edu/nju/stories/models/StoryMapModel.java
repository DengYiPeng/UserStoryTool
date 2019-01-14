package edu.nju.stories.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "storymap")
public class StoryMapModel {

    @Id
    private String _id;
    private String name;
    private String creatorId;
    private List<String> memberIds;
    private int state;

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String CREATOR_ID = "creatorId";
    public static final String MEMBER_IDS = "memberIds";
    public static final String STATE = "state";

}
