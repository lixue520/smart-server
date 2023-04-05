package cn.edu.guet.service;

import cn.edu.guet.entity.Robot;
import cn.edu.guet.entity.DTO.RobotDTO;
import cn.edu.guet.entity.VO.RobotVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author DavidNan
 * @Desc (Robot)表服务接口
 * @Date 2023-04-16 16:05:19
 */
public interface RobotService extends IService<Robot> {

    /**
     * 分页查询
     */
    R<IPage<RobotVO>> queryByPage(RobotDTO param);

    /**
     * 根据ID查详情
     */
    R queryById(Integer id);

    /**
     * 新增数据
     */
    R insert(Robot robot);

    /**
     * 修改数据
     */
    R update(Robot robot);

    /**
     * 通过主键删除数据
     */
    R deleteById(Integer id);

}
