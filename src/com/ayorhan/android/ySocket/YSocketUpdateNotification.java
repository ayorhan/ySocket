package com.ayorhan.android.ySocket;

/**
 * Created with IntelliJ IDEA.
 * User: ayorhan
 * Date: 5/29/13
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class YSocketUpdateNotification {
    private Object command;
    private Object updatePacket;

    public YSocketUpdateNotification(Object command) {
        this.command = command;
    }

    public YSocketUpdateNotification(Object command, Object updatePacket) {
        this.command = command;
        this.updatePacket = updatePacket;
    }

    public Object getCommand() {
        return command;
    }

    public void setCommand(Object command) {
        this.command = command;
    }

    public Object getUpdatePacket() {
        return updatePacket;
    }

    public void setUpdatePacket(Object updatePacket) {
        this.updatePacket = updatePacket;
    }
}
