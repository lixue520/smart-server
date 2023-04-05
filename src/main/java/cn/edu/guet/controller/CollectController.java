package cn.edu.guet.controller;

import cn.edu.guet.entity.Collect;
import cn.edu.guet.entity.VO.CollectVO;
import cn.edu.guet.entity.DTO.CollectDTO;
import cn.edu.guet.service.CollectService;
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
 * @Desc (Collect)表控制层
 * @Date 2023-04-19 13:18:15
 */
@Api(tags = "")
@RestController
@RequestMapping("collect")
@RequiredArgsConstructor
public class CollectController {

    private final CollectService collectService;


    @ApiOperation("-分页查询")
    @SysLog("-分页查询")
    @PostMapping("/queryByPage")
    public R<IPage<CollectVO>> queryByPage(@RequestBody CollectDTO param) {
        return collectService.queryByPage(param);
    }

    @ApiOperation("-查询最近30条")
    @SysLog("-分页查询")
    @PostMapping("/getall")
    public R<IPage<CollectVO>> queryAll(@RequestBody CollectDTO param) {
        return collectService.QueryAll(param);
    }


    @ApiOperation("-根据ID查详情")
    @SysLog("-根据ID查详情")
    @PostMapping("/queryById")
    public R<CollectVO> queryById(@RequestBody CollectDTO param) {
        return collectService.queryById(param.getId());
    }


    @ApiOperation("-新增数据")
    @SysLog("-新增数据")
    @PostMapping("/insert")
    public R insert(@RequestBody Collect param) {
        return collectService.insert(param);
    }


    @ApiOperation("-编辑数据")
    @SysLog("-编辑数据")
    @PostMapping("/update")
    public R update(@RequestBody Collect param) {
        if (Objects.isNull(param.getId())) {
            return R.failed("ID不能为空");
        }
        return collectService.update(param);
    }


    @ApiOperation("-删除数据")
    @SysLog("-删除数据")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody CollectDTO param) {
        return collectService.deleteById(param.getId());
    }

}
