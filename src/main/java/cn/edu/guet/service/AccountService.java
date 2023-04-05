package cn.edu.guet.service;

import cn.edu.guet.entity.Account;
import cn.edu.guet.entity.DTO.AccountDTO;
import cn.edu.guet.entity.VO.AccountVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author DavidNan
 * @Desc (Account)表服务接口
 * @Date 2023-04-19 13:26:02
 */
public interface AccountService extends IService<Account> {

    /**
     * 分页查询
     */
    R<IPage<AccountVO>> queryByPage(AccountDTO param);

    /**
     * 根据ID查详情
     */
    R queryById(Integer id);

    /**
     * 新增数据
     */
    R insert(Account account);

    /**
     * 修改数据
     */
    R update(Account account);

    /**
     * 通过主键删除数据
     */
    R deleteById(Integer id);

}
