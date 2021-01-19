package io.github.talelin.latticy.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.dto.SpuDTO;
import io.github.talelin.latticy.mapper.SpuMapper;
import io.github.talelin.latticy.model.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuImgService spuImgService;

    @Autowired
    private SpuDetailImgService spuDetailImgService;

    @Autowired
    private SpuKeyService spuKeyService;


    public SpuDetailDO getDetail(Long id) {
        return this.getBaseMapper().getDetail(id);
    }

    @Transactional
    public void create(SpuDTO dto) {
        SpuDO spuDO = new SpuDO();
        BeanUtils.copyProperties(dto, spuDO);
        CategoryDO categoryDO = categoryService.getCategoryById(dto.getCategoryId().intValue());
//        spuDO.setCategoryId(categoryDO.getParentId());
        spuDO.setRootCategoryId(categoryDO.getParentId());
        this.save(spuDO);

        List<String> spuImgList = new ArrayList<>();
        if(dto.getSpuImgList() == null) {
            spuImgList.add(dto.getImg());
        } else {
            spuImgList = dto.getSpuImgList();
        }

        this.insertSpuImgList(spuImgList, spuDO.getId());
        if(dto.getDetailImgList() != null) {
            this.insertSpuDetailImgList(dto.getDetailImgList(), spuDO.getId());
        }

        if (dto.getSpecKeyIdList() != null) {
            this.insertSpukeyList(dto.getSpecKeyIdList(), spuDO.getId());
        }

    }

    private void insertSpuImgList(List<String> spuImgList, Long spuId) {
        List<SpuImgDO> spuImgDOList = spuImgList.stream().map(s -> {
            SpuImgDO spuImgDO = new SpuImgDO();
            spuImgDO.setImg(s);
            spuImgDO.setSpuId(spuId.intValue());
            return spuImgDO;
        }).collect(Collectors.toList());

        this.spuImgService.saveBatch(spuImgDOList);
    }

    private void insertSpuDetailImgList(List<String> spuDetailImgList, Long spuId) {

        List<SpuDetailImgDO> spuDetailImgDOList = new ArrayList<>();
        for (int i = 0; i < spuDetailImgList.size(); i++) {
            SpuDetailImgDO spuDetailImgDO = new SpuDetailImgDO();
            spuDetailImgDO.setImg(spuDetailImgList.get(i))
                    .setSpuId(spuId.intValue())
                    .setIndex(i);
            spuDetailImgDOList.add(spuDetailImgDO);
        }

        spuDetailImgService.saveBatch(spuDetailImgDOList);

    }

    private void insertSpukeyList(List<Integer> spuKeyIdList, Long spuId) {
        List<SpuKeyDO> spuKeyDOList = spuKeyIdList.stream().
                map(sk ->{
                    SpuKeyDO spuKeyDO = new SpuKeyDO();
                    spuKeyDO.setSpuId(spuId.intValue())
                            .setSpecKeyId(sk);
                    return spuKeyDO;
                }).collect(Collectors.toList());
        this.spuKeyService.saveBatch(spuKeyDOList);
    }

}
