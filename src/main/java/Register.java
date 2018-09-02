import javax.swing.*;
import java.util.HashMap;

public class Register {
    private PriceList mPriceList;
    private int mTotal;
    private HashMap<String, Integer> mItemTotals;

    public Register(){
        mPriceList = new PriceList();
        mTotal = 0;
        mItemTotals = new HashMap<String, Integer>();
    }


    public void addNewItemToPriceList(String name, double price, double markdown) {
        mPriceList.addItem(name, price, markdown);
    }

    public int getPriceListSize() {
        return mPriceList.getNumberOfItems();
    }

    //transaction
    public int getTotal(){
        return mTotal;
    }

    public void scanItem(String name) {
        Item item = mPriceList.getItem(name);
        double markdown = item.getMarkdown();
        double cost = item.getPrice();
        double costAfterMarkdown = cost-markdown;
        mTotal += costAfterMarkdown;
        addItemToItemTotals(name);
    }

    private void addItemToItemTotals(String name){
        if (mItemTotals.containsKey(name)){
            int value = mItemTotals.get(name);
            mItemTotals.put(name, value+1);
        } else {
            mItemTotals.put(name, 1);
        }
    }

    public void scanItemByWeight(String name, double weight) {
        Item item = mPriceList.getItem(name);
        double markdown = item.getMarkdown();
        double cost = mPriceList.getItem(name).getPrice()*weight;
        double costAfterMarkdown = cost - markdown;
        mTotal += costAfterMarkdown;
    }

    public int getTotalByName(String name) {
        return mItemTotals.get(name);
    }
}
