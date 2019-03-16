package edu.nju.stories.rest;


import edu.nju.stories.annotation.LoginRequired;
import edu.nju.stories.constants.Headers;
import edu.nju.stories.form.*;
import edu.nju.stories.service.StoryCardService;
import edu.nju.stories.vo.SimpleResponse;
import edu.nju.stories.vo.StoryCardListVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class MapCardRest {


    @Autowired
    StoryCardService storyCardService;


    @ApiOperation(value = "获取故事地图卡片列表", response = StoryCardListVO.class, notes = "")
    @GetMapping(value = "/list")
    @LoginRequired
    public SimpleResponse getStoryMapList(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                          @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                          @RequestParam("mapId") String mapId){
        List<StoryCardListVO> storyCardList = storyCardService.getStoryCardList(mapId);
        return SimpleResponse.OK(storyCardList);
    }

    @ApiOperation(value = "修改卡片标题", response = boolean.class, notes = "")
    @PostMapping(value = "/modify_title")
    @LoginRequired
    public SimpleResponse modifyCardTitle(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                             @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                             @RequestBody ModifyCardTitleForm form){
        boolean result = storyCardService.modifyTitle(form.getCardId(), form.getTitle());
        return SimpleResponse.OK(result);
    }

    @ApiOperation(value = "修改卡片内容", response = boolean.class, notes = "")
    @PostMapping(value = "/modify_content")
    @LoginRequired
    public SimpleResponse modifyCardContent(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                          @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                          @RequestBody ModifyCardContentForm form){
        boolean result = storyCardService.modifyContent(form.getCardId(), form.getContent());
        return SimpleResponse.OK(result);
    }

    @ApiOperation(value = "修改卡片状态", response = boolean.class, notes = "")
    @PostMapping(value = "/modify_state")
    @LoginRequired
    public SimpleResponse modifyCardState(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                            @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                            @RequestBody ModifyCardStateForm form){
        boolean result = storyCardService.modifyState(form.getCardId(), form.getState());
        return SimpleResponse.OK(result);
    }


    @ApiOperation(value = "新建泳道", response = boolean.class, notes = "")
    @PostMapping(value = "/create_lane")
    @LoginRequired
    public SimpleResponse createLane(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                          @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                          @RequestBody CreateListForm form){
        boolean result = storyCardService.createLane(form.getMapId(), form.getAxis(),form.getNumberOfCards(), userId);
        return SimpleResponse.OK(result);
    }

    @ApiOperation(value = "新建卡片列表", response = boolean.class, notes = "")
    @PostMapping(value = "/create_list")
    @LoginRequired
    public SimpleResponse createList(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                     @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                     @RequestBody CreateListForm form){
        boolean result = storyCardService.createList(form.getMapId(), form.getAxis(),form.getNumberOfCards(), userId);
        return SimpleResponse.OK(result);
    }

    @ApiOperation(value = "修改任务卡片负责人", response = boolean.class, notes = "")
    @PostMapping(value = "/modify_owner")
    @LoginRequired
    public SimpleResponse modifyOwner(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                     @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                     @RequestBody ModifyOwnerForm form){
        boolean result = storyCardService.modifyOwnerOfChar(form.getCardId(), form.getOperatorId(), form.getOwnerId());
        return SimpleResponse.OK(result);
    }
}
