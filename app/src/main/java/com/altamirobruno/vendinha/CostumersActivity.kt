package com.altamirobruno.vendinha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class CostumersActivity : AppCompatActivity() {
    private lateinit var customer_rv : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_costumers)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val customers = mutableListOf<Customer>()
        val adapter = CustomerAdapter(customers)
        customer_rv = findViewById(R.id.customer_rv)
        customer_rv.layoutManager = LinearLayoutManager(this)
        customer_rv.adapter = adapter

        Thread{
            val app = application as App
            val dao = app.db.customerDao()
            val response = dao.getAll()
            customers.addAll(response)
            adapter.notifyDataSetChanged()

        }.start()


    }


    private inner class CustomerAdapter(private val customers: MutableList<Customer>) :
        RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            fun bind(item : Customer){
                val nameCustomer = itemView.findViewById<TextView>(R.id.customer_name)
                val simpleDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "br"))
                val data = simpleDate.format(item.createdAt)
                nameCustomer.text = item.name
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.costumer_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return customers.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemCurrent = customers[position]
            holder.bind(itemCurrent)


        }
    }

}