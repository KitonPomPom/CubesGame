package kitonpompom.cubesgame.activities

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.utils.ImageManager
import kitonpompom.cubesgame.activities.utils.Interface

class AdapterAddFragment(var callbackArrayImageSizeInterface : CallbackArrayImageSizeInterface): RecyclerView.Adapter <AdapterAddFragment.ImageHolder>(), Interface.AddValueListBitmap{
    val mainArray = ArrayList<Bitmap>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rc_add_image_add_hor_adapter, parent,false)
        return ImageHolder(view, this)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.setData(mainArray[position])
    }

    override fun getItemCount(): Int {
        return mainArray.size
    }

    class ImageHolder (itemView : View, val adapter: AdapterAddFragment) : RecyclerView.ViewHolder(itemView)  {
        lateinit var imItem : ImageView
        lateinit var btDelete : ImageButton
        lateinit var mainArray2: ArrayList<Bitmap>
        fun setData ( bitmap: Bitmap){
            imItem = itemView.findViewById(R.id.im_image_hor)
            ImageManager.chooseScaleType(imItem,bitmap)
            imItem.setImageBitmap(bitmap)
            btDelete = itemView.findViewById(R.id.id_imbt_delete)
            btDelete.setOnClickListener(){
                adapter.mainArray.removeAt(adapterPosition)
                adapter.notifyItemRemoved(adapterPosition) //сообщить адаптеру какой именно итэм удален
                for(n in 0 until adapter.mainArray.size) adapter.notifyItemChanged(n) // цикл для того что бы при удалении сохранялась анимация
                adapter.callbackArrayImageSizeInterface.deleteImage()
            }

        }

        //override fun addValueViewModelListBitmap(dataModel: DataModel) {
    // dataModel.listBitmapForAdapterFragPWP.value = adapter.mainArray
        //}


    }

    fun updateAdapter(newList : List<Bitmap>){ // функция обновляет адаптер
        mainArray.clear()
        //Log.d("MyLog", "newList: ${newList.size}")
                mainArray.addAll(newList)
        //Log.d("MyLog", "mainArraySize: ${mainArray.size}")
        notifyDataSetChanged()
        callbackArrayImageSizeInterface.callbackArrayImageSize(mainArray.size)

    }

    interface CallbackArrayImageSizeInterface{
        //Интерфейс для передачи размера массива, для изменения кол-ва доб. картинок на FragmentAddImage
        fun callbackArrayImageSize(sizeArray:Int)
        //Для удаления из массива ссылок на FragmentAddImage
        fun deleteImage()
    }

    override fun addValueViewModelListBitmap(dataModel: DataModel) {
        dataModel.listBitmapForAdapterFragPWP.value = mainArray
    }

}