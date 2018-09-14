import java.util.HashMap;

public class Transaction {
    private double mTotal;
    private HashMap<String, Integer> mItemTotals;
    private PriceList mPriceList;

    public Transaction(PriceList priceList){
        mTotal = 0;
        mItemTotals = new HashMap<String, Integer>();
        mPriceList = priceList;
    }

    public double getTotal(){
        return mTotal;
    }



    public void scanItem(String name) {
        //get pricing
        Item item = mPriceList.getItem(name);
        double markdown = item.getMarkdown();
        double cost = item.getPrice();
        //calculate price after markdown
        double costAfterMarkdown = cost-markdown;
        //get specials from price list
        Special special = mPriceList.getSpecialBuyNGetMForXPercentOff(name);
        //calculate new prices

        //update special prices
        //update total based on specials
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
