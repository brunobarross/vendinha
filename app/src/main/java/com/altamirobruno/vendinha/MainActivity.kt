package com.altamirobruno.vendinha

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dataSet = mutableListOf<DataSet>()
        dataSet.add(
            DataSet(
                id = 1,
                group =  R.string.clientes,
                icon = R.drawable.person_add_outline,
                name = R.string.novo_cliente

            )
        )
        dataSet.add(
            DataSet(
                id = 2,
                group = R.string.clientes,
                icon = R.drawable.people_outline,
                name = R.string.ver_todos

            )
        )
        val adapter = MainAdapter(dataSet){ id ->
            println(id)
            when(id){
                1 -> {
                    val intent = Intent(this@MainActivity, CustomerFormActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this@MainActivity, CostumersActivity::class.java)
                    startActivity(intent)
                }
            }
            Log.i("Teste", "clicou $id!!")

        }

        val recyclerView: RecyclerView = findViewById(R.id.main_rv)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2, 48, true))


    }

    private inner class MainAdapter
        (
        private val dataSet: MutableList<DataSet>,
        private val onItemClickListener: (Int) -> Unit,
    ) :
        RecyclerView.Adapter<MainAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: DataSet) {
                val group: TextView = itemView.findViewById(R.id.main_item_group)
                val name: TextView = itemView.findViewById(R.id.main_item_text)
                val icon: ImageView = itemView.findViewById(R.id.main_item_icon)
                val container: ConstraintLayout = itemView.findViewById(R.id.main_item_wrapper)
                name.setText(item.name)
                icon.setImageResource(item.icon)
                container.setOnClickListener{
                    onItemClickListener.invoke(item.id)
                }


            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemCurrent = dataSet[position]
            holder.bind(itemCurrent)


        }

    }
}

