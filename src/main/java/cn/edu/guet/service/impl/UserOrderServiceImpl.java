package cn.edu.guet.service.impl;

import cn.edu.guet.entity.UserOrder;
import cn.edu.guet.entity.DTO.UserOrderDTO;
import cn.edu.guet.entity.VO.UserOrderVO;
import cn.edu.guet.mapper.UserOrderMapper;
import cn.edu.guet.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author DavidNan
 * @Desc (UserOrder)表服务实现类
 * @Date 2023-04-23 00:38:07
 */
@RequiredArgsConstructor
@Service("userOrderService")
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

    private final UserOrderMapper userOrderMapper;


    @Override
    public R<IPage<UserOrderVO>> queryByPage(UserOrderDTO param) {
        return R.ok(userOrderMapper.queryByPage(param, param));
    }


    @Override
    public R queryById(Integer id) {
        return R.ok(userOrderMapper.queryById(id));
    }


    @Override
    public R insert(UserOrder userOrder) {
        int num = userOrderMapper.insert(userOrder);
        return R.ok(num);
    }


    @Override
    public R update(UserOrder userOrder) {
        int num = userOrderMapper.update(userOrder);
        return R.ok(num);
    }


    @Override
    public R deleteById(Integer id) {
        int num = userOrderMapper.deleteById(id);
        return R.ok(num);
    }

}
