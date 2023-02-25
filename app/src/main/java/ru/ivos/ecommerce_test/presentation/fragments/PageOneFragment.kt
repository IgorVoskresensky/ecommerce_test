package ru.ivos.ecommerce_test.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.ivos.ecommerce_test.databinding.FragmentPageOneBinding
import ru.ivos.ecommerce_test.presentation.MainViewModel

@AndroidEntryPoint
class PageOneFragment : Fragment() {

    private var _binding: FragmentPageOneBinding? = null
    private val binding: FragmentPageOneBinding
        get() = _binding ?: throw RuntimeException("Binding is empty")

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFlashSale()
    }
}