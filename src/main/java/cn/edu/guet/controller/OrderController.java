package cn.edu.guet.controller;

import cn.edu.guet.entity.Order;
import cn.edu.guet.entity.VO.OrderVO;
import cn.edu.guet.entity.DTO.OrderDTO;
import cn.edu.guet.service.OrderService;
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
 * @Desc (Order)表控制层
 * @Date 2023-04-23 00:06:59
 */
@Api(tags = "")
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @ApiOperation("-分页查询")
    @SysLog("-分页查询")
    @PostMapping("/queryByPage")
    public R<IPage<OrderVO>> queryByPage(@RequestBody OrderDTO param) {
        return orderService.queryByPage(param);
    }


    @ApiOperation("-根据ID查详情")
    @SysLog("-根据ID查详情")
    @PostMapping("/queryById")
    public R<OrderVO> queryById(@RequestBody OrderDTO param) {
        return orderService.queryById(param.getId());
    }


    @ApiOperation("-新增数据")
    @SysLog("-新增数据")
    @PostMapping("/insert")
    public R insert(@RequestBody Order param) {
        return orderService.insert(param);
    }


    @ApiOperation("-编辑数据")
    @SysLog("-编辑数据")
    @PostMapping("/update")
    public R update(@RequestBody Order param) {
        if (Objects.isNull(param.getId())) {
            return R.failed("ID不能为空");
        }
        return orderService.update(param);
    }


    @ApiOperation("-删除数据")
    @SysLog("-删除数据")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody OrderDTO param) {
        return orderService.deleteById(param.getId());
    }

}
