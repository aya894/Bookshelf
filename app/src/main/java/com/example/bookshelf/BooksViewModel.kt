package com.example.bookshelf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BooksViewModel : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    init {
        loadBooks()
    }

    private fun loadBooks() {
        _books.value = listOf(
            Book("Jazz History", "Donald D. Megill", R.drawable.jazz1),
            Book("The History of Jazz", "Ted Gioia", R.drawable.jazz2),
            Book("Jazz Styles", "Mark Gridley", R.drawable.jazz3),
            Book("Kansas City Jazz", "Frank Driggs", R.drawable.jazz4),
            Book("Jazz Overview", "Richard Wang", R.drawable.jazz5)
        )
    }
}
