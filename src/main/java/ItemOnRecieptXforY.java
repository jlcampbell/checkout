import java.util.HashMap;

public class ItemOnRecieptXforY {
    private Double mOriginalPrice;
    private Double mMarkdown;
    private HashMap<String, Integer> mSpecial;
    int mQuantity;
    Item mItem;

    public ItemOnRecieptXforY(Item item){
        mItem = item;
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
        mSpecial = item.getXForYSpecial();
        mQuantity = 0;
    }

    public void addItem(){
        mQuantity += 1;
    }
    public double getTotal(){
        int quantityToBePurchasedToApplySpecial = mSpecial.get("X");
        int specialPrice = mSpecial.get("YDollars");
        double answer;
        int numberOfTimesSpecialIsApplied = mQuantity/quantityToBePurchasedToApplySpecial;
        int extras = mQuantity % quantityToBePurchasedToApplySpecial;
        double total = numberOfTimesSpecialIsApplied*specialPrice + extras*(mOriginalPrice-mMarkdown);
        return total;
    }


}