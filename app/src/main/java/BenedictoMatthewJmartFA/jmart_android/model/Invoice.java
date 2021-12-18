package BenedictoMatthewJmartFA.jmart_android.model;

import java.util.Date;

public abstract class Invoice extends Serializable
{
    public int buyerId;
    public int complaintId;
    public Date date;
    public int productId;
    public Rating rating;
//    public Status status;

    protected Invoice(int buyerId, int productId){
        this.rating = Rating.NONE;
        this.date =java.util.Calendar.getInstance().getTime();
        this.complaintId = -1;
        this.buyerId = buyerId;
        this.productId = productId;
    }

    public abstract double getTotalPay(Product product);

    public enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED, DELIVERED
    }

    enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }


}
