package cn.edu.guet.controller;

import cn.edu.guet.entity.User;
import cn.edu.guet.entity.VO.UserVO;
import cn.edu.guet.entity.DTO.UserDTO;
import cn.edu.guet.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.edu.guet.config.SysLog;
import cn.edu.guet.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @Author DavidNan
 * @Desc (User)表控制层
 * @Date 2023-04-16 16:07:31
 */
@Api(tags = "")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @ApiOperation("-分页查询")
    @SysLog("-分页查询")
    @PostMapping("/queryByPage")
    public R<IPage<UserVO>> queryByPage(@RequestBody UserDTO param) {
        return userService.queryByPage(param);
    }


    @ApiOperation("-根据ID查详情")
    @SysLog("-根据ID查详情")
    @PostMapping("/queryById")
    public R<UserVO> queryById(@RequestBody UserDTO param) {
        return userService.queryById(param.getUser_id());
    }


    @ApiOperation("-新增数据")
    @SysLog("-新增数据")
    @PostMapping("/insert")
    public R insert(@RequestBody User param) {
        return userService.insert(param);
    }


    @ApiOperation("-编辑数据")
    @SysLog("-编辑数据")
    @PostMapping("/update")
    public R update(@RequestBody User param) {
        if (Objects.isNull(param.getUser_id())) {
            return R.failed("ID不能为空");
        }
        return userService.update(param);
    }


    @ApiOperation("-删除数据")
    @SysLog("-删除数据")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody UserDTO param) {
        return userService.deleteById(param.getUser_id());
    }

}
