public class ItemOnReceipt {
    protected Double mOriginalPrice;
    protected Double mMarkdown;
    protected int mQuantity;
    protected Item mItem;
    public ItemOnReceipt(Item item){
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
        mItem = item;
    }
    public void addItem(){
        mQuantity += 1;
    }
    private double getPriceMinusMarkdown(){
        return mOriginalPrice-mMarkdown;
    }
    public double getTotal(){
        return mQuantity*getPriceMinusMarkdown();
    }

    public void removeItem() {
        mQuantity -= 1;
    }
}
