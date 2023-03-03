package ru.ivos.ecommerce_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    /**
     * В задании указано, что список категорий должен скроллиться и что все категории
     * можно взять из макета.
     * В макете 6 категорий, все 6 из которых помещаются на экран как в вёрстке,
     * так и в самом макете, поэтому я просто повторил их все по два раза
     */
    private val titles = listOf(
        "Phones", "Headphones", "Games", "Cars", "Furniture", "Kids",
        "Phones", "Headphones", "Games", "Cars", "Furniture", "Kids"
    )

    private val images = listOf(
        R.drawable.ic_category_phone,
        R.drawable.ic_category_headphones,
        R.drawable.ic_category_games,
        R.drawable.ic_category_cars,
        R.drawable.ic_category_furniture,
        R.drawable.ic_category_kids,
        R.drawable.ic_category_phone,
        R.drawable.ic_category_headphones,
        R.drawable.ic_category_games,
        R.drawable.ic_category_cars,
        R.drawable.ic_category_furniture,
        R.drawable.ic_category_kids
    )

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String, res: Int){
            binding.apply {
                ivItemCategory.setImageResource(res)
                tvItemCategory.text = title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val title = titles[position]
        val res = images[position]
        holder.bind(title, res)
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}