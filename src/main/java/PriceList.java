import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class PriceList {
    private HashMap<String, Item> mItems;

    public PriceList(){
        mItems = new HashMap<String, Item>();
    }

    public int getNumberOfItems(){
        return mItems.size();
    }
    public void addItem(String name, double price, double markdown){
        Item temp = new Item(name, price, markdown);
        mItems.put(name, temp);
    }


    public Item getItem(String name) {
        return mItems.get(name);
    }
//TODO consider refactor so pricelist only contains CRUD for item
    public void setSpecial(String name, int n, int m, int percentOffM) {
        Item item = getItem(name);
        item.setSpecial(n, m, percentOffM);
        mItems.put(name, item);
    }

    public HashMap<String, Integer> getSpecial(String name) {
        Item item = getItem(name);
        return item.getSpecial();
    }
}