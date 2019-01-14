package edu.nju.stories.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;


@Data
@ApiModel
public class StoryCardListVO {
    private int xAxis;
    private String title;
    private List<StoryCardVO> vos;

    public StoryCardListVO(int xAxis, String title, List<StoryCardVO> vos){
        this.xAxis = xAxis;
        this.title = title;
        this.vos = vos;
    }

    public StoryCardListVO(){

    }

}
