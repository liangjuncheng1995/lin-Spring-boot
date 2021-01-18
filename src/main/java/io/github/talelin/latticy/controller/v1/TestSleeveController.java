package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.mapper.BannerMapper;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.service.TestSleeveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/test")
@RestController
public class TestSleeveController {

    @Autowired
    private TestSleeveService testSleeveService;

    @Autowired
    private BannerMapper bannerMapper;

    @GetMapping("/test1")
    public List<BannerDO>  test1() {
        return testSleeveService.getBanners();
    }

    @GetMapping("/test2")
    public Long test2() {
        return testSleeveService.insertBanner();
    }

    @GetMapping("/test3")
    public List<BannerDO>  test3() {
        return testSleeveService.getBanners();
    }

    @GetMapping("/test4")
    public List<BannerDO>  test4() {
        bannerMapper.selectList(null);
        return testSleeveService.getBanners();
    }

}

