package com.breedish.loadbalancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobbinRule implements Rule {

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public Server next(LoadBalancer lb) {
        List<Server> liveServers = lb.getLiveServers();
        if (liveServers == null || liveServers.isEmpty()) return null;

        int selected = counter.incrementAndGet() % liveServers.size();
        return liveServers.get(selected);
    }
}
