package ru.ivos.ecommerce_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.ivos.ecommerce_test.databinding.ItemBrandsBinding

class BrandsAdapter : RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>() {

    inner class BrandsViewHolder(private val binding: ItemBrandsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: String){
            binding.apply {
                tvNameBrandsItem.text = word

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(word)
                    }
                }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        return BrandsViewHolder(
            ItemBrandsBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        val word = differ.currentList[position]
        holder.bind(word)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}