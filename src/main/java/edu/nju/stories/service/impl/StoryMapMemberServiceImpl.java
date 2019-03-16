package edu.nju.stories.service.impl;

import edu.nju.stories.constants.ErrorCode;
import edu.nju.stories.dao.StoryMapDao;
import edu.nju.stories.dao.UserDao;
import edu.nju.stories.exception.LogicException;
import edu.nju.stories.models.StoryMapModel;
import edu.nju.stories.models.UserModel;
import edu.nju.stories.service.StoryMapMemberService;
import edu.nju.stories.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryMapMemberServiceImpl implements StoryMapMemberService {

    @Autowired
    StoryMapDao storyMapDao;

    @Autowired
    UserDao userDao;

    @Override
    public boolean addMember(String operatorId, String beOperatedEmail, String mapId) {
        StoryMapModel model = storyMapDao.findById(mapId);
        UserModel userModel = userDao.findByEmail(beOperatedEmail);
        if (userModel == null){
            throw new LogicException(ErrorCode.USER_NOT_EXISTS, "用户不存在");

        }
        if (model.getMemberIds().contains(operatorId)){
            storyMapDao.addMember(mapId, userModel.get_id());
        }else{
            throw new LogicException(ErrorCode.NO_PERMISSION, "没有对故事地图的操作权限");
        }
        return true;
    }

    @Override
    public boolean removeMember(String operatorId, String beOperatedEmail, String mapId) {
        StoryMapModel model = storyMapDao.findById(mapId);
        UserModel userModel = userDao.findByEmail(beOperatedEmail);
        if (userModel == null){
            throw new LogicException(ErrorCode.USER_NOT_EXISTS, "用户不存在");

        }
        if (model.getMemberIds().contains(operatorId)){
            storyMapDao.removeMember(mapId, userModel.get_id());
        }else{
            throw new LogicException(ErrorCode.NO_PERMISSION, "没有对故事地图的操作权限");
        }
        return true;
    }

    @Override
    public List<UserVO> getMapMemberUsers(String operatorUserId, String mapId) {
        StoryMapModel model = storyMapDao.findById(mapId);
        if (model.getMemberIds().contains(operatorUserId)){
            List<UserModel> userModels = userDao.findByIds(model.getMemberIds());
            return userModels.stream().map(UserVO::new).collect(Collectors.toList());
        }else{
            throw new LogicException(ErrorCode.NO_PERMISSION, "没有对故事地图的操作权限");
        }
    }
}
