package com.vmware.num_gen.imp.controller;

import com.vmware.num_gen.imp.service.NumGenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("v1.0/api")
@Api(value = "Rest endpoints for number generator management apis")
public class NumGenController {

    @Autowired
    @Qualifier("numGenServiceImpl")
    NumGenService numGenService;

}