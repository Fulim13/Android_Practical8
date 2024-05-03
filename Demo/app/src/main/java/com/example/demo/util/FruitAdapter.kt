package com.example.demo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.data.Fruit
import com.example.demo.databinding.ItemFruitBinding

class FruitAdapter (
    val fn: (ViewHolder, Fruit) -> Unit = { _, _ -> }
) : ListAdapter<Fruit, FruitAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Fruit>() {
        override fun areItemsTheSame(a: Fruit, b: Fruit) = a.id == b.id
        override fun areContentsTheSame(a: Fruit, b: Fruit) = a == b
    }

    class ViewHolder(val binding: ItemFruitBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = getItem(position)

        holder.binding.imgPhoto.setImageBlob(fruit.photo)
        holder.binding.txtId.text = fruit.id
        holder.binding.txtName.text = fruit.name
        holder.binding.txtPrice.text = "%.2f".format(fruit.price)
        holder.binding.txtCategory.text = fruit.category.name

        fn(holder, fruit)
    }

}