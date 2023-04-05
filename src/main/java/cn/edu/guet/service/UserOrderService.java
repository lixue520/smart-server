package cn.edu.guet.service;

import cn.edu.guet.entity.UserOrder;
import cn.edu.guet.entity.DTO.UserOrderDTO;
import cn.edu.guet.entity.VO.UserOrderVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author DavidNan
 * @Desc (UserOrder)表服务接口
 * @Date 2023-04-23 00:38:07
 */
public interface UserOrderService extends IService<UserOrder> {

    /**
     * 分页查询
     */
    R<IPage<UserOrderVO>> queryByPage(UserOrderDTO param);

    /**
     * 根据ID查详情
     */
    R queryById(Integer id);

    /**
     * 新增数据
     */
    R insert(UserOrder userOrder);

    /**
     * 修改数据
     */
    R update(UserOrder userOrder);

    /**
     * 通过主键删除数据
     */
    R deleteById(Integer id);

}
