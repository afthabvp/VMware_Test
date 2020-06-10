package com.vmware.num_gen.imp.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGenerateRequest {

    @ApiModelProperty(value = "Goal for genrate task ",required = true)
    private String Goal;

    @ApiModelProperty(value = "Step for genrate task ", required = true)
    private String Step;
}
