import javax.swing.*;

public class Register {
    private PriceList mPriceList;
    private int mTotal;

    public Register(){
        mPriceList = new PriceList();
        mTotal = 0;
    }


    public void addNewItemToPriceList(String name, double price, double markdown) {
        mPriceList.addItem(name, price, markdown);
    }

    public int getPriceListSize() {
        return mPriceList.getNumberOfItems();
    }



    //transaction
    public int getTotal(){
        return mTotal;
    }

    public void scanItem(String name) {
        Item item = mPriceList.getItem(name);
        double markdown = item.getMarkdown();
        double cost = item.getPrice();
        double costAfterMarkdown = cost-markdown;
        mTotal += costAfterMarkdown;
    }

    public void scanItemByWeight(String name, double weight) {
        double cost = mPriceList.getItem(name).getPrice()*weight;
        mTotal += cost;
    }
}
