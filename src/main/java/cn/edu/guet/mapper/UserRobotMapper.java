package cn.edu.guet.mapper;

import cn.edu.guet.entity.UserRobot;
import cn.edu.guet.entity.VO.UserRobotVO;
import cn.edu.guet.entity.DTO.UserRobotDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author DavidNan
 * @Desc (UserRobot)表数据库访问层
 * @Date 2023-04-16 16:07:52
 */
@Mapper
public interface UserRobotMapper extends BaseMapper<UserRobot> {

    /**
     * 分页查询
     */
    IPage<UserRobotVO> queryByPage(Page page, @Param("param") UserRobotDTO param);


    /**
     * 根据ID查详情
     */
    UserRobotVO queryById(Integer id);


    /**
     * 新增数据
     */
    int insert(UserRobot userRobot);


    /**
     * 修改数据
     */
    int update(UserRobot userRobot);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
