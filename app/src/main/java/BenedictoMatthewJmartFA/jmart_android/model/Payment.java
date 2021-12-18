package BenedictoMatthewJmartFA.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    @Override
    public double getTotalPay(Product product){
        return (product.price - ((product.price * product.discount)/100));
    }

    public static class Record{
        public  Date date;
        public String message;
        public Invoice.Status status;
    }
}
