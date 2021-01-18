package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.BannerDTO;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.service.BannerService;
import io.github.talelin.latticy.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RequestMapping("/v1/banner")
@RestController
@Validated
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @PutMapping("/{id}")
    public void update(@RequestBody @Validated BannerDTO dto,
                       @PathVariable @Positive Long id) {
        BannerDTO dto1 = dto;
    }

    @GetMapping("/page")
    public PageResponseVO<BannerDO> getBanners(@RequestParam(required = false, defaultValue = "0")
                               @Min(value = 0) Integer page,
                           @RequestParam(required = false, defaultValue = "10")
                           @Min(value = 1) @Max(value = 20) Integer count) {
        Page<BannerDO> pager = new Page<>(page, count);
        IPage<BannerDO> paging = bannerService.getBaseMapper().selectPage(pager, null);
        return new PageResponseVO<>(paging.getTotal(), paging.getRecords(), paging.getCurrent(), paging.getSize());
    }

//自定义错误的消息
//    @GetMapping("/page")
//    public String getBanners(@RequestParam(required = false, defaultValue = "0")
//                             @Min(value = 0) Integer page,
//                             @RequestParam(required = false, defaultValue = "10")
//                             @Min(value = 1, message = "{businessPage.count.min}") @Max(value = 20, message="{businessPage.count.max}") Integer count) {
//        return "sdkk";
//    }
}
