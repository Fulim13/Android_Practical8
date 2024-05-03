package com.example.demo.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObjects

class CategoryVM : ViewModel() {

    private val categoriesLD = MutableLiveData<List<Category>>()
    private var listener : ListenerRegistration? = null

    init {
        listener = CATEGORIES.addSnapshotListener { snap, _ ->
            categoriesLD.value = snap?.toObjects()
        }
    }

    override fun onCleared() {
        listener?.remove()
    }

    // ---------------------------------------------------------------------------------------------

    fun init() = Unit

    fun getCategoriesLD() = categoriesLD

    fun getAll() = categoriesLD.value ?: emptyList()

    fun get(id: String) = getAll().find { it.id == id }

}