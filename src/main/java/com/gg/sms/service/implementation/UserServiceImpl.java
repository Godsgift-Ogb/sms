package com.gg.sms.service.implementation;

import com.gg.sms.dto.UserDto;
import com.gg.sms.entity.School;
import com.gg.sms.entity.User;
import com.gg.sms.exception.ResourceNotFoundException;
import com.gg.sms.repository.SchoolRepository;
import com.gg.sms.repository.UserRepository;
import com.gg.sms.response.JsonResponse;
import com.gg.sms.service.SchoolService;
import com.gg.sms.service.UserService;
import com.gg.sms.utils.Constants;
import com.gg.sms.utils.UserConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    @Value("${app.owner.name}")
    private String appOwner;

    public UserServiceImpl(UserRepository userRepository, SchoolRepository schoolRepository) {
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public JsonResponse createUser(UserDto userDto) {
        Boolean userExist = userRepository.existsByEmail(userDto.getEmail());
        Optional<User> userFound = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if(userExist){
            return new JsonResponse<>(Constants.ALREADY_EXIST,"User already exist", userFound);
        }
        User user = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .userType(userDto.getUserType())
                .build();
            Optional<School> school = schoolRepository.findById(userDto.getSchoolId());
        if (UserConstant.student.equalsIgnoreCase(userDto.getUserType()) || UserConstant.teacher.equalsIgnoreCase(userDto.getUserType())) {
            if (school.isPresent()){
                user.setSchool(school.get());
                User saved = userRepository.save(user);
                return new JsonResponse<>("201", "Account Created Successfully", saved);
            }else {
                return new JsonResponse<>(Constants.FAILURE,"School Not Found",null);
            }


        }
      return new JsonResponse<>(Constants.FAILURE,"Please Enter A Valid UserType",null);
    }

    @Override
    public JsonResponse findUserById(Long id) {
        System.err.print(appOwner);
        Optional<User> user = userRepository.findById(id);
        if(id > 5){
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST,"ID not available");
        }
        if(user.isEmpty()){
            return new JsonResponse<>(Constants.FAILURE,"User not found",null);
        }
        return new JsonResponse<>(Constants.SUCCESS,"User detail fetched successfully",user);
    }

    public JsonResponse findAllUser(){
        List<User> users = userRepository.findAll();
        return new JsonResponse<>(Constants.SUCCESS,"All Users Successfully Fetched",users);
    }

    @Override
    public JsonResponse updateUser(Long id,UserDto userDto) {
       Optional<User> existingUser = userRepository.findById(id);
       if (existingUser.isPresent()){
          User existing = existingUser.get();
//           existing.setUsername(userDto.getUsername());
//           existing.setFirstName(userDto.getFirstName());
//           existing.setLastName(userDto.getLastName());
//           existing.setEmail(userDto.getEmail());
           existing.setUserType(userDto.getUserType());
           User saved = userRepository.save(existing);
           return new JsonResponse<>(Constants.SUCCESS,"Update User Success",saved);
       }
       return new JsonResponse<>(Constants.FAILURE,"User Not Found",null);
    }

    @Override
    public JsonResponse deleteUserById(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()){
            userRepository.deleteById(id);
            return new JsonResponse<>(Constants.SUCCESS, "User Successfully Deleted",null);
        }
        return new JsonResponse<>(Constants.FAILURE,"User Not Found", null);
    }
}
