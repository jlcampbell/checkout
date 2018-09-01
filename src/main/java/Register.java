import javax.swing.*;

public class Register {
    private PriceList mPriceList;

    public Register(){
        mPriceList = new PriceList();
    }


    //transaction
    public int getTotal(){
        return 0;
    }


    public void addNewItemToPriceList(String name, double price) {
        mPriceList.addItem(name, price);
    }

    public int getPriceListSize() {
        return mPriceList.getNumberOfItems();
    }
}
