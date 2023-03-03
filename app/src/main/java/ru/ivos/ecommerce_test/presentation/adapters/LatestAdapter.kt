package ru.ivos.ecommerce_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ivos.ecommerce_test.databinding.ItemLatestBinding
import ru.ivos.ecommerce_test.domain.models.remote.Latest

class LatestAdapter : RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    inner class LatestViewHolder(private val binding: ItemLatestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(latest: ru.ivos.ecommerce_test.domain.models.remote.Latest){
            binding.apply {
                Glide.with(itemView).load(latest.imageUrl).into(ivItemLatest)
                tvCategoryLatestItem.text = latest.category
                tvNameLatestItem.text = latest.name
                tvPriceLatestItem.text = "$${latest.price}"

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(latest)
                    }
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ru.ivos.ecommerce_test.domain.models.remote.Latest>(){
        override fun areItemsTheSame(oldItem: ru.ivos.ecommerce_test.domain.models.remote.Latest, newItem: ru.ivos.ecommerce_test.domain.models.remote.Latest): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ru.ivos.ecommerce_test.domain.models.remote.Latest, newItem: ru.ivos.ecommerce_test.domain.models.remote.Latest): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        return LatestViewHolder(
            ItemLatestBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val latest = differ.currentList[position]
        holder.bind(latest)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((ru.ivos.ecommerce_test.domain.models.remote.Latest) -> Unit)? = null

    fun setOnItemClickListener(listener: (ru.ivos.ecommerce_test.domain.models.remote.Latest) -> Unit) {
        onItemClickListener = listener
    }
}