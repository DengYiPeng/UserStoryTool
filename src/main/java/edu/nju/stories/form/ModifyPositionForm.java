package edu.nju.stories.form;

import lombok.Data;

@Data
public class ModifyPositionForm {

    private String operatorId;
    private String cardId;
    private int targetXAxis;
    private int targetYAxis;
}
