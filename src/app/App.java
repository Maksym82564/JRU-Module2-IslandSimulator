package app;

import models.map.IslandMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public void runApp() {
        IslandMap islandMap = new IslandMap(10,10);

        Wildlife wildlife = new Wildlife(islandMap);
        wildlife.createWildlife(40, 15, 30);

        Statistic statistic = new Statistic(islandMap, wildlife);
        ScheduledExecutorService statisticExecutor = Executors.newScheduledThreadPool(1);
        statisticExecutor.scheduleAtFixedRate(statistic, 0, 1000, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        wildlife.endWildlife();
        statisticExecutor.shutdown();
    }
}
