package com.example.supercookie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "mensaje";
    private ListView listView;
    private ListAdapter listAdapter;
    ArrayList<Product> products = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<Product> productOrders = new ArrayList<>();

    ArrayList<String> lOrderItems = new ArrayList<String>();
    //firebase
    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener AutoListener;

    //FIREBASE
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.AnonymousBuilder().build());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //firebase
        mAuth = getInstance();
        AutoListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "Usuario Ingresado!!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setIsSmartLockEnabled(false)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
        //COCA COLA

        getProduct();
        listView = (ListView) findViewById(R.id.customListView);
        listAdapter = new ListAdapter(this,products);
        listView.setAdapter(listAdapter);

        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(v -> placeOrder());

    }


    public void getProduct() {
        products.add(new Product("Coca-cola en Lata",100.0d,R.drawable.primer));
        products.add(new Product("Coca-cola Mini",10.0d,R.drawable.segunda));
        products.add(new Product("Coca-cola Portatil",10.0d,R.drawable.tercera));
        products.add(new Product("Coca-cola Medio Litro",10.0d,R.drawable.cuarta));
        products.add(new Product("Coca-cola Litro",10.0d,R.drawable.quinta));
        products.add(new Product("Coca-cola 1.1 Litros",10.0d,R.drawable.sexta));
        products.add(new Product("Coca-cola 1.25 Litros",10.0d,R.drawable.septima));
        products.add(new Product("Coca-cola 1.5 Litros",10.0d,R.drawable.octava));
        products.add(new Product("Coca-cola Dos Litros",10.0d,R.drawable.novena));
        products.add(new Product("Coca-cola Dos y medio Litros",10.0d,R.drawable.decima));
        products.add(new Product("Coca-cola Tres Litros",10.0d,R.drawable.eleven));
        products.add(new Product("Coca-cola 12oz Lata",10.0d,R.drawable.dieta));
        products.add(new Product("Coca-cola Medio Litro",10.0d,R.drawable.dieta1));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.dieta2));

        products.add(new Product("Coca-cola lata",10.0d,R.drawable.ligth));
        products.add(new Product("Coca-cola Medio Litro",10.0d,R.drawable.ligth1));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.ligth2));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.sprite));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.sprint2));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.sprint3));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.power));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.cana_dry_medio));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.fanta_medio_litro));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.fanta_dos_litros));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.veinte));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.vienteuno));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.veintidos));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.veintetres));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.veintecuatro));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.veinticinco));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.veintiseis));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.del_valle_500_ml));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.del_valle_2_lt));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.del_valle_250_ml));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.maltapet));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.trece));

        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.catorce));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.quince));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.diecices));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.diecisiete));
        products.add(new Product("Coca-cola 2 litros",10.0d,R.drawable.diecinueve));
    }

    private void placeOrder()
    {
        productOrders.clear();
        lOrderItems.clear();

        for(int i=0;i<listAdapter.listProducts.size();i++)
        {
            if(listAdapter.listProducts.get(i).CartQuantity > 0)
            {
                Product products = new Product(
                        listAdapter.listProducts.get(i).ProductName
                        ,listAdapter.listProducts.get(i).ProductPrice
                        ,listAdapter.listProducts.get(i).ProductImage
                );

                products.CartQuantity = listAdapter.listProducts.get(i).CartQuantity;
                productOrders.add(products);

                lOrderItems.add(products.getJsonObject().toString());

            }
        }


        JSONArray jsonArray = new JSONArray(lOrderItems);
        openSummary(jsonArray.toString());



    }

    //COCA COLA
 /* public void openSummary(String orderItems)
  {
    Intent summaryIntent = new Intent(this,Summary.class);
    summaryIntent.putExtra("orderItems",orderItems);
    startActivity(summaryIntent);
  }*/

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void openSummary(String orderItems)
    {
        Intent summaryIntent = new Intent(this, Factura.class);
        summaryIntent.putExtra("orderItems",orderItems);
        startActivity(summaryIntent);
    }
    //firebase
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(AutoListener);

    }

    protected void onPause(){
        super.onPause();
        mAuth.removeAuthStateListener(AutoListener);


    }
    public void signout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Saliste Exitosamente!!", Toast.LENGTH_SHORT).show();

                    }
                });
        finish();
    }



}
