package com.ertikmen.controller;


import com.ertikmen.dto.request.UserRequestDto;
import com.ertikmen.repository.entity.User;
import com.ertikmen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ertikmen.constant.RestApiUrl.*;

@RestController
@RequestMapping(USER)    // swagger::7099/users/get_users
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get_users")
    public ResponseEntity<List<User>> userList(){

        return ResponseEntity.ok(userService.getUsers());

    }

    @GetMapping("/find_user_by_id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/update_user_by_id/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user){
        User userInfo=userService.findById(id);
        if (userInfo!=null){
            userInfo.setId(id);
            userInfo.setName(user.getName());
            userInfo.setSurName(user.getSurName());
            userInfo.setEmail(user.getEmail());
            userInfo.setPassword(user.getPassword());
            userInfo.setPhone(user.getPhone());
            return userService.updateUser(userInfo);
        }
        return null;
    }
    @DeleteMapping("/delete_user_by_id/{id}")
    public String deleteUser(@PathVariable Long id){
        User userInfo=userService.findById(id);
        if (userInfo!=null){
            return userService.deleteUser(id);
        }
        return null;
    }


    @PostMapping("/user_save")
    public ResponseEntity<?> createUser(UserRequestDto userRequestDto){

        try {
            userService.createUser(userRequestDto);
            return ResponseEntity.ok(userRequestDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Kullanıcı Oluşturulamadı.");
        }
    }




}
