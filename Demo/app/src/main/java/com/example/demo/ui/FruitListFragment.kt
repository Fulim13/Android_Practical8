package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demo.R
import com.example.demo.data.Category
import com.example.demo.data.CategoryVM
import com.example.demo.data.FruitVM
import com.example.demo.databinding.FragmentFruitListBinding
import com.example.demo.util.FruitAdapter

class FruitListFragment : Fragment() {

    private lateinit var binding: FragmentFruitListBinding
    private val nav by lazy { findNavController() }

    private val categoryVM: CategoryVM by activityViewModels()
    private val fruitVM: FruitVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFruitListBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        val adapter = FruitAdapter { h, f ->
            h.binding.root.setOnClickListener { detail(f.id) }
        }
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        // TODO(13): Change to result live data
        fruitVM.getResultLD().observe(viewLifecycleOwner) {
            binding.txtCount.text = "${it.size} Record(s)"

            // TODO(9): Populate [category]
            it.forEach { it.category = categoryVM.get(it.categoryId) ?: Category() }
            adapter.submitList(it)
        }

        // -----------------------------------------------------------------------------------------

        // TODO(14): Search view -> setOnQueryTextListener
        binding.sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String) = true

            override fun onQueryTextChange(name: String): Boolean {
                fruitVM.search(name)
                return true
            }

        })

        // -----------------------------------------------------------------------------------------

        // TODO(11): Populate category spinner
        val arrayAdapter = ArrayAdapter<Category>(context!!, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnCategory.adapter = arrayAdapter

        arrayAdapter.add(Category("","All"))
        arrayAdapter.addAll(categoryVM.getAll())

        // TODO(15): Spinner (spnCategory) -> onItemSelectedListener
        binding.spnCategory.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val category = binding.spnCategory.selectedItem as Category
                fruitVM.filter(category.id)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

        }

        // -----------------------------------------------------------------------------------------

        // TODO(16): Spinner (spnField) -> onItemSelectedListener
        binding.spnField.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val field = binding.spnField.selectedItem as String
                val reverse = binding.chkReverse.isChecked
                fruitVM.sort(field,reverse)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

        }

        // -----------------------------------------------------------------------------------------

        // TODO(17): Checkbox (chkReverse) -> setOnCheckedChangeListener
        binding.chkReverse.setOnCheckedChangeListener { _, _ ->
            val field = binding.spnField.selectedItem as String
            val reverse = binding.chkReverse.isChecked
            fruitVM.sort(field,reverse)
        }
        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun detail(fruitId: String) {
        nav.navigate(R.id.fruitDetailFragment, bundleOf(
            "fruitId" to fruitId
        ))
    }

}