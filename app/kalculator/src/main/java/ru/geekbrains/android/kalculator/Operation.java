package ru.geekbrains.android.kalculator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

public enum Operation implements Serializable {
    SUM(BigDecimal::add),                   // Операция суммирования
    SUBTRACT(BigDecimal::subtract),         // Операция вычитания
    DIVIDE(BigDecimal::divide),             // Операция деления
    MULTIPLY(BigDecimal::multiply);         // Операция умножения

    Operation(BinaryOperator<BigDecimal> operator) {
        this.operator = operator;
    }

    private final BinaryOperator<BigDecimal> operator;

    public BigDecimal calc(BigDecimal oper1, BigDecimal oper2, MathContext mc) {
        return operator.apply(oper1, oper2, mc);
    }
}
