import java.util.HashMap;

public class Item {
    private String mName;
    private double mPrice;
    private double mMarkdown;
    //private HashMap<String, Integer> mSpecial;
    private Special mSpecial;
    private SpecialBuyNGetMForXPercentOff mNforMSpecial;
    private SpecialXForY mXForYSpecial;
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
    public void setSpecialBuyNGetMForXPercentOff(int n, int m, int percentOffM) {
        mNforMSpecial = new SpecialBuyNGetMForXPercentOff(n, m, percentOffM);
    }

    public void setSpecialBuyNGetMForXPercentOff(int n, int m, int percentOffM, int limit) {
        mNforMSpecial = new SpecialBuyNGetMForXPercentOff(n, m, percentOffM, limit);
    }

    public SpecialBuyNGetMForXPercentOff getSpecialBuyNGetMForXPercentOff(){
        return mNforMSpecial;
    }

    public Special getSpecial(){return mSpecial;}

    public void setSpecialXForYDollars(int x, double yDollars) {

        mXForYSpecial = new SpecialXForY(x, yDollars);
        }

    public void setSpecialXForYDollars(int x, double yDollars, int limit) {
        mXForYSpecial = new SpecialXForY(x, yDollars, limit);
    }

    public SpecialXForY getSpecialXForYDollars(){
        return mXForYSpecial;
    }

//    public void setBuyKGetJEqualOrLessXOffSpecial(int k, int j, int percentOffLesserValue) {
//        mBuyKGetJLesserForPercentOff = new HashMap<>();
//        mBuyKGetJLesserForPercentOff.put("K", k);
//        mBuyKGetJLesserForPercentOff.put("J", j);
//        mBuyKGetJLesserForPercentOff.put("percentOffLesserValue", percentOffLesserValue);
//    }
//    public HashMap<String, Integer> getBuyKGetJEqualOrLessXOffSpecial(){
//        return mBuyKGetJLesserForPercentOff;
//    }

}
