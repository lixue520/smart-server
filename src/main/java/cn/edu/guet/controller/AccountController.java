package cn.edu.guet.controller;

import cn.edu.guet.entity.Account;
import cn.edu.guet.entity.VO.AccountVO;
import cn.edu.guet.entity.DTO.AccountDTO;
import cn.edu.guet.service.AccountService;
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
 * @Desc (Account)表控制层
 * @Date 2023-04-19 13:26:01
 */
@Api(tags = "")
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @ApiOperation("-分页查询")
    @SysLog("-分页查询")
    @PostMapping("/queryByPage")
    public R<IPage<AccountVO>> queryByPage(@RequestBody AccountDTO param) {
        return accountService.queryByPage(param);
    }


    @ApiOperation("-根据ID查详情")
    @SysLog("-根据ID查详情")
    @PostMapping("/queryById")
    public R<AccountVO> queryById(@RequestBody AccountDTO param) {
        return accountService.queryById(param.getId());
    }


    @ApiOperation("-新增数据")
    @SysLog("-新增数据")
    @PostMapping("/insert")
    public R insert(@RequestBody Account param) {
        return accountService.insert(param);
    }


    @ApiOperation("-编辑数据")
    @SysLog("-编辑数据")
    @PostMapping("/update")
    public R update(@RequestBody Account param) {
        if (Objects.isNull(param.getId())) {
            return R.failed("ID不能为空");
        }
        return accountService.update(param);
    }


    @ApiOperation("-删除数据")
    @SysLog("-删除数据")
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody AccountDTO param) {
        return accountService.deleteById(param.getId());
    }

}
