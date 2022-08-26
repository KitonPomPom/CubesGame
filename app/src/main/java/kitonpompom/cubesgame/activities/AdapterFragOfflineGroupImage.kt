package kitonpompom.cubesgame.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.R
// Адаптер для картинок на главной странице офлайн фрагмента, в котором ещё один адаптер с картинками
class AdapterFragOfflineGroupImage: RecyclerView.Adapter <AdapterFragOfflineGroupImage.ItemHolder>() {
    val mainArray = ArrayList<String>()
    var mainArrayAllCategories: List<AllCategories>? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rc_offline_group_image_adapter, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(mainArrayAllCategories!![position].categoryTitle, mainArrayAllCategories!![position].categoryItem)
    }

    override fun getItemCount(): Int {
        return mainArrayAllCategories!!.size
    }

    class ItemHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        lateinit var textViewNumbersCubeImage : TextView
        lateinit var rcView: RecyclerView
        fun setData (dataLetter : String, categoryItem: List<CategoryItem>){
            textViewNumbersCubeImage = itemView.findViewById(R.id.tvNumberCubsImage)
            textViewNumbersCubeImage.text = dataLetter

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "Нажал", Toast.LENGTH_LONG).show()
            }

            rcView = itemView.findViewById(R.id.id_rc_viewImage)
            rcView.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            val adapterFragOfflineImage = AdapterFragOfflineImage(itemView.context, categoryItem)
            rcView.adapter = adapterFragOfflineImage


        }
    }

    fun updateAdapter(newListAllCategories: List<AllCategories> ){ // функция обновляет адаптер

        mainArray.clear()
        mainArrayAllCategories = newListAllCategories

        notifyDataSetChanged()
    }
}