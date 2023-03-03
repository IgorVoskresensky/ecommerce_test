package ru.ivos.ecommerce_test.presentation.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.FragmentPageOneBinding
import ru.ivos.ecommerce_test.presentation.MainActivity
import ru.ivos.ecommerce_test.presentation.adapters.BrandsAdapter
import ru.ivos.ecommerce_test.presentation.adapters.CategoryAdapter
import ru.ivos.ecommerce_test.presentation.adapters.FlashSaleAdapter
import ru.ivos.ecommerce_test.presentation.adapters.LatestAdapter
import ru.ivos.ecommerce_test.presentation.viewmodels.PageOneViewModel
import ru.ivos.ecommerce_test.utils.PageOneStates
import ru.ivos.ecommerce_test.utils.bitmap
import ru.ivos.ecommerce_test.utils.gone
import ru.ivos.ecommerce_test.utils.visible

@AndroidEntryPoint
class PageOneFragment : Fragment() {

    private var _binding: FragmentPageOneBinding? = null
    private val binding: FragmentPageOneBinding
        get() = _binding ?: throw RuntimeException("Binding is empty")

    private val viewModel by viewModels<PageOneViewModel>()

    private lateinit var flashSaleAdapter: FlashSaleAdapter
    private lateinit var latestAdapter: LatestAdapter
    private lateinit var brandsAdapter: BrandsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var suggestionAdapter: ArrayAdapter<String>

    private var brandList = emptyList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageOneBinding.inflate(inflater, container, false)
        (activity as MainActivity).setBottomNavVisible()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        observeViewModel()
        setProfileImage()
    }

    private fun initAdapters() = with(binding) {
        flashSaleAdapter = FlashSaleAdapter()
        rvFlashSalePageOne.adapter = flashSaleAdapter
        flashSaleAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_pageOneFragment_to_pageTwoFragment)
        }
        latestAdapter = LatestAdapter()
        rvLatestPageOne.adapter = latestAdapter
        brandsAdapter = BrandsAdapter()
        rvBrandsPageOne.adapter = brandsAdapter
        categoryAdapter = CategoryAdapter()
        llCategories.adapter = categoryAdapter
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is PageOneStates.LOADING -> {
                    binding.screenGroupPageOne.gone()
                    binding.pbPageOne.visible()
                }
                is PageOneStates.SUCCESS -> {
                    binding.pbPageOne.gone()
                    binding.screenGroupPageOne.visible()
                    flashSaleAdapter.differ.submitList(it.listFlash)
                    latestAdapter.differ.submitList(it.listLatest)
                    brandsAdapter.differ.submitList(it.listBrands)
                    brandList = it.listBrands
                    suggestionAdapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        brandList
                    )
                    binding.svPageOne.setAdapter(suggestionAdapter)

                    if (it.listFlash.isEmpty() || it.listLatest.isEmpty() || it.listBrands.isEmpty()) {
                        binding.screenGroupPageOne.gone()
                        binding.tvNoInternetPageOne.visible()
                    }
                }
                is PageOneStates.FAILURE -> {
                    binding.screenGroupPageOne.gone()
                    binding.tvNoInternetPageOne.visible()
                    binding.pbPageOne.gone()
                }
            }
        }
    }

    private fun setProfileImage() {
        if (bitmap != null) {
            binding.ivAvatarFillPageOne.setImageBitmap(bitmap)
        } else binding.ivAvatarFillPageOne.setImageBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.profile
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}