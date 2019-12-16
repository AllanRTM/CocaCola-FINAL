package com.example.supercookie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Factura extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "direccion";
    ListView lvSummary;
    TextView tvTotal;
    Double Total=0d;
    ArrayList<Product> productOrders = new ArrayList<>();

    EditText di;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        lvSummary = findViewById(R.id.lvSummary);
        tvTotal = findViewById(R.id.tvTotal);
        getOrderItemData();

    }
    private void getOrderItemData() {
        Bundle extras = getIntent().getExtras();
        if(extras !=null )
        {
            String orderItems = extras.getString("orderItems",null);
            if(orderItems!=null && orderItems.length()>0)
            {
                try{
                    JSONArray jsonOrderItems = new JSONArray(orderItems);
                    for(int i=0;i<jsonOrderItems.length();i++)
                    {
                        JSONObject jsonObject = new JSONObject(jsonOrderItems.getString(i));
                        Product product = new Product(
                                jsonObject.getString("ProductName")
                                ,jsonObject.getDouble("ProductPrice")
                                ,jsonObject.getInt("ProductImage")
                        );
                        product.CartQuantity = jsonObject.getInt("CartQuantity");
                        /* Calculate Total */
                        Total = Total + (product.CartQuantity * product.ProductPrice);
                        productOrders.add(product);
                    }

                    if(productOrders.size() > 0)
                    {
                        ListAdapter listAdapter = new ListAdapter(this,productOrders);
                        lvSummary.setAdapter(listAdapter);
                        tvTotal.setText("TOTAL A PAGAR: "+Total +" LPS");
                    }
                    else
                    {
                        showMessage("Empty");
                    }
                }
                catch (Exception e)
                {
                    showMessage(e.toString());
                }
            }

        }
    }



    public void showMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    public void pagar(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);

        intent.putExtra("direccion ",R.id.direccion);
        EditText editText=(EditText) findViewById(R.id.direccion);
        String mensaje= editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE,mensaje);
        startActivity(intent);

    }

}
