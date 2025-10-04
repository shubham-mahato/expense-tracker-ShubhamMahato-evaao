package com.shubham.intern.expense_tracker.repository;

import com.shubham.intern.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory_Name(String categoryName);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
