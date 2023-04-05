package cn.edu.guet.controller;

import cn.edu.guet.entity.UserOrder;
import cn.edu.guet.entity.VO.UserOrderVO;
import cn.edu.guet.entity.DTO.UserOrderDTO;
import cn.edu.guet.service.UserOrderService;
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
 * @Desc (UserOrder)表控制层
 * @Date 2023-04-23 00:38:05
 */
@Api(tags = "")
@RestController
@RequestMapping("userOrder")
@RequiredArgsConstructor
public class UserOrderController {

    private final UserOrderService userOrderService;


    @ApiOperation("-分页查询")
    @SysLog("-分页查询")
    @PostMapping("/queryByPage")
    public R<IPage<UserOrderVO>> queryByPage(@RequestBody UserOrderDTO param) {
        return userOrderService.queryByPage(param);
    }


    @ApiOperation("-根据ID查详情")
    @SysLog("-根据ID查详情")
    @PostMapping("/queryById")
    public R<UserOrderVO> queryById(@RequestBody UserOrderDTO param) {
        return userOrderService.queryById(param.getId());
    }


    @ApiOperation("-新增数据")
    @SysLog("-新增数据")
    @PostMapping("/insert")
    public R insert(@RequestBody UserOrder param) {
        return userOrderService.insert(param);
    }


    @ApiOperation("-编辑数据")
    @SysLog("-编辑数据")
    @PostMapping("/update")
    public R update(@RequestBody UserOrder param) {
        if (Objects.isNull(param.getId())) {
            return R.failed("ID不能为空");
        }
        return userOrderService.update(param);
    }


    @ApiOperation("-删除数据")
    @SysLog("-删除数据")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody UserOrderDTO param) {
        return userOrderService.deleteById(param.getId());
    }

}
