package com.assignment.bidding.service.impl;

import com.assignment.bidding.model.User;
import com.assignment.bidding.repository.UserRepository;
import com.assignment.bidding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("not found"));
    }
}
