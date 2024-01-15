package com.breedish.loadbalancer;

public class LeastConnectionsRule implements Rule {

    @Override
    public Server next(LoadBalancer lb) {
        return null;
    }
}
