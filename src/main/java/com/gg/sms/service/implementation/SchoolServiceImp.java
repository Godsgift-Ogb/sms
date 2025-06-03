package com.gg.sms.service.implementation;

import com.gg.sms.dto.SchoolDto;
import com.gg.sms.entity.School;
import com.gg.sms.repository.SchoolRepository;
import com.gg.sms.response.JsonResponse;
import com.gg.sms.service.SchoolService;
import com.gg.sms.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolServiceImp implements SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolServiceImp(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public JsonResponse createSchool(SchoolDto schoolDto) {
        Boolean schoolExist = schoolRepository.existsByEmail(schoolDto.getEmail());
        if (schoolExist){
            return new JsonResponse<>(Constants.ALREADY_EXIST,"School already exist",null);
        }
        School school = new School();
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setEmail(schoolDto.getEmail());
        school.setAccountBalance(schoolDto.getAccountBalance());
        School saved = schoolRepository.save(school);
        return new JsonResponse<>(Constants.SUCCESS,"School Created Successfully",saved);
    }

    @Override
    public JsonResponse findById(Long id) {
        Optional<School> school = schoolRepository.findById(id);
        if (school.isEmpty()){
            return new JsonResponse<>(Constants.FAILURE,"School id not found",null);
        }
        return new JsonResponse<>(Constants.SUCCESS,"School Found",school);


    }

    @Override
    public JsonResponse updateSchool(Long id, SchoolDto schoolDto) {
        Optional<School> school = schoolRepository.findById(id);
        if (school.isPresent()){
            School existingSchool = school.get();
            existingSchool.setName(schoolDto.getName());
            School saved = schoolRepository.save(existingSchool);
            return new JsonResponse<>(Constants.SUCCESS,"School update Successful",saved);
        }
        return new JsonResponse<>(Constants.FAILURE,"School Not Found",null);
    }

    @Override
    public JsonResponse deleteSchool(Long id) {
        boolean schoolExist = schoolRepository.existsById(id);
        if (schoolExist){
             schoolRepository.deleteById(id);
             return new JsonResponse<>(Constants.SUCCESS,"School deleted successfully",null);
        }
        return new JsonResponse<>(Constants.FAILURE,"School Not Found",null);
    }
}
