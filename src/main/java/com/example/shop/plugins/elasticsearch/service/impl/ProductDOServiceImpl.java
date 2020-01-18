package com.example.shop.plugins.elasticsearch.service.impl;

import com.example.shop.plugins.elasticsearch.dao.ProductRepository;
import com.example.shop.plugins.elasticsearch.domain.EsProductDO;
import com.example.shop.plugins.elasticsearch.service.ProductDOService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ly
 * @date 2020/01/15
 */
@Service("esProductService")
public class ProductDOServiceImpl implements ProductDOService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<EsProductDO> searchProduct(String keyword) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.should(QueryBuilders.matchPhraseQuery("name",keyword));
        builder.should(QueryBuilders.matchPhraseQuery("description",keyword));
        Page<EsProductDO> search = (Page<EsProductDO>) productRepository.search(builder);
        return search.getContent();
    }
}
