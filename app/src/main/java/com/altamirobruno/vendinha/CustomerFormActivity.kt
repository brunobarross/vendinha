package com.altamirobruno.vendinha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CustomerFormActivity : AppCompatActivity() {
    private lateinit var inptName : EditText
    private lateinit var inptTelefone : EditText
    private lateinit var inptEndereco : EditText
    private lateinit var inptComplemento : EditText
    private lateinit var buttonSave : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_customer_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inptName = findViewById(R.id.inpt_name_customer)
        inptTelefone = findViewById(R.id.inpt_telefone_customer)
        inptEndereco = findViewById(R.id.inpt_endereco_customer)
        inptComplemento = findViewById(R.id.inpt_complemento_customer)
        buttonSave = findViewById(R.id.button_save_customer)
        buttonSave.setOnClickListener{
           val isValid = validate()
            if(!isValid) {
                Toast.makeText(this, R.string.erro_cadastro_cliente, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Thread{
                val app = application as App
                val dao = app.db.customerDao()
                dao.insert(
                    Customer(
                        name = inptName.text.toString(),
                        phone = inptTelefone.text.toString(),
                        address = inptEndereco.text.toString(),
                        complement = inptComplemento.text.toString()
                    )
                )
                runOnUiThread{
                    val intent = Intent(this@CustomerFormActivity, CostumersActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }.start()
        }

    }

    private fun validate() : Boolean{
        if(!inptName.text.toString().isEmpty()  && !inptTelefone.text.toString().isEmpty() && !inptEndereco.text.toString().isEmpty() && !inptComplemento.text.toString().isEmpty()){
            return true
        }
        return false
    }
}