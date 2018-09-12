import java.util.ArrayList;
import java.util.HashMap;

//Support "Buy N, get M of equal or lesser value for %X off" on weighted items
public class ItemOnReceiptBuyNGetMForXPercentOffByWeight {

    private Double mOriginalPrice;
    private Double mMarkdown;
    private HashMap<String, Integer> mSpecial;
    ArrayList<Double> mWeights;
    Item mItem;

    public ItemOnReceiptBuyNGetMForXPercentOffByWeight(Item item){
        mItem = item;
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
        mSpecial = item.getBuyKGetJEqualOrLessXOffSpecial();
        mWeights = new ArrayList<>();
    }
    public void addItem(Double weight){
        mWeights.add(weight);
    }

    public int getNumberOfItems() {
        return mWeights.size();
    }

    public double getSpecialPrice(double weight, double markdown, double originalPrice, int percentOff) {
        return (weight*originalPrice-markdown)*percentOff/100;
    }

    public double getOriginalPriceMinusMarkdown(double weight) {
        return (mOriginalPrice-mMarkdown)*weight;
    }
}








