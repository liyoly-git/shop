package com.example.shop.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shop.domain.UserDO;
import com.example.shop.service.UserService;
import com.example.shop.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ly
 * @date 2020/01/15
 */
@RestController
@RequestMapping("userManage")
public class UserManageController {

    @Autowired
    private UserService userService;

    @RequestMapping("list/{pageNum}/{pageSize}")
    public ApiResult listUser(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        Page<UserDO> page = userService.page(new Page<>(pageNum,pageSize));
        if(page.getRecords().size()==0){
            return ApiResult.error(505,"分页失败");
        }
        return ApiResult.success(page);
    }

    @RequestMapping("delete")
    public ApiResult deleteUser(String username){
        boolean key = userService.deleteUser(username);
        if(!key){
            return ApiResult.error(506,"删除失败");

        }
        return ApiResult.success("删除成功");

    }
}
