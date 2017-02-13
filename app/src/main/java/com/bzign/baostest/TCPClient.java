package com.bzign.baostest;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by demae on 12/02/2017.
 */

public class TCPClient {

    private short[] serverMessage;
    public static final String SERVERIP = "192.168.98.250"; //IP address of the BAOS Server
    public static final int SERVERPORT = 12004;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;

    private OutputStream streamOut;
    private  DataOutputStream dataOut;
    private InputStream streamIn;

    /**
     *  Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public TCPClient(OnMessageReceived listener) { mMessageListener = listener;
    }

    /**
     * Sends the message entered by client to the server
     * @param message text entered by client
     */
    public void sendMessage(short[] message, int length) throws IOException {
        if (dataOut != null && length>0) {
            dataOut.writeInt(length);
            for (int _index=0; _index<length; _index++) {
                dataOut.writeByte((int)message[_index]);
            }
        }
    }

    public void stopClient(){
        mRun = false;
    }
    public void clearMessage()
    {
        if (serverMessage.length>0)
        {
            for (int _index=0; _index<255; _index++) serverMessage[_index]=0;
        }
    }
    public void run() {

        mRun = true;

        try {
            //here you must put your computer's IP address.
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.e("TCP Client", "C: Connecting...");

            //create a socket to make the connection with the server
            Socket socket = new Socket(serverAddr, SERVERPORT);

            try {
                streamOut = socket.getOutputStream();
                dataOut = new DataOutputStream(streamOut);
                //receive the message which the server sends back
                streamIn = socket.getInputStream();
                DataInputStream _dis = new DataInputStream(streamIn);
                serverMessage =  new short[255];
                //in this while the client listens for the messages sent by the server
                while (mRun) {
                    int _len = _dis.readInt();
                    if (_len > 0) {
                        byte[] _data = new byte[_len];
                        _dis.readFully(_data);
                        if (mMessageListener != null) {
                            clearMessage();
                            for (int _index=0;_index<_len;_index++) serverMessage[_index] = _data[_index];
                            //call the method messageReceived from MyActivity class
                            mMessageListener.messageReceived(serverMessage);
                    }}
                    serverMessage = null;
                }

                Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage.toString() + "'");

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);

            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                socket.close();
            }

        } catch (Exception e) {

            Log.e("TCP", "C: Error", e);

        }

    }

    //Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity
    //class at on asynckTask doInBackground
    public interface OnMessageReceived {public void messageReceived(short[] message);
    }
}