package com.github.wiliamsouza.apollo.device;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;

import javax.websocket.Session;
import java.io.IOException;

public class DeviceListener implements IDeviceChangeListener {

    private Session webSocketSession;

    public DeviceListener(Session webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public void deviceConnected(IDevice device) {
        String msg = "Device connected " + device.getSerialNumber();
        System.out.println(msg);
        System.out.println(device.getState());
        try {
            this.webSocketSession.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            // TODO: Print a more clear error message here
            e.printStackTrace();
        }
        System.out.println("**********************************************************************");
    }

    public void deviceDisconnected(IDevice device) {
        String msg = "Device disconnected " + device.getSerialNumber();
        System.out.println(msg);
        System.out.println(device.getState());
        try {
            this.webSocketSession.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            // TODO: Print a more clear error message here
            e.printStackTrace();
        }
        System.out.println("**********************************************************************");
    }

    public void deviceChanged(IDevice device, int changeMask) {
        String msg = "Device changed " + device.getSerialNumber();
        System.out.println(msg);
        System.out.println(device.getState());
        try {
            this.webSocketSession.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            // TODO: Print a more clear error message here
            e.printStackTrace();
        }
        System.out.println("**********************************************************************");
    }
}