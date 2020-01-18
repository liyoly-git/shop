package com.example.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shop.domain.UserDO;
import com.example.shop.dto.UserDTO;
import com.example.shop.mapper.UserMapper;
import com.example.shop.service.UserService;
import com.example.shop.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ly
 * @date 2020/01/13
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = UserUtil.md5DigestAsHex(userDTO.getPassword());
        return userMapper.login(username, password) > 0;

    }

    @Override
    public boolean register(UserDTO userDTO) {
        String password = userDTO.getPassword();
        // 密码加密存入数据库
        userDTO.setPassword(UserUtil.md5DigestAsHex(password));

        return userMapper.register(userDTO) > 0;
    }

    @Override
    public boolean deleteUser(String username) {
        return userMapper.deleteUser(username) > 0;

    }
}
