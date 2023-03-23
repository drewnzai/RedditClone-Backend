package com.drew.Reddit.services;

import com.drew.Reddit.dto.UserDto;
import com.drew.Reddit.mapper.UserMapper;
import com.drew.Reddit.models.User;
import com.drew.Reddit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
*   Holds all logic related to the creation, storage, and retrieval of users.
*   Additionally, it provides user information to the public.
* */


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto getUserDetails(String username){

        return userMapper.mapUsertoUserDto(userRepository.findByUsername(username).get());
    }
    public void userAuthenticate(Long id){
       userRepository.findById(id).get().setEnabled(true);
    }

    public Boolean checkAuthStatus(Long id){
        return userRepository.findById(id).get().isEnabled();
    }

    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateProfileImage(String username, String fileName) {
        User user = userRepository.findByUsername(username).get();
        user.setProfile_image(fileName);

    }

    public User getUser(String username){
        return userRepository.findByUsername(username).get();
    }
}
