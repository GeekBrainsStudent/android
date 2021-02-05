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
    private EditText display;               // дисплей калькулятора

    public void setDisplay(EditText display) {
        this.display = display;
    }

    public BigDecimal getOperand1() {
        return operand1;
    }

    public void setOperand1(BigDecimal operand1) {
        this.operand1 = operand1;
    }

    public BigDecimal getOperand2() {
        return operand2;
    }

    public void setOperand2(BigDecimal operand2) {
        this.operand2 = operand2;
    }

    public BigDecimal getMemory() {
        return memory;
    }

    public void setMemory(BigDecimal memory) {
        this.memory = memory;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

//    Реализация методов интерфейса OnClickHandler
//    как я понял в рамках данного задания не надо реализовывать
    @Override
    public void pushMemRead() {

    }

    @Override
    public void pushMemClear() {

    }

    @Override
    public void pushBackspace() {

    }

    @Override
    public void pushClear() {

    }

    @Override
    public void pushMemPositive() {

    }

    @Override
    public void pushMemNegative() {

    }

    @Override
    public void pushRadic() {

    }

    @Override
    public void pushPercent() {

    }

    @Override
    public void pushNumber(String number) {

    }

    @Override
    public void pushOperation(String operation) {

    }

    @Override
    public void pushCalc() {

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
