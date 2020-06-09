package com.vmware.num_gen.imp.service.impl;

import com.vmware.num_gen.imp.dto.TaskGenerateRequest;
import com.vmware.num_gen.imp.service.NumGenService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NumGenServiceImpl implements NumGenService {

    Map<String, String> map = new ConcurrentHashMap();
    private static final String TMP = System.getProperty("java.io.tmpdir");

    @Override
    public String createTask(TaskGenerateRequest taskGenerateRequest) {

        int goal = Integer.valueOf(taskGenerateRequest.getGoal());
        int step = Integer.valueOf(taskGenerateRequest.getStep());

        String taskId = UUID.randomUUID().toString();
        try {

            String tmp = TMP + taskId + "_output.txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
            map.put(taskId, "IN_PROGRESS");
            while (goal >= 0) {
                bw.write(goal+"\n");
                goal = goal - step;
            }
            bw.close();
            map.put(taskId, "SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
            map.put(taskId, "ERROR");
        }
        return taskId;
    }

    @Override
    public String status(String uuid) {
        if (!map.containsKey(uuid)) {
            return "NOT_FOUND";
        }
        return map.get(uuid);
    }

    @Override
    public String action(String uuid, String action) {
        StringBuilder result = new StringBuilder();
        if (map.containsKey(uuid) && map.get(uuid).equals("SUCCESS") && action.equals("get_numlist")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(TMP + uuid + "_output.txt"));
                String st;
                while ((st = br.readLine()) != null) {
                    result.append(st);
                    result.append(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1): "";
    }
}
