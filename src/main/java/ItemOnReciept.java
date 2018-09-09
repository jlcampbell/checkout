import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemOnReciept {
    private String mName;
    private Double mOriginalPrice;
    private Double mMarkdown;
    private HashMap<String, Integer> mSpecial;
    int mQuantity;

    public ItemOnReciept(Item item){
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
        mSpecial = item.getSpecial();
        mQuantity = 0;
    }



    public double calculateSpecialPrice(){
        double priceAfterMarkdown = mOriginalPrice - mMarkdown;
        double specialPrice = priceAfterMarkdown - priceAfterMarkdown*mSpecial.get("percent off")/100;
        return specialPrice;
    }

}
