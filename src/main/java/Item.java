import java.util.HashMap;

public class Item {
    private String mName;
    private double mPrice;
    private double mMarkdown;
    private HashMap<String, Integer> mSpecial;

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

    public void setSpecial(int n, int m, int percentOffM) {
        mSpecial = new HashMap<>();
        mSpecial.put("N", n);
        mSpecial.put("M", m);
        mSpecial.put("percent off", percentOffM);
    }

    public HashMap<String, Integer> getSpecial(){
        return mSpecial;
    }
}
