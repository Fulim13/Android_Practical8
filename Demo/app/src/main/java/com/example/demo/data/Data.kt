package com.example.demo.data

import android.content.Context
import android.graphics.BitmapFactory
import com.example.demo.R
import com.example.demo.util.toBlob
import com.google.firebase.Firebase
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.firestore

data class Category(
    @DocumentId
    var id: String = "",
    var name: String = ""
) {
    // TODO(1): Additional members: [count], [toString()]

    var count: Int = 0
    override fun toString() = name
}

data class Fruit(
    @DocumentId
    var id: String = "",
    var name: String = "",
    var price: Double = 0.00,
    var categoryId: String = "",
    var photo: Blob = Blob.fromBytes(ByteArray(0))
) {
    // TODO(2): Additional members: [category]

    var category: Category = Category()
}

// -------------------------------------------------------------------------------------------------

val CATEGORIES = Firebase.firestore.collection("categories")
val FRUITS = Firebase.firestore.collection("fruits")

// -------------------------------------------------------------------------------------------------

fun RESTORE(ctx: Context) {
    fun getBlob(res: Int) = BitmapFactory.decodeResource(ctx.resources, res).toBlob()

    val categories = listOf(
        Category("C001", "Local"),
        Category("C002", "Imported"),
    )

    val fruits = listOf(
        Fruit("F001", "Banana"    , 0.00, "C001", getBlob(R.raw.banana)),
        Fruit("F002", "Lemon"     , 0.00, "C001", getBlob(R.raw.lemon)),
        Fruit("F003", "Mango"     , 0.00, "C001", getBlob(R.raw.mango)),
        Fruit("F004", "Pineapple" , 0.00, "C001", getBlob(R.raw.pineapple)),
        Fruit("F005", "Watermelon", 0.00, "C001", getBlob(R.raw.watermelon)),
        Fruit("F006", "Apple"     , 0.00, "C002", getBlob(R.raw.apple)),
        Fruit("F007", "Cherries"  , 0.00, "C002", getBlob(R.raw.cherries)),
        Fruit("F008", "Grapes"    , 0.00, "C002", getBlob(R.raw.grapes)),
        Fruit("F009", "Kiwi"      , 0.00, "C002", getBlob(R.raw.kiwi)),
        Fruit("F010", "Strawberry", 0.00, "C002", getBlob(R.raw.strawberry)),
    )

    // TODO(3): Restore categories


    // TODO(4): Restore fruits (randomize price 0.01 - 9.99)


}