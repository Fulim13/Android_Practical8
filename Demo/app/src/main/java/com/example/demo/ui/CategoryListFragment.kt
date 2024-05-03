package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demo.R
import com.example.demo.data.CategoryVM
import com.example.demo.data.FruitVM
import com.example.demo.databinding.FragmentCategoryListBinding
import com.example.demo.util.CategoryAdapter

class CategoryListFragment : Fragment() {

    private lateinit var binding: FragmentCategoryListBinding
    private val nav by lazy { findNavController() }

    private val categoryVM: CategoryVM by activityViewModels()
    private val fruitVM: FruitVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        val adapter = CategoryAdapter { h, c ->
            h.binding.root.setOnClickListener { detail(c.id) }
        }
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        categoryVM.getCategoriesLD().observe(viewLifecycleOwner) {
            binding.txtCount.text = "${it.size} Record(s)"

            // TODO(6): Populate [count]

            adapter.submitList(it)
        }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun detail(categoryId: String) {
        nav.navigate(R.id.categoryDetailFragment, bundleOf(
            "categoryId" to categoryId
        ))
    }

}