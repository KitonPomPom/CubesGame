package kitonpompom.cubesgame.activities

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.utils.CubeTransformer
import kitonpompom.cubesgame.activities.utils.SliderTransformer
import kitonpompom.cubesgame.databinding.ItemRcOfflineGroupImageAdapterBinding
import kitonpompom.cubesgame.databinding.ItemRcOfflineImageAdapterBinding

// Адаптер для картинок на главной странице офлайн фрагмента, в котором ещё один адаптер с картинками
class AdapterFragOfflineGroupImage(var openFragPlayWithPictures: OpenFragPlayInterface): RecyclerView.Adapter <AdapterFragOfflineGroupImage.ItemHolder>() {
    val mainArray = ArrayList<String>()
    var mainArrayAllCategories: List<AllCategories>? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemRcOfflineGroupImageAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rc_offline_group_image_adapter, parent, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(mainArray[position], mainArrayAllCategories!![position].bitmapItem, openFragPlayWithPictures)
    }

    override fun getItemCount(): Int {
        return mainArray.size
    }

    class ItemHolder (private val binding: ItemRcOfflineGroupImageAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var textViewNumbersCubeImage : TextView
        //lateinit var rcView: RecyclerView
        //lateinit var rvViewPager: ViewPager
        fun setData (dataLetter : String, arrayListBitmap: List<Bitmap>, openFragPlayInterface: OpenFragPlayInterface){
            //textViewNumbersCubeImage = itemView.findViewById(R.id.tvNumberCubsImage)
            //textViewNumbersCubeImage.text = dataLetter
            binding.tvNumberCubsImage.text = dataLetter
            val adapterFragOfflineImage = AdapterFragOfflineImage(openFragPlayInterface)
            binding.idViewPager2.adapter = adapterFragOfflineImage
            //binding.idViewPager2.setPageTransformer(CubeTransformer())
            binding.idViewPager2.offscreenPageLimit = 4
            binding.idViewPager2.setPageTransformer(SliderTransformer(4))
            adapterFragOfflineImage.updateAdapter(arrayListBitmap)
            //Log.d("MyLog", "Размер массива битмап: ${arrayListBitmap.size}")

            //itemView.setOnClickListener{
                //Toast.makeText(itemView.context, "Нажал", Toast.LENGTH_LONG).show()
                //openFragPlayInterface.openFragPlayWithPicturesInterface()
            //}

            //gval rvViewPager: ViewPager2 = itemView.findViewById(R.id.id_viewPager2)
            //rcView.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            //val adapterFragOfflineImage = AdapterFragOfflineImage(itemView.context, arrayListBitmap)

            //rvViewPager.adapter = adapterFragOfflineImage



        }
    }

    fun updateAdapter(newListAllCategories: List<AllCategories> ){ // функция обновляет адаптер

        mainArray.clear()
        mainArray.add(newListAllCategories[0].categoryTitle)
        mainArray.add(newListAllCategories[1].categoryTitle)
        mainArrayAllCategories = newListAllCategories

        notifyDataSetChanged()
    }

    interface OpenFragPlayInterface{
        fun openFragPlayWithPicturesInterface(mainArray: ArrayList<Bitmap>)
    }
}