package rpc;

import entity.Item;
import org.json.JSONArray;
import org.json.JSONObject;
import recommendation.Recommendation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RecommendItem", urlPatterns = "/recommendation")
public class RecommendItem extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session == null) {
      response.setStatus(403);
      return;
    }

    String userId = request.getParameter("user_id");

    double lat = Double.parseDouble(request.getParameter("lat"));
    double lon = Double.parseDouble(request.getParameter("lon"));

    Recommendation recommendation = new Recommendation();
    List<Item> items = recommendation.recommendItems(userId, lat, lon);

    JSONArray array = new JSONArray();
    for (Item item : items) {
      array.put(item.toJSONObject());
    }

    RpcHelper.writeJsonArray(response, array);
  }
}
