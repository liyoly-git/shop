package com.example.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shop.domain.UserDO;
import com.example.shop.dto.UserDTO;

/**
 * @author ly
 * @date 2020/01/13
 */
public interface UserService extends IService<UserDO> {

    boolean login(UserDTO userDTO);

    boolean register(UserDTO userDTO);

    boolean deleteUser(String username);
}
