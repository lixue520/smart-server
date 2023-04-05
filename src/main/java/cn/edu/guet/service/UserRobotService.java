package cn.edu.guet.service;

import cn.edu.guet.entity.UserRobot;
import cn.edu.guet.entity.DTO.UserRobotDTO;
import cn.edu.guet.entity.VO.UserRobotVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author DavidNan
 * @Desc (UserRobot)表服务接口
 * @Date 2023-04-16 16:07:52
 */
public interface UserRobotService extends IService<UserRobot> {

    /**
     * 分页查询
     */
    R<IPage<UserRobotVO>> queryByPage(UserRobotDTO param);

    /**
     * 根据ID查详情
     */
    R queryById(Integer id);

    /**
     * 新增数据
     */
    R insert(UserRobot userRobot);

    /**
     * 修改数据
     */
    R update(UserRobot userRobot);

    /**
     * 通过主键删除数据
     */
    R deleteById(Integer id);

}
