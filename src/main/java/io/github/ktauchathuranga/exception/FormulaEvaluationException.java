package io.github.ktauchathuranga.exception;

/**
 * Exception thrown when an error occurs during formula evaluation.
 */
public class FormulaEvaluationException extends RuntimeException {

    /**
     * Constructs a new FormulaEvaluationException with the specified message.
     *
     * @param message the detail message
     */
    public FormulaEvaluationException(String message) {
        super(message);
    }

    /**
     * Constructs a new FormulaEvaluationException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public FormulaEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }
}