package com.ayorhan.android.ySocket;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ayorhan
 * Date: 5/29/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class YSocketWriteAsyncTask extends AsyncTask <Void, Void, Void> {
    private DataOutputStream outputStream;
    private YSocketManager manager;
    private Object command;

    private Context context;
    private ProgressDialog progress;
    private boolean showSpinner;


    public YSocketWriteAsyncTask(YSocketManager manager) {
        Log.d("ySocket", "WRITE CALLED");
        this.showSpinner = false;
        if (manager != null){
            this.manager = manager;
            this.outputStream = manager.getDataOutputStream();
            Log.d("ySocket","WRITE SET");
        } else {
            Log.d("ySocket","manager is null");
        }
    }

    public YSocketWriteAsyncTask(YSocketManager manager, Context context, Integer messageID) {
        Log.d("ySocket","WRITE CALLED");
        this.context = context;
        this.progress = new ProgressDialog(context);
        this.progress.setMessage(context.getResources().getString(messageID));
        this.progress.setCancelable(false);
        this.showSpinner = true;


        if (manager != null){
            this.manager = manager;
            this.outputStream = manager.getDataOutputStream();
            Log.d("ySocket","WRITE SET");
        } else {
            Log.d("ySocket","manager is null");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (showSpinner){
            this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.progress.show();
        }
    }

    @Override
    //protected Void doInBackground(XMLSocketMessage... xmlSocketMessages) {
    protected Void doInBackground(Void... voids) {
        try{
            Log.d("ySocket","Background WRITE CALLED");
            if (manager == null){
                Log.d("ySocket","MANAGER IS NULL");
            }

            if(outputStream == null){
                Log.d("ySocket","OUTPUTSTREAM IS NULL");
            }

            if (YSocketManager.isConnected()){
                Log.d("ySocket","WRITE CONNECTED");
            } else {
                Log.d("ySocket","WRITE NOT CONNECTED");
            }
            if(YSocketManager.isConnected() && outputStream != null){
                //Log.d("ySocket","writing :" + xmlSocketMessages[0].getMessage());
                //outputStream.write(xmlSocketMessages[0].getMessage().getBytes());
                //outputStream.flush();
                //this.command = xmlSocketMessages[0].getCommand();
            } else {
                Log.d("ySocket","ERROR - CANNOT WRITE");
            }
        //} catch (IOException e) {
        //    e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (showSpinner)
            this.progress.dismiss();

        Log.d("ySocket","post completed");
        if (command != null)
            Log.d("ySocket","sending " + command.toString() + " completed");
        //if (command == CanakConstants.Command.LEFT_TABLE_VOLUNTEER)
        //    SocketConnectionManager.closeSocket();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
