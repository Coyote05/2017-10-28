package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONObject;

/**
 *
 * @author Norman
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6060);

        try {
            while (true) {

                Socket socket = serverSocket.accept();
                start(socket);
            }
        } finally {
            serverSocket.close();
        }
    }

    private static void start(final Socket socket) throws IOException {

        Thread thread = new Thread() {

            int jsonObject = 0;
            int jsonString = 0;
            int jsonNumber = 0;
            int jsonArray = 0;
            int jsonBool = 0;
            int jsonNull = 0;
            int errorCode = 0;
            String errorMsg = "";

            @Override
            public void run() {
                try {

                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                    String line = reader.readLine();

                    if (line.contains("firstName")) {

                        JSONObject jSONOb = new JSONObject(line);

                        for (Map.Entry<String, Object> key : jSONOb.toMap().entrySet()) {

                            Object object = key.getValue();

                            if (object instanceof Integer == false || object instanceof String == false) {

                                JSONObject isObject = new JSONObject(object);
                                for (Map.Entry<String, Object> value : isObject.toMap().entrySet()) {

                                    Object ob = value.getValue();
                                    if (ob instanceof Object[]) {
                                        jsonArray++;
                                    }

                                    if (ob instanceof Object) {
                                        jsonObject++;
                                    }
                                }
                            }

                            if (object instanceof String) {
                                jsonString++;
                            }

                            if (object instanceof Integer) {
                                jsonNumber++;
                            }

                            if (object instanceof Object[]) {
                                jsonArray++;
                            }

                            if (object instanceof Boolean) {
                                jsonBool++;
                            }
                        }

                        EntityManagerFactory emf = Persistence.createEntityManagerFactory("2017-10-28PU");
                        EntityManager em = emf.createEntityManager();

                        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateStat");
                        spq.registerStoredProcedureParameter("json_object", Integer.class, ParameterMode.IN);
                        spq.setParameter("json_object", jsonObject);
                        spq.registerStoredProcedureParameter("json_string", Integer.class, ParameterMode.IN);
                        spq.setParameter("json_string", jsonString);
                        spq.registerStoredProcedureParameter("json_number", Integer.class, ParameterMode.IN);
                        spq.setParameter("json_number", jsonNumber);
                        spq.registerStoredProcedureParameter("json_array", Integer.class, ParameterMode.IN);
                        spq.setParameter("json_array", jsonArray);
                        spq.registerStoredProcedureParameter("json_bool", Integer.class, ParameterMode.IN);
                        spq.setParameter("json_bool", jsonBool);
                        spq.registerStoredProcedureParameter("json_null", Integer.class, ParameterMode.IN);
                        spq.setParameter("json_null", jsonNull);
                        spq.execute();
                        em.close();

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();

                        jSONObject.put("json_object", jsonObject);
                        jSONObject.put("json_string", jsonString);
                        jSONObject.put("json_number", jsonNumber);
                        jSONObject.put("json_array", jsonArray);
                        jSONObject.put("json_bool", jsonBool);
                        jSONObject.put("json_null", jsonNull);
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                    if (line.contains("get_full_stat_all")) {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();
                        Stat stat = new Stat();

                        jSONObject.put("json_object", stat.getJsonObject());
                        jSONObject.put("json_string", stat.getJsonString());
                        jSONObject.put("json_number", stat.getJsonNumber());
                        jSONObject.put("json_array", stat.getJsonArray());
                        jSONObject.put("json_bool", stat.getJsonBool());
                        jSONObject.put("json_null", stat.getJsonNull());
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);

                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                    if (line.contains("get_full_stat_json_object")) {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();
                        Stat stat = new Stat();

                        jSONObject.put("json_object", stat.getJsonObject());
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                    if (line.contains("get_full_stat_json_string")) {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();
                        Stat stat = new Stat();

                        jSONObject.put("json_string", stat.getJsonString());
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                    if (line.contains("get_full_stat_json_number")) {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();
                        Stat stat = new Stat();

                        jSONObject.put("json_number", stat.getJsonNumber());
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                    if (line.contains("get_full_stat_json_array")) {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();
                        Stat stat = new Stat();

                        jSONObject.put("json_array", stat.getJsonArray());
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                    if (line.contains("get_full_stat_json_bool")) {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();
                        Stat stat = new Stat();

                        jSONObject.put("json_bool", stat.getJsonBool());
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                    if (line.contains("get_full_stat_json_null")) {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();
                        Stat stat = new Stat();

                        jSONObject.put("json_null", stat.getJsonNull());
                        jSONObject.put("error_code", errorCode);
                        jSONObject.put("error_msg", errorMsg);
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();

                    } else {

                        JSONObject jSONObject = new JSONObject();
                        JSONObject response = new JSONObject();

                        jSONObject.put("error_code", 999);
                        jSONObject.put("error_msg", "Érvénytelen paraméter: <HIBÁS PARAMÉTER>");
                        response.put("response", jSONObject);

                        writer.write(response.toString());
                        writer.flush();
                    }

                } catch (IOException ex) {
                    errorCode++;
                    errorMsg = ex.toString();
                } finally {
                    closeSocket();
                }
            }

            private void closeSocket() {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        };
        thread.start();
    }
;
}
