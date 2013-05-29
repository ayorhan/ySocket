package com.ayorhan.android.ySocket;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: ayorhan
 * Date: 5/29/13
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class YSocketManager {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String server;
    private int port;
    private static YSocketManager _instance = null;

    private YSocketManager(String server, int port){
        this.server = server;
        this.port = port;
    }

    // First time use only
    public YSocketManager init(String server, int port){
        if (isConnected()){
            Log.v("ySocket", "Connection Manager new instance is creating..........");
            closeSocket();
        }

        release();
        _instance = new YSocketManager(server, port);
        return _instance;
    }

    // Get current instance, can be null, though! be careful!
    public static YSocketManager getInstance(){
        return _instance;
    }

    public boolean connect(){
        try {
            if(!isConnected()){
                Log.v("ySocket","Opening socket using server: " + server + " port: " + port);

                socket = new Socket(server, port);
                socket.setKeepAlive(true);
                printSocketInformation(socket);
                Log.d("ySocket","socket opened");
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                Log.d("ySocket","got the streams");
            } else {
                throw new YSocketConnectionException();
            }
            return socket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (YSocketConnectionException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void closeSocket() {
        try {

            if(socket != null)
                socket.close();

            if (dataInputStream != null)
                dataInputStream.close();

            if (dataOutputStream != null)
                dataOutputStream.close();

            release();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public void release(){
        socket = null;
        dataInputStream = null;
        dataOutputStream = null;
        _instance = null;
    }

    /**
     * Prints debug output (to stdout) for the given Java Socket.
     */
    public void printSocketInformation(Socket socket)
    {
        try
        {
            Log.d("ySocket", String.format("Port:                 %s\n",   socket.getPort()));
            Log.d("ySocket", String.format("Canonical Host Name:  %s\n",   socket.getInetAddress().getCanonicalHostName()));
            Log.d("ySocket", String.format("Host Address:         %s\n\n", socket.getInetAddress().getHostAddress()));
            Log.d("ySocket", String.format("Local Address:        %s\n",   socket.getLocalAddress()));
            Log.d("ySocket", String.format("Local Port:           %s\n",   socket.getLocalPort()));
            Log.d("ySocket", String.format("Local Socket Address: %s\n\n", socket.getLocalSocketAddress()));
            Log.d("ySocket", String.format("Receive Buffer Size:  %s\n",   socket.getReceiveBufferSize()));
            Log.d("ySocket", String.format("Send Buffer Size:     %s\n\n", socket.getSendBufferSize()));
            Log.d("ySocket", String.format("Keep-Alive:           %s\n",   socket.getKeepAlive()));
            Log.d("ySocket", String.format("SO Timeout:           %s\n",   socket.getSoTimeout()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static boolean isConnected() {
        if(_instance == null || socket == null)
            return false;

        return socket.isConnected();
    }
}
