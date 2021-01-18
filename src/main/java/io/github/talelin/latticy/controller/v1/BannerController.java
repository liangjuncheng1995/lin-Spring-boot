package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.core.annotation.*;
import io.github.talelin.latticy.bo.BannerWithItemsBO;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.BannerDTO;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.service.BannerService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RequestMapping("/v1/banner")
@RestController
@Validated
@PermissionModule(value = "Banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @PostMapping
    @PermissionMeta(value = "创建Banner")
    @GroupRequired
    public CreatedVO create(@RequestBody @Validated BannerDTO bannerDTO) {
        BannerDO bannerDO = new BannerDO();
        BeanUtils.copyProperties(bannerDTO, bannerDO);
        this.bannerService.save(bannerDO);
        return new CreatedVO();
    }
    //模块名、权限名
    //@LoginRequired 登录限制注解
    //@GroupRequired 分组权限
    //@AdminRequired 登录且是只有管理员才能使用接口的注解
    //@PermissionMeta //不会开启保护接口的功能，正常也能访问
    //@PermissionModule

    @PutMapping("/{id}")
    @PermissionMeta(value = "更新Banner")
    @GroupRequired
    public UpdatedVO update(@RequestBody @Validated BannerDTO dto,
                            @PathVariable @Positive Long id) {
        BannerDTO dto1 = dto;
        bannerService.update(dto, id);
        return new UpdatedVO();
    }

    @GetMapping("/{id}")
    @LoginRequired
    @PermissionMeta(value = "查询Banner")
    public BannerWithItemsBO getWithItems(@PathVariable @Positive Long id) {
        return bannerService.getWithItems(id);
    }

    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除Banner")
    @GroupRequired
    public DeletedVO delete(@PathVariable @Positive() Long id) {
        bannerService.delete(id);
        return new DeletedVO();
        //级联删除数据
        //查询banner_item_id
    }



    @GetMapping("/page")
    @LoginRequired
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
