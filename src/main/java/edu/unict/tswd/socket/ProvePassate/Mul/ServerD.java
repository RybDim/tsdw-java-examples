package edu.unict.tswd.socket.ProvePassate.Mul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerD {
    private static final int PORT = 7777;

    private static int MUL(String str){
        int res = 1;
        for(int i=0; i < str.length(); i++){
            res *= Character.getNumericValue(str.charAt(i));
        }

        return res;
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(PORT);
        } catch(IOException e){
            System.out.println("serverSocket");
            System.exit(1);
        }

        System.out.println(serverSocket + "started...");

        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            String buffer = in.readLine();
            System.out.println("ServerD received: " + buffer);
            out.println("ServerD answer: " + MUL(buffer));

        } catch(IOException e){
            System.out.println("accept");
            System.exit(1);
        }


        try{
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch(IOException e){
            System.out.println("close");
            System.exit(1);
        }
    }
}
