package com.sarahjting.roost.user.services;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.UserDto;

public interface UserCreator {
    User execute(UserDto userDto);
}
