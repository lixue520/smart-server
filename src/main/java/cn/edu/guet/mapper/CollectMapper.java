package cn.edu.guet.mapper;

import cn.edu.guet.entity.Collect;
import cn.edu.guet.entity.VO.CollectVO;
import cn.edu.guet.entity.DTO.CollectDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author DavidNan
 * @Desc (Collect)表数据库访问层
 * @Date 2023-04-19 13:18:15
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {

    /**
     * 分页查询
     */
    IPage<CollectVO> queryByPage(Page page, @Param("param") CollectDTO param);

    IPage<CollectVO> QueryAll(Page page, @Param("param") CollectDTO param);

    /**
     * 根据ID查详情
     */
    CollectVO queryById(Integer id);


    /**
     * 新增数据
     */
    int insert(Collect collect);


    /**
     * 修改数据
     */
    int update(Collect collect);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
