import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHttp {
    private static int port;

    public static void main(String [] args) throws Exception {
        Socket soc;
        port = 8080;
        Reader reader;
        PrintStream sortie=null;
        String response;
        String urlReceived;

        ServerSocket s = new ServerSocket(port);

        while (true) {

            soc = s.accept();
            reader = new InputStreamReader(soc.getInputStream());
            sortie = new PrintStream(soc.getOutputStream());
            BufferedReader breader = new BufferedReader(reader);

            urlReceived =  breader.readLine();



        }
    }
}
