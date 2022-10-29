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
import kitonpompom.cubesgame.activities.utils.CubeAnimation
import kitonpompom.cubesgame.activities.utils.ItemTouchMoveAndSwipe
import kotlin.math.abs

class AdapterFragPWP(val clickScaleItemInterface: ClickScaleItemInterface): RecyclerView.Adapter<AdapterFragPWP.ImageHolder>(), ItemTouchMoveAndSwipe.ItemTouchDragAdapterPWP{
    var mainArrayView = ArrayList<dataArrayBitmap>()
    var arrayListBitmap = ArrayList<ArrayList<Bitmap>>()
    var duration = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rc_playing_with_pictures, parent,false)
        return AdapterFragPWP.ImageHolder(view, this, parent.id)
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


    class ImageHolder (itemView : View, val adapter: AdapterFragPWP, id: Int) : RecyclerView.ViewHolder(itemView)  {
        lateinit var imItemOne : ImageView
        //lateinit var imScale : ImageView
        private var x1: Float = 0.0f
        private var x2: Float = 0.0f
        private var y1: Float = 0.0f
        private var y2: Float = 0.0f
        var noReplaySwipe = true
        var startUpdateItem = true


        @SuppressLint("ClickableViewAccessibility")
        fun setData ( item: dataArrayBitmap, clickScaleItemInterface: ClickScaleItemInterface){
            imItemOne = itemView.findViewById(R.id.id_item_play_with_pictures_one)
            itemView.visibility = View.VISIBLE
            imItemOne.setImageBitmap(item.arrayBitmap[0])

            imItemOne.setOnClickListener{
                //itemView.visibility = View.INVISIBLE

                clickScaleItemInterface.clickScaleItem(item.arrayBitmap[0], item.arrayBitmap[1],
                    item.arrayBitmap[2], item.arrayBitmap[3],
                    item.arrayBitmap[4], item.arrayBitmap[5],
                    adapterPosition, itemView, imItemOne)
            }

            //imItemTwo = itemView.findViewById(R.id.id_item_play_with_pictures_two)
           // val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f)
            //val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f)
            //if(startUpdateItem == false) {
                //startUpdateItem = false
            }
            /*when(adapter.duration){

                0 -> {
                    if (imItemOne.visibility == View.VISIBLE) {
                        imItemOne.setImageBitmap(item.arrayBitmap[0])
                    } else {
                        imItemOne.setImageBitmap(item.arrayBitmap[0])
                    }
                }
                1 ->{
                    if(imItemOne.visibility == View.VISIBLE) {
                        imItemTwo.setImageBitmap(item.arrayBitmap[0])
                        imItemTwo.visibility = View.VISIBLE
                        imItemOne.startAnimation(CubeAnimation.create(1, false, 400))
                        imItemTwo.startAnimation(CubeAnimation.create(1, true, 400))
                        imItemOne.visibility = View.GONE
                    }else{
                        imItemOne.setImageBitmap(item.arrayBitmap[0])
                        imItemOne.visibility = View.VISIBLE
                        imItemTwo.startAnimation(CubeAnimation.create(1, false, 400))
                        imItemOne.startAnimation(CubeAnimation.create(1, true, 400))
                        imItemTwo.visibility = View.GONE
                    }
                }
                2 ->{
                    if(imItemOne.visibility == View.VISIBLE) {
                        imItemTwo.setImageBitmap(item.arrayBitmap[0])
                        imItemTwo.visibility = View.VISIBLE
                        imItemOne.startAnimation(CubeAnimation.create(2, false, 400))
                        imItemTwo.startAnimation(CubeAnimation.create(2, true, 400))
                        imItemOne.visibility = View.GONE
                    }else{
                        imItemOne.setImageBitmap(item.arrayBitmap[0])
                        imItemOne.visibility = View.VISIBLE
                        imItemTwo.startAnimation(CubeAnimation.create(2, false, 400))
                        imItemOne.startAnimation(CubeAnimation.create(2, true, 400))
                        imItemTwo.visibility = View.GONE
                    }
                }
                3 ->{
                    if(imItemOne.visibility == View.VISIBLE) {
                        imItemTwo.setImageBitmap(item.arrayBitmap[0])
                        imItemTwo.visibility = View.VISIBLE
                        imItemOne.startAnimation(CubeAnimation.create(3, false, 400))
                        imItemTwo.startAnimation(CubeAnimation.create(3, true, 400))
                        imItemOne.visibility = View.GONE
                    }else{
                        imItemOne.setImageBitmap(item.arrayBitmap[0])
                        imItemOne.visibility = View.VISIBLE
                        imItemTwo.startAnimation(CubeAnimation.create(3, false, 400))
                        imItemOne.startAnimation(CubeAnimation.create(3, true, 400))
                        imItemTwo.visibility = View.GONE
                    }
                }
                4 ->{
                    if(imItemOne.visibility == View.VISIBLE) {
                        imItemTwo.setImageBitmap(item.arrayBitmap[0])
                        imItemTwo.visibility = View.VISIBLE
                        imItemOne.startAnimation(CubeAnimation.create(4, false, 400))
                        imItemTwo.startAnimation(CubeAnimation.create(4, true, 400))
                        imItemOne.visibility = View.GONE
                    }else{
                        imItemOne.setImageBitmap(item.arrayBitmap[0])
                        imItemOne.visibility = View.VISIBLE
                        imItemTwo.startAnimation(CubeAnimation.create(4, false, 400))
                        imItemOne.startAnimation(CubeAnimation.create(4, true, 400))
                        imItemTwo.visibility = View.GONE
                    }
                }

            }

             */







            /*imItemOne.setOnTouchListener { v, event ->
                val maxSizeView = v.width
                val minDistance = 15
                val minDistanceUpDown = 7

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана

                        x1 = event.x //Позиция по оси Х куда нажали
                        y1 = event.y //Позиция по оси Y куда нажали
                        //Log.d("MyLog", "event - down: y1 - $y1")
                        noReplaySwipe = true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        x2 = event.x
                        y2 = event.y
                        //Log.d("MyLog", "event - move: y2 - $y2")
                        var deltaX: Float = x2 - x1
                        var deltaY: Float = y2 - y1
                        //Log.d("MyLog", "deltaY - $deltaY")
                        //Log.d("MyLog", "deltaX - $deltaX")
                        if (abs(deltaX) > minDistance && noReplaySwipe){
                            if(x2 > x1){
                                adapter.right(adapterPosition)
                                //Log.d("MyLog", "Свайп на право x2:$x2 x1:$x1")
                                noReplaySwipe = false
                            }else {
                                adapter.left(adapterPosition)
                                //Log.d("MyLog", "Свайп на лево x2:$x2 x1:$x1")
                                noReplaySwipe = false
                            }
                        }
                        if (abs(deltaY) > minDistanceUpDown && noReplaySwipe){
                            if(y2 > y1){
                                adapter.down(adapterPosition)
                                //Log.d("MyLog", "Свайп вниз y2:$y2 y1:$y1")
                                noReplaySwipe = false
                            }else {
                                adapter.up(adapterPosition, imItemOne, imItemTwo)
                                //Log.d("MyLog", "Свайп вверх y2:$y2 y1:$y1")
                                noReplaySwipe = false
                            }
                        }

                    }
                    MotionEvent.ACTION_UP -> {
                        //v.performClick()
                       /* Log.d("MyLog", "Просто нажал")
                        ObjectAnimator.ofPropertyValuesHolder(imItemOne, scaleX, scaleY).apply {
                            duration = 3000
                            start()
                        }
                        ObjectAnimator.ofPropertyValuesHolder(imItemTwo, scaleX, scaleY).apply {
                            duration = 3000
                            start()
                        }*/
                    }

                }
                return@setOnTouchListener true
            }

            imItemTwo.setOnTouchListener { v, event ->
                val maxSizeView = v.width
                val minDistance = 15
                val minDistanceUpDown = 7

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана

                        x1 = event.x //Позиция по оси Х куда нажали
                        y1 = event.y //Позиция по оси Y куда нажали
                        //Log.d("MyLog", "event - down: y1 - $y1")
                        noReplaySwipe = true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        x2 = event.x
                        y2 = event.y
                        //Log.d("MyLog", "event - move: y2 - $y2")
                        var deltaX: Float = x2 - x1
                        var deltaY: Float = y2 - y1
                        //Log.d("MyLog", "deltaY - $deltaY")
                        //Log.d("MyLog", "deltaX - $deltaX")
                        if (abs(deltaX) > minDistance && noReplaySwipe){
                            if(x2 > x1){
                                adapter.right(adapterPosition)
                                Log.d("MyLog", "Свайп на право x2:$x2 x1:$x1")
                                noReplaySwipe = false
                            }else {
                                adapter.left(adapterPosition)
                                Log.d("MyLog", "Свайп на лево x2:$x2 x1:$x1")
                                noReplaySwipe = false
                            }
                        }
                        if (abs(deltaY) > minDistanceUpDown && noReplaySwipe){
                            if(y2 > y1){
                                adapter.down(adapterPosition)
                                Log.d("MyLog", "Свайп вниз y2:$y2 y1:$y1")
                                noReplaySwipe = false
                            }else {
                                adapter.up(adapterPosition, imItemOne, imItemTwo)
                                Log.d("MyLog", "Свайп вверх y2:$y2 y1:$y1")
                                noReplaySwipe = false
                            }
                        }

                    }
                    MotionEvent.ACTION_UP -> {
                        //v.performClick()
                        //Log.d("MyLog", "event - up: x - ${event.x}, y - ${event.y}")
                    }

                }
                return@setOnTouchListener true
            }*/

        //}


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

    fun up(position: Int, itemOne: ImageView, itemTwo: ImageView){
        duration = 1
            var tempBitmap = mainArrayView[position].arrayBitmap[0]
        mainArrayView[position].arrayBitmap[0] = mainArrayView[position].arrayBitmap[4]
        mainArrayView[position].arrayBitmap[4] = mainArrayView[position].arrayBitmap[5]
        mainArrayView[position].arrayBitmap[5] = mainArrayView[position].arrayBitmap[3]
        mainArrayView[position].arrayBitmap[3] = tempBitmap
        notifyItemChanged(position)


        //if (itemTwo.visibility == View.VISIBLE)
          //  itemTwo.startAnimation(CubeAnimation.create(1,false,3000))
           // itemTwo.visibility == View.VISIBLE


    }

    fun down(position: Int){
        duration = 2
        var tempBitmap = mainArrayView[position].arrayBitmap[0]
        mainArrayView[position].arrayBitmap[0] = mainArrayView[position].arrayBitmap[3]
        mainArrayView[position].arrayBitmap[3] = mainArrayView[position].arrayBitmap[5]
        mainArrayView[position].arrayBitmap[5] = mainArrayView[position].arrayBitmap[4]
        mainArrayView[position].arrayBitmap[4] = tempBitmap
        notifyItemChanged(position)
    }

    fun right(position: Int){
        duration = 4
        var tempBitmap = mainArrayView[position].arrayBitmap[0]
        mainArrayView[position].arrayBitmap[0] = mainArrayView[position].arrayBitmap[1]
        mainArrayView[position].arrayBitmap[1] = mainArrayView[position].arrayBitmap[5]
        mainArrayView[position].arrayBitmap[5] = mainArrayView[position].arrayBitmap[2]
        mainArrayView[position].arrayBitmap[2] = tempBitmap
        notifyItemChanged(position)
    }

    fun left(position: Int){
        duration = 3
        var tempBitmap = mainArrayView[position].arrayBitmap[0]
        mainArrayView[position].arrayBitmap[0] = mainArrayView[position].arrayBitmap[2]
        mainArrayView[position].arrayBitmap[2] = mainArrayView[position].arrayBitmap[5]
        mainArrayView[position].arrayBitmap[5] = mainArrayView[position].arrayBitmap[1]
        mainArrayView[position].arrayBitmap[1] = tempBitmap
        notifyItemChanged(position)
    }



    interface ClickScaleItemInterface{
        fun clickScaleItem( b0 : Bitmap, b1 : Bitmap, b2 : Bitmap, b3 : Bitmap, b4 : Bitmap, b5 : Bitmap, position: Int, itemView: View, imItem: ImageView)

    }



}