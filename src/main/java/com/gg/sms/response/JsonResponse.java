package com.gg.sms.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonResponse <T>{
    private String status;
    private String message;
    private T data;
}
