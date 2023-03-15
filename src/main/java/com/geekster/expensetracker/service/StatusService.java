package com.geekster.expensetracker.service;

import com.geekster.expensetracker.dao.StatusRepo;
import com.geekster.expensetracker.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;

    public int saveStatus(Status statusObj) {
        Status status=statusRepo.save(statusObj);
        return status.getStatusId();
    }
}
