package com.assignment.bidding.util;

import com.assignment.bidding.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static Long getUserIdFromContext(){
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User userPrincipal = (User) auth.getPrincipal();
        return userPrincipal.getId();
    }
}
