package com.geekster.expensetracker.controller;

import com.geekster.expensetracker.dao.StatusRepo;
import com.geekster.expensetracker.dao.UserRepo;
import com.geekster.expensetracker.dto.ExpenseDto;
import com.geekster.expensetracker.model.Expense;
import com.geekster.expensetracker.model.Status;
import com.geekster.expensetracker.model.User;
import com.geekster.expensetracker.service.ExpenseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class ExpenseController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    StatusRepo statusRepo;
    @PostMapping(value = "/saveExpense")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseDto newExpense){
        JSONObject isValidate=isValidateExpense(newExpense);
        Expense expense=new Expense();
        if (isValidate.isEmpty()){
            expense=setExpense(newExpense);
            expenseService.addNewExpense(expense);
            return new ResponseEntity<>("Your Expense saved",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(isValidate.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private Expense setExpense(ExpenseDto newExpense) {
        JSONObject jsonObject=new JSONObject(newExpense);
        Expense expense=new Expense();
        expense.setTitle(jsonObject.getString("title"));
        expense.setDescription(jsonObject.getString("description"));
        expense.setPrice(jsonObject.getString("price"));

        Timestamp createDate=new Timestamp(System.currentTimeMillis());
        expense.setDate(createDate);

        String userId=jsonObject.getString("userId");
        User byUser=userRepo.findByUserId(Integer.parseInt(userId));
        expense.setUser(byUser);

        Status status=statusRepo.findById(1).get();
        expense.setStatusId(status);
        return expense;
    }

    private JSONObject isValidateExpense(ExpenseDto newExpense) {
        JSONObject json=new JSONObject(newExpense);
        JSONObject errorList=new JSONObject();
        if (!json.has("title")){
            errorList.put("title","Missing parameter");
        }
        if (!json.has("price")){
            errorList.put("price","Missing parameter");
        }
        if (!json.has("userId")){
            errorList.put("userId","Missing parameter");
        }
        return errorList;
    }

    @GetMapping(value = "/getExpense")
    public ResponseEntity<String> getAllExpense(@RequestParam String userId){
        JSONArray expenseList=expenseService.getAll(Integer.parseInt(userId));
        return new ResponseEntity<>(expenseList.toString(),HttpStatus.OK);
    }
    @GetMapping(value = "/getTotalExpenditure")
    public ResponseEntity<String> getTotalExpenditure(@RequestParam String userId){
        long totalExpenditure =expenseService.getTotalExpenditure(Integer.parseInt(userId));
        return new ResponseEntity<>("Total Monthly Expenditure:- "+totalExpenditure,HttpStatus.OK);
    }
    @GetMapping(value = "/getMonthlyExpenditure")
    public ResponseEntity<String> getMonthlyExpenditure(@RequestParam String userId,@RequestParam String monthNumber){
//        Month month = Month.of(2);
//        String monthName=month.toString();
        long totalExpenditure =expenseService.getMonthlyExpenditure(userId, Integer.parseInt(monthNumber));
        return new ResponseEntity<>("Total Monthly Expenditure:- "+totalExpenditure,HttpStatus.OK);
    }

    @PutMapping(value = "/updateExpense")
    public ResponseEntity<String> updateExpense(@RequestParam String expenseId,@RequestBody ExpenseDto updateExpense){
        JSONObject isValidate=isValidateExpense(updateExpense);
        Expense expense=new Expense();
        if (isValidate.isEmpty()){
            expense=setExpense(updateExpense);
            expenseService.updateExpense(Integer.parseInt(expenseId),expense);
            return new ResponseEntity<>("Your Expense updated",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(isValidate.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteExpense")
    public ResponseEntity<String> deleteExpense(@RequestParam String expenseId){
        expenseService.deletePostById(expenseId);
        return new ResponseEntity<>("delete Expense successfully ",HttpStatus.NO_CONTENT);
    }


}




















