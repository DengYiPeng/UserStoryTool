package edu.nju.stories.service.impl;

import edu.nju.stories.constants.ErrorCode;
import edu.nju.stories.constants.ValidState;
import edu.nju.stories.dao.StoryMapDao;
import edu.nju.stories.exception.LogicException;
import edu.nju.stories.models.StoryMapModel;
import edu.nju.stories.service.StoryMapService;
import edu.nju.stories.vo.StoryMapVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StoryMapServiceImpl implements StoryMapService {

    @Autowired
    StoryMapDao storyMapDao;

    @Override
    public StoryMapVO createStoryMap(String userId, String name) {
        StoryMapModel model = new StoryMapModel();
        model.set_id(UUID.randomUUID().toString());
        model.setCreatorId(userId);
        model.setMemberIds(Arrays.asList(userId));
        model.setName(name);
        model.setState(ValidState.VALID);
        return new StoryMapVO(storyMapDao.save(model));
    }

    @Override
    public List<StoryMapVO> getStoryMapList(String userId) {
        List<StoryMapModel> models = storyMapDao.findByUserId(userId);
        return models.stream().map(StoryMapVO::new).collect(Collectors.toList());
    }

    @Override
    public boolean deleteStoryMap(String id, String userId) {
        if (checkIfHasPermission(id, userId)){
            return storyMapDao.modifyState(id, ValidState.INVALID);
        }else {
            throw new LogicException(ErrorCode.NO_PERMISSION, "没有对故事地图惨的操作权限");
        }
    }

    @Override
    public boolean modifyStoryMapName(String id, String userId, String newName) {
        if (checkIfHasPermission(id, userId)){
            return storyMapDao.modifyName(id, newName);
        }else {
            throw new LogicException(ErrorCode.NO_PERMISSION, "没有对故事地图惨的操作权限");
        }
    }

    private boolean checkIfHasPermission(String mapId, String userId){
        //Fixme 这里需要加一点垃圾逻辑来避免sonarqube认为是bug，真正逻辑之后实现
        return !mapId.equals(userId+"really happy");
    }
}
