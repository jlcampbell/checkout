import java.util.HashMap;

public class Transaction {

    private HashMap<String, ItemOnReceipt> mItemTotals;
    private HashMap<String, ItemOnReceiptBuyNGetM> mItemTotalsBuyNGetM;
    private HashMap<String, ItemOnRecieptXforY> mItemTotalsXForY;
    private PriceList mPriceList;

    public Transaction(PriceList priceList){
        mItemTotals = new HashMap<>();
        mItemTotalsBuyNGetM = new HashMap<>();
        mItemTotalsXForY = new HashMap<>();
        mPriceList = priceList;
    }

    public void scanItem(String name) {
        addItemToItemTotals(name);
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
        if (mItemTotals.containsKey(name)){
            itemOnRecieptXforY = mItemTotalsXForY.get(name);
            itemOnRecieptXforY.addItem();
        }else {
            itemOnRecieptXforY = new ItemOnRecieptXforY(item);
            itemOnRecieptXforY.addItem();
            mItemTotals.put(name, itemOnRecieptXforY);
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

//        ItemOnReceiptBuyNGetM itemOnReceipt;
//
//        if (mItemTotals.containsKey(name)){
//            itemOnReceipt = mItemTotals.get(name);
//            if (itemOnReceipt instanceof ItemOnReceiptBuyNGetM){
//                itemOnReceipt.addItem();}
//        } else {
//            itemOnReceipt = new ItemOnReceiptBuyNGetM(item);
//            itemOnReceipt.addItem();
//            mItemTotals.put(name, itemOnReceipt);
//        }
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
