package com.example.shop.plugins.elasticsearch.service;

import com.example.shop.plugins.elasticsearch.domain.EsProductDO;

import java.util.List;

/**
 * @author ly
 * @date 2020/01/15
 */
public interface ProductDOService {
    List<EsProductDO> searchProduct(String keyword);
}
