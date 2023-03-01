package ru.ivos.ecommerce_test.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.FragmentPageTwoBinding
import ru.ivos.ecommerce_test.domain.models.remote.Details
import ru.ivos.ecommerce_test.presentation.adapters.DetailsAdapter
import ru.ivos.ecommerce_test.presentation.viewmodels.PageTwoViewModel
import ru.ivos.ecommerce_test.utils.PageTwoStates
import ru.ivos.ecommerce_test.utils.gone
import ru.ivos.ecommerce_test.utils.visible

@AndroidEntryPoint
class PageTwoFragment : Fragment() {


    private var _binding: FragmentPageTwoBinding? = null
    private val binding: FragmentPageTwoBinding
        get() = _binding ?: throw RuntimeException("Binding is empty")

    private val viewModel by viewModels<PageTwoViewModel>()

    private lateinit var details: Details

    private lateinit var adapter: DetailsAdapter
    private lateinit var image : ImageView
    private lateinit var name : TextView
    private lateinit var price : TextView
    private lateinit var description : TextView
    private lateinit var rating : TextView
    private lateinit var reviews : TextView
    private lateinit var quantity : TextView
    private lateinit var sum : TextView
    private lateinit var colorOne : ImageView
    private lateinit var colorTwo : ImageView
    private lateinit var colorThree : ImageView
    private lateinit var plus : AppCompatButton
    private lateinit var minus : AppCompatButton
    private lateinit var addToCard : AppCompatButton

    private var dynamicSum = 0.0
    private var quantityCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
        setupClickListeners()
    }

    private fun initViews() = with(binding) {
        image = ivImagePageTwo
        name = tvNamePageTwo
        price = tvPricePageTwo
        description = tvDescriptionPageTwo
        rating = tvRatingValuePageTwo
        reviews = tvReviewsValuePageTwo
        quantity = tvQuantityPageTwo
        sum = tvSumPageTwo
        colorOne = ivColorOnePageTwo
        colorTwo = ivColorTwoPageTwo
        colorThree = ivColorThreePageTwo
        plus = btnPlusPageTwo
        minus = btnMinusPageTwo
        addToCard = btnAddToCartPageTwo
        adapter = DetailsAdapter()
        crvPageTwo.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is PageTwoStates.LOADING -> {
                    binding.screenGroupPageTwo.gone()
                    binding.pbPageTwo.visible()
                }
                is PageTwoStates.SUCCESS -> {
                    binding.screenGroupPageTwo.visible()
                    binding.pbPageTwo.gone()
                    details = it.details
                    Glide.with(image).load(details.imageUrls.first()).into(image)
                    adapter.differ.submitList(it.details.imageUrls)
                    name.text = details.name
                    price.text = details.price.toString()
                    description.text = details.description
                    rating.text = details.rating.toString()
                    reviews.text = "(${details.numberOfReviews} reviews)"
                    colorOne.background.setTint(Color.parseColor(details.colors[0]))
                    colorTwo.background.setTint(Color.parseColor(details.colors[1]))
                    colorThree.background.setTint(Color.parseColor(details.colors[2]))
                }
                is PageTwoStates.FAILURE -> {
                    binding.screenGroupPageTwo.gone()
                    binding.pbPageTwo.gone()
                    binding.tvNoInternetPageTwo.visible()
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnBackPageTwo.setOnClickListener {
            findNavController().navigate(R.id.action_pageTwoFragment_to_pageOneFragment)
        }
        binding.crvPageTwo.setItemSelectListener(object : CarouselLayoutManager.OnSelected{
            override fun onItemSelected(position: Int) {
                Glide.with(image).load(adapter.differ.currentList[position]).into(image)
            }
        })
        plus.setOnClickListener {
            quantity.text = "Quantity: ${++quantityCount}"
            dynamicSum += details.price
            sum.text = dynamicSum.toString()
        }
        minus.setOnClickListener {
            if(quantityCount > 0) {
                --quantityCount
            }
            quantity.text = "Quantity: ${quantityCount}"
            dynamicSum -= details.price
            if(dynamicSum <= 0 ){
                dynamicSum = 0.0
                sum.text = dynamicSum.toString()
            } else {
                sum.text = dynamicSum.toString()
            }
        }
        addToCard.setOnClickListener {
            findNavController().navigate(R.id.action_pageTwoFragment_to_cartFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}