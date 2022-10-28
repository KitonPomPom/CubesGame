package kitonpompom.cubesgame.activities

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.ImageView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController


import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.utils.CubeAnimation
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.*
import kotlin.collections.ArrayList

// Адаптер горизонтальный с картинками, находится внутри  другого адаптера на главном офлайн фрагменте
/*class AdapterFragOfflineImage(private val context: Context, private val bitmapItem: List <Bitmap>):
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
        holder.imageItem.setImageBitmap(bitmapItem[position])
    }

    override fun getItemCount(): Int {
        return bitmapItem.size
    }

}*/
class AdapterFragOfflineImage(var openFragPlayWithPictures: AdapterFragOfflineGroupImage.OpenFragPlayInterface) : RecyclerView.Adapter <AdapterFragOfflineImage.ImageHolder>() {
    val mainArray = ArrayList<Bitmap>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rc_offline_image_adapter, parent,false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.setData(mainArray[position], mainArray, openFragPlayWithPictures)
    }

    override fun getItemCount(): Int {
        return mainArray.size
    }

    class ImageHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imItem : ImageView
        fun setData (bitMap : Bitmap, mainArray: ArrayList<Bitmap>, openFragPlayWithPictures: AdapterFragOfflineGroupImage.OpenFragPlayInterface){
            imItem = itemView.findViewById(R.id.id_imView_Offline)
            imItem.setImageBitmap(bitMap)
            imItem.setOnClickListener(){
                //dataModel.listBitmapForAdapterFragPWP.value = mainArray
                openFragPlayWithPictures.openFragPlayWithPicturesInterface(mainArray)
                //ObjectAnimator.ofFloat(imItem, View.ROTATION_Y, 0f,90f).apply {
                  //  duration = 2000
                   // interpolator  = AccelerateDecelerateInterpolator()
                   // repeatCount = Animation.ABSOLUTE
                   // repeatMode = Animation.INFINITE
                   // start()
                /*var sc = 350/2/(Math.pow(Math.pow((350.0/2.0),2.0)+Math.pow(350.0/2.0,2.0),0.5))
                    val rotationX = PropertyValuesHolder.ofFloat(View.ROTATION_Y, 0f, 90f)
                    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.4f)
                    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
                    val scaleXnext = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
                    val scaleYnext = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
                    val translationX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 350f)
                    val translationZ = PropertyValuesHolder.ofFloat(View.TRANSLATION_Z, 0.2f)
                val rotatX =  ObjectAnimator.ofPropertyValuesHolder(imItem, rotationX, translationX).apply {
                    duration = 3000
                }
                val transX =  ObjectAnimator.ofPropertyValuesHolder(imItem, translationX).apply {
                    duration = 3000
                }
                val scaleStart = ObjectAnimator.ofPropertyValuesHolder(imItem, scaleX,scaleY).apply {
                    duration = 1500
                }
                val scaleNext = ObjectAnimator.ofPropertyValuesHolder(imItem, scaleXnext,scaleYnext).apply {
                    duration = 1500
                    interpolator = AccelerateInterpolator()
                }
                AnimatorSet().apply {
                        play(rotatX)
                            .with(scaleStart)

                        start()
                }*/



                //(AnimatorInflater.loadAnimator(itemView.context, R.animator.animation_cube) as ObjectAnimator).apply {
                    //target = imItem
                    //start()

                }
                //imItem.startAnimation(ani)
        }
    }

    fun updateAdapter(newList : List<Bitmap> ){ // функция обновляет адаптер
        mainArray.clear()
        mainArray.addAll(newList)
        notifyDataSetChanged()

    }
}