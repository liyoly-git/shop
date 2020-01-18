package com.example.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ly
 * @date 2020/01/15
 */
@Data
@TableName("shop_product")
public class ProductDO implements Serializable {

    private static final long serialVersionUID = 5143378578350593779L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String name;
    
    private String img;
    
    private String description;

    private Integer price;
}
