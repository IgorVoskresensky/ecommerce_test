package ru.ivos.ecommerce_test.presentation.fragments

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.FragmentPageTwoBinding
import ru.ivos.ecommerce_test.domain.constants.Constants
import ru.ivos.ecommerce_test.domain.models.remote.Details
import ru.ivos.ecommerce_test.presentation.adapters.DetailsAdapter
import ru.ivos.ecommerce_test.presentation.viewmodels.PageTwoViewModel
import ru.ivos.ecommerce_test.utils.*

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
    private lateinit var colorOne : MaterialButton
    private lateinit var colorTwo : MaterialButton
    private lateinit var colorThree : MaterialButton
    private lateinit var share: ImageView
    private lateinit var addToFavorite: ImageView
    private lateinit var removeFavorite: ImageView
    private lateinit var plus : AppCompatButton
    private lateinit var minus : AppCompatButton
    private lateinit var addToCard : AppCompatButton

    private var dynamicSum = 0.0
    private var quantityCount = 0

    private lateinit var colorState: ColorStateList

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
        setupColorState()
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
        share = btnSharePageTwo
        addToFavorite = btnAddToFavorites
        removeFavorite = btnRemovefromFavorites
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
                    price.text = "$${details.price}"
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
            sum.text = "$${dynamicSum}"
        }
        minus.setOnClickListener {
            if(quantityCount > 0) {
                --quantityCount
            }
            quantity.text = "Quantity: ${quantityCount}"
            dynamicSum -= details.price
            if(dynamicSum <= 0 ){
                dynamicSum = 0.0
                sum.text = "$${dynamicSum}"
            } else {
                sum.text = "$${dynamicSum}"
            }
        }
        addToCard.setOnClickListener {
            findNavController().navigate(R.id.action_pageTwoFragment_to_cartFragment)
        }
        share.setOnClickListener {
            val shareLink = "${Constants.BASE_URL}f7f99d04-4971-45d5-92e0-70333383c239"
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareLink)
                type = "text/plain"
            }
            startActivity(intent)
        }
        addToFavorite.setOnClickListener {
            viewModel.insertFavorite(mapDetailsToFavorite(details))
            addToFavorite.gone()
            removeFavorite.visible()
        }
        removeFavorite.setOnClickListener {
            viewModel.deleteFavorite(mapDetailsToFavorite(details).name)
            removeFavorite.gone()
            addToFavorite.visible()
        }
        colorOne.setOnClickListener {
            colorOne.strokeColor = colorState
            colorTwo.isChecked = false
            colorThree.isChecked = false
        }
        colorTwo.setOnClickListener {
            colorTwo.strokeColor = colorState
            colorOne.isChecked = false
            colorThree.isChecked = false
        }
        colorThree.setOnClickListener {
            colorThree.strokeColor = colorState
            colorTwo.isChecked = false
            colorOne.isChecked = false
        }
    }

    private fun setupColorState() {
        colorState = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked),
            ),
            intArrayOf(
                android.R.color.transparent,
                Color.parseColor("#ADADAD")
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}