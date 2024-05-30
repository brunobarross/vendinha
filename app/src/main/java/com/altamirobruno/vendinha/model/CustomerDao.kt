package com.altamirobruno.vendinha.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.altamirobruno.vendinha.Customer

@Dao
interface CustomerDao {
    @Insert
    fun insert(customer: Customer)

    //buscar tipo no banco
    @Query("SELECT * FROM customer")
    fun getAll(): List<Customer>
}