package cn.edu.guet.mapper;

import cn.edu.guet.entity.User;
import cn.edu.guet.entity.VO.UserVO;
import cn.edu.guet.entity.DTO.UserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author DavidNan
 * @Desc (User)表数据库访问层
 * @Date 2023-04-16 16:07:31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询
     */
    IPage<UserVO> queryByPage(Page page, @Param("param") UserDTO param);


    /**
     * 根据ID查详情
     */
    UserVO queryById(Integer user_id);


    /**
     * 新增数据
     */
    int insert(User user);


    /**
     * 修改数据
     */
    int update(User user);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer user_id);

}
