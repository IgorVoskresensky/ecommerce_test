package ru.ivos.ecommerce_test.domain.models

data class DomainFlashSale(
    val category: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val discount: Int = 0,
    val imageUrl: String = ""
)
