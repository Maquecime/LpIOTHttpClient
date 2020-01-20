import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ClientHttp {

    public static void main(String[] args) {
        String urlPost = "http://localhost/action.php";
        String urlGet = "http://localhost";

        List<String> postKeys = new ArrayList<>();
        postKeys.add("var1");
        postKeys.add("var2");
        List<String> postValues = new ArrayList<>();
        postValues.add("2");
        postValues.add("3");

        try {
            String reponsePost= post(urlPost, postKeys, postValues);
            String reponseGet= get(urlGet);
            System.out.println("POST : \n");
            System.out.println(reponsePost);
            System.out.println("\n GET : \n");
            System.out.println(reponseGet);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String get(String url) throws IOException {

        String source ="";
        URL maroute = new URL(url);
        URLConnection connection = maroute.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                         connection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            source += inputLine;
        in.close();
        return source;
    }

    public static String post(String adress, List<String> keys, List<String> values) throws IOException{
        String result = "";
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        try {
//encodage des paramètres de la requête
            String data="";
            for(int i=0;i<keys.size();i++){
                if (i!=0) data += "&";
                data += URLEncoder.encode(keys.get(i), "UTF-8")+"="+ URLEncoder.encode(values.get(i), "UTF-8");
            }
//création de la connection
            URL url = new URL(adress);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);


//envoi de la requête
            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(data);
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                result+=ligne;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{writer.close();}catch(Exception e){}
            try{reader.close();}catch(Exception e){}
        }
        return result;
    }}
