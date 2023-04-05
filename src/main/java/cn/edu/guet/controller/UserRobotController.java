package cn.edu.guet.controller;

import cn.edu.guet.entity.UserRobot;
import cn.edu.guet.entity.VO.UserRobotVO;
import cn.edu.guet.entity.DTO.UserRobotDTO;
import cn.edu.guet.service.UserRobotService;
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
 * @Desc (UserRobot)表控制层
 * @Date 2023-04-16 16:07:51
 */
@Api(tags = "")
@RestController
@RequestMapping("userRobot")
@RequiredArgsConstructor
public class UserRobotController {

    private final UserRobotService userRobotService;


    @ApiOperation("-分页查询")
    @SysLog("-分页查询")
    @PostMapping("/queryByPage")
    public R<IPage<UserRobotVO>> queryByPage(@RequestBody UserRobotDTO param) {
        return userRobotService.queryByPage(param);
    }


    @ApiOperation("-根据ID查详情")
    @SysLog("-根据ID查详情")
    @PostMapping("/queryById")
    public R<UserRobotVO> queryById(@RequestBody UserRobotDTO param) {
        return userRobotService.queryById(param.getId());
    }


    @ApiOperation("-新增数据")
    @SysLog("-新增数据")
    @PostMapping("/insert")
    public R insert(@RequestBody UserRobot param) {
        return userRobotService.insert(param);
    }


    @ApiOperation("-编辑数据")
    @SysLog("-编辑数据")
    @PostMapping("/update")
    public R update(@RequestBody UserRobot param) {
        if (Objects.isNull(param.getId())) {
            return R.failed("ID不能为空");
        }
        return userRobotService.update(param);
    }


    @ApiOperation("-删除数据")
    @SysLog("-删除数据")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody UserRobotDTO param) {
        return userRobotService.deleteById(param.getId());
    }

}
