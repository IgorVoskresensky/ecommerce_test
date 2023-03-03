package ru.ivos.ecommerce_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ivos.ecommerce_test.databinding.ItemFlashSaleBinding
import ru.ivos.ecommerce_test.domain.models.remote.FlashSale

class FlashSaleAdapter : RecyclerView.Adapter<FlashSaleAdapter.FlashSaleViewHolder>() {

    inner class FlashSaleViewHolder(private val binding: ItemFlashSaleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flashSale: FlashSale){
            binding.apply {
                Glide.with(itemView).load(flashSale.imageUrl).into(ivItemFlashSale)
                tvCategoryFlashSaleItem.text = flashSale.category
                tvNameFlashSaleItem.text = flashSale.name
                tvPriceFlashSaleItem.text = "$${flashSale.price}"
                tvDiscountFlashSaleItem.text = "${flashSale.discount}% off"

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(flashSale)
                    }
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<FlashSale>(){
        override fun areItemsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleViewHolder {
        return FlashSaleViewHolder(
            ItemFlashSaleBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: FlashSaleViewHolder, position: Int) {
        val flashSale = differ.currentList[position]
        holder.bind(flashSale)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((FlashSale) -> Unit)? = null

    fun setOnItemClickListener(listener: (FlashSale) -> Unit) {
        onItemClickListener = listener
    }
}