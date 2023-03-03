package ru.ivos.ecommerce_test.domain.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "rating")
    val rating: Double = 0.0,
    @ColumnInfo(name = "numberOfReviews")
    val numberOfReviews: Int = 0,
    @ColumnInfo(name = "price")
    val price: Int = 0,
    @ColumnInfo(name = "colors")
    val colors: List<String>,
    @ColumnInfo(name = "imageUrls")
    val imageUrls: List<String>,
)