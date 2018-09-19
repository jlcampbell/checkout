public class SpecialXForY extends Special {

    private int mNumberThatMustBePurchased;
    private double mPriceForSet;
    private int mLimit;

    public SpecialXForY(int numberThatMustBePurchased, double priceForSet) {
        super(numberThatMustBePurchased);
        mNumberThatMustBePurchased = numberThatMustBePurchased;
        mPriceForSet = priceForSet;
        mLimit = 999;
    }
    public SpecialXForY(int numberThatMustBePurchased, double priceForSet, int limit){
        super(numberThatMustBePurchased);
        mNumberThatMustBePurchased = numberThatMustBePurchased;
        mPriceForSet = priceForSet;
        mLimit = limit;
    }
    public int getNumberThatMustBePurchased(){
        return mNumberThatMustBePurchased;
    }
    public double getPriceForSet(){
        return mPriceForSet;
    }
    public int getLimit(){
        return mLimit;
    }

}
