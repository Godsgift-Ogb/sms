package com.gg.sms.service;

import com.gg.sms.dto.SchoolDto;
import com.gg.sms.entity.School;
import com.gg.sms.response.JsonResponse;

import java.util.Optional;


public interface SchoolService {
     JsonResponse createSchool(SchoolDto schoolDto);

     JsonResponse findById(Long id);

     JsonResponse updateSchool(Long id , SchoolDto schoolDto);

     JsonResponse deleteSchool(Long id);
}
