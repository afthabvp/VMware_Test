package com.vmware.num_gen.imp.service.impl;

import com.vmware.num_gen.imp.dto.TaskGenerateRequest;
import com.vmware.num_gen.imp.exception.BusinessException;
import com.vmware.num_gen.imp.service.NumGenService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.vmware.num_gen.imp.util.ErrorCode.*;
import static com.vmware.num_gen.imp.util.FileStatus.*;
@Service
public class NumGenServiceImpl implements NumGenService {

    Map<String, String> statusMap = new ConcurrentHashMap();
    private static final String TMP = System.getProperty("java.io.tmpdir");
    private static final String SUFFIX_FILE = "_output.txt";
    private static final String ACTION = "get_numlist";

    @Override
    public String createTask(TaskGenerateRequest taskGenerateRequest) {
        int goal =0;
        int step = 0;
        try {
            goal = Integer.valueOf(taskGenerateRequest.getGoal());
            step = Integer.valueOf(taskGenerateRequest.getStep());
        }catch (Exception e){
            throw BusinessException.throwBusinessException(INVALID_INPUT);
        }
        String taskId = UUID.randomUUID().toString();
        try {

            String tmp = TMP + taskId + SUFFIX_FILE;
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
            statusMap.put(taskId, IN_PROGRESS.getValue());
            while (goal >= 0) {
                bw.write(goal+"\n");
                goal = goal - step;
            }
            bw.close();
            statusMap.put(taskId, SUCCESS.getValue());
        } catch (IOException e) {
            statusMap.put(taskId, ERROR.getValue());
            throw BusinessException.throwBusinessException(ERROR_WRITE_FILE);
        }
        return taskId;
    }

    @Override
    public String status(String uuid) {
        if(uuid == null || uuid.equals("") || !statusMap.containsKey(uuid)){
            throw BusinessException.throwBusinessException(INVALID_UUID);
        }
        return statusMap.get(uuid);
    }

    @Override
    public String action(String uuid, String action) {
        StringBuilder result = new StringBuilder();
        if(uuid == null || uuid.equals("")){
            throw BusinessException.throwBusinessException(INVALID_UUID);
        }
        if(action == null || action.equals("")){
            throw BusinessException.throwBusinessException(INVALID_ACTION);
        }
        if (statusMap.containsKey(uuid) && statusMap.get(uuid).equals(SUCCESS.getValue()) && action.equals(ACTION)) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(TMP + uuid + SUFFIX_FILE));
                String st;
                while ((st = br.readLine()) != null) {
                    result.append(st);
                    result.append(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw BusinessException.throwBusinessException(NO_RESEULT_FOUND);
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1): "";
    }
}
