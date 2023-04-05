package cn.edu.guet.mapper;

import cn.edu.guet.entity.Robot;
import cn.edu.guet.entity.VO.RobotVO;
import cn.edu.guet.entity.DTO.RobotDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author DavidNan
 * @Desc (Robot)表数据库访问层
 * @Date 2023-04-16 16:05:18
 */
@Mapper
public interface RobotMapper extends BaseMapper<Robot> {

    /**
     * 分页查询
     */
    IPage<RobotVO> queryByPage(Page page, @Param("param") RobotDTO param);


    /**
     * 根据ID查详情
     */
    RobotVO queryById(Integer id);


    /**
     * 新增数据
     */
    int insert(Robot robot);


    /**
     * 修改数据
     */
    int update(Robot robot);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
