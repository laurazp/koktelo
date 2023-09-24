package com.luridevlabs.koktelo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luridevlabs.koktelo.databinding.RowCocktailListItemBinding
import com.luridevlabs.koktelo.model.Drink

class CocktailListAdapter : RecyclerView.Adapter<CocktailListAdapter.CocktailListViewHolder>() {

    private var cocktailList: List<Drink> = emptyList()

    var onClickListener: (Drink) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailListViewHolder {
        val binding = RowCocktailListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cocktailList.size
    }

    override fun onBindViewHolder(holder: CocktailListViewHolder, position: Int) {
        val item = cocktailList[position]

        holder.rootView.setOnClickListener {
            onClickListener.invoke(item)
        }

        holder.nameTextView.text = item.drinkName

        Glide.with(holder.cocktailImageView)
            .load(item.drinkImageUrl)
            .into(holder.cocktailImageView)
    }

    fun submitList(list: List<Drink>) {
        cocktailList = list
        notifyDataSetChanged()
    }

    inner class CocktailListViewHolder(binding: RowCocktailListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val nameTextView = binding.tvCocktailItemName
        val cocktailImageView = binding.ivCocktailItemImage
    }

}