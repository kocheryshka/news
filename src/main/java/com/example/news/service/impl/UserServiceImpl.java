package com.example.news.service.impl;

import com.example.news.model.User;
import com.example.news.repository.UserRepository;
import com.example.news.service.UserService;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                MessageFormat.format("Пользователь с email {0} не найден!", id))
        );
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existingUser = findById(user.getEmail());
        BeanUtils.copyNonNullProperties(user, existingUser);

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
