import javax.swing.*;
import java.util.HashMap;

public class Register {
    private PriceList mPriceList;
    private Transaction mTransaction;

    public Register(){
        mPriceList = new PriceList();
    }

    public void startTransaction(){
        mTransaction = new Transaction(mPriceList);
    }

    public void scanItem(String name){
        mTransaction.scanItem(name);
    }
    //TODO combine scan methods
    public void scanItemByWeight(String name, Double weight){
        mTransaction.scanItemByWeight(name, weight);
    }
    public double getTotal(){
        return mTransaction.getTotal();
    }


    public void addNewItemToPriceList(String name, double price, double markdown) {
        mPriceList.addItem(name, price, markdown);
    }

    public int getPriceListSize() {
        return mPriceList.getNumberOfItems();
    }
}
