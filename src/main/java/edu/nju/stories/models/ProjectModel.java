package edu.nju.stories.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "project")
public class ProjectModel {

}
