package com.vmware.num_gen.imp.controller;

import com.vmware.num_gen.imp.dto.TaskGenerateRequest;
import com.vmware.num_gen.imp.service.NumGenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import  static  com.vmware.num_gen.imp.util.handler.FieldValidationHelper.*;

@Controller
@RequestMapping("v1.0/api")
@Api(value = "Rest endpoints for number generator management apis")
public class NumGenController {

    @Autowired
    @Qualifier("numGenServiceImpl")
    NumGenService numGenService;

    @ApiOperation(value = "create a task")
    @PostMapping
    public ResponseEntity createTask(@Validated(Generate.class) @RequestBody TaskGenerateRequest taskGenerateRequest) {
        return new ResponseEntity("response", HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "status of task")
    @GetMapping(value = "/tasks/{uuid}/status")
    public ResponseEntity status(@PathVariable("uuid") String uuid) {

        return new ResponseEntity("response", HttpStatus.OK);
    }

    @ApiOperation(value = "action on a task")
    @GetMapping(value = "/tasks/{uuid}/")
    public ResponseEntity action(@PathVariable("uuid") String uuid,@RequestParam String action) {
        return new ResponseEntity("response", HttpStatus.OK);
    }
}