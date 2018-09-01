import org.json.simple.JSONObject;

import java.util.HashMap;

public class PriceList {
    private HashMap<String, Item> mItems;

    public PriceList(){
        mItems = new HashMap<String, Item>();
    }

    public int getNumberOfItems(){
        return mItems.size();
    }
    public void addItem(String name, double price){
        Item temp = new Item(name, price);
        mItems.put(name, temp);
    }


    public Item getItem(String name) {
        return mItems.get(name);
    }
}
