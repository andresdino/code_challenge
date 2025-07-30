package com.ms.prueba.controller;

import com.ms.prueba.dto.ApiResponseDto;
import com.ms.prueba.entity.User;
import com.ms.prueba.service.implement.BaseService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")


public class UserController extends BaseController<User>{

    protected UserController(BaseService<User> service) {
        super(service);
    }

    @Override
    @Hidden
    public ResponseEntity<ApiResponseDto<User>> findById(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Hidden
    public ResponseEntity<ApiResponseDto<User>> save(@RequestBody User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Hidden
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable Long id){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Hidden
    public ResponseEntity<ApiResponseDto<Void>> update(@PathVariable Long id, @RequestBody User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
