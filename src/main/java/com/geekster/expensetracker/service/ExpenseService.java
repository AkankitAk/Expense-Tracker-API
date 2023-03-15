package com.geekster.expensetracker.service;

import com.geekster.expensetracker.dao.ExpenseRepo;
import com.geekster.expensetracker.model.Expense;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepo expenseRepo;
    public void addNewExpense(Expense expense) {
        expenseRepo.save(expense);
    }

    public JSONArray getAll(int userId) {
        JSONArray expenseArr=new JSONArray();
        List<Expense> expenseList=expenseRepo.findExpenseByUserId(userId);
        if(expenseList.isEmpty()){
            JSONObject object=new JSONObject();
            object.put("errorMessage","No expense");
            expenseArr.put(object);
            return expenseArr;
        }
        for (Expense expense:expenseList) {
            JSONObject obj=setExpense(expense);
            expenseArr.put(obj);
        }
        return expenseArr;
    }

    private JSONObject setExpense(Expense expense) {
        JSONObject masterJson=new JSONObject();
        JSONObject json=new JSONObject();
        json.put("title",expense.getTitle());
        json.put("description",expense.getDescription());
        json.put("price",expense.getPrice());
        json.put("date",expense.getDate());
        masterJson.put("expenseDetails",json);
        return masterJson;
    }

    public long getTotalExpenditure(int userId) {
        return expenseRepo.getAllExpenditure(userId);
    }

    public void updateExpense(int expenseId,Expense updateExpense) {
        Expense oldExpense=expenseRepo.findById(expenseId).get();
        updateExpense.setExpenseId(oldExpense.getExpenseId());

        updateExpense.setDate(oldExpense.getDate());

        Timestamp updateDate=new Timestamp(System.currentTimeMillis());
        updateExpense.setUpdateDate(updateDate);
        expenseRepo.save(updateExpense);
    }

    public void deletePostById(String expenseId) {
        expenseRepo.deleteByPostId(Integer.parseInt(expenseId));
    }

    public long getMonthlyExpenditure(String userId, int monthNumber) {
        long sum=0;
        List<Expense> expenseList= expenseRepo.findExpenseByUserId(Integer.parseInt(userId));
        for (Expense expense:expenseList){
            Timestamp date=expense.getDate();
            int month=date.getMonth()+1;
            if(month==monthNumber){
                int price= Integer.parseInt(expense.getPrice());
                sum+=price;
            }
        }
        return sum;
    }
}















