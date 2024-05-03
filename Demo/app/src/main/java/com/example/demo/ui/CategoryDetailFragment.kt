package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demo.data.CategoryVM
import com.example.demo.data.FruitVM
import com.example.demo.databinding.FragmentCategoryDetailBinding
import com.example.demo.util.FruitAdapter

class CategoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCategoryDetailBinding
    private val nav by lazy { findNavController() }
    private val categoryId by lazy { arguments?.getString("categoryId") ?: "" }

    private val categoryVM: CategoryVM by activityViewModels()
    private val fruitVM: FruitVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        binding.btnBack.setOnClickListener { nav.navigateUp() }

        val adapter = FruitAdapter()
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val category = categoryVM.get(categoryId)
        if (category == null) {
            nav.navigateUp()
            return null
        }

        // TODO(8): Get all fruits by categoryId
        //          Populate [count] and recycler view
        val fruits = fruitVM.getAll(category.id)
        fruits.forEach { it.category = category }
        category.count = fruits.size

        binding.txtId.text = category.id
        binding.txtName.text = category.name
        binding.txtCount.text = "${category.count} Fruit(s)"

        adapter.submitList(fruits)

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

}