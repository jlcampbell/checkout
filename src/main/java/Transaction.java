import java.util.HashMap;

public class Transaction {

    private HashMap<String, ItemOnReceipt> mItemTotals;
    private PriceList mPriceList;

    public Transaction(PriceList priceList){
        mItemTotals = new HashMap<>();
        mPriceList = priceList;
    }

    public void scanItem(String name) {
        addItemToItemTotals(name);
    }

    private void addItemToItemTotals(String name){
        Item item = mPriceList.getItem(name);
        if (mItemTotals.containsKey(name)){
            ItemOnReceipt itemOnReceipt = mItemTotals.get(name);
            itemOnReceipt.addItem();
        } else {
            ItemOnReceipt itemOnReceipt = new ItemOnReceipt(item);
            itemOnReceipt.addItem();
            mItemTotals.put(name, itemOnReceipt);
        }
    }

    public double getTotal(){
        double total = 0;
        for (String name : mItemTotals.keySet()){
            total += mItemTotals.get(name).getTotal();
        }
        return total;
    }

//    public void scanItemByWeight(String name, double weight) {
//        Item item = mPriceList.getItem(name);
//        double markdown = item.getMarkdown();
//        double cost = mPriceList.getItem(name).getPrice()*weight;
//        double costAfterMarkdown = cost - markdown;
//        mTotal += costAfterMarkdown;
//    }




}
