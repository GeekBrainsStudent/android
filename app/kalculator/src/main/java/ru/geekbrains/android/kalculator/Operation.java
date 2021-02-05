package ru.geekbrains.android.kalculator;

import java.io.Serializable;

public enum Operation implements Serializable {
    SUM,            // Операция суммирования
    SUBTRACT,       // Операция вычитания
    DIVIDE,         // Операция деления
    MULTIPLY,       // Операция умножения
    PERCENT,        // Операция процент
    RADIC;          // Операция извлечения из кв. корня
}
