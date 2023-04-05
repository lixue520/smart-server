package cn.edu.guet.service.impl;

import cn.edu.guet.entity.Collect;
import cn.edu.guet.entity.DTO.CollectDTO;
import cn.edu.guet.entity.VO.CollectVO;
import cn.edu.guet.mapper.CollectMapper;
import cn.edu.guet.service.CollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import cn.edu.guet.entity.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author DavidNan
 * @Desc (Collect)表服务实现类
 * @Date 2023-04-19 13:18:16
 */
@RequiredArgsConstructor
@Service("collectService")
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    private final CollectMapper collectMapper;


    @Override
    public R<IPage<CollectVO>> queryByPage(CollectDTO param) {
        return R.ok(collectMapper.queryByPage(param, param));
    }

    @Override
    public R<IPage<CollectVO>> QueryAll(CollectDTO param) {
        return R.ok(collectMapper.QueryAll(param, param));
    }


    @Override
    public R queryById(Integer id) {
        return R.ok(collectMapper.queryById(id));
    }


    @Override
    public R insert(Collect collect) {
        int num = collectMapper.insert(collect);
        return R.ok(num);
    }


    @Override
    public R update(Collect collect) {
        int num = collectMapper.update(collect);
        return R.ok(num);
    }


    @Override
    public R deleteById(Integer id) {
        int num = collectMapper.deleteById(id);
        return R.ok(num);
    }

}
