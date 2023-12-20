package pl.lcc.NDetector.detector;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.HashMap;
import java.util.Map;

public class Detector {

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

    public String evaluate(){
        return "huh";
    }

    public HalfAssertion amount(Stat stat){
        return new HalfAssertion(stat);
    }

   public class HalfAssertion{

            public HalfAssertion(Stat stat) {

    }
        public Detector mustBe(Operator op, int n){

            return Detector.this;
        }
    }

}
