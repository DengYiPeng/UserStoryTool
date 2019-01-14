package edu.nju.stories.rest;


import edu.nju.stories.annotation.LoginRequired;
import edu.nju.stories.constants.Headers;
import edu.nju.stories.form.CreateStoryMapForm;
import edu.nju.stories.form.LoginForm;
import edu.nju.stories.form.ModifyStoryMapForm;
import edu.nju.stories.service.StoryMapService;
import edu.nju.stories.vo.SimpleResponse;
import edu.nju.stories.vo.StoryMapVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapStoryRest {

    @Autowired
    StoryMapService storyMapService;

    @ApiOperation(value = "创建故事地图", response = StoryMapVO.class, notes = "")
    @PostMapping(value = "/create")
    @LoginRequired
    public SimpleResponse createStoryMap(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                         @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                         @RequestBody CreateStoryMapForm form){
        return SimpleResponse.OK(storyMapService.createStoryMap(userId, form.getName()));
    }

    @ApiOperation(value = "删除故事地图", response = boolean.class, notes = "")
    @PostMapping(value = "/delete")
    @LoginRequired
    public SimpleResponse deleteStoryMap(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                         @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                         @RequestParam("mapId") String mapId){
        boolean result = storyMapService.deleteStoryMap(mapId, userId);
        return SimpleResponse.OK(result);
    }

    @ApiOperation(value = "修改故事地图名称", response = boolean.class, notes = "")
    @PostMapping(value = "/modify_name")
    @LoginRequired
    public SimpleResponse modifyStoryMapName(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                             @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                             @RequestBody ModifyStoryMapForm form){
        boolean result = storyMapService.modifyStoryMapName(form.getMapId(), userId, form.getName());
        return SimpleResponse.OK(result);
    }


    @ApiOperation(value = "获取用户的故事地图列表", response = StoryMapVO.class, notes = "")
    @GetMapping(value = "/list")
    @LoginRequired
    public SimpleResponse getMapList(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                             @RequestHeader(Headers.ACCESS_USER_ID) String userId){
        List<StoryMapVO> storyMapList = storyMapService.getStoryMapList(userId);
        return SimpleResponse.OK(storyMapList);
    }
}
