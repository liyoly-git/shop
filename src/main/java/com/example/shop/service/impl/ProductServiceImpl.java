package com.example.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shop.domain.ProductDO;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author ly
 * @date 2020/01/15
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {
}
