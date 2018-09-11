import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//keep track of how many of this type of item we have for specials
//calculate and apply specials price
public class ItemOnReciept {
    private Double mOriginalPrice;
    private Double mMarkdown;
    private HashMap<String, Integer> mSpecial;
    int mQuantity;
    Item mItem;

    public ItemOnReciept(Item item){
        mItem = item;
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
        mSpecial = item.getSpecial();
        mQuantity = 0;
    }
    public void addItem(){
       mQuantity += 1;
    }
//    public int getQuantityDiscounted(){
//        int quantityToBePurchasedToApplySpecial = mSpecial.get("N");
//        int quantityToBeGottenAtDiscount = mSpecial.get("M");
//        int specialGroup = quantityToBePurchasedToApplySpecial+ quantityToBeGottenAtDiscount;
//        int quantityDiscounted;
//        if (mQuantity>mSpecial.get("N")){
//            quantityDiscounted = mQuantity-mSpecial.get("N");
//        }else{
//            quantityDiscounted = 0;
//        }
//        return quantityDiscounted;
//    }
    public double[] getPriceArray(){
        int quantityToBePurchasedToApplySpecial = mSpecial.get("N");
        int quantityToBeGottenAtDiscount = mSpecial.get("M");
        int counter = 0;
        double[] priceArray = new double[mQuantity];
        for (int i = 0; i<mQuantity; i++){
            if (counter==quantityToBeGottenAtDiscount+quantityToBePurchasedToApplySpecial){
                counter=0;
            }
            if (counter<quantityToBePurchasedToApplySpecial){
                priceArray[i]=mOriginalPrice-mMarkdown;
            }else{
                priceArray[i]=calculateSpecialPrice();
            }
            counter += 1;
        }
        return priceArray;
    }
    public double getTotal(){
        double total=0;
        double[] priceArray=getPriceArray();
        for (double i:priceArray){
            total+=i;
        }
        return total;
    }



    public double calculateSpecialPrice(){
        double priceAfterMarkdown = mOriginalPrice - mMarkdown;
        double specialPrice = priceAfterMarkdown - priceAfterMarkdown*mSpecial.get("percent off")/100;
        return specialPrice;
    }

}
