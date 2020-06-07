package com.vmware.num_gen.imp.service;

import com.vmware.num_gen.imp.dto.TaskGenerateRequest;

public interface NumGenService {

    String createTask(TaskGenerateRequest taskGenerateRequest);

    String status(String uuid);

    String action(String action);

}
