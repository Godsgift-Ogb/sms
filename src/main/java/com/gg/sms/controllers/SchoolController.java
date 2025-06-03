package com.gg.sms.controllers;

import com.gg.sms.dto.SchoolDto;
import com.gg.sms.dto.UserDto;
import com.gg.sms.entity.School;
import com.gg.sms.response.JsonResponse;
import com.gg.sms.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
    @PostMapping("/create")
    public ResponseEntity<JsonResponse> createSchool(@RequestBody SchoolDto schoolDto){
       JsonResponse schoolResponse = schoolService.createSchool(schoolDto);
       return ResponseEntity.ok(schoolResponse);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<JsonResponse> findById(@PathVariable Long id){
        JsonResponse school = schoolService.findById(id);
        return ResponseEntity.ok(school);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JsonResponse> updateSchool(@PathVariable Long id, @RequestBody SchoolDto schoolDto){
        JsonResponse schoolResponse = schoolService.updateSchool(id, schoolDto);
        return ResponseEntity.ok(schoolResponse);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<JsonResponse> deleteSchool(@PathVariable Long id){
        JsonResponse school = schoolService.deleteSchool(id);
        return ResponseEntity.ok(school);
    }
}
