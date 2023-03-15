package com.geekster.expensetracker.dao;

import com.geekster.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense,Integer> {

    @Query(value = "select * from tbl_expense where user_id= :userId and status_id=1 order by create_date",nativeQuery = true)
    List<Expense> findExpenseByUserId(int userId);

    @Query(value = "select SUM(price) from tbl_expense where user_id= :userId and status_id=1",nativeQuery = true)
    long getAllExpenditure(int userId);

    @Modifying
    @Transactional
    @Query(value = "update tbl_expense set status_id=2 where expense_id= :expenseId",
            countQuery="select count(*) from tbl_user",nativeQuery = true)
    void deleteByPostId(int expenseId);


}
