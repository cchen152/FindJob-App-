package rpc;

import entity.Item;
import external.GitHubClient;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchItem", urlPatterns = "/search")
public class SearchItem extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    GitHubClient gitHubClient = new GitHubClient();
    double lat = Double.valueOf(request.getParameter("lat"));
    double lon = Double.valueOf(request.getParameter("lon"));

    List<Item> items = gitHubClient.search(lat, lon, null);
    JSONArray array = new JSONArray();
    items.forEach(item -> array.put(item.toJSONObject()));
    RpcHelper.writeJsonArray(response, array);
  }
}
