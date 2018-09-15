public class ItemOnReceipt {
    protected Double mOriginalPrice;
    protected Double mMarkdown;
    protected Item mItem;
    public ItemOnReceipt(Item item){
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
        mItem = item;
    }
    public void addItem(){

    }
    public double getTotal(){
        return 0.00;
    }
}
