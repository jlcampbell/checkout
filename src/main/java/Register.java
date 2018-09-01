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

    public void addNewItemToPriceList(String name, double price) {
        mPriceList.addItem(name, price);
    }

    public int getPriceListSize() {
        return mPriceList.getNumberOfItems();
    }

    public void scanItem(String name) {
        double cost = mPriceList.getItem(name).getPrice();
        mTotal += cost;
    }
}
