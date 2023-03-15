package com.geekster.expensetracker.controller;

import com.geekster.expensetracker.model.Status;
import com.geekster.expensetracker.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/status")
public class StatusController {
    @Autowired
    StatusService statusService;
    @PostMapping(value = "/create")
    public ResponseEntity<String> createStatus(@RequestBody String newStatus){
        Status statusObj=requestStatus(newStatus);
        int statusId=statusService.saveStatus(statusObj);
        return new ResponseEntity<>("Status created status id- "+statusId, HttpStatus.CREATED);
    }

    private Status requestStatus(String newStatus) {
        JSONObject jsonObject=new JSONObject(newStatus);
        Status obj=new Status();
        obj.setStatusType(jsonObject.getString("statusType"));
        obj.setStatusDescription(jsonObject.getString("statusDescription"));
        return obj;
    }
}
