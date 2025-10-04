package com.shubham.intern.expense_tracker.service;

import com.shubham.intern.expense_tracker.exception.ResourceNotFoundException;
import com.shubham.intern.expense_tracker.model.Category;
import com.shubham.intern.expense_tracker.model.Expense;
import com.shubham.intern.expense_tracker.repository.CategoryRepository;
import com.shubham.intern.expense_tracker.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public  ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Expense not Found with id: "+ id));
    }

    @Transactional
    public Expense addExpense(Expense expense) {
        Category category = categoryRepository.findByName(expense.getCategory().getName())
                .orElseGet(() -> categoryRepository.save(expense.getCategory()));
        expense.setCategory(category);
        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense existingExpense = getExpenseById(id);

        Category category = categoryRepository.findByName(expenseDetails.getCategory().getName())
                .orElseGet(() -> categoryRepository.save(expenseDetails.getCategory()));

        existingExpense.setAmount(expenseDetails.getAmount());
        existingExpense.setDate(expenseDetails.getDate());
        existingExpense.setNote(expenseDetails.getNote());
        existingExpense.setCategory(category);
        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id) {
        if(!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not Found with id: "+ id);
        }
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByCategory(String categoryName) {
        return expenseRepository.findByCategory_Name(categoryName);
    }

    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate);
    }
}
