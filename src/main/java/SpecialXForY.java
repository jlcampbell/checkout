public class SpecialXForY extends Special {
    private int mNumberThatMustBePurchased;
    private double mPriceForSet;
    public SpecialXForY(int numberThatMustBePurchased, double priceForSet) {
        super(numberThatMustBePurchased);
        mNumberThatMustBePurchased = numberThatMustBePurchased;
        mPriceForSet = priceForSet;
    }

    public int getNumberThatMustBePurchased(){
        return mNumberThatMustBePurchased;
    }

}
