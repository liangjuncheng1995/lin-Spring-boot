package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.SpecKeyDO;
import io.github.talelin.latticy.mapper.SpecKeyMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-01-19
 */
@Service
public class SpecKeyService extends ServiceImpl<SpecKeyMapper, SpecKeyDO> {
    public List<SpecKeyDO> getBySpuId(Long spuId) {
        return this.baseMapper.getBySpuId(spuId);
    }
}
