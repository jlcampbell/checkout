public class Item {
    private String mName;
    private double mPrice;
    private double mMarkdown;

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
}
