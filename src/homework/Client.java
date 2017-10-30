package homework;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Norman
 */
public class Client {

    public static void main(String[] args) throws IOException {

        String json = "{\n"
                + "    \"firstName\": \"John\",\n"
                + "    \"lastName\": \"Smith\",\n"
                + "    \"male\": true,\n"
                + "    \"age\": 25,\n"
                + "    \"address\": {\n"
                + "        \"streetAddress\": \"21 2nd Street\",\n"
                + "        \"city\": \"New York\",\n"
                + "        \"state\": \"NY\",\n"
                + "        \"postalCode\": 10021\n"
                + "    },\n"
                + "    \"phoneNumbers\": {\n"
                + "        \"type\": \"home\",\n"
                + "        \"number\": \"212 555-1234\"\n"
                + "    }\n"
                + "}";

        int operation = -1;
        while (operation != 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("send: send json to server");
            System.out.println("exit: to exit");
            System.out.println("get_full_stat_all ; get_full_stat_json_object, ...");
            System.out.println("Waiting for orders...");
            String data = scanner.next();

            if (data.equals("send")) {

                Socket socket = new Socket("localhost", 6060);
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                PrintWriter writer = new PrintWriter(os);
                JSONObject jSONObject = new JSONObject(json);
                writer.println(jSONObject);
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                String line = reader.readLine();
                jSONObject = new JSONObject(line);

                System.out.println(jSONObject.toString(13));

                os.close();
                reader.close();
                socket.close();
            } else {

                Socket socket = new Socket("localhost", 6060);
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                PrintWriter writer = new PrintWriter(os);
                writer.println(data);
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                String line = reader.readLine();
                JSONObject jSONObject = new JSONObject(line);

                System.out.println(jSONObject.toString(13));

                os.close();
                reader.close();
                socket.close();
            }

            if (data.equals("exit")) {

                operation = 0;
            }
        }

    }
}
