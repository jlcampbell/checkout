import java.util.ArrayList;
import java.util.HashMap;

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


    public double getOriginalPriceMinusMarkdown() {
        return mOriginalPrice-mMarkdown;
    }
}








