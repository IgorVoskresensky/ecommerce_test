package ru.ivos.ecommerce_test.domain.models

data class DomainDetails(
    val name: String,
    val description: String,
    val rating: Double,
    val numberOfReviews: Int,
    val price: Int,
    val colors: List<String>,
    val imageUrls: List<String>,
)