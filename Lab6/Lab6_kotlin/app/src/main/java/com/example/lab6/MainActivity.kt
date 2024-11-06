package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val items = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupEdgeToEdgePadding()

        // 設定各按鈕的點擊事件
        findViewById<Button>(R.id.btnToast).setOnClickListener {
            showToast("預設 Toast")
        }

        findViewById<Button>(R.id.btnSnackBar).setOnClickListener {
            showSnackBar(it, "按鈕式 Snackbar", "按鈕") {
                showToast("已回應")
            }
        }

        findViewById<Button>(R.id.btnDialog1).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("按鈕式 AlertDialog")
                .setMessage("AlertDialog 內容")
                .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
                .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
                .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
                .show()
        }

        findViewById<Button>(R.id.btnDialog2).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("列表式 AlertDialog")
                .setItems(items) { _, index -> showToast("你選的是「${items[index]}」") }
                .show()
        }

        findViewById<Button>(R.id.btnDialog3).setOnClickListener {
            var selectedPosition = 0
            AlertDialog.Builder(this)
                .setTitle("單選式 AlertDialog")
                .setSingleChoiceItems(items, selectedPosition) { _, index -> selectedPosition = index }
                .setPositiveButton("確定") { _, _ -> showToast("你選的是〈${items[selectedPosition]}〉") }
                .show()
        }
    }

    private fun setupEdgeToEdgePadding() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSnackBar(view: android.view.View, message: String, actionText: String, action: () -> Unit) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction(actionText) {
            action()
        }.show()
    }
}
