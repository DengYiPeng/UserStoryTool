package edu.nju.stories.form;


import lombok.Data;

@Data
public class AddCardForm {

    private String operatorId;
    private String mapId;
    private int xAxis;
    private int yAxis;
    private String content = "";



}
