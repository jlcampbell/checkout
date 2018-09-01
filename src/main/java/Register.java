import javax.swing.*;

public class Register {
    private PriceList mPriceList;
    private int mTotal;

    public Register(){
        mPriceList = new PriceList();
        mTotal = 0;
    }

    //transaction
    public int getTotal(){
        return mTotal;
    }

    public void addNewItemToPriceList(String name, double price, double markdown) {
        mPriceList.addItem(name, price, markdown);
    }

    public int getPriceListSize() {
        return mPriceList.getNumberOfItems();
    }

    public void scanItem(String name) {
        double cost = mPriceList.getItem(name).getPrice();
        mTotal += cost;
    }

    public void scanItemByWeight(String name, double weight) {
        double cost = mPriceList.getItem(name).getPrice()*weight;
        mTotal += cost;
    }
}
