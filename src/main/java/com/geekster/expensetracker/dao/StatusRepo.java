package com.geekster.expensetracker.dao;

import com.geekster.expensetracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status,Integer> {
}
