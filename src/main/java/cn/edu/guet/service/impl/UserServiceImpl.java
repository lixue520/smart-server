package cn.edu.guet.service.impl;

import cn.edu.guet.entity.User;
import cn.edu.guet.entity.DTO.UserDTO;
import cn.edu.guet.entity.VO.UserVO;
import cn.edu.guet.mapper.UserMapper;
import cn.edu.guet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author DavidNan
 * @Desc (User)表服务实现类
 * @Date 2023-04-16 16:07:32
 */
@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;


    @Override
    public R<IPage<UserVO>> queryByPage(UserDTO param) {
        return R.ok(userMapper.queryByPage(param, param));
    }


    @Override
    public R queryById(Integer user_id) {
        return R.ok(userMapper.queryById(user_id));
    }


    @Override
    public R insert(User user) {
        int num = userMapper.insert(user);
        return R.ok(num);
    }


    @Override
    public R update(User user) {
        int num = userMapper.update(user);
        return R.ok(num);
    }


    @Override
    public R deleteById(Integer user_id) {
        int num = userMapper.deleteById(user_id);
        return R.ok(num);
    }

}
