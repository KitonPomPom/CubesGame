package kitonpompom.cubesgame.activities //адаптер для фрагмента с картиками

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.activities.utils.ClickableState
import kitonpompom.cubesgame.activities.utils.CubeAnimation
import kitonpompom.cubesgame.activities.utils.ItemTouchMoveAndSwipe
import kotlin.math.abs

class AdapterFragPWP(val clickScaleItemInterface: ClickScaleItemInterface): RecyclerView.Adapter<AdapterFragPWP.ImageHolder>(), ItemTouchMoveAndSwipe.ItemTouchDragAdapterPWP{
    var mainArrayView = ArrayList<dataArrayBitmap>()
    var arrayListBitmap = ArrayList<ArrayList<Bitmap>>()
    var duration = 0
    var click =  ClickableState()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rc_playing_with_pictures, parent,false)
        return AdapterFragPWP.ImageHolder(view, this, parent.id, click)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.setData(mainArrayView[position], clickScaleItemInterface)
    }

    override fun getItemCount(): Int {
        return mainArrayView.size
    }

    override fun onMove(startPos: Int, targetPos: Int) {
        val targetItem = mainArrayView[targetPos]
        mainArrayView[targetPos] = mainArrayView[startPos]
        mainArrayView[targetPos] = targetItem
        notifyItemMoved(startPos, targetPos)
    }


    class ImageHolder (itemView : View, val adapter: AdapterFragPWP, id: Int, var clickk: ClickableState) : RecyclerView.ViewHolder(itemView)  {
        lateinit var imItemOne : ImageView
        lateinit var imItemTwo : ImageView

        @SuppressLint("ClickableViewAccessibility")
        fun setData ( item: dataArrayBitmap, clickScaleItemInterface: ClickScaleItemInterface){
            imItemOne = itemView.findViewById(R.id.id_item_play_with_pictures_one)
            imItemTwo = itemView.findViewById(R.id.id_item_play_with_pictures_two)
            itemView.visibility = View.VISIBLE
            imItemOne.setImageBitmap(item.arrayBitmap[0])
            var x1: Float = 0.0f
            var x2: Float = 0.0f
            var y1: Float = 0.0f
            var y2: Float = 0.0f
            var noReplaySwipe = true
            //Log.d("MyLog", "click set data ${clickk.clickable}")

                imItemOne.setOnClickListener {
                    Log.d("MyLog", "imItemOne.setOnClickListener Adapter")
                    if (clickk.clickable) {
                        clickScaleItemInterface.clickScaleItem(
                            item.arrayBitmap[0], item.arrayBitmap[1],
                            item.arrayBitmap[2], item.arrayBitmap[3],
                            item.arrayBitmap[4], item.arrayBitmap[5],
                            adapterPosition, itemView, imItemOne
                        )
                    }
                }

            /*imItemTwo.setOnClickListener {
                Log.d("MyLog", "imItemTwo.setOnClickListener Adapter")
                if (clickk.clickable) {
                    clickScaleItemInterface.clickScaleItem(
                        item.arrayBitmap[0], item.arrayBitmap[1],
                        item.arrayBitmap[2], item.arrayBitmap[3],
                        item.arrayBitmap[4], item.arrayBitmap[5],
                        adapterPosition, itemView, imItemTwo
                    )
                }
            }*/

                imItemOne.setOnTouchListener(){viewRc, eventRc ->
                    Log.d("MyLog", "imItemOne.setOnTouchListener Adapter")
                    val minDistance = 15
                    val minDistanceUpDown = 7
                    when (eventRc.action) {
                        MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана

                            x1 = eventRc.x //Позиция по оси Х куда нажали
                            y1 = eventRc.y //Позиция по оси Y куда нажали
                            noReplaySwipe = true
                        }MotionEvent.ACTION_MOVE -> {
                        x2 = eventRc.x
                        y2 = eventRc.y
                        var deltaX: Float = x2 - x1
                        var deltaY: Float = y2 - y1
                        if (Math.abs(deltaX) > minDistance && noReplaySwipe){
                            if(x2 > x1){
                                Log.d("MyLog", "rightAdapter")
                                clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                    item.arrayBitmap[2], item.arrayBitmap[3],
                                    item.arrayBitmap[4], item.arrayBitmap[5],
                                    adapterPosition, itemView, imItemOne)
                                noReplaySwipe = false
                            }else {
                                Log.d("MyLog", "leftAdapter")
                                clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                    item.arrayBitmap[2], item.arrayBitmap[3],
                                    item.arrayBitmap[4], item.arrayBitmap[5],
                                    adapterPosition, itemView, imItemOne)
                                noReplaySwipe = false
                            }
                        }
                        if (Math.abs(deltaY) > minDistanceUpDown && noReplaySwipe){
                            if(y2 > y1){
                                Log.d("MyLog", "downAdapter")
                                clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                    item.arrayBitmap[2], item.arrayBitmap[3],
                                    item.arrayBitmap[4], item.arrayBitmap[5],
                                    adapterPosition, itemView, imItemOne)
                                noReplaySwipe = false
                            }else {
                                Log.d("MyLog", "upAdapter")
                                clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                    item.arrayBitmap[2], item.arrayBitmap[3],
                                    item.arrayBitmap[4], item.arrayBitmap[5],
                                    adapterPosition, itemView, imItemOne)
                                noReplaySwipe = false
                            }
                        }
                    }
                        MotionEvent.ACTION_UP -> {
                            x2 = eventRc.x
                            y2 = eventRc.y
                            var deltaX: Float = x2 - x1
                            var deltaY: Float = y2 - y1
                            if (Math.abs(deltaX) < minDistance && Math.abs(deltaY) < minDistanceUpDown){
                                Log.d("MyLog", "Click Adapter")
                            }
                        }
                    }
                    return@setOnTouchListener false
                }
        }
    }


    fun updateAdapter(newList : ArrayList<dataArrayBitmap>){ // функция обновляет адаптер
        mainArrayView.clear()
        mainArrayView.addAll(newList)
        notifyDataSetChanged()

    }

    fun updateAdapterPosition(newList: ArrayList<Bitmap>, position: Int){
        mainArrayView[position].arrayBitmap[0] = newList[0]
        mainArrayView[position].arrayBitmap[1] = newList[1]
        mainArrayView[position].arrayBitmap[2] = newList[2]
        mainArrayView[position].arrayBitmap[3] = newList[3]
        mainArrayView[position].arrayBitmap[4] = newList[4]
        mainArrayView[position].arrayBitmap[5] = newList[5]
        notifyItemChanged(position)
    }

    /*fun touchActionUpClick(posClick: Int){
        if (click.clickable) {
            clickScaleItemInterface.clickScaleItem(
                mainArrayView[posClick].arrayBitmap[0], mainArrayView[posClick].arrayBitmap[1],
                mainArrayView[posClick].arrayBitmap[2], mainArrayView[posClick].arrayBitmap[3],
                mainArrayView[posClick].arrayBitmap[4], mainArrayView[posClick].arrayBitmap[5],
                adapterPosition, itemView, imItemOne
            )
        }
    }*/

    interface ClickScaleItemInterface{
        fun clickScaleItem( b0 : Bitmap, b1 : Bitmap, b2 : Bitmap, b3 : Bitmap, b4 : Bitmap,
                            b5 : Bitmap, position: Int, itemView: View, imItem: ImageView)
        fun moveItem(b0 : Bitmap, b1 : Bitmap, b2 : Bitmap, b3 : Bitmap, b4 : Bitmap,
                     b5 : Bitmap, position: Int, itemView: View, imItem: ImageView)

    }



}