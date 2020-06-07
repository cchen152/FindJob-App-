package rpc;

import db.MySQLConnection;
import entity.Item;
import external.GitHubClient;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "SearchItem", urlPatterns = "/search")
public class SearchItem extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String userId = request.getParameter("user_id");
    GitHubClient gitHubClient = new GitHubClient();
    double lat = Double.valueOf(request.getParameter("lat"));
    double lon = Double.valueOf(request.getParameter("lon"));

    MySQLConnection connection = new MySQLConnection();
    Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);
    connection.close();

    List<Item> items = gitHubClient.search(lat, lon, null);
    JSONArray array = new JSONArray();
    items.forEach(
        item -> {
          JSONObject obj = item.toJSONObject();
          obj.put("favorite", favoritedItemIds.contains(item.getItemId()));
          array.put(obj);
        });
    RpcHelper.writeJsonArray(response, array);
  }
}
