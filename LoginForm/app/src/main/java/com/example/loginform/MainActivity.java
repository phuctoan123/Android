package com.example.loginform;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Vibrator;
import android.os.VibrationEffect;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private Button loginBtn;
    private ImageView facebookBtn, instagramBtn;

    private Vibrator vibrator;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Mapping to file xml
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginBtn = findViewById(R.id.login_btn);
        facebookBtn = findViewById(R.id.facebook_btn);
        instagramBtn = findViewById(R.id.instagram_btn);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Hàm rung nhẹ
        View.OnClickListener vibrateClickListener = view -> {
            if (vibrator != null && vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createOneShot(
                        40, // thời gian rung (ms)
                        VibrationEffect.DEFAULT_AMPLITUDE // cường độ
                ));
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Empty information !", Toast.LENGTH_SHORT).show();
                } else {

                    if (username.equals("admin") && password.equals("123456")) {
                        Toast.makeText(MainActivity.this, "Login sucessfully!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Wrong username or password, please type again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // Xử lý click Facebook
        facebookBtn.setOnClickListener(v -> {
            vibrateClickListener.onClick(v);
            Toast.makeText(MainActivity.this, "Login bằng Facebook!", Toast.LENGTH_SHORT).show();
        });

        // Xử lý click Instagram
        instagramBtn.setOnClickListener(v -> {
            vibrateClickListener.onClick(v);
            Toast.makeText(MainActivity.this, "Login bằng Instagram!", Toast.LENGTH_SHORT).show();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}