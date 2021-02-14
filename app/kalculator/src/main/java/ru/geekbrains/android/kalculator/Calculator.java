package ru.geekbrains.android.kalculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator implements OnClickHandler, Parcelable {

    private final int PRECISION = 16;       // точность

    private BigDecimal operand1;            // поле соджержит первый операнд
    private BigDecimal operand2;            // второй операнд
    private BigDecimal memory;              // содержит операнд в памяти
    private Operation operation;            // какая операция была нажата (если была нажата)
    private final MathContext mc;           // объект для применения точности
    private boolean newNumber;              // флаг печати нового числа на дисплее

    public void setOperand1(BigDecimal operand1) { this.operand1 = operand1; }
    public void setOperand2(BigDecimal operand2) { this.operand2 = operand2; }
    public void setMemory(BigDecimal memory) { this.memory = memory; }
    public void setOperation(Operation operation) { this.operation = operation; }

    public Calculator() {
        mc = new MathContext(PRECISION, RoundingMode.HALF_UP);
    }

    //    Реализация методов интерфейса OnClickHandler

    @Override
    public String pushMemRead() {
        return memory.toString();
    }

    @Override
    public void pushMemClear(String displayText) {
        memory = null;
    }

    @Override
    public String pushBackspace(String displayText) {
        return (displayText.length() > 1) ?
            displayText.substring(0,displayText.length() - 1) : "0";
    }

    @Override
    public String pushClear() {
        operand1 = null;
        operand2 = null;
        operation = null;
        newNumber = true;
        return "0";
    }

    @Override
    public void pushMemPositive(String displayText) {
        memory = new BigDecimal(displayText, mc).plus();
    }

    @Override
    public void pushMemNegative(String displayText) {
        memory = new BigDecimal(displayText, mc).negate();
    }

    @Override
    public String pushRadic(String displayText) {
        double d = Double.parseDouble(displayText);
        return BigDecimal.valueOf(Math.sqrt(d)).toString();
    }

    @Override
    public String pushPercent(String displayText) {
        String res = displayText;
        if(operation == Operation.MULTIPLY && operand1 != null) {
            operand1 = (operand1.multiply(new BigDecimal(displayText, mc), mc)).divide(new BigDecimal(100, mc), mc);
            res = operand1.toString();
            operation = null;
        }
        return res;
    }

    @Override
    public String pushNumber(String displayText, String buttonText) {
        if(displayText.equals("0") || newNumber) {
            switch(buttonText) {
                case "0":   return "0";
                case ".":   newNumber = false; return "0.";
                default:    newNumber = false; return buttonText;
            }
        } else {
            if(buttonText.equals(".") && displayText.contains("."))
                return displayText;
            return displayText + buttonText;
        }
    }

    @Override
    public String pushOperation(String displayText, String buttonText) {
        newNumber = true;
        operand1 = new BigDecimal(displayText, mc);
        operand2 = null;
        switch(buttonText) {
            case "\u00f7":  operation = Operation.DIVIDE; break;
            case "\u00d7":  operation = Operation.MULTIPLY; break;
            case "\u2212":  operation = Operation.SUBTRACT; break;
            case "+":       operation = Operation.SUM; break;
        }
        return displayText;
    }

    @Override
    public String pushCalc(String displayText) {
        String res = displayText;
        if(operation != null && operand1 != null) {
            operand2 = (operand2 == null) ? new BigDecimal(displayText, mc) : operand2;
            operand1 = operation.calc(operand1, operand2, mc);
            res = operand1.toString();
        }
        return res;
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
