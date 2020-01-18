package com.example.shop.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shop.domain.ProductDO;
import com.example.shop.service.ProductService;
import com.example.shop.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ly
 * @date 2020/01/15
 */
@RestController
@RequestMapping("productManage")
public class ProductManageController {

    @Autowired
    private ProductService productService;

    @GetMapping("list/{pageNum}/{pageSize}")
    public ApiResult listProduct(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        Page<ProductDO> page = productService.page(new Page<>(pageNum,pageSize));
        if(page.getRecords().size()==0){
            return ApiResult.error(505,"分页失败");
        }
        return ApiResult.success(page);
    }

    @GetMapping("delete/{id}")
    public ApiResult deleteProduct(@PathVariable Integer id){
        productService.removeById(id);
        return ApiResult.success();
    }

    @PostMapping("add")
    public ApiResult addProduct(ProductDO productDO){
        productService.save(productDO);
        return ApiResult.success();
    }
}
