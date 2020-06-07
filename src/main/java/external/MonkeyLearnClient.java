package external;

import com.monkeylearn.ExtraParam;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MonkeyLearnClient {

  private static final String API_KEY = "ca1152ae0562f3010c92f7a8b5db0026e1d35064";

  public static List<List<String>> extractKeywords(String[] text) {
    if (text == null || text.length == 0) {
      return new ArrayList<>();
    }

    MonkeyLearn monkeyLearn = new MonkeyLearn(API_KEY);

    ExtraParam[] extraParams = {new ExtraParam("max_keywords", "3")};
    MonkeyLearnResponse response;

    try {
      response = monkeyLearn.extractors.extract("ex_YCya9nrn", text, extraParams);
      JSONArray resultArray = response.arrayResult;
      return getKeyWords(resultArray);
    } catch (MonkeyLearnException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  private static List<List<String>> getKeyWords(JSONArray array) {
    List<List<String>> topKeywords = new ArrayList<>();
    for (int i = 0; i < array.size(); i++) {
      List<String> keywords = new ArrayList<>();
      JSONArray keywordsArray = (JSONArray) array.get(i);
      for (int j = 0; j < keywordsArray.size(); j++) {
        JSONObject keywordObject = (JSONObject) keywordsArray.get(j);

        String keyword = (String) keywordObject.get("keyword");
        keywords.add(keyword);
      }
      topKeywords.add(keywords);
    }
    return topKeywords;
  }
}
