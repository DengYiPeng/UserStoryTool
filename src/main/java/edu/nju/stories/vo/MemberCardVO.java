package edu.nju.stories.vo;

import lombok.Data;

import java.util.List;


@Data
public class MemberCardVO {
    private UserVO userVO;
    private List<SimpleStoryCardVO>  cards;
}
