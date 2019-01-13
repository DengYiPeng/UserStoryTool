package edu.nju.stories.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "project")
public class StoryMapModel {

    @Id
    private String _id;
    private String name;
    private String creatorId;
    private List<String> memberRoleIds;
    private int state;

}
