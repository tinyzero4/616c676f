package com.breedish.loadbalancer;

import lombok.Data;

import java.util.Objects;

@Data
public class Server {

    private final String host;
    private final int port;
    private final MetaInfo meta = new MetaInfo();

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static class MetaInfo {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return port == server.port && Objects.equals(host, server.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
