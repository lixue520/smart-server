package cn.edu.guet.service.impl;

import cn.edu.guet.entity.Robot;
import cn.edu.guet.entity.DTO.RobotDTO;
import cn.edu.guet.entity.VO.RobotVO;
import cn.edu.guet.mapper.RobotMapper;
import cn.edu.guet.service.RobotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author DavidNan
 * @Desc (Robot)表服务实现类
 * @Date 2023-04-16 16:05:19
 */
@RequiredArgsConstructor
@Service("robotService")
public class RobotServiceImpl extends ServiceImpl<RobotMapper, Robot> implements RobotService {

    private final RobotMapper robotMapper;


    @Override
    public R<IPage<RobotVO>> queryByPage(RobotDTO param) {
        return R.ok(robotMapper.queryByPage(param, param));
    }


    @Override
    public R queryById(Integer id) {
        return R.ok(robotMapper.queryById(id));
    }


    @Override
    public R insert(Robot robot) {
        int num = robotMapper.insert(robot);
        return R.ok(num);
    }


    @Override
    public R update(Robot robot) {
        int num = robotMapper.update(robot);
        return R.ok(num);
    }


    @Override
    public R deleteById(Integer id) {
        int num = robotMapper.deleteById(id);
        return R.ok(num);
    }

}
