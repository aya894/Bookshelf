package com.example.bookshelf

import BooksAdapter
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView // Référence au RecyclerView
    private lateinit var booksAdapter: BooksAdapter // Adaptateur pour le RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crée une instance de ConstraintLayout comme root layout
        val rootLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            id = View.generateViewId() // Génère un ID pour ce layout
        }

        // Crée une instance de RecyclerView
        recyclerView = RecyclerView(this).apply {
            id = View.generateViewId() // Génère un ID unique pour le RecyclerView
            layoutManager = GridLayoutManager(this@MainActivity, 2) // Grille avec 2 colonnes
        }
        rootLayout.addView(recyclerView) // Ajoute le RecyclerView au layout principal

        // Définir les contraintes pour le RecyclerView
        val constraintSet = ConstraintSet().apply {
            clone(rootLayout)
            connect(recyclerView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            connect(recyclerView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(recyclerView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            connect(recyclerView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        }
        constraintSet.applyTo(rootLayout)

        // Applique le layout racine à l'activité
        setContentView(rootLayout)

        // Initialisation du ViewModel
        val booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        // Observateur pour la liste des livres
        booksViewModel.books.observe(this) { books ->
            // Initialiser l'adaptateur uniquement lorsque les données sont disponibles
            booksAdapter = BooksAdapter(books, this)
            recyclerView.adapter = booksAdapter
        }
    }
}
