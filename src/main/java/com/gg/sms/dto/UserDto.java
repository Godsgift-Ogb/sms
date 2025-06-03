package com.gg.sms.dto;

import com.gg.sms.entity.School;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
    private Long schoolId;
}
