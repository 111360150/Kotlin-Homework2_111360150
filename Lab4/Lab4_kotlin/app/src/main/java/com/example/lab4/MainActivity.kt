package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tvMeal: TextView
    private lateinit var btnSelect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMeal = findViewById(R.id.tv_meal)
        btnSelect = findViewById(R.id.btn_choice)
        btnSelect.setOnClickListener {
            startActivityForResult(Intent(this, MainActivity2::class.java), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) {
            Toast.makeText(this, "未選擇任何選項", Toast.LENGTH_SHORT).show()
            return
        }

        if (requestCode == 1 && resultCode == 101) {
            val bundle = data.extras
            bundle?.let {
                val str1 = it.getString("drink", "無飲料")
                val str2 = it.getString("sugar", "無甜度")
                val str3 = it.getString("ice", "無冰塊")
                tvMeal.text = String.format("飲料: %s\n\n甜度: %s\n\n冰塊: %s", str1, str2, str3)
            }
        }
    }
}
