# Text to Formula

![Maven Central](https://img.shields.io/maven-central/v/io.github.ktauchathuranga/text-to-formula?color=blue)
![GitHub License](https://img.shields.io/github/license/ktauchathuranga/text-to-formula?color=green)
![Java Version](https://img.shields.io/badge/Java-22-blue)

**Text to Formula** is a lightweight and robust Java library for evaluating mathematical expressions provided as strings. It supports basic arithmetic operations (`+`, `-`, `*`, `/`), parentheses for grouping, and decimal numbers, making it ideal for applications requiring dynamic mathematical computations.

## Features

- **Arithmetic Operations**: Supports addition (`+`), subtraction (`-`), multiplication (`*`), and division (`/`).
- **Parentheses**: Handles nested expressions with proper precedence (e.g., `((10 + 2) * (5 - 4)) / 2`).
- **Decimal Support**: Evaluates expressions with decimal numbers (e.g., `2.5 + 3.7`).
- **Robust Error Handling**: Throws meaningful exceptions for invalid expressions, division by zero, and mismatched parentheses.
- **Whitespace Tolerance**: Ignores spaces in expressions (e.g., `2 + 4` and `2+4` are equivalent).
- **Lightweight**: No external dependencies, ensuring easy integration.

## Installation

Add the `text-to-formula` library to your project using your preferred build tool. The library is available on [Maven Central](https://search.maven.org/artifact/io.github.ktauchathuranga/text-to-formula).

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.ktauchathuranga</groupId>
    <artifactId>text-to-formula</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle (Groovy DSL)

Add the following to your `build.gradle`:

```gradle
dependencies {
    implementation 'io.github.ktauchathuranga:text-to-formula:1.0.0'
}
```

### Gradle (Kotlin DSL)

Add the following to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.ktauchathuranga:text-to-formula:1.0.0")
}
```

### SBT

Add the following to your `build.sbt`:

```scala
libraryDependencies += "io.github.ktauchathuranga" % "text-to-formula" % "1.0.0"
```

### Leiningen

Add the following to your `project.clj`:

```clojure
:dependencies [[io.github.ktauchathuranga/text-to-formula "1.0.0"]]
```

## Usage

The library provides a simple API through the `FormulaEvaluator` class. Below is an example demonstrating how to evaluate mathematical expressions.

### Example Code

```java
import io.github.ktauchathuranga.FormulaEvaluator;
import io.github.ktauchathuranga.exception.FormulaEvaluationException;

public class Main {
    public static void main(String[] args) {
        FormulaEvaluator evaluator = new FormulaEvaluator();

        try {
            // Simple expression
            double result1 = evaluator.evaluate("2 + 4");
            System.out.println("2 + 4 = " + result1); // Outputs: 2 + 4 = 6.0

            // Complex expression with parentheses
            double result2 = evaluator.evaluate("((10 + 2) * (5 - 4)) / 2");
            System.out.println("((10 + 2) * (5 - 4)) / 2 = " + result2); // Outputs: ((10 + 2) * (5 - 4)) / 2 = 6.0

            // Expression with decimals
            double result3 = evaluator.evaluate("2.5 + 3.7");
            System.out.println("2.5 + 3.7 = " + result3); // Outputs: 2.5 + 3.7 = 6.2
        } catch (FormulaEvaluationException e) {
            System.err.println("Error evaluating expression: " + e.getMessage());
        }
    }
}
```

### Key API Details

- **Class**: `io.github.ktauchathuranga.FormulaEvaluator`
- **Method**: `double evaluate(String expression)`
    - Takes a string representing a mathematical expression.
    - Returns the computed result as a `double`.
    - Throws `FormulaEvaluationException` for invalid inputs.
- **Exception**: `io.github.ktauchathuranga.exception.FormulaEvaluationException`
    - Handles errors like invalid characters, division by zero, or mismatched parentheses.

## Requirements

- **Java Version**: Java 22 or later (the library is compiled with Java 22).
- **No External Dependencies**: The library is self-contained.

If you need a version compatible with an earlier Java version (e.g., Java 21 or 17), please open an issue on the [GitHub repository](https://github.com/ktauchathuranga/text-to-formula).

## Javadoc

The full API documentation is available in the Javadoc, included in the [Maven Central release](https://repo1.maven.org/maven2/io/github/ktauchathuranga/text-to-formula/1.0.0/text-to-formula-1.0.0-javadoc.jar). You can also generate it locally by running:

```bash
mvn javadoc:javadoc
```

## Building from Source

To build the library from source:

1. Clone the repository:
   ```bash
   git clone https://github.com/ktauchathuranga/text-to-formula.git
   cd text-to-formula
   ```

2. Build with Maven:
   ```bash
   mvn clean package
   ```

3. Run tests:
   ```bash
   mvn test
   ```

The compiled JAR will be available in the `target` directory.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Make your changes and commit them with clear messages.
4. Push to your fork and submit a pull request.

### Reporting Issues

If you encounter bugs or have feature requests, please open an issue on the [GitHub Issues page](https://github.com/ktauchathuranga/text-to-formula/issues). Provide:
- A clear description of the issue.
- Steps to reproduce (if applicable).
- Expected and actual behavior.
- Any relevant logs or screenshots.

## License

This project is licensed under the [MIT License](LICENSE). See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with the [Shunting Yard algorithm](https://en.wikipedia.org/wiki/Shunting_yard_algorithm) for robust expression parsing.
- Thanks to the open-source community for inspiration and feedback.

## Contact

For questions or support, contact the maintainer:
- **Ashen Chathuranga** (ktauchathuranga@gmail.com)
- GitHub: [ktauchathuranga](https://github.com/ktauchathuranga)
