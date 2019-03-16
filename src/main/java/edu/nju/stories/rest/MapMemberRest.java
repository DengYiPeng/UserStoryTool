package edu.nju.stories.rest;


import edu.nju.stories.annotation.LoginRequired;
import edu.nju.stories.constants.Headers;
import edu.nju.stories.form.CreateStoryMapForm;
import edu.nju.stories.form.UpdateMemberForm;
import edu.nju.stories.service.StoryMapMemberService;
import edu.nju.stories.service.StoryMapService;
import edu.nju.stories.vo.SimpleResponse;
import edu.nju.stories.vo.StoryMapVO;
import edu.nju.stories.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MapMemberRest {

    @Autowired
    StoryMapMemberService storyMapMemberService;

    @ApiOperation(value = "添加成员", response = Boolean.class, notes = "")
    @PostMapping(value = "/add_member")
    @LoginRequired
    public SimpleResponse addMember(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                         @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                         @RequestBody UpdateMemberForm form){
        return SimpleResponse.OK(storyMapMemberService.addMember(form.getOperatorId(), form.getBeOperatedEmail(), form.getMapId()));
    }

    @ApiOperation(value = "移除成员", response = Boolean.class, notes = "")
    @PostMapping(value = "/remove_member")
    @LoginRequired
    public SimpleResponse removeMember(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                         @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                         @RequestBody UpdateMemberForm form){
        return SimpleResponse.OK(storyMapMemberService.removeMember(form.getOperatorId(), form.getBeOperatedEmail(), form.getMapId()));
    }

    @ApiOperation(value = "获取地图的成员列表", response = UserVO.class, notes = "")
    @PostMapping(value = "/members")
    @LoginRequired
    public SimpleResponse getMembersOfMap(@RequestHeader(Headers.ACCESS_TOKEN) String token,
                                       @RequestHeader(Headers.ACCESS_USER_ID) String userId,
                                       @RequestParam("mapId") String mapId){
        return SimpleResponse.OK(storyMapMemberService.getMapMemberUsers(userId, mapId));
    }
}
