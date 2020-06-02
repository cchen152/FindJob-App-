package rpc;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RpcHelper {

  public static void writeJsonArray(HttpServletResponse response, JSONArray array) throws IOException {
      response.setContentType("application/json");
      PrintWriter writer = response.getWriter();
      writer.print(array);
  }

  public static void writeJsonObject(HttpServletResponse response, JSONObject obj) throws IOException {
      response.setContentType("application/json");
      PrintWriter writer = response.getWriter();
      writer.print(obj);
  }
}
