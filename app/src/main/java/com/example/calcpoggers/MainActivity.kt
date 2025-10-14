package com.example.calcpoggers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var btnAdd : Button
    lateinit var btnSubtraction : Button
    lateinit var btnMultiplication : Button
    lateinit var btnDivision : Button
    lateinit var btnPorcent : Button
    lateinit var btnResult : Button
    lateinit var etA : EditText
    lateinit var etB : EditText
    lateinit var resultTv : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btn_add)
        btnSubtraction = findViewById(R.id.btn_subtraction)
        btnMultiplication = findViewById(R.id.btn_multiplication)
        btnDivision = findViewById(R.id.btn_division)
        btnPorcent = findViewById(R.id.btn_porc)
        btnResult = findViewById(R.id.btn_result)
        etA = findViewById(R.id.et_a)
        etB = findViewById(R.id.et_b)
        resultTv = findViewById(R.id.result_tv)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {
        var a = etA.text.toString().toDouble()
        var b = etB.text.toString().toDouble()
        var result = 0.0

        when(v?.id){
            R.id.btn_add ->{
                result = a+b
            }
            R.id.btn_subtraction ->{
                result = a-b
            }
            R.id.btn_multiplication ->{
                result = a*b
            }
            R.id.btn_division ->{
                result = a/b
            }
            R.id.btn_porc ->{
                result = (a*b)/100
            }
            R.id.btn_result ->{
                resultTv.text = "Result is $result"
            }

        }



    }
}