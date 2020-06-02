package rpc;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RecommendItem", urlPatterns = "/recommendation")
public class RecommendItem extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    JSONArray array = new JSONArray();
    array.put(
        new JSONObject()
            .put("name", "abcd")
            .put("address", "san francisco")
            .put("time", "01/01/2017"));
    array.put(
        new JSONObject().put("name", "1234").put("address", "san jose").put("time", "01/02/2017"));

    RpcHelper.writeJsonArray(response, array);
  }
}
