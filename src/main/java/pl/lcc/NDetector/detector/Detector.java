package pl.lcc.NDetector.detector;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Detector implements NDetector{

    //Hibernate object
    final private Statistics stats;

    final private Map<String, StatsSnapshot> timePoints;

    final private String initName;

    final private List<Assertion> assertions = new ArrayList<>();

    public Detector(Statistics stats, String init) {
        this.stats = stats;
        if (!stats.isStatisticsEnabled()) {
            stats.setStatisticsEnabled(true);
        }
        timePoints = new HashMap<>();
        timePoints.put(init, new StatsSnapshot(stats));
        initName = init;
    }

    public Detector(SessionFactory factory, String init) {
        this(factory.getStatistics(), init);
    }

    public String evaluate() {
        var result = "";
        for (Assertion assertion : assertions) {
            result += assertion.evaluate();
        }
        return result;
    }

    public AssertionBuilder command(Stat stat) {

        return new AssertionBuilder(stat);
    }

    public class AssertionBuilder {

        StatsSnapshot initTime;
        StatsSnapshot otherTime;
        final Stat SqlCommand;
        Operator op;
        int value;

        public AssertionBuilder(Stat stat) {
            this.SqlCommand = stat;
        }

        public Detector shouldBe(Operator op, int n) {
            this.op = op;
            this.value = n;
            if (initTime == null) {
                initTime = timePoints.get(initName);
            }
            if (otherTime == null) {
                otherTime = new StatsSnapshot(stats);
            }
            assertions.add(new Assertion(this));
            return Detector.this;
        }

        public AssertionBuilder from(String snapName){
            initTime = timePoints.get(snapName);
            return this;
        }

        public AssertionBuilder between(String snapStart, String snapEnd){
            initTime = timePoints.get(snapStart);
            otherTime = timePoints.get(snapEnd);
            return this;
        }
    }

    //This class is used to represent and check an assertion.
    public record Assertion(StatsSnapshot initTime, StatsSnapshot otherTime, Stat command, Operator op, int value){

        Assertion(AssertionBuilder builder) {
            this(builder.initTime, builder.otherTime, builder.SqlCommand, builder.op, builder.value);
        }

        public String evaluate(){
            long difference = command.extract(otherTime) - command.extract(initTime);
            boolean result = op.evaluate(difference, value);
            if (result){
                return "";
            } else{
                return "Assertion Error: " + command + " " + op
                        + " expected: " + value
                        + " actual: " + difference + System.lineSeparator();
            }

        }

    }

}
