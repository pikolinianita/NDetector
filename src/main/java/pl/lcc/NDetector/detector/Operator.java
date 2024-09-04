package pl.lcc.NDetector.detector;

import java.util.function.BiPredicate;

//This enum is used to represent the different operators that can be used in assertions.
// function evaluate() is used to evaluate the assertion.
public enum Operator {
    EQUAL(Long::equals);

    private final BiPredicate<Long,Long> operation;
    Operator(BiPredicate<Long,Long> operation){
        this.operation = operation;
    }

    boolean evaluate(long first, long second){
        return operation.test(first, second);
    }
}
