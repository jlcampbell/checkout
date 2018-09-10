import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//keep track of how many of this type of item we have for specials
//calculate and apply specials price
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
    public int getQuantityDiscounted(){
        int quantityDiscounted;
        if (mQuantity>mSpecial.get("N")){
            quantityDiscounted = mQuantity-mSpecial.get("N");
        }else{
            quantityDiscounted = 0;
        }
        return quantityDiscounted;
    }



    public double calculateSpecialPrice(){
        double priceAfterMarkdown = mOriginalPrice - mMarkdown;
        double specialPrice = priceAfterMarkdown - priceAfterMarkdown*mSpecial.get("percent off")/100;
        return specialPrice;
    }

}
