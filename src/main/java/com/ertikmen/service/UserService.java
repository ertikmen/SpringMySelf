package com.ertikmen.service;


import com.ertikmen.dto.request.UserRequestDto;
import com.ertikmen.mapper.IUserMapper;
import com.ertikmen.repository.IUserRepository;
import com.ertikmen.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public User createUser(UserRequestDto userRequestDto) {
        User user= IUserMapper.INSTANCE.toUser(userRequestDto);
        return userRepository.save(user);
    }

    public List<User> getUsers() {

        return userRepository.findAll();

    }

    public User findById(Long id) {
        Optional<User> user=userRepository.findById(id);
        if (user.isEmpty()){
            throw new RuntimeException("Kullanıcı Bulunamadı.");
        }
        return user.get();
    }


    public User updateUser(User userInfo) {
        return userRepository.save(userInfo);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "Kayıt Silindi.";
    }
}
