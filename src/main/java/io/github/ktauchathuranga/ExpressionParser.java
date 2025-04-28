package io.github.ktauchathuranga;

import io.github.ktauchathuranga.exception.FormulaEvaluationException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Internal class for parsing and evaluating mathematical expressions.
 * Uses the Shunting Yard algorithm for parsing and a stack-based approach for evaluation.
 */
class ExpressionParser {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("\\d+\\.?\\d*|[-+*/()]");
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();

    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
    }

    /**
     * Evaluates the given expression and returns the result.
     *
     * @param expression the mathematical expression
     * @return the evaluated result
     * @throws FormulaEvaluationException if the expression is invalid
     */
    double evaluate(String expression) throws FormulaEvaluationException {
        List<String> tokens = tokenize(expression);
        List<String> rpn = toReversePolishNotation(tokens);
        return evaluateRPN(rpn);
    }

    /**
     * Tokenizes the input expression into numbers, operators, and parentheses.
     */
    private List<String> tokenize(String expression) throws FormulaEvaluationException {
        List<String> tokens = new ArrayList<>();
        // Remove all whitespace
        String cleanedExpression = expression.replaceAll("\\s+", "");
        Matcher matcher = TOKEN_PATTERN.matcher(cleanedExpression);

        int lastEnd = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Check for invalid characters between tokens
            if (start > lastEnd) {
                throw new FormulaEvaluationException("Invalid characters in expression: " + expression);
            }

            tokens.add(matcher.group());
            lastEnd = end;
        }

        // Check for invalid characters after the last token
        if (lastEnd < cleanedExpression.length()) {
            throw new FormulaEvaluationException("Invalid characters in expression: " + expression);
        }

        return tokens;
    }

    /**
     * Converts tokens to Reverse Polish Notation (RPN) using the Shunting Yard algorithm.
     */
    private List<String> toReversePolishNotation(List<String> tokens) throws FormulaEvaluationException {
        List<String> output = new ArrayList<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") &&
                        PRECEDENCE.getOrDefault(operatorStack.peek(), 0) >= PRECEDENCE.get(token)) {
                    output.add(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    output.add(operatorStack.pop());
                }
                if (operatorStack.isEmpty()) {
                    throw new FormulaEvaluationException("Mismatched parentheses");
                }
                operatorStack.pop(); // Remove '('
            }
        }

        while (!operatorStack.isEmpty()) {
            String op = operatorStack.pop();
            if (op.equals("(")) {
                throw new FormulaEvaluationException("Mismatched parentheses");
            }
            output.add(op);
        }

        return output;
    }

    /**
     * Evaluates the RPN expression using a stack.
     */
    private double evaluateRPN(List<String> rpn) throws FormulaEvaluationException {
        Deque<Double> stack = new ArrayDeque<>();

        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new FormulaEvaluationException("Invalid expression: insufficient operands");
                }
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token));
            }
        }

        if (stack.size() != 1) {
            throw new FormulaEvaluationException("Invalid expression: too many operands");
        }

        return stack.pop();
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return PRECEDENCE.containsKey(token);
    }

    private double applyOperator(double a, double b, String operator) throws FormulaEvaluationException {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new FormulaEvaluationException("Division by zero");
                }
                return a / b;
            default:
                throw new FormulaEvaluationException("Unknown operator: " + operator);
        }
    }
}