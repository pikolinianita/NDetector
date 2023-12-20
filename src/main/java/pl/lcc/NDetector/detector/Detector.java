package pl.lcc.NDetector.detector;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.HashMap;
import java.util.Map;

public class Detector implements NDetector{

    Statistics stats;

    Map<String, StatsSnapshot> timePoints;

    public Detector(Statistics stats, String init) {
        this.stats = stats;
        if (!stats.isStatisticsEnabled()) {
            stats.setStatisticsEnabled(true);
        }
        timePoints = new HashMap<>();
        timePoints.put(init, new StatsSnapshot(stats));
    }

    public Detector(SessionFactory factory, String init) {
        this(factory.getStatistics(), init);
    }

    public String evaluate() {
        return "huh";
    }

    public HalfAssertion command(Stat stat) {

        return new HalfAssertion(stat);
    }

    public class HalfAssertion {

        public HalfAssertion(Stat stat) {

        }

        public Detector mustBe(Operator op, int n) {
            return Detector.this;
        }

        //TODO
        public HalfAssertion from(String snap){
            throw new RuntimeException("half from");
        }

        //TODO
        public HalfAssertion between(String snap, String snap2){
            throw new RuntimeException("half between");
        }
    }

    public record Assertion(StatsSnapshot initTime, StatsSnapshot otherTime, Stat command, Operator op, int value){
        public String evaluate(){
            long difference = command.extract(otherTime) - command.extract(initTime);
            boolean result = op.evaluate(difference, value);
            if (result){
                return "";
            } else{
                return "Assertion Error: " + command + " " + op
                        + " expected: " + String.valueOf(value)
                        + " actual: " + String.valueOf(difference);
            }

        }

    }

}
