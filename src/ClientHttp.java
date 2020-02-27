import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHttp {

    public static void main(String[] args) {

        try {
            String reponsePost= post();
            String reponseGet= get();
            System.out.println("POST : \n");
            System.out.println(reponsePost);
            System.out.println("\n GET : \n");
            System.out.println(reponseGet);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String get() throws IOException {
        String adresse = "localhost";
        int port = 80;
        Socket socket = new Socket(adresse, port);


        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("GET /index.html HTTP/1.1\r");
        out.println("Host:localhost\r");
        out.println("Connection:Close\r");
        out.println("\r");

        boolean loop = true;
        StringBuilder sb = new StringBuilder(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
        }
        socket.close();
        return sb.toString();
    }

    public static String post() throws IOException{
        String params = "var1=1&var2=2";

        String adresse = "localhost";
        int port = 80;
        Socket socket = new Socket(adresse, port);


        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("POST /action.php HTTP/1.1\r");
        out.println("Host:localhost\r");
        out.println("Connection:Close\r");
        out.println("Content-type:application/x-www-form-urlencoded\r");
        out.println("Content-length:"+params.length()+"\r");
        out.println("\r");

        // Send parameters
        out.println(params+"\r");

        boolean loop = true;
        StringBuilder sb = new StringBuilder(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
        }
        socket.close();
        return sb.toString();
    }}
