package com.dsterwz.dbtest_legend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dsterwz.dbtest_legend.models.FoodApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private FoodApi foodApi;

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    private void init() {
        editTextEmail = findViewById(R.id.edit_text_email_s);
        editTextPassword = findViewById(R.id.edit_text_password_s);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://food.madskill.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        foodApi = retrofit.create(FoodApi.class);
    }

    public void onClickLogin(View view) {
    }

    private boolean checkEmail(String email) {
        return email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,3})$");
    }
}