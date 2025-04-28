package io.github.ktauchathuranga;

import io.github.ktauchathuranga.exception.FormulaEvaluationException;

/**
 * The main class for evaluating mathematical expressions provided as strings.
 * Supports basic arithmetic operations (+, -, *, /) and parentheses.
 *
 * <p>Example usage:
 * <pre>
 *     FormulaEvaluator evaluator = new FormulaEvaluator();
 *     double result = evaluator.evaluate("((10 + 2) * (5 - 4)) / 2");
 *     System.out.println(result); // Outputs: 6.0
 * </pre>
 *
 * @author Kasun Tharuranga
 */
public class FormulaEvaluator {

    private final ExpressionParser parser;

    /**
     * Constructs a new FormulaEvaluator instance.
     */
    public FormulaEvaluator() {
        this.parser = new ExpressionParser();
    }

    /**
     * Evaluates the given mathematical expression and returns the result.
     *
     * @param expression the mathematical expression as a string (e.g., "((10 + 2) * (5 - 4)) / 2")
     * @return the calculated result as a double
     * @throws FormulaEvaluationException if the expression is invalid or cannot be evaluated
     */
    public double evaluate(String expression) throws FormulaEvaluationException {
        if (expression == null || expression.trim().isEmpty()) {
            throw new FormulaEvaluationException("Expression cannot be null or empty");
        }
        return parser.evaluate(expression);
    }
}