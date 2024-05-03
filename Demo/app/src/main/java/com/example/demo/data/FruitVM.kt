package com.example.demo.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObjects

class FruitVM : ViewModel() {

    private val fruitsLD = MutableLiveData<List<Fruit>>(emptyList())
    private var listener : ListenerRegistration? = null

    init {
        listener = FRUITS.addSnapshotListener { snap, _ ->
            fruitsLD.value = snap?.toObjects()
            updateResult()
        }
    }

    override fun onCleared() {
        listener?.remove()
    }

    // ---------------------------------------------------------------------------------------------

    fun init() = Unit

    fun getFruitsLD() = fruitsLD

    fun getAll() = fruitsLD.value ?: emptyList()

    fun get(id: String) = getAll().find { it.id == id }

    // TODO(5): Get all fruits by categoryId
    fun getAll(categoryId: String) = getAll()

    // ---------------------------------------------------------------------------------------------

    private val resultLD = MutableLiveData<List<Fruit>>()
    private var name = ""
    private var categoryId = ""
    private var field = ""
    private var reverse = false

    fun getResultLD() = resultLD

    fun search(name: String) {
        this.name = name
        updateResult()
    }

    fun filter(categoryId: String) {
        this.categoryId = categoryId
        updateResult()
    }

    fun sort(field: String, reverse: Boolean) {
        this.field = field
        this.reverse = reverse
        updateResult()
    }

    fun updateResult() {
        var list = getAll()

        // TODO(12A): Search by name, filter by categoryId


        // TODO(12B): Sort by field


        // TODO(12C): Reverse (descending order)


        resultLD.value = list
    }

}