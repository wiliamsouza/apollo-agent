package com.github.wiliamsouza.apollo.device;

import com.android.ddmlib.AndroidDebugBridge;

import javax.websocket.Session;

public class DeviceMonitor {

    private Session webSocketSession;
    private String adbPath;

    public DeviceMonitor(Session webSocketSession, String adbPath) {
        this.webSocketSession = webSocketSession;
        this.adbPath = adbPath;
    }

    public void start() {
        AndroidDebugBridge.init(false);
        AndroidDebugBridge.addDeviceChangeListener(new DeviceListener(webSocketSession));
        AndroidDebugBridge adb = AndroidDebugBridge.createBridge(adbPath, true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO: Print a more clear error message here
            e.printStackTrace();
        }
        if (!adb.isConnected()) {
            System.out.println("Couldn't connect to ADB server");
        }
    }

    public void finish() {
        AndroidDebugBridge.disconnectBridge();
        AndroidDebugBridge.terminate();
    }
}
