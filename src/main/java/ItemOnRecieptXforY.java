import java.util.HashMap;

public class ItemOnRecieptXforY extends ItemOnReceipt {
    private SpecialXForY mSpecial;
    int mQuantity;

    public ItemOnRecieptXforY(Item item){
        super(item);
        mSpecial = item.getSpecialXForYDollars();
        mQuantity = 0;
    }
    @Override
    public void addItem(){
        mQuantity += 1;
    }

    @Override
    public double getTotal(){
        int quantityToBePurchasedToApplySpecial = mSpecial.getNumberThatMustBePurchased();
        double specialPrice = mSpecial.getPriceForSet();
        double answer;
        int numberOfTimesSpecialIsApplied = mQuantity/quantityToBePurchasedToApplySpecial;
        int extras = mQuantity % quantityToBePurchasedToApplySpecial;
        double total = numberOfTimesSpecialIsApplied*specialPrice + extras*(mOriginalPrice-mMarkdown);
        return total;
    }


}
