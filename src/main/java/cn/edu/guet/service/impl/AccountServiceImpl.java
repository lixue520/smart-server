package cn.edu.guet.service.impl;

import cn.edu.guet.entity.Account;
import cn.edu.guet.entity.DTO.AccountDTO;
import cn.edu.guet.entity.VO.AccountVO;
import cn.edu.guet.mapper.AccountMapper;
import cn.edu.guet.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author DavidNan
 * @Desc (Account)表服务实现类
 * @Date 2023-04-19 13:26:02
 */
@RequiredArgsConstructor
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    private final AccountMapper accountMapper;


    @Override
    public R<IPage<AccountVO>> queryByPage(AccountDTO param) {
        return R.ok(accountMapper.queryByPage(param, param));
    }


    @Override
    public R queryById(Integer id) {
        return R.ok(accountMapper.queryById(id));
    }


    @Override
    public R insert(Account account) {
        int num = accountMapper.insert(account);
        return R.ok(num);
    }


    @Override
    public R update(Account account) {
        int num = accountMapper.update(account);
        return R.ok(num);
    }


    @Override
    public R deleteById(Integer id) {
        int num = accountMapper.deleteById(id);
        return R.ok(num);
    }

}
