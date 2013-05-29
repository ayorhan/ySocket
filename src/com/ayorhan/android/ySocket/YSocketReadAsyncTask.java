package com.ayorhan.android.ySocket;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ayorhan
 * Date: 5/29/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class YSocketReadAsyncTask extends AsyncTask<Void, YSocketUpdateNotification, Void> {
    private DataInputStream inputStream;
    private YSocketManager manager;
    private boolean isActive;
    private int read;

    public YSocketReadAsyncTask(YSocketManager manager) {
        super();
        if (manager != null){
            this.manager = manager;
            this.inputStream = manager.getDataInputStream();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            isActive = true;
            Log.d("ySocket", "*** READ doInBackground");
            if (manager != null && YSocketManager.isConnected()){
                Log.d("ySocket","*** READ Connected. Starting reading..");

                while (isActive){

                    // ==== READ BUFFER ====
                    byte [] buffer = new byte[manager.getSocket().getReceiveBufferSize()];
                    read = inputStream.read(buffer);
                    if (read > 0){ // There is something in the buffer
                        String response = parseBuffer(buffer, read);
                        Log.d("ySocket","*** READ value:"+read + " buffer: " + new String(buffer, "UTF8")  + " response:" + response);

                        // ==== HANDLE COMMAND ====
                        processIncomingCommand(response);

                        // ===== DEBUG =====
                        //manager.printSocketInformation(manager.getSocket());
                    }
                }

                Log.d("ySocket","READ OUT OF WHILE!!!!");
            } else {
                Log.d("ySocket","ERROR - CANNOT READ");
            }
        } catch (IOException e) {
            e.printStackTrace();
            //TODO restart the app
        } catch (Exception e) {
            e.printStackTrace();
            //TODO restart the app
        }
        return null;
    }

    private String parseBuffer(byte[] buffer, int read) throws UnsupportedEncodingException {
        byte [] response = new byte[read];
        System.arraycopy(buffer, 0, response, 0, read);
        return fixChars(new String(response, "UTF8"));
    }

    private String fixChars(String b) {
        b = b.replace("]c[", "ç");
        b = b.replace("]i[", "ı");
        b = b.replace("]g[", "ğ");
        b = b.replace("]s[", "ş");
        b = b.replace("]u[", "ü");
        b = b.replace("]o[", "ö");
        b = b.replace("]C[", "Ç");
        b = b.replace("]I[", "İ");
        b = b.replace("]G[", "Ğ");
        b = b.replace("]S[", "Ş");
        b = b.replace("]U[", "Ü");
        b = b.replace("]O[", "Ö");
        b = b.replace("]B[", "ß");
        b = b.replace("&", "&amp;");

        return b;
    }

    private void processIncomingCommand(String response) {
        Log.d("ySocket","processing: " + response);

        ArrayList<String> messages = parseMessages(response);

        for (String message : messages){
            //XMLParser parser = new XMLParser();
            //XMLSocketMessage incoming = parser.parseSocketResponse(message);

            //if (incoming.getCommand() == CanakConstants.Command.LEFT_TABLE_VOLUNTEER){
                //CanakLog.d("closing socket");
                //isActive = false;
            //}

            //MessageHandler.processIncomingMessage(incoming);
        }
    }

    private ArrayList<String> parseMessages(String response) {
        ArrayList<String> messages = new ArrayList<String>();
        String opening = "<messages>";
        String closing = "</messages>";
        int start = 0, end = 0;
        int finalend = response.length();

        while(end < finalend){
            start = response.indexOf(opening);
            end = response.indexOf(closing) + closing.length();
            String parsed = response.substring(start, end);
            messages.add(parsed);
            response = response.substring(end);
            finalend = response.length();
        }
        return messages;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("ySocket","onPostExe completed");
        YSocketManager.closeSocket();
    }

    @Override
    protected void onProgressUpdate(YSocketUpdateNotification... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        YSocketManager.closeSocket();
    }
}
