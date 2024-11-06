package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    private EditText set_drink;
    private RadioGroup rg1, rg2;
    private Button btn_send;

    private String sugar = "無糖";
    private String ice_opt = "去冰";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rg1 = findViewById(R.id.radioGroup);
        rg1.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.radioButton1) {
                sugar = "無糖(no sugar)";
            } else if (i == R.id.radioButton2) {
                sugar = "微糖(low sugar)";
            } else if (i == R.id.radioButton3) {
                sugar = "半糖(half sugar)";
            } else if (i == R.id.radioButton4) {
                sugar = "全糖(standard)";
            }
        });

        rg2 = findViewById(R.id.radioGroup2);
        rg2.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.radioButton5) {
                ice_opt = "去冰(no ice)";
            } else if (i == R.id.radioButton6) {
                ice_opt = "微冰(low ice)";
            } else if (i == R.id.radioButton7) {
                ice_opt = "少冰(less ice)";
            } else if (i == R.id.radioButton8) {
                ice_opt = "正常冰(normal)";
            }
        });

        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(view -> {
            set_drink = findViewById(R.id.ed_drink);
            String drink = set_drink.getText().toString().trim();

            if (TextUtils.isEmpty(drink)) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent i = new Intent();
            Bundle b = new Bundle();
            b.putString("drink", drink);
            b.putString("sugar", sugar);
            b.putString("ice", ice_opt);
            i.putExtras(b);
            setResult(101, i);
            finish();
        });
    }
}
