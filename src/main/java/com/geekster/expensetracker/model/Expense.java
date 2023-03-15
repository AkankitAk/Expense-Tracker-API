package com.geekster.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expense")
public class Expense {
    @Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;
    @Length(min = 3,max = 20,message = "title should contain characters between 3-20 ")
    @Column(name = "title")
    private String title;
    @Length(min = 3,max = 20,message = "Description should contain characters between 3-20 ")
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private String price;
    @Column(name = "create_date")
    private Timestamp date;
    @Column(name = "update_date")
    private Timestamp updateDate;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "status_id")
    @ManyToOne
    private Status statusId;

}
