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

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnAdd : Button
    lateinit var btnSubtraction : Button
    lateinit var btnMultiplication : Button
    lateinit var btnDivision : Button
    lateinit var btnPorcent : Button
    lateinit var btnResult : Button
    lateinit var etA : EditText
    lateinit var etB : EditText
    lateinit var resultTv : TextView

    // Guarda a operação selecionada: "+", "-", "*", "/", "%"
    private var selectedOp: Char? = null

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

        // IMPORTANT: registrar os listeners
        btnAdd.setOnClickListener(this)
        btnSubtraction.setOnClickListener(this)
        btnMultiplication.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
        btnPorcent.setOnClickListener(this)
        btnResult.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> {
                selectedOp = '+'
                resultTv.text = "Operação: +"
            }
            R.id.btn_subtraction -> {
                selectedOp = '-'
                resultTv.text = "Operação: -"
            }
            R.id.btn_multiplication -> {
                selectedOp = '*'
                resultTv.text = "Operação: ×"
            }
            R.id.btn_division -> {
                selectedOp = '/'
                resultTv.text = "Operação: ÷"
            }
            R.id.btn_porc -> {
                selectedOp = '%'
                resultTv.text = "Operação: % (a * b / 100)"
            }
            R.id.btn_result -> {
                // Ao clicar em "=", lemos os valores e aplicamos a operação selecionada
                val a = etA.text.toString().toDoubleOrNull()
                val b = etB.text.toString().toDoubleOrNull()

                if (a == null || b == null) {
                    resultTv.text = "Digite números válidos."
                    return
                }

                val op = selectedOp
                if (op == null) {
                    resultTv.text = "Selecione uma operação antes de '='."
                    return
                }

                val result = when (op) {
                    '+' -> a + b
                    '-' -> a - b
                    '*' -> a * b
                    '/' -> {
                        if (b == 0.0) {
                            resultTv.text = "Erro: divisão por zero."
                            return
                        } else a / b
                    }
                    '%' -> (a * b) / 100.0
                    else -> {
                        resultTv.text = "Operação desconhecida."
                        return
                    }
                }

                // Mostra o resultado com formatação simples (remove .0 se inteiro)
                val text = if (result % 1.0 == 0.0) {
                    result.toLong().toString()
                } else {
                    result.toString()
                }
                resultTv.text = "Resultado: $text"
            }
        }
    }
}
