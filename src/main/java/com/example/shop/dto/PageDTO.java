package com.example.shop.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shop.domain.ProductDO;
import com.example.shop.domain.UserDO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ly
 * @date 2020/01/15
 */
@Data
public class PageDTO implements Serializable {

    private static final long serialVersionUID = 3533319590891951643L;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 每页的数量
     */
    private Integer pageSize;


    public Page<UserDO> toUserPage() {
        return new Page<UserDO>(pageNum, pageSize);
    }

    public Page<ProductDO> toProductPage() {
        return new Page<ProductDO>(pageNum, pageSize);
    }

}
