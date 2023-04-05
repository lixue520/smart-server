package cn.edu.guet.service;

import cn.edu.guet.entity.Collect;
import cn.edu.guet.entity.DTO.CollectDTO;
import cn.edu.guet.entity.VO.CollectVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author DavidNan
 * @Desc (Collect)表服务接口
 * @Date 2023-04-19 13:18:16
 */
public interface CollectService extends IService<Collect> {

    /**
     * 分页查询
     */
    R<IPage<CollectVO>> queryByPage(CollectDTO param);

    R<IPage<CollectVO>> QueryAll(CollectDTO param);

    /**
     * 根据ID查详情
     */
    R queryById(Integer id);

    /**
     * 新增数据
     */
    R insert(Collect collect);

    /**
     * 修改数据
     */
    R update(Collect collect);

    /**
     * 通过主键删除数据
     */
    R deleteById(Integer id);

}
