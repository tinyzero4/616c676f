package com.breedish.loadbalancer;

import java.util.Collections;
import java.util.List;

public class LoadBalancer {

    private volatile List<Server> servers;
    private final Rule rule;


    public LoadBalancer(List<Server> servers, Rule rule) {
        setServers(servers);
        this.rule = rule;
    }

    public Server next() {
        return rule.next(this);
    }

    public void setServers(List<Server> servers) {
        this.servers = List.copyOf(servers);
    }

    public List<Server> getServers() {
        return Collections.unmodifiableList(servers);
    }

    public List<Server> getLiveServers() {
        return getServers();
    }

}
