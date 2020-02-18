package com.vandana.mvvmexample.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vandana.mvvmexample.R;

public class MainActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    private EditText editUserName;
    private EditText editPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        initUI();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginResult();
            }
        });


        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.loginStatus.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String str) {
                progressBar.setVisibility(View.INVISIBLE);
                System.out.println("onChanged: " + str);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

            }
        });
        Bundle b = getIntent().getExtras();
        if(b!=null) {
            String myString = b.getString("KEY_DATA_EXTRA_FROM_ACTV_B");
            textView.setText(myString);
            // and any other data that the other app sent

        }
    }


    private void loginResult() {

        progressBar.setVisibility(View.VISIBLE);
        String userName = editUserName.getText().toString();
        String password = editPassword.getText().toString();

        loginViewModel.doLogin(userName, password);
    }


    private void initUI() {
        editUserName = findViewById(R.id.et_name);
        editPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.bt_submit);
        progressBar = findViewById(R.id.progress_login);
        progressBar.setVisibility(View.INVISIBLE);
    }


}
