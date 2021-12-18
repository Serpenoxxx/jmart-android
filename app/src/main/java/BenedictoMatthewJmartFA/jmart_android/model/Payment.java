package BenedictoMatthewJmartFA.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Contains payment details.
 * Inherits Invoice.
 *
 * @author Benedicto Matthew W
 */

public class Payment extends Invoice{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    /** Gets final amount needed to be paid
     *
     * @param product represents the product's details
     * @return  final price needed to be paid
     */

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
