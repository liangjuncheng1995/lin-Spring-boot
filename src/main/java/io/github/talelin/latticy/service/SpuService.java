package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.mapper.SpuMapper;
import io.github.talelin.latticy.model.SpuDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.SpuDetailDO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-01-19
 */
@Service
public class SpuService extends ServiceImpl<SpuMapper,SpuDO> {

    public SpuDetailDO getDetail(Long id) {
        return this.getBaseMapper().getDetail(id);
    }
}
