package cn.edu.guet.service.impl;

import cn.edu.guet.entity.UserRobot;
import cn.edu.guet.entity.DTO.UserRobotDTO;
import cn.edu.guet.entity.VO.UserRobotVO;
import cn.edu.guet.mapper.UserRobotMapper;
import cn.edu.guet.service.UserRobotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author DavidNan
 * @Desc (UserRobot)表服务实现类
 * @Date 2023-04-16 16:07:53
 */
@RequiredArgsConstructor
@Service("userRobotService")
public class UserRobotServiceImpl extends ServiceImpl<UserRobotMapper, UserRobot> implements UserRobotService {

    private final UserRobotMapper userRobotMapper;


    @Override
    public R<IPage<UserRobotVO>> queryByPage(UserRobotDTO param) {
        return R.ok(userRobotMapper.queryByPage(param, param));
    }


    @Override
    public R queryById(Integer id) {
        return R.ok(userRobotMapper.queryById(id));
    }


    @Override
    public R insert(UserRobot userRobot) {
        int num = userRobotMapper.insert(userRobot);
        return R.ok(num);
    }


    @Override
    public R update(UserRobot userRobot) {
        int num = userRobotMapper.update(userRobot);
        return R.ok(num);
    }


    @Override
    public R deleteById(Integer id) {
        int num = userRobotMapper.deleteById(id);
        return R.ok(num);
    }

}
