import sun.awt.image.BufferedImageDevice;

import java.io.*;
import java.net.*;

/**
 * Created by uino on 07/04/17.
 */
public class Server {

    private static ServerSocket serverSocket;
    private final static String ADDRESS = "localhost";
    private final static int PORT = 3000;

    private static Socket socket;
    private static BufferedReader inSocket;
    private static PrintWriter outSocket;


    private static void waitForClients(){
        try {
            socket = serverSocket.accept();
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void waitForAction() throws IOException {
        while(true){
            String inString = inSocket.readLine();
            if (inString.equals("login")){
                String user = inSocket.readLine();
                String pass = inSocket.readLine();
                //TODO implementre login check HasMap
                outSocket.println("true");
            }else if (inString.equals("partita")){
                outSocket.println("12345");
                String sequenzaRitorno = inSocket.readLine();
                if (sequenzaRitorno.equals("12345")){
                    outSocket.println("true");
                }else{
                    outSocket.println("false");
                }
            } else if (inString.equals("quit")) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(PORT);
            waitForClients();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
