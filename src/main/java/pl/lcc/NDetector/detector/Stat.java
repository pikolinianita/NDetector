package pl.lcc.NDetector.detector;

import java.util.function.Function;

public enum Stat {
    FETCH(StatsSnapshot::loaded);

    private final Function<StatsSnapshot,Long> extract;

    Stat(Function<StatsSnapshot,Long> extract) {
        this.extract = extract;
    }

    public long extract(StatsSnapshot snap){
        return extract.apply(snap);
    }
}
