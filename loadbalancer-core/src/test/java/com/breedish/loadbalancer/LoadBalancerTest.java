package com.breedish.loadbalancer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoadBalancerTest {

    @Test
    public void getServer() {
        List<Server> servers = List.of(new Server("h1", 8000));
        LoadBalancer lb = new LoadBalancer(servers, new RoundRobbinRule());
        Server server = lb.next();
        assertNotNull(server);
    }

    @Test
    public void setServerList() {
        List<Server> servers = List.of(new Server("h1", 8000));
        List<Server> newServers = List.of(new Server("h1", 8001));

        LoadBalancer lb = new LoadBalancer(servers, new RoundRobbinRule());
        lb.setServers(Stream.concat(servers.stream(), newServers.stream()).toList());

        List<Server> expected = new ArrayList<>();
        expected.addAll(servers);
        expected.addAll(newServers);

        assertEquals(expected, lb.getLiveServers());
    }

    @Test
    public void selectSameServerWhenSingleServerAvailable() {
        List<Server> servers = List.of(new Server("h1", 8000));
        LoadBalancer lb = new LoadBalancer(servers, new RoundRobbinRule());

        assertNotNull(lb.next());
        assertNotNull(lb.next());
        assertNotNull(lb.next());
    }

}
