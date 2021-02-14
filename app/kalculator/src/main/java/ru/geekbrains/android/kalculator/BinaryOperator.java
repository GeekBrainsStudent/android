package ru.geekbrains.android.kalculator;

import java.math.MathContext;

public interface BinaryOperator<T> {
    T apply(T t1, T t2, MathContext mc);
}

