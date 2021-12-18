package BenedictoMatthewJmartFA.jmart_android.model;

import static BenedictoMatthewJmartFA.jmart_android.LoginActivity.loggedAccount;

/**
 * Contains product details.
 *
 * @author Benedicto Matthew W
 */

public class Product extends Serializable{
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    public String toString(){
        loggedAccount().name = name;
        return loggedAccount().name;
    }

}
