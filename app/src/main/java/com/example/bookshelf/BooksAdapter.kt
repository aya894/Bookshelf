import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.Book
import com.example.bookshelf.ViewIdGenerator
import com.google.android.filament.View

class BooksAdapter(private val books: List<Book>, private val context: Context) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    // BookViewHolder utilise directement les références aux vues créées
    class BookViewHolder(val layout: ConstraintLayout, val bookImage: ImageView, val bookTitle: TextView) :
        RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        // Construire un ConstraintLayout programmatique
        val layout = ConstraintLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        // Ajout de l'ImageView
        val bookImage = ImageView(context).apply {
            id = ViewIdGenerator.generateViewId() // Utilisation d'IDs uniques
            layoutParams = ConstraintLayout.LayoutParams(0, 300).apply {
                setMargins(16, 16, 16, 8)
            }
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        layout.addView(bookImage)

        // Ajout du TextView
        val bookTitle = TextView(context).apply {
            id = ViewIdGenerator.generateViewId() // Utilisation d'IDs uniques
            layoutParams = ConstraintLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                setMargins(16, 8, 16, 16)
            }
            textSize = 16f
            setTextColor(Color.BLACK)
        }
        layout.addView(bookTitle)

        // Contrainte pour l'image et le texte
        val constraints = ConstraintSet().apply {
            clone(layout)

            // Contraintes pour l'image
            connect(bookImage.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            connect(bookImage.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(bookImage.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

            // Contraintes pour le texte
            connect(bookTitle.id, ConstraintSet.TOP, bookImage.id, ConstraintSet.BOTTOM)
            connect(bookTitle.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(bookTitle.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        }
        constraints.applyTo(layout)

        return BookViewHolder(layout, bookImage, bookTitle)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bookImage.setImageResource(book.imageResId)
        holder.bookTitle.text = book.title
    }

    override fun getItemCount(): Int = books.size
}
