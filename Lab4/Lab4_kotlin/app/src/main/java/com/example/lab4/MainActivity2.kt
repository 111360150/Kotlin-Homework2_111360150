package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    private lateinit var setDrink: EditText
    private lateinit var rg1: RadioGroup
    private lateinit var rg2: RadioGroup
    private lateinit var btnSend: Button

    private var sugar: String = "無糖"
    private var iceOpt: String = "去冰"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rg1 = findViewById(R.id.radioGroup)
        rg1.setOnCheckedChangeListener { _, checkedId ->
            sugar = when (checkedId) {
                R.id.radioButton1 -> "無糖(no sugar)"
                R.id.radioButton2 -> "微糖(low sugar)"
                R.id.radioButton3 -> "半糖(half sugar)"
                R.id.radioButton4 -> "全糖(standard)"
                else -> sugar // Default value
            }
        }

        rg2 = findViewById(R.id.radioGroup2)
        rg2.setOnCheckedChangeListener { _, checkedId ->
            iceOpt = when (checkedId) {
                R.id.radioButton5 -> "去冰(no ice)"
                R.id.radioButton6 -> "微冰(low ice)"
                R.id.radioButton7 -> "少冰(less ice)"
                R.id.radioButton8 -> "正常冰(normal)"
                else -> iceOpt // Default value
            }
        }

        btnSend = findViewById(R.id.btn_send)
        btnSend.setOnClickListener {
            setDrink = findViewById(R.id.ed_drink)
            val drink = setDrink.text.toString().trim()

            if (TextUtils.isEmpty(drink)) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent().apply {
                putExtra("drink", drink)
                putExtra("sugar", sugar)
                putExtra("ice", iceOpt)
            }
            setResult(101, intent)
            finish()
        }
    }
}
