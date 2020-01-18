package com.example.shop.plugins.elasticsearch.dao;

import com.example.shop.plugins.elasticsearch.domain.EsProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ly
 * @date 2020/01/15
 */
public interface ProductRepository extends ElasticsearchRepository<EsProductDO,String> {
}
