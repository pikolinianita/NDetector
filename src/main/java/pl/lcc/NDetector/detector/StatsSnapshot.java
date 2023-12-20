package pl.lcc.NDetector.detector;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.HashMap;
import java.util.Map;

public record StatsSnapshot(long loaded, long deleted, long updated, long inserted, long fetched,
                     long loadedColl, long removedColl, long updatedColl, long recreatedColl, long fetchedColl,
                     long transaction, long transactionSuccess) {

    public StatsSnapshot(Statistics stats) {
        this(stats.getEntityLoadCount(),
                stats.getEntityDeleteCount(),
                stats.getEntityUpdateCount(),
                stats.getEntityInsertCount(),
                stats.getEntityFetchCount(),
                stats.getCollectionLoadCount(),
                stats.getCollectionRemoveCount(),
                stats.getCollectionUpdateCount(),
                stats.getCollectionRecreateCount(),
                stats.getCollectionFetchCount(),
                stats.getTransactionCount(),
                stats.getSuccessfulTransactionCount()
        );
    }

}

