import java.util.HashMap;

public class Transaction {

    private HashMap<String, ItemOnReceipt> mItemTotals;
    private HashMap<String, ItemOnReceiptBuyNGetM> mItemTotalsBuyNGetM;
    private HashMap<String, ItemOnRecieptXforY> mItemTotalsXForY;
    private HashMap<String, ItemOnReceiptBuyNGetMForXPercentOffByWeight> mItemByWeight;

    private PriceList mPriceList;

    public Transaction(PriceList priceList){
        mItemTotals = new HashMap<>();
        mItemTotalsBuyNGetM = new HashMap<>();
        mItemTotalsXForY = new HashMap<>();
        mItemByWeight = new HashMap<>();
        mPriceList = priceList;
    }

    public void scanItem(String name) {
        addItemToItemTotals(name);
    }

    public void scanItemByWeight(String name, double weight) {
        addItemToItemTotalsByWeight(name, weight);
    }

    private void addItemToItemTotalsByWeight(String name, double weight){
        Item item = mPriceList.getItem(name);
        addItemWeightSpecial(name, item, weight);
    }
    private void addItemWeightSpecial(String name, Item item, double weight){
        ItemOnReceiptBuyNGetMForXPercentOffByWeight itemDiscountWeight;
        if (mItemByWeight.containsKey(name)){
            itemDiscountWeight = mItemByWeight.get(name);
            itemDiscountWeight.addItem(weight);
        } else {
            itemDiscountWeight = new ItemOnReceiptBuyNGetMForXPercentOffByWeight(item);
            itemDiscountWeight.addItem(weight);
            mItemByWeight.put(name, itemDiscountWeight);
        }
    }

    private void addItemNGetM(String name, Item item){
        ItemOnReceiptBuyNGetM itemOnReceiptBuyNGetM;
        if(mItemTotalsBuyNGetM.containsKey(name)){
            itemOnReceiptBuyNGetM = mItemTotalsBuyNGetM.get(name);
            itemOnReceiptBuyNGetM.addItem();
        } else {
            itemOnReceiptBuyNGetM = new ItemOnReceiptBuyNGetM(item);
            itemOnReceiptBuyNGetM.addItem();
            mItemTotalsBuyNGetM.put(name, itemOnReceiptBuyNGetM);
        }
    }

    private void addItem(String name, Item item){
        ItemOnReceipt itemOnReceipt;
        if (mItemTotals.containsKey(name)){
            itemOnReceipt = mItemTotals.get(name);
            itemOnReceipt.addItem();
        } else {
            itemOnReceipt = new ItemOnReceipt(item);
            itemOnReceipt.addItem();
            mItemTotals.put(name, itemOnReceipt);
        }
    }

    private void addItemXForY(String name, Item item) {
        ItemOnRecieptXforY itemOnRecieptXforY;
        if (mItemTotalsXForY.containsKey(name)){
            itemOnRecieptXforY = mItemTotalsXForY.get(name);
            itemOnRecieptXforY.addItem();
        }else {
            itemOnRecieptXforY = new ItemOnRecieptXforY(item);
            itemOnRecieptXforY.addItem();
            mItemTotalsXForY.put(name, itemOnRecieptXforY);
        }
    }

    private void addItemToItemTotals(String name){
        Item item = mPriceList.getItem(name);
            if (item.getSpecialBuyNGetMForXPercentOff() != null){
                addItemNGetM(name, item);
            } else if(item.getSpecialXForYDollars() != null) {
                addItemXForY(name, item);
            }
            else {
                addItem(name, item);
            }
    }

    public double getTotal(){
        double total = 0;
        for (String name : mItemTotals.keySet()){
            total += mItemTotals.get(name).getTotal();
        }
        for (String name : mItemTotalsBuyNGetM.keySet()){
            total += mItemTotalsBuyNGetM.get(name).getTotal();
        }
        for (String name : mItemTotalsXForY.keySet()){
            total += mItemTotalsXForY.get(name).getTotal();
        }
        for (String name : mItemByWeight.keySet()){
            total += mItemByWeight.get(name).getTotal();
        }
        return total;
    }

    public void removeItem(String name) {
        if (mItemTotals.keySet().contains(name)){
            ItemOnReceipt itemOnReceipt = mItemTotals.get(name);
            itemOnReceipt.removeItem();
        } else if (mItemTotalsBuyNGetM.keySet().contains(name)){
            ItemOnReceiptBuyNGetM itemOnReceiptBuyNGetM = mItemTotalsBuyNGetM.get(name);
            itemOnReceiptBuyNGetM.removeItem();
        } else if (mItemTotalsXForY.keySet().contains(name)){
            ItemOnRecieptXforY itemOnRecieptXforY = mItemTotalsXForY.get(name);
            itemOnRecieptXforY.removeItem();
        }

    }
}
