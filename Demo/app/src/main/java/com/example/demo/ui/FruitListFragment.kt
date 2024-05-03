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
        fruitVM.getFruitsLD().observe(viewLifecycleOwner) {
            binding.txtCount.text = "${it.size} Record(s)"

            // TODO(9): Populate [category]

            adapter.submitList(it)
        }

        // -----------------------------------------------------------------------------------------

        // TODO(14): Search view -> setOnQueryTextListener
        binding.sv

        // -----------------------------------------------------------------------------------------

        // TODO(11): Populate category spinner


        // TODO(15): Spinner (spnCategory) -> onItemSelectedListener
        binding.spnCategory

        // -----------------------------------------------------------------------------------------

        // TODO(16): Spinner (spnField) -> onItemSelectedListener
        binding.spnField

        // -----------------------------------------------------------------------------------------

        // TODO(17): Checkbox (chkReverse) -> setOnCheckedChangeListener
        binding.chkReverse

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun detail(fruitId: String) {
        nav.navigate(R.id.fruitDetailFragment, bundleOf(
            "fruitId" to fruitId
        ))
    }

}