package cn.edu.guet.service.impl;

import cn.edu.guet.entity.Order;
import cn.edu.guet.entity.DTO.OrderDTO;
import cn.edu.guet.entity.VO.OrderVO;
import cn.edu.guet.mapper.OrderMapper;
import cn.edu.guet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author DavidNan
 * @Desc (Order)表服务实现类
 * @Date 2023-04-23 00:07:01
 */
@RequiredArgsConstructor
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderMapper orderMapper;


    @Override
    public R<IPage<OrderVO>> queryByPage(OrderDTO param) {
        return R.ok(orderMapper.queryByPage(param, param));
    }


    @Override
    public R queryById(Integer id) {
        return R.ok(orderMapper.queryById(id));
    }


    @Override
    public R insert(Order order) {
        int num = orderMapper.insert(order);
        return R.ok(num);
    }


    @Override
    public R update(Order order) {
        int num = orderMapper.update(order);
        return R.ok(num);
    }


    @Override
    public R deleteById(Integer id) {
        int num = orderMapper.deleteById(id);
        return R.ok(num);
    }

}
