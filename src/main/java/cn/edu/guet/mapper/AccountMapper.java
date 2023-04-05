package cn.edu.guet.mapper;

import cn.edu.guet.entity.Account;
import cn.edu.guet.entity.VO.AccountVO;
import cn.edu.guet.entity.DTO.AccountDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author DavidNan
 * @Desc (Account)表数据库访问层
 * @Date 2023-04-19 13:26:01
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 分页查询
     */
    IPage<AccountVO> queryByPage(Page page, @Param("param") AccountDTO param);


    /**
     * 根据ID查详情
     */
    AccountVO queryById(Integer id);


    /**
     * 新增数据
     */
    int insert(Account account);


    /**
     * 修改数据
     */
    int update(Account account);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
