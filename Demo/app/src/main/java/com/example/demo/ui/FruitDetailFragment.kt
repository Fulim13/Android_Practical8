package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.demo.data.Category
import com.example.demo.data.CategoryVM
import com.example.demo.data.FruitVM
import com.example.demo.databinding.FragmentFruitDetailBinding
import com.example.demo.util.setImageBlob

class FruitDetailFragment : Fragment() {

    private lateinit var binding: FragmentFruitDetailBinding
    private val nav by lazy { findNavController() }
    private val fruitId by lazy { arguments?.getString("fruitId") ?: "" }

    private val categoryVM: CategoryVM by activityViewModels()
    private val fruitVM: FruitVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentFruitDetailBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        binding.btnBack.setOnClickListener { nav.navigateUp() }

        val fruit = fruitVM.get(fruitId)
        if (fruit == null) {
            nav.navigateUp()
            return null
        }

        // TODO(10): Populate [category]
        fruit.category = categoryVM.get(fruit.categoryId) ?: Category()

        binding.imgPhoto.setImageBlob(fruit.photo)
        binding.txtId.text = fruit.id
        binding.txtName.text = fruit.name
        binding.txtPrice.text = "%.2f".format(fruit.price)
        binding.txtCategory.text = "${fruit.category.name} (${fruit.category.id})"

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

}