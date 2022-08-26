package kitonpompom.cubesgame.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.R
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.*
// Адаптер горизонтальный с картинками, находится внутри  другого адаптера на главном офлайн фрагменте
class AdapterFragOfflineImage(private val context: Context, private val categoryItem: List <CategoryItem>):
    RecyclerView.Adapter<AdapterFragOfflineImage.CategoryItemViewHolder>() {

        class CategoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var imageItem: ImageView
            init {
                imageItem = itemView.findViewById(R.id.id_imView_Offline)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rc_offline_image_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.imageItem.setImageResource(categoryItem[position].imageUrl)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }

}