package entity;


import org.json.JSONObject;

import java.util.Set;

public class Item {

    private String itemId;

    private String name;

    private String address;

    private String imageUrl;

    private String url;

    private Set<String> keywords;

    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();

        object.put("item_id", itemId);
        object.put("name", name);
        object.put("address", address);
        object.put("keywords", keywords);
        object.put("image_url", imageUrl);
        object.put("url", url);
        return object;
    }


    public static final class ItemBuilder {
        private String itemId;
        private String name;
        private String address;
        private String imageUrl;
        private String url;
        private Set<String> keywords;

        private ItemBuilder() {
        }

        public static ItemBuilder anItem() {
            return new ItemBuilder();
        }

        public ItemBuilder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public ItemBuilder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ItemBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public ItemBuilder withKeywords(Set<String> keywords) {
            this.keywords = keywords;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.address = this.address;
            item.keywords = this.keywords;
            item.url = this.url;
            item.imageUrl = this.imageUrl;
            item.itemId = this.itemId;
            item.name = this.name;
            return item;
        }
    }
}