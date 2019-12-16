package com.example.supercookie;

import android.util.Log;
import java.util.UUID;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ConfigHelper {

  public static final String GOOGLE_PAY_MERCHANT_ID = "sandbox-sq0idb-5mku5ctu2dNfnyKSNTdENQ";

  private static final String CHARGE_SERVER_HOST = "https://coca-cola-service.herokuapp.com/chargeForCookie";
  private static final String CHARGE_SERVER_URL = "https://" + CHARGE_SERVER_HOST + "/";

  public static boolean serverHostSet() {
    return !CHARGE_SERVER_HOST.equals("https://coca-cola-service.herokuapp.com/chargeForCookie");
  }

  public static boolean merchantIdSet() {
    return !GOOGLE_PAY_MERCHANT_ID.equals("sandbox-sq0idb-5mku5ctu2dNfnyKSNTdENQ");
  }

  public static void printCurlCommand(String nonce) {
    String uuid = UUID.randomUUID().toString();
    Log.i("ExampleApplication",
        "Run this curl command to charge the nonce:\n"
            + "curl --request POST https://connect.squareupsandbox.com/v2/payments \\\n"
            + "--header \"Content-Type: application/json\" \\\n"
            + "--header \"Authorization: Bearer sandbox-sq0idb-5mku5ctu2dNfnyKSNTdENQ\" \\\n"
            + "--header \"Accept: application/json\" \\\n"
            + "--data \'{\n"
            + "\"idempotency_key\": \"" + uuid + "\",\n"
            + "\"amount_money\": {\n"
            + "\"amount\": 100,\n"
            + "\"currency\": \"USD\"},\n"
            + "\"source_id\": \"" + nonce + "\""
            + "}\'");
  }

  public static Retrofit createRetrofitInstance() {
    return new Retrofit
        .Builder()
        .baseUrl(ConfigHelper.CHARGE_SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
  }
}
