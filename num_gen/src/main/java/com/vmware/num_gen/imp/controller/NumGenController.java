package com.vmware.num_gen.imp.controller;

import com.google.gson.Gson;
import com.vmware.num_gen.imp.dto.TaskGenerateRequest;
import com.vmware.num_gen.imp.service.NumGenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("v1.0/api")
@Api(value = "Rest endpoints for number generator management apis")
public class NumGenController {

    @Autowired
    @Qualifier("numGenServiceImpl")
    NumGenService numGenService;

    @ApiOperation(value = "create a task")
    @PostMapping(value = "/generate")
    public ResponseEntity createTask(@RequestBody TaskGenerateRequest taskGenerateRequest) {

        String uuid = numGenService.createTask(taskGenerateRequest);
        return new ResponseEntity(new Gson().toJson("uuid :"+uuid), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "status of task")
    @GetMapping(value = "/tasks/{uuid}/status")
    public ResponseEntity status(@PathVariable("uuid") String uuid) {
        String status = numGenService.status(uuid);
        return new ResponseEntity(new Gson().toJson("status:"+status), HttpStatus.OK);
    }

    @ApiOperation(value = "action on a task")
    @GetMapping(value = "/tasks/{uuid}/")
    public ResponseEntity action(@PathVariable("uuid") String uuid,@RequestParam String action) {

        String result = numGenService.action(uuid, action);
        return new ResponseEntity(new Gson().toJson("result:"+result), HttpStatus.OK);
    }
}