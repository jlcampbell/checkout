public class SpecialBuyNGetMForXPercentOff extends Special {
    private int mNumberDiscounted;
    private int mPercentOff;
    private int mLimit;
    private boolean mHasLimit;
    public SpecialBuyNGetMForXPercentOff(int numberThatMustBePurchased, int numberDiscounted, int percentOff) {
        super(numberThatMustBePurchased);
        mNumberDiscounted = numberDiscounted;
        mPercentOff = percentOff;
        mHasLimit = false;
    }
    public SpecialBuyNGetMForXPercentOff(int numberThatMustBePurchased, int numberDiscounted, int percentOff, int limit){
        super(numberThatMustBePurchased);
        mNumberDiscounted = numberDiscounted;
        mPercentOff = percentOff;
        mHasLimit = true;
        mLimit = limit;

    }
    public int getmLimit(){
        return mLimit;
    }
    public boolean hasLimit(){
        return mHasLimit;
    }
    public int getNumberDiscounted(){
        return mNumberDiscounted;
    }
    public int getPercentOff(){
        return mPercentOff;
    }

}
