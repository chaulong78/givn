package com.msp.givn.service.user.impl;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.dto.UserRegisterDTO;
import com.msp.givn.entity.User;
import com.msp.givn.entity.UserDetail;
import com.msp.givn.repository.user.UserRepository;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByIdAndEmail(int id, String email) {
        return userRepository.findByIdAndEmail(id, email);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String getPasswordById(int id) {
        return userRepository.getPasswordById(id);
    }

    @Override
    @Transactional
    public boolean updatePassword(int id, String password) {
        return userRepository.updatePassword(id, password);
    }

    @Override
    @Transactional
    public void updateEnabled(int id, boolean enabled) {
        userRepository.updateEnabled(id, enabled);
    }

    @Override
    @Transactional
    public void updateAvatar(int id, String avatar) {
        userRepository.updateAvatar(id, avatar);
    }

    @Override
    @Transactional
    public void updateEmail(int id, String email) {
        userRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateSessionAvatar(CustomUserDetail user, String avatar) {
        user.setAvatar(avatar);
    }

    @Override
    public void updateSessionEmail(CustomUserDetail user, String email) {
        user.setEmail(email);
    }

    @Override
    public User createNewUser(UserRegisterDTO registerDTO) {
        User user = new User();
        user.setUserName(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return save(user);
    }

    @Override
    public CustomUserDetail getCustomUserDetail() {
        return (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
