package rpc;

import entity.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

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

    // Convert a JSON object to Item object
    public static Item parseFavoriteItem(JSONObject favoriteItem) {
        Item.ItemBuilder builder = Item.ItemBuilder.anItem();
        builder.withItemId(favoriteItem.getString("item_id"));
        builder.withName(favoriteItem.getString("name"));
        builder.withAddress(favoriteItem.getString("address"));
        builder.withUrl(favoriteItem.getString("url"));
        builder.withImageUrl(favoriteItem.getString("image_url"));

        Set<String> keywords = new HashSet<>();
        JSONArray array = favoriteItem.getJSONArray("keywords");
        for (int i = 0; i < array.length(); ++i) {
            keywords.add(array.getString(i));
        }
        builder.withKeywords(keywords);
        return builder.build();
    }

    private RpcHelper(){};

}
