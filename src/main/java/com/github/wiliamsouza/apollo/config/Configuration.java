package com.github.wiliamsouza.apollo.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Configuration {

    private String Name;
    private String APIKey;
    private String AdbPath;
    private String APIUri;
    private String WebSocketUri;

    public Configuration(String config) throws FileNotFoundException {
        InputStream file = new FileInputStream(new File(config));
        Yaml yaml = new Yaml(new Constructor(Agent.class));
        Agent agent = (Agent) yaml.load(file);
        this.Name = agent.option.name;
        this.APIKey = agent.option.apikey;
        this.AdbPath = agent.option.adb;
        this.APIUri = agent.option.api;
        this.WebSocketUri = agent.option.websocket;
    }

    public String getName() {
        return Name;
    }

    public String getAPIKey() {
        return APIKey;
    }

    public String getAdbPath() {
        return AdbPath;
    }

    public String getAPIUri() {
        return APIUri;
    }

    public String getWebSocketUri() {
        return WebSocketUri;
    }
}