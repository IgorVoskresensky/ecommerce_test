package ru.ivos.ecommerce_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ivos.ecommerce_test.databinding.ItemDetailsBinding

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    inner class DetailsViewHolder(private val binding: ItemDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(url: String){
            binding.apply {
                Glide.with(itemView).load(url).into(ivDetailsItem)
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(
            ItemDetailsBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val url = differ.currentList[position]
        holder.bind(url)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}