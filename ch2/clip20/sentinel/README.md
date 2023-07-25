Run redis containers with slave and sentinel
```
$docker-compose up -d
```

Show logs of containers
```
$docker-compose logs -f
```

Show sentinel configuration
```
$ docker-compose exec -it sentinel2 /bin/bash

$ redis-cli -p 26379
127.0.0.1:26379> info sentinel
# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
sentinel_simulate_failure_flags:0
master0:name=mymaster,status=ok,address=172.27.0.4:6379,slaves=2,sentinels=3
```

Failover example
```
$ docker-compose stop redis

$ docker-compose start redis
```
