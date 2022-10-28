package kitonpompom.cubesgame.activities

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.R
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.*

// Адаптер для игры
class AdapterFragPlayingWithPictures(private val context: Context, private val listBitmap: List <Bitmap>):
    RecyclerView.Adapter<AdapterFragPlayingWithPictures.CategoryItemViewHolder>() {

        class CategoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var imageItem: ImageView
            init {
                imageItem = itemView.findViewById(R.id.id_item_play_with_pictures_one)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rc_playing_with_pictures, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.imageItem.setImageBitmap(listBitmap[position])
    }

    override fun getItemCount(): Int {
        return listBitmap.size
    }

}