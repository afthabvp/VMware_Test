package com.vmware.num_gen.imp.dto;


import com.vmware.num_gen.imp.util.ErrorCode;
import com.vmware.num_gen.imp.util.handler.FieldValidationHelper.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TaskGenerateRequest {

    @ApiModelProperty(value = "Goal for genrate task ",required = true)
    @NotBlank(message = ErrorCode.GOAL_EMPTY, groups = {Generate.class})
    private String Goal;

    @ApiModelProperty(value = "Step for  genrate task ", required = true)
    @NotBlank(message = ErrorCode.STEP_EMPTY, groups = {Generate.class})
    private String Step;

}
