import java.util.HashMap;

public class Transaction {

    private HashMap<String, ItemOnReceipt> mItemTotals;
    private PriceList mPriceList;

    public Transaction(PriceList priceList){
        mItemTotals = new HashMap<>();
        mPriceList = priceList;
    }

    public void scanItem(String name) {
        //get pricing
        Item item = mPriceList.getItem(name);

        //get specials from price list
        Special special = mPriceList.getSpecial(name);
        //calculate new prices



        addItemToItemTotals(name);
    }

    private void addItemToItemTotals(String name){
        Item item = mPriceList.getItem(name);
        if (mItemTotals.containsKey(name)){
            ItemOnReceipt itemOnReceipt = mItemTotals.get(name);
            itemOnReceipt.addItem();
        } else {
            mItemTotals.put(name, new ItemOnReceipt(item));
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
