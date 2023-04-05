package cn.edu.guet.service;

import cn.edu.guet.entity.User;
import cn.edu.guet.entity.DTO.UserDTO;
import cn.edu.guet.entity.VO.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author DavidNan
 * @Desc (User)表服务接口
 * @Date 2023-04-16 16:07:32
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询
     */
    R<IPage<UserVO>> queryByPage(UserDTO param);

    /**
     * 根据ID查详情
     */
    R queryById(Integer user_id);

    /**
     * 新增数据
     */
    R insert(User user);

    /**
     * 修改数据
     */
    R update(User user);

    /**
     * 通过主键删除数据
     */
    R deleteById(Integer user_id);

}
