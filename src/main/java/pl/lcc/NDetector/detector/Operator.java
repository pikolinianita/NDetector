package pl.lcc.NDetector.detector;

import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

public enum Operator {
    EQUAL(Long::equals);

    private BiPredicate<Long,Long> operation;
    Operator(BiPredicate<Long,Long> operation){
        this.operation = operation;
    }

    boolean evaluate(long first, long second){
        return operation.test(first, second);
    }
}
