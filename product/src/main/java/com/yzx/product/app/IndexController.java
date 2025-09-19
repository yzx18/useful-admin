package com.yzx.product.app;

import com.yzx.model.Result;
import com.yzx.product.entity.SearchParam;
import com.yzx.product.service.EsSearchService;
import com.yzx.product.service.ProductCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: IndexController
 * @author: yzx
 * @date: 2025/9/18 11:28
 * @Version: 1.0
 * @description:
 */
@RestController
@RequestMapping("/product")
public class IndexController {

    @Autowired
    private ProductCateGoryService productCateGoryService;

    @Autowired
    private EsSearchService esSearchService;

    @GetMapping("/index/category")
    public Result index() {
        return productCateGoryService.getCateGory();
    }

    @PostMapping("/search")
    public Result search(SearchParam searchParam) {
        return esSearchService.search(searchParam);
    }

    @GetMapping("/getProductInfo/{spuId}")
    public Result getProductInfo(@PathVariable Long spuId) {

    }
}
