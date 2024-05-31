package com.altamirobruno.vendinha

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phone") val phone : String,
    @ColumnInfo(name = "address") val address : String,
    @ColumnInfo(name = "complement") val complement : String,
    @ColumnInfo(name = "created_date") val createdAt: Date = Date()

)

