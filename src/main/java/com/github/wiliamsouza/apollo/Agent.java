package com.github.wiliamsouza.apollo;

import com.github.wiliamsouza.apollo.config.Configuration;
import com.github.wiliamsouza.apollo.device.DeviceMonitor;
import com.github.wiliamsouza.apollo.websocket.WebSocketEndpoint;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Agent {

    public static void main(String[] args) {

        String config = "/etc/apollo/agent.conf";
        Configuration configuration = null;
        CommandLine cmd;
        Session webSocketSession = null;

        Option configFile = OptionBuilder.withArgName("file")
                          .hasArg()
                          .withDescription("Apollo agent configuration file.")
                          .create("config");
        Options options = new Options();
        options.addOption(configFile);
        options.addOption("h", "help", false, "Print this message.");

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new BasicParser();
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("help")) {
                formatter.printHelp("apollo-agent.jar", options);
            }
            String conf = cmd.getOptionValue("config");
            if (conf != null) {
                config = conf;
            }
        }
        catch (ParseException e){
            System.err.println("Error parsing command line arguments.");
            formatter.printHelp("apollo", options);
            System.exit(1);
        }

        try {
            configuration = new Configuration(config);
        } catch (FileNotFoundException e) {
            System.err.printf("Configuration file not found: %s\n", config);
            System.exit(1);
        }

        URI uri = URI.create(configuration.getWebSocketUri() + configuration.getAPIKey());
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            webSocketSession = container.connectToServer(WebSocketEndpoint.class, uri);
        } catch (DeploymentException e) {
            // TODO: Print a more clear error message here
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            // TODO: Print a more clear error message here
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(configuration.getAPIUri());

        DeviceMonitor monitor = new DeviceMonitor(webSocketSession, configuration.getAdbPath());
        monitor.start();
        //monitor.finish();

        System.out.printf("Apollo agent: %s.\n", configuration.getName());

        while (true) {
        }
    }
}
