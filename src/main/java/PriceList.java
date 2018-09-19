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

    public void setSpecialBuyNGetMForXPercentOff(String name, int n, int m, int percentOffM) {
        Item item = getItem(name);
        item.setSpecialBuyNGetMForXPercentOff(n, m, percentOffM);
        mItems.put(name, item);
    }
    public void setSpecialBuyNGetMForXPercentOff(String name, int n, int m, int percentOffM, int limit) {
        Item item = getItem(name);
        item.setSpecialBuyNGetMForXPercentOff(n, m, percentOffM, limit);
        mItems.put(name, item);
    }

    public SpecialBuyNGetMForXPercentOff getSpecialBuyNGetMForXPercentOff(String name) {
        Item item = getItem(name);
        return item.getSpecialBuyNGetMForXPercentOff();
    }

    public void setSpecialXForY(String name, int x, double y) {
        Item item = getItem(name);
        item.setSpecialXForYDollars(x,y);
        mItems.put(name, item);
    }

    public void setSpecialXForY(String name, int x, double y, int limit) {
        Item item = getItem(name);
        item.setSpecialXForYDollars(x,y, limit);
        mItems.put(name, item);
    }

    public Special getSpecial(String name){
        Item item = getItem(name);
        return item.getSpecial();
    }

    public SpecialXForY getXForYSpecial(String name) {
        return getItem(name).getSpecialXForYDollars();
    }


}
