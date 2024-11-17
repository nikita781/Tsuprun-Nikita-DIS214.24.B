package test;

import main.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    // Тест сложения целых чисел
    @Test
    public void testAddIntegers() {
        Calculator calculator = new Calculator();
        assertEquals(5.0, calculator.add(2, 3), "2 + 3 должно быть 5");
    }

    // Тест вычитания целых чисел
    @Test
    public void testSubtractIntegers() {
        Calculator calculator = new Calculator();
        assertEquals(1.0, calculator.subtract(3, 2), "3 - 2 должно быть 1");
    }

    // Тест сложения дробных чисел
    @Test
    public void testAddDoubles() {
        Calculator calculator = new Calculator();
        assertEquals(5.5, calculator.add(2.2, 3.3), "2.2 + 3.3 должно быть 5.5");
    }

    // Тест вычитания дробных чисел
    @Test
    public void testSubtractDoubles() {
        Calculator calculator = new Calculator();
        assertEquals(0.7, calculator.subtract(3.0, 2.3), 0.001, "3.0 - 2.3 должно быть примерно 0.7");
    }

    // Тест на пустое значение
    @Test
    public void testAddWithNull() {
        Calculator calculator = new Calculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add(null, 3);
        });
        assertEquals("Одно из значений является пустым", exception.getMessage());
    }

    // Тест на смешанные значения: целое и дробное
    @Test
    public void testAddMixedTypes() {
        Calculator calculator = new Calculator();
        assertEquals(5.5, calculator.add(2, 3.5), "2 + 3.5 должно быть 5.5");
    }

    // Тест на вычитание с целыми и дробными числами
    @Test
    public void testSubtractMixedTypes() {
        Calculator calculator = new Calculator();
        assertEquals(1.5, calculator.subtract(5, 3.5), "5 - 3.5 должно быть 1.5");
    }

    // Тест умножения целых чисел
    @Test
    public void testMultiplyIntegers() {
        Calculator calculator = new Calculator();
        assertEquals(6.0, calculator.multiply(2, 3), "2 * 3 должно быть 6");
    }

    // Тест умножения дробных чисел
    @Test
    public void testMultiplyDoubles() {
        Calculator calculator = new Calculator();
        assertEquals(7.26, calculator.multiply(2.2, 3.3), 0.001, "2.2 * 3.3 должно быть 7.26");
    }

    // Тест деления целых чисел
    @Test
    public void testDivideIntegers() {
        Calculator calculator = new Calculator();
        assertEquals(2.0, calculator.divide(6, 3), "6 / 3 должно быть 2");
    }

    // Тест деления дробных чисел
    @Test
    public void testDivideDoubles() {
        Calculator calculator = new Calculator();
        assertEquals(2.0, calculator.divide(6.6, 3.3), 0.001, "6.6 / 3.3 должно быть 2.0");
    }

    // Тест на деление на ноль
    @Test
    public void testDivideByZero() {
        Calculator calculator = new Calculator();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(5, 0);
        });
        assertEquals("Деление на ноль", exception.getMessage());
    }

    // Тест на пустое значение при делении
    @Test
    public void testDivideWithNull() {
        Calculator calculator = new Calculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(null, 3);
        });
        assertEquals("Одно из значений является пустым", exception.getMessage());
    }
}