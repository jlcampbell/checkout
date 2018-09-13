public class SpecialBuyNGetMForXPercentOff extends Special {
    private int mNumberDiscounted;
    private int mPercentOff;
    public SpecialBuyNGetMForXPercentOff(int numberThatMustBePurchased, int numberDiscounted, int percentOff) {
        super(numberThatMustBePurchased);
        mNumberDiscounted = numberDiscounted;
        mPercentOff = percentOff;
    }
    public int getNumberDiscounted(){
        return mNumberDiscounted;
    }
    public int getPercentOff(){
        return mPercentOff;
    }

}
