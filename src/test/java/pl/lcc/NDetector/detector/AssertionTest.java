package pl.lcc.NDetector.detector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertionTest {
    @Test
    void should_do_with_fetch() {
        var stats1 = new StatsSnapshot(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        var stats2 = new StatsSnapshot(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        var assertion = new Detector.Assertion(stats1, stats2, Stat.FETCH, Operator.EQUAL, 1);
        String result = assertion.evaluate();
        System.out.println(result);
    }
}