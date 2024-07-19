package com.assignment.bidding.service;

import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.User;

public interface UserService {
    User createUser(User user);
    User findById(Long userId);

}
