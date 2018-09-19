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
    public void removeItem() { mQuantity -= 1; }

    @Override
    public double getTotal(){
        //if limit is less than quantity
        //total = (mQuantity-limit)*regularPrice
//                    + (limit % numberThatMustBePurchased)*regularPrice
//                    + (limit/numberThatMustBePurchased)*specialSetPrice


        int limit = mSpecial.getLimit();
        int bound = (limit > mQuantity) ? mQuantity : limit;
        double total = totalPartOne(bound)+totalPartTwo(bound)+totalPartThree(bound);


        return total;
    }
    private double totalPartOne(int bound){
        return (mQuantity-bound)*(mItem.getPrice()-mItem.getMarkdown());
    }
    private double totalPartTwo(int bound){
        return (bound % mSpecial.getNumberThatMustBePurchased())*(mItem.getPrice()-mItem.getMarkdown());
    }
    private double totalPartThree(int bound){
        return (bound / mSpecial.getNumberThatMustBePurchased())*mSpecial.getPriceForSet();
    }

}
