package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demo.data.RESTORE
import com.example.demo.databinding.FragmentHomeBinding
import com.example.demo.util.toast

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val nav by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // -----------------------------------------------------------------------------------------

        binding.btnRestore.setOnClickListener { restore() }

        // -----------------------------------------------------------------------------------------

        return binding.root
    }

    private fun restore() {
        RESTORE(context!!)
        toast("Done")
    }

}








