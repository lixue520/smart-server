package cn.edu.guet.service;

import cn.edu.guet.entity.Order;
import cn.edu.guet.entity.DTO.OrderDTO;
import cn.edu.guet.entity.VO.OrderVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author DavidNan
 * @Desc (Order)表服务接口
 * @Date 2023-04-23 00:07:01
 */
public interface OrderService extends IService<Order> {

    /**
     * 分页查询
     */
    R<IPage<OrderVO>> queryByPage(OrderDTO param);

    /**
     * 根据ID查详情
     */
    R queryById(Integer id);

    /**
     * 新增数据
     */
    R insert(Order order);

    /**
     * 修改数据
     */
    R update(Order order);

    /**
     * 通过主键删除数据
     */
    R deleteById(Integer id);

}
