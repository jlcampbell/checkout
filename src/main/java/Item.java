import java.util.HashMap;

public class Item {
    private String mName;
    private double mPrice;
    private double mMarkdown;
    //private HashMap<String, Integer> mSpecial;
    private SpecialBuyNGetMForXPercentOff mSpecial;
    private HashMap<String, Integer> mXForYSpecial;
    private HashMap<String, Integer> mBuyKGetJLesserForPercentOff;


    public Item(String name, double price, double markdown){
        mName = name;
        mPrice = price;
        mMarkdown = markdown;

    }
    public double getPrice(){
        return mPrice;
    }
    public double getMarkdown(){
        return mMarkdown;
    }
//TODO refactor name
    public void setSpecial(int n, int m, int percentOffM) {
        mSpecial = new SpecialBuyNGetMForXPercentOff(n, m, percentOffM);
    }

    public SpecialBuyNGetMForXPercentOff getSpecial(){
        return mSpecial;
    }

    public void setXForYDollarsSpecial(int x, int yDollars) {
        mXForYSpecial = new HashMap<>();
        mXForYSpecial.put("X", x);
        mXForYSpecial.put("YDollars", yDollars);
    }

    public HashMap<String, Integer> getXForYSpecial(){
        return mXForYSpecial;
    }

    public void setBuyKGetJEqualOrLessXOffSpecial(int k, int j, int percentOffLesserValue) {
        mBuyKGetJLesserForPercentOff = new HashMap<>();
        mBuyKGetJLesserForPercentOff.put("K", k);
        mBuyKGetJLesserForPercentOff.put("J", j);
        mBuyKGetJLesserForPercentOff.put("percentOffLesserValue", percentOffLesserValue);
    }
    public HashMap<String, Integer> getBuyKGetJEqualOrLessXOffSpecial(){
        return mBuyKGetJLesserForPercentOff;
    }

}
