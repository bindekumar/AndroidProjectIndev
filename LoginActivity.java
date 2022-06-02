package com.indev.ari_tracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.indev.ari_tracker.Database.SharedPrefHelper;
import com.indev.ari_tracker.R;
import com.indev.ari_tracker.RestAPI.Ari_Tracker;
import com.indev.ari_tracker.RestAPI.ClientAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username) EditText et_username;
    @BindView(R.id.et_password) EditText et_password;
    @BindView(R.id.show_pass_btn) ImageView show_pass_btn;

    SharedPrefHelper sharedPrefHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //  getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.appcolor));
        ButterKnife.bind(this);
        sharedPrefHelper=new SharedPrefHelper(this);
    }

    public void login_btn(View view) {

        if (checkValidation()) {
            callLoginAPI(et_username.getText().toString().trim(),et_password.getText().toString().trim());
        }
    }

    private boolean checkValidation() {
        if (et_username.getText().toString().trim().length() == 0) {
            et_username.setError("Please enter username!");
            et_username.requestFocus();
            return false;
        }

        if (et_password.getText().toString().trim().length() == 0) {
            et_password.setError("Please enter password!");
            et_password.requestFocus();
            return false;
        }
        return true;
    }

    public void ShowHidePass(@NonNull View view){
        if (view.getId() == R.id.show_pass_btn){

            if (et_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                show_pass_btn.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                //Show Password
                et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                show_pass_btn.setImageResource(R.drawable.ic_baseline_visibility_24);
                //Hide Password
                et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    private void callLoginAPI(String id,String pass){
        try{
            JSONObject data=new JSONObject();
            data.put("user_name",id);
            data.put("user_password",pass);
            MediaType JOSON= MediaType.parse("application/json;charset=utf-8");
            RequestBody body=RequestBody.create(JOSON,String.valueOf(data));
            ClientAPI.getClient().create(Ari_Tracker.class).LoginAPI(body).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        Log.d("1111111111111", String.valueOf(jsonObject));
                        Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
                        sharedPrefHelper.setString("login_check", "yes");
                        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                    }
                }
                public void onFailure(Call<JsonObject> call, Throwable t) {
                 Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.getStackTrace();
        }
    }

}