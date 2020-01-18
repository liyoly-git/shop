package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.domain.UserDO;
import com.example.shop.dto.UserDTO;
import org.springframework.stereotype.Repository;

/**
 * @author ly
 * @date 2020/01/13
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    int login(String username, String password);

    int register(UserDTO userDTO);

    int deleteUser(String username);

}
