package ru.geekbrains.android.kalculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.math.BigDecimal;

public class Calculator implements OnClickHandler, Parcelable {

    private BigDecimal operand1;            // поле соджержит первый операнд
    private BigDecimal operand2;            // второй операнд
    private BigDecimal memory;              // содержит операнд в памяти
    private Operation operation;            // какая операция была нажата (если была нажата)

    public void setOperand1(BigDecimal operand1) { this.operand1 = operand1; }
    public void setOperand2(BigDecimal operand2) { this.operand2 = operand2; }
    public void setMemory(BigDecimal memory) { this.memory = memory; }
    public void setOperation(Operation operation) { this.operation = operation; }

//    Реализация методов интерфейса OnClickHandler
//    с заглушками
    @Override
    public String pushMemRead() {
        return "memRead";
    }

    @Override
    public void pushMemClear(String displayText) {

    }

    @Override
    public String pushBackspace(String displayText) {
        return "backspace";
    }

    @Override
    public String pushClear() {
        return "clear";
    }

    @Override
    public void pushMemPositive(String displayText) {

    }

    @Override
    public void pushMemNegative(String displayText) {

    }

    @Override
    public String pushRadic(String displayText) {
        return "radic";
    }

    @Override
    public String pushPercent(String displayText) {
        return "percent";
    }

    @Override
    public String pushNumber(String displayText, String buttonText) {
        return buttonText;
    }

    @Override
    public String pushOperation(String displayText, String buttonText) {
        return buttonText;
    }

    @Override
    public String pushCalc(String displayText) {
        return "Calc";
    }

//    Реализация методов Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(operand1);
        dest.writeSerializable(operand2);
        dest.writeSerializable(memory);
        dest.writeSerializable(operation);
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {

        @Override
        public Calculator createFromParcel(Parcel source) {
            Calculator calc = new Calculator();
            calc.setOperand1((BigDecimal) source.readSerializable());
            calc.setOperand2((BigDecimal) source.readSerializable());
            calc.setMemory((BigDecimal) source.readSerializable());
            calc.setOperation((Operation) source.readSerializable());
            return calc;
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };
}
