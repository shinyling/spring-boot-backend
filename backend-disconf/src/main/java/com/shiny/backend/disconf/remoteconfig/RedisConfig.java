package com.shiny.backend.disconf.remoteconfig;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Component;

/**
 * @author shiny
 **/
@Component
@DisconfFile(filename = "redis.properties")
public class RedisConfig {

    private String host;

    private String port;

    @DisconfFileItem(name="redis.host",associateField = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @DisconfFileItem(name="redis.port",associateField = "port")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
