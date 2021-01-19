package io.github.talelin.latticy.mapper;

import io.github.talelin.latticy.model.SpecKeyDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-01-19
 */
public interface SpecKeyMapper extends BaseMapper<SpecKeyDO> {


   List<SpecKeyDO> getBySpuId(Long spuId);
}
