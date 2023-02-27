package ru.ivos.ecommerce_test.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
