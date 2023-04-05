package cn.edu.guet.mapper;

import cn.edu.guet.entity.Order;
import cn.edu.guet.entity.VO.OrderVO;
import cn.edu.guet.entity.DTO.OrderDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author DavidNan
 * @Desc (Order)表数据库访问层
 * @Date 2023-04-23 00:07:00
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页查询
     */
    IPage<OrderVO> queryByPage(Page page, @Param("param") OrderDTO param);


    /**
     * 根据ID查详情
     */
    OrderVO queryById(Integer id);


    /**
     * 新增数据
     */
    int insert(Order order);


    /**
     * 修改数据
     */
    int update(Order order);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
