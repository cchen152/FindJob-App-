package recommendation;

import db.MySQLConnection;
import entity.Item;
import external.GitHubClient;

import java.util.*;

public class Recommendation {

  public List<Item> recommendItems(String userId, double lat, double lon) {
    List<Item> recommendedItems = new ArrayList<>();
    MySQLConnection connection = new MySQLConnection();
    Set<String> favoritedItems = connection.getFavoriteItemIds(userId);

    Map<String, Integer> allKeywords = new HashMap<>();
    for (String itemId : favoritedItems) {
      Set<String> keywords = connection.getKeywords(itemId);
      for (String keyword : keywords) {
        allKeywords.put(keyword, allKeywords.getOrDefault(keyword, 0) + 1);
      }
    }
    connection.close();

    List<Map.Entry<String, Integer>> keywordList = new ArrayList<>(allKeywords.entrySet());
    Collections.sort(
        keywordList,
        (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) ->
            Integer.compare(e2.getValue(), e1.getValue()));

    // Cut down search list only to 3
    if (keywordList.size() > 3) {
      keywordList = keywordList.subList(0, 3);
    }

    Set<String> visitedItemIds = new HashSet<>();
    GitHubClient client = new GitHubClient();

    for (Map.Entry<String, Integer> keyword : keywordList) {
      List<Item> items = client.search(lat, lon, keyword.getKey());
      for (Item item : items) {
        if (!favoritedItems.contains(item.getItemId())
            && !visitedItemIds.contains(item.getItemId())) {
          recommendedItems.add(item);
          visitedItemIds.add(item.getItemId());
        }
      }
    }
    return recommendedItems;
  }
}
