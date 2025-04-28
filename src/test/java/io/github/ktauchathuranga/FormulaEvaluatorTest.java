package io.github.ktauchathuranga;

import io.github.ktauchathuranga.exception.FormulaEvaluationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormulaEvaluatorTest {

    private FormulaEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new FormulaEvaluator();
    }

    @Test
    void testSimpleExpression() {
        assertEquals(6.0, evaluator.evaluate("2 + 4"), 0.0001);
    }

    @Test
    void testComplexExpression() {
        assertEquals(6.0, evaluator.evaluate("((10 + 2) * (5 - 4)) / 2"), 0.0001);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(FormulaEvaluationException.class, () -> evaluator.evaluate("10 / 0"));
    }

    @Test
    void testInvalidExpression() {
        assertThrows(FormulaEvaluationException.class, () -> evaluator.evaluate("10 + + 5"));
    }

    @Test
    void testMismatchedParentheses() {
        assertThrows(FormulaEvaluationException.class, () -> evaluator.evaluate("(10 + 5"));
    }

    @Test
    void testEmptyExpression() {
        assertThrows(FormulaEvaluationException.class, () -> evaluator.evaluate(""));
    }

    @Test
    void testNullExpression() {
        assertThrows(FormulaEvaluationException.class, () -> evaluator.evaluate(null));
    }
}