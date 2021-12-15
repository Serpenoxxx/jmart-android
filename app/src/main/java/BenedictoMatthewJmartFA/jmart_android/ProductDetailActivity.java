package BenedictoMatthewJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import BenedictoMatthewJmartFA.jmart_android.model.Product;
import static BenedictoMatthewJmartFA.jmart_android.FilterFragment.productName;
import static BenedictoMatthewJmartFA.jmart_android.FilterFragment.products;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> pr = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra("Position",0);
        System.out.println(position);
//        TextView PDidEdt  = findViewById(R.id.PDidEdt);
        TextView PDNameEdit  = findViewById(R.id.ProductNameView);
        TextView PDCategoryEdit  = findViewById(R.id.ProductCategoryView);
        TextView PDConditionEdit  = findViewById(R.id.ProductConditionView);
        TextView PDPriceEdit  = findViewById(R.id.ProductPriceView);
//        TextView PDDiscountEdit  = findViewById(R.id.PDDiscountEdit);
        TextView PDWeightEdit  = findViewById(R.id.ProductWeightView);
//        TextView PDShipmentEdit  = findViewById(R.id.PDShipmentEdit);
        System.out.println(productName.get(position));
        for(Product prod: products){
            if(prod.name == productName.get(position)){
                pr.add(String.valueOf(prod.accountId));
                pr.add(prod.name);
                pr.add(String.valueOf(prod.category));
                pr.add(String.valueOf(prod.conditionUsed));
                pr.add(String.valueOf(prod.price));
                pr.add(String.valueOf(prod.discount));
                pr.add(String.valueOf(prod.weight));
                pr.add(String.valueOf(prod.shipmentPlans));
            }
        }

//        PDidEdt.setText(pr.get(0));
        PDNameEdit.setText(pr.get(1));
        PDCategoryEdit.setText(pr.get(2));
        PDConditionEdit.setText(pr.get(3));
        PDPriceEdit.setText(pr.get(4));
//        PDDiscountEdit.setText(pr.get(5));
        PDWeightEdit.setText(pr.get(6));
//        PDShipmentEdit.setText(pr.get(7));
    }


}