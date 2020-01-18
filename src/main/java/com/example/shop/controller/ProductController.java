package com.example.shop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shop.domain.ProductDO;
import com.example.shop.plugins.elasticsearch.domain.EsProductDO;
import com.example.shop.plugins.elasticsearch.service.ProductDOService;
import com.example.shop.service.ProductService;
import com.example.shop.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ly
 * @date 2020/01/15
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDOService esProductService;

    @RequestMapping("list/{pageNum}/{pageSize}")
    public ApiResult listProduct(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        Page<ProductDO> page = productService.page(new Page<>(pageNum,pageSize));
        if(page.getRecords().size() == 0)
            return ApiResult.error();
        return ApiResult.success(page);
    }

    @RequestMapping("/search")
    public ApiResult searchProduct(String keyword){
        List<EsProductDO> list = esProductService.searchProduct(keyword);
        return ApiResult.success(list);
    }

    @RequestMapping("/addCart")
    public ApiResult addCart(Integer id){
        productService.addCart
    }
}
