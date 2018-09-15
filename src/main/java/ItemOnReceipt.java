public class ItemOnReceipt {
    protected Double mOriginalPrice;
    protected Double mMarkdown;
    public ItemOnReceipt(Item item){
        mOriginalPrice = item.getPrice();
        mMarkdown = item.getMarkdown();
    }
    public void addItem(){

    }
    public double getTotal(){
        return 0.00;
    }
}
