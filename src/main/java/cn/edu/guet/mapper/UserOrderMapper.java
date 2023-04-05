package cn.edu.guet.mapper;

import cn.edu.guet.entity.UserOrder;
import cn.edu.guet.entity.VO.UserOrderVO;
import cn.edu.guet.entity.DTO.UserOrderDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author DavidNan
 * @Desc (UserOrder)表数据库访问层
 * @Date 2023-04-23 00:38:06
 */
@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrder> {

    /**
     * 分页查询
     */
    IPage<UserOrderVO> queryByPage(Page page, @Param("param") UserOrderDTO param);


    /**
     * 根据ID查详情
     */
    UserOrderVO queryById(Integer id);


    /**
     * 新增数据
     */
    int insert(UserOrder userOrder);


    /**
     * 修改数据
     */
    int update(UserOrder userOrder);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
