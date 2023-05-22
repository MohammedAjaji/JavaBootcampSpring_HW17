package com.example.spring_homework17.Service;


import com.example.spring_homework17.Model.User;
import com.example.spring_homework17.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user){
        User oldUser = userRepository.getById(id);

        if (oldUser == null){
            return false;
        }

        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());



        userRepository.save(oldUser);
        return true;

    }

    public Boolean deleteUser(Integer id){
        User oldUser = userRepository.getById(id);
        if (oldUser == null){
            return false;
        }
        userRepository.delete(oldUser);
        return true;
    }
}
