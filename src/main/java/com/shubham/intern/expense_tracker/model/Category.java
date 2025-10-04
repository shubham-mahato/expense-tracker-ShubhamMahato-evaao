package com.shubham.intern.expense_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount Cannot be null")
    @Positive(message = "Amount must be Positive")
    @Column(nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Data cannot be null")
    @Column(nullable = false)
    @PastOrPresent(message = "Data cannot be in future")
    private String data;

    @Size(max=255 , message = "Note cannot be longer than 255 Characters" )
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
