package com.gg.sms.service;

import com.gg.sms.dto.UserDto;
import com.gg.sms.entity.User;
import com.gg.sms.response.JsonResponse;

public interface UserService {
    JsonResponse createUser(UserDto user);
    JsonResponse findUserById(Long id);

    JsonResponse updateUser(Long id, UserDto userDto);
    JsonResponse deleteUserById(Long id);

    JsonResponse findAllUser();
}
