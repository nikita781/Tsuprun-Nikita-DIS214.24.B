package main;

public class Calculator {
    // Метод для сложения двух чисел (принимает целые и дробные значения)
    public double add(Number a, Number b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Одно из значений является пустым");
        }
        return a.doubleValue() + b.doubleValue();
    }

    // Метод для вычитания двух чисел (принимает целые и дробные значения)
    public double subtract(Number a, Number b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Одно из значений является пустым");
        }
        return a.doubleValue() - b.doubleValue();
    }

    // Метод для умножения двух чисел (принимает целые и дробные значения)
    public double multiply(Number a, Number b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Одно из значений является пустым");
        }
        return a.doubleValue() * b.doubleValue();
    }

    // Метод для деления двух чисел (принимает целые и дробные значения)
    public double divide(Number a, Number b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Одно из значений является пустым");
        }
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return a.doubleValue() / b.doubleValue();
    }
}
