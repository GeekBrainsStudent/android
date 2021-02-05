package ru.geekbrains.android.kalculator;

public interface OnClickHandler {

    /* Интерфейс связывает события макета с классом бизнес-логики. */

//    нажат MR
    void pushMemRead();

//    нажат MC
    void pushMemClear();

//    Нажат Backspace
    void pushBackspace();

//    нажат С
    void pushClear();

//    нажат М+
    void pushMemPositive();

//    нажат М-
    void pushMemNegative();

//    нажат корень
    void pushRadic();

//    нажат %
    void pushPercent();

//    нажато число (1,2,3,4,5,6,7,8,9,0,.)
    void pushNumber(String number);

//    нажата операция (+, -, /, *)
    void pushOperation(String operation);

//    нажат =
    void pushCalc();
}
