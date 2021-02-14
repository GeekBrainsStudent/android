package ru.geekbrains.android.kalculator;

public interface OnClickHandler {

    /* Интерфейс связывает события макета с классом бизнес-логики. */

//    нажат MR
    String pushMemRead();

//    нажат MC
    void pushMemClear(String displayText);

//    Нажат Backspace
    String pushBackspace(String displayText);

//    нажат С
    String pushClear();

//    нажат М+
    void pushMemPositive(String displayText);

//    нажат М-
    void pushMemNegative(String displayText);

//    нажат корень
    String pushRadic(String displayText);

//    нажат %
    String pushPercent(String displayText);

//    нажато число (1,2,3,4,5,6,7,8,9,0,.)
    String pushNumber(String displayText, String buttonText);

//    нажата операция (+, -, /, *)
    String pushOperation(String displayText, String buttonText);

//    нажат =
    String pushCalc(String displayText);
}
