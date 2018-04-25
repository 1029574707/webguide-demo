package com.ceiec.webguide.formal.controller;

import com.ceiec.webguide.formal.dao.UserMapper;
import com.ceiec.webguide.formal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CreateDate: 2018/4/9 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

@RestController
@RequestMapping("/usertest")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable int id){
        return userMapper.findUserById(id);
    }

    @GetMapping("/findAll")
    public List<UserEntity> getAllUser(){
        return userMapper.fildAllUser();
    }

    @PutMapping("/update/{id}")
    public void updateUserById(@PathVariable int id, @RequestBody UserEntity newUser){
        userMapper.updateUser(newUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable int id){
        userMapper.deleteUser(id);
    }

    @PostMapping("/insert")
    public void insertUser(@RequestBody UserEntity userEntity){
        userMapper.insertUser(userEntity);
    }
}
