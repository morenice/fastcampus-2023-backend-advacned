package org.example;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.params.GeoSearchParam;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try(var jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try(var jedis = jedisPool.getResource()) {
                // geo add
                jedis.geoadd("stores2:geo",127.02985530619755, 37.49911212874, "some1");
                jedis.geoadd("stores2:geo",127.0333352287619, 37.491921163986234, "some2");

                // geo dist
                Double geodist = jedis.geodist("stores2:geo", "some1", "some2");
                System.out.println(geodist);

                // geo search
                List<GeoRadiusResponse> radiusResponseList = jedis.geosearch(
                        "stores2:geo",
                        new GeoCoordinate(127.033, 37.495),
                        500,
                        GeoUnit.M
                );

                List<GeoRadiusResponse> radiusResponseList1 = jedis.geosearch("stores2:geo",
                        new GeoSearchParam()
                                .fromLonLat(new GeoCoordinate(127.033, 37.495))
                                .byRadius(500, GeoUnit.M)
                                .withCoord()
                );

                radiusResponseList1.forEach(response ->
                        System.out.println("%s %f %f".formatted(
                                response.getMemberByString(),
                                response.getCoordinate().getLatitude(),
                                response.getCoordinate().getLongitude())
                        ));

                jedis.unlink("stores2:geo");
            }
        }
    }
}