package com.vmware.num_gen.imp.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
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

        String uuid = numGenService.createTask(taskGenerateRequest);
        JSONPObject jsonObject = new JSONPObject("",Object.class);
        return new ResponseEntity(jsonObject, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "status of task")
    @GetMapping(value = "/tasks/{uuid}/status")
    public ResponseEntity status(@PathVariable("uuid") String uuid) {
        String status = numGenService.status(uuid);
        JSONPObject jsonObject = new JSONPObject("status:"+status,Object.class);
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    @ApiOperation(value = "action on a task")
    @GetMapping(value = "/tasks/{uuid}/")
    public ResponseEntity action(@PathVariable("uuid") String uuid,@RequestParam String action) {

        String result = numGenService.action(action);
        JSONPObject jsonObject = new JSONPObject("result:"+result,Object.class);
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }
}