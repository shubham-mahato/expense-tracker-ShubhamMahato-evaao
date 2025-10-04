package com.shubham.intern.expense_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount Cannot be null")
    @Positive(message = "Amount must be Positive")
    @Column(nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Date cannot be null")
    @Column(nullable = false)
    @PastOrPresent(message = "Date cannot be in future")
    private LocalDate date;

    @Size(max=255 , message = "Note cannot be longer than 255 Characters" )
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category cannot be null")
    private Category category;
}
