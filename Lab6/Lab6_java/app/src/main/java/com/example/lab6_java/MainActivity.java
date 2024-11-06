package com.example.lab6_java;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final String[] items = {"選項 1", "選項 2", "選項 3", "選項 4", "選項 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupEdgeToEdgePadding();

        // 設定各按鈕的點擊事件
        findViewById(R.id.btnToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("預設 Toast");
            }
        });

        findViewById(R.id.btnSnackBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(v, "按鈕式 Snackbar", "按鈕", new Runnable() {
                    @Override
                    public void run() {
                        showToast("已回應");
                    }
                });
            }
        });

        findViewById(R.id.btnDialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("按鈕式 AlertDialog")
                        .setMessage("AlertDialog 內容")
                        .setNeutralButton("左按鈕", (dialog, which) -> showToast("左按鈕"))
                        .setNegativeButton("中按鈕", (dialog, which) -> showToast("中按鈕"))
                        .setPositiveButton("右按鈕", (dialog, which) -> showToast("右按鈕"))
                        .show();
            }
        });

        findViewById(R.id.btnDialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("列表式 AlertDialog")
                        .setItems(items, (dialog, index) -> showToast("你選的是「" + items[index] + "」"))
                        .show();
            }
        });

        findViewById(R.id.btnDialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] selectedPosition = {0};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("單選式 AlertDialog")
                        .setSingleChoiceItems(items, selectedPosition[0], (dialog, index) -> selectedPosition[0] = index)
                        .setPositiveButton("確定", (dialog, which) -> showToast("你選的是〈" + items[selectedPosition[0]] + "〉"))
                        .show();
            }
        });
    }

    private void setupEdgeToEdgePadding() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSnackBar(View view, String message, String actionText, Runnable action) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction(actionText, v -> action.run())
                .show();
    }
}
