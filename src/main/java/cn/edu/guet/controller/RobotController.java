package cn.edu.guet.controller;

import cn.edu.guet.entity.Robot;
import cn.edu.guet.entity.VO.RobotVO;
import cn.edu.guet.entity.DTO.RobotDTO;
import cn.edu.guet.service.RobotService;
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
 * @Desc (Robot)表控制层
 * @Date 2023-04-16 16:05:18
 */
@Api(tags = "")
@RestController
@RequestMapping("robot")
@RequiredArgsConstructor
public class RobotController {

    private final RobotService robotService;


    @ApiOperation("-分页查询")
    @SysLog("-分页查询")
    @PostMapping("/queryByPage")
    public R<IPage<RobotVO>> queryByPage(@RequestBody RobotDTO param) {
        return robotService.queryByPage(param);
    }


    @ApiOperation("-根据ID查详情")
    @SysLog("-根据ID查详情")
    @PostMapping("/queryById")
    public R<RobotVO> queryById(@RequestBody RobotDTO param) {
        return robotService.queryById(param.getId());
    }


    @ApiOperation("-新增数据")
    @SysLog("-新增数据")
    @PostMapping("/insert")
    public R insert(@RequestBody Robot param) {
        return robotService.insert(param);
    }


    @ApiOperation("-编辑数据")
    @SysLog("-编辑数据")
    @PostMapping("/update")
    public R update(@RequestBody Robot param) {
        if (Objects.isNull(param.getId())) {
            return R.failed("ID不能为空");
        }
        return robotService.update(param);
    }


    @ApiOperation("-删除数据")
    @SysLog("-删除数据")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody RobotDTO param) {
        return robotService.deleteById(param.getId());
    }

}
