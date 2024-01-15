package com.breedish.loadbalancer;

public interface Rule {

    Server next(LoadBalancer lb);

}
