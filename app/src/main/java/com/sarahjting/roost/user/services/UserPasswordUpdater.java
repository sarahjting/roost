package com.sarahjting.roost.user.services;

import com.sarahjting.roost.user.User;

public interface UserPasswordUpdater {
    User execute(User user, String oldPassword, String newPassword);
}
