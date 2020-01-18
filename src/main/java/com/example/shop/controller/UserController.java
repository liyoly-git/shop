package com.example.shop.controller;

import com.example.shop.dto.UserDTO;
import com.example.shop.enums.ResultEnum;
import com.example.shop.service.UserService;
import com.example.shop.utils.ApiResult;
import com.example.shop.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 * @date 2020/01/10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody UserDTO userDTO) {
        if(!userService.login(userDTO)){
            return ApiResult.error(ResultEnum.USERNAME_PASSWORD_ERR);
        }
        JwtUtil jwtUtil = new JwtUtil();
        Map<String,Object> chaim = new HashMap<>();
        String jwtToken = jwtUtil.encode(userDTO.getUsername(), 5 * 60 * 1000,chaim);

        return ApiResult.success(jwtToken);
    }

    @PostMapping("/register")
    public ApiResult register(@RequestBody UserDTO userDTO) {
        try {
            userService.register(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.error(ResultEnum.EXIST_USER);
        }
        return ApiResult.success();
    }

    @RequestMapping("/testdemo")
    public ResponseEntity<String> testdemo(){
        return ResponseEntity.ok("我爱蛋炒饭");
    }
}
