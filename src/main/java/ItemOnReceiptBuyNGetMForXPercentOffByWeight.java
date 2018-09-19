import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

//Support "Buy N, get M of equal or lesser value for %X off" on weighted items
public class ItemOnReceiptBuyNGetMForXPercentOffByWeight extends ItemOnReceipt {

    private SpecialBuyNGetMForXPercentOff mSpecial;
    ArrayList<Double> mWeights;

    private int mNumberYouMustBuy;
    private int mNumberYouGetAtSpecial;
    private int mPercentOff;

    public ItemOnReceiptBuyNGetMForXPercentOffByWeight(Item item){
        super(item);
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
        if (item.getSpecialBuyNGetMForXPercentOff() != null){
            mSpecial = item.getSpecialBuyNGetMForXPercentOff();

        }

        mWeights = new ArrayList<>();
    }

    public void addItem(Double weight){
        mWeights.add(weight);
    }

    public void removeItem(){
        int size = mWeights.size();
        mWeights.remove(size-1);
    }

    public int getNumberOfItems() {
        return mWeights.size();
    }

    public double getSpecialPrice(double weight) {
        mPercentOff = mSpecial.getPercentOff();
        return getOriginalPriceMinusMarkdown(weight)*mPercentOff/100;
    }

    public double getOriginalPriceMinusMarkdown(double weight) {
        return (mOriginalPrice*weight)-mMarkdown;
    }

    @Override
    public double getTotal() {
        double total;
        if (mSpecial != null){
            total = getTotalSpecialIsValid();
        } else {
            total = getTotalNullSpecial();
        }
        return total;
    }
    private double getTotalNullSpecial(){
        double total = 0;
        for (double weight : mWeights){
            total += getOriginalPriceMinusMarkdown(weight);
        }
        return total;
    }
    private double getTotalSpecialIsValid(){
        mNumberYouMustBuy = mSpecial.getNumberThatMustBePurchased();
        mNumberYouGetAtSpecial = mSpecial.getNumberDiscounted();

        double total = 0;
        int count = 0;
        int limitCount = 0;
        int limit = (mSpecial.hasLimit()) ? mSpecial.getmLimit() : 999;
        //order each price group
        //from each set the lowest ones get the special
        Collections.sort(mWeights, Collections.reverseOrder());
        for (double weight : mWeights){
            if (count == mNumberYouMustBuy+mNumberYouGetAtSpecial){
                count = 0;
            }
            if (count < mNumberYouMustBuy ){
                total += getOriginalPriceMinusMarkdown(weight);
                count += 1;
            } else if (count >= mNumberYouMustBuy && count< mNumberYouMustBuy+mNumberYouGetAtSpecial){
                total += getSpecialPrice(weight);
                count += 1;
            }
            limitCount += 1;
            if (limitCount >= limit){
                count = 0;
            }
        }
        return total;
    }
}








