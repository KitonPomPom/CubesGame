package kitonpompom.cubesgame.activities //адаптер для фрагмента с картиками

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.activities.utils.*
import kotlin.collections.ArrayList

class AdapterFragPWP(val clickScaleItemInterface: ClickScaleItemInterface): RecyclerView.Adapter<AdapterFragPWP.ImageHolder>(), ItemTouchMoveAndSwipe.ItemTouchDragAdapterPWP{
    var mainArrayView = ArrayList<dataArrayBitmap>()
    var arrayListBitmap = ArrayList<ArrayList<Bitmap>>()
    var countStart = CountStartLinearVisible()
    var click =  ClickableState()
    var clickBack =  ClickableStateBack()
    var noMove = NoMoveIfOpenScale()
    var noMoveBack = NoMoveIfOpenScaleBack()
    var updateLineNoImage = UpdateLineNoImage()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rc_playing_with_pictures_hard, parent,false)
        return AdapterFragPWP.ImageHolder(view, this, parent.id, click, noMove, clickBack, noMoveBack, updateLineNoImage)
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


    class ImageHolder (itemView : View, val adapter: AdapterFragPWP, id: Int, var clickk: ClickableState, var noMovee: NoMoveIfOpenScale,
                       var clickkBack: ClickableStateBack, var noMoveeBack: NoMoveIfOpenScaleBack, var updateLineNoImagee: UpdateLineNoImage ) : RecyclerView.ViewHolder(itemView)  {
        lateinit var imItemOne : ImageView
        lateinit var imItemTwo : ImageView
        lateinit var lineLeft :LinearLayout
        lateinit var lineTop :LinearLayout
        lateinit var lineRight :LinearLayout
        lateinit var lineBottom :LinearLayout
        var counter = 0

        @SuppressLint("ClickableViewAccessibility")
        fun setData ( item: dataArrayBitmap, clickScaleItemInterface: ClickScaleItemInterface){
            imItemOne = itemView.findViewById(R.id.id_item_play_with_pictures_one)
            imItemTwo = itemView.findViewById(R.id.id_item_play_with_pictures_two)
            lineLeft = itemView.findViewById(R.id.linLayLeft)
            lineTop = itemView.findViewById(R.id.linLayTop)
            lineRight = itemView.findViewById(R.id.linLayRight)
            lineBottom = itemView.findViewById(R.id.linLayBottom)
            itemView.visibility = View.VISIBLE

            imItemOne.setImageBitmap(item.arrayBitmap[0])

            var x1: Float = 0.0f
            var x2: Float = 0.0f
            var y1: Float = 0.0f
            var y2: Float = 0.0f
            var noReplaySwipe = true

            if (item.arrayLine[0] == 0) lineLeft.visibility = View.GONE else lineLeft.visibility = View.VISIBLE
            if (item.arrayLine[1] == 0) lineTop.visibility = View.GONE else lineTop.visibility = View.VISIBLE
            if (item.arrayLine[2] == 0) lineRight.visibility = View.GONE else lineRight.visibility = View.VISIBLE
            if (item.arrayLine[3] == 0) lineBottom.visibility = View.GONE else lineBottom.visibility = View.VISIBLE

            //Log.d("MyLog", "Count $adapterPosition")
            //if(countStartLinearVisible.count)
            //clickScaleItemInterface.updateLine(adapterPosition)

            //if (!countStartLinearVisible.count) {
            //    if (adapterPosition == 143)
            //        adapter.countStart.count = true
            //}

                //clickScaleItemInterface.countPlus(countStartLinearVisible.count)
            //updateLineNoImagee.updateLineNoImage = true

                imItemOne.setOnClickListener {
                    if (clickk.clickable && clickkBack.clickable) {
                        clickScaleItemInterface.clickScaleItem(
                            item.arrayBitmap[0], item.arrayBitmap[1],
                            item.arrayBitmap[2], item.arrayBitmap[3],
                            item.arrayBitmap[4], item.arrayBitmap[5],
                            item.arrayNumber[0], item.arrayNumber[1],
                            item.arrayNumber[2], item.arrayNumber[3],
                            item.arrayNumber[4], item.arrayNumber[5],
                            item.arrayPosition[0], item.arrayPosition[1],
                            item.arrayPosition[2], item.arrayPosition[3],
                            item.arrayPosition[4], item.arrayPosition[5],
                            adapterPosition, itemView, imItemOne)
                        Log.d("MyLog", "ItemNumber ${item.arrayNumber}")
                        Log.d("MyLog", "ItemPosition ${item.arrayPosition}")
                    }
                }

                imItemOne.setOnTouchListener(){ viewRc, eventRc ->
                    if (noMovee.noMoveIfOpenScale && noMoveeBack.noMoveIfOpenScale){ //Не запускаем если открыт увеличеный Итем
                        if (clickk.clickable && clickkBack.clickable) {// Не запускаем пока идет анимация
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
                                        //Log.d("MyLog", "rightAdapter")
                                        clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                            item.arrayBitmap[2], item.arrayBitmap[3],
                                            item.arrayBitmap[4], item.arrayBitmap[5],
                                            item.arrayNumber[0], item.arrayNumber[1],
                                            item.arrayNumber[2], item.arrayNumber[3],
                                            item.arrayNumber[4], item.arrayNumber[5],
                                            item.arrayPosition[0], item.arrayPosition[1],
                                            item.arrayPosition[2], item.arrayPosition[3],
                                            item.arrayPosition[4], item.arrayPosition[5],
                                            adapterPosition, itemView, imItemOne)
                                        noReplaySwipe = false
                                    }else {
                                        //Log.d("MyLog", "leftAdapter")
                                        clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                            item.arrayBitmap[2], item.arrayBitmap[3],
                                            item.arrayBitmap[4], item.arrayBitmap[5],
                                            item.arrayNumber[0], item.arrayNumber[1],
                                            item.arrayNumber[2], item.arrayNumber[3],
                                            item.arrayNumber[4], item.arrayNumber[5],
                                            item.arrayPosition[0], item.arrayPosition[1],
                                            item.arrayPosition[2], item.arrayPosition[3],
                                            item.arrayPosition[4], item.arrayPosition[5],
                                            adapterPosition, itemView, imItemOne)
                                        noReplaySwipe = false
                                    }
                                }
                                if (Math.abs(deltaY) > minDistanceUpDown && noReplaySwipe){
                                    if(y2 > y1){
                                        //Log.d("MyLog", "downAdapter")
                                        clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                            item.arrayBitmap[2], item.arrayBitmap[3],
                                            item.arrayBitmap[4], item.arrayBitmap[5],
                                            item.arrayNumber[0], item.arrayNumber[1],
                                            item.arrayNumber[2], item.arrayNumber[3],
                                            item.arrayNumber[4], item.arrayNumber[5],
                                            item.arrayPosition[0], item.arrayPosition[1],
                                            item.arrayPosition[2], item.arrayPosition[3],
                                            item.arrayPosition[4], item.arrayPosition[5],
                                            adapterPosition, itemView, imItemOne)
                                        noReplaySwipe = false
                                    }else {
                                        //Log.d("MyLog", "upAdapter")
                                        clickScaleItemInterface.moveItem(item.arrayBitmap[0], item.arrayBitmap[1],
                                            item.arrayBitmap[2], item.arrayBitmap[3],
                                            item.arrayBitmap[4], item.arrayBitmap[5],
                                            item.arrayNumber[0], item.arrayNumber[1],
                                            item.arrayNumber[2], item.arrayNumber[3],
                                            item.arrayNumber[4], item.arrayNumber[5],
                                            item.arrayPosition[0], item.arrayPosition[1],
                                            item.arrayPosition[2], item.arrayPosition[3],
                                            item.arrayPosition[4], item.arrayPosition[5],
                                            adapterPosition, itemView, imItemOne)
                                        noReplaySwipe = false
                                    }
                                }
                            }
                                MotionEvent.ACTION_UP -> {
                                    x2 = eventRc.x
                                    y2 = eventRc.y
                                    noReplaySwipe = false
                                    var deltaX: Float = x2 - x1
                                    var deltaY: Float = y2 - y1
                                    if (Math.abs(deltaX) < minDistance && Math.abs(deltaY) < minDistanceUpDown){
                                        //Log.d("MyLog", "Click Adapter")
                                    }
                                }
                            }
                        }
                    }
                    return@setOnTouchListener false
                }
        }
    }

    /*
    override fun getItemId(position: Int): Long {
        //Log.d("MyLog", "Posit $position")
        return (position.toLong())
    }
*/

    //Обновляем адаптер когда первый раз рисуется
    fun updateAdapter(newList : ArrayList<dataArrayBitmap>){ // функция обновляет адаптер
        mainArrayView.clear()
        mainArrayView.addAll(newList)
        for (i in 0 until mainArrayView.size) {
            //START_UPDATE_LINE - означает что обновляется при первом запуске
            //NO_POSITION_MOVE - заглушка что бы не проверять рядом стоящие элементы на совпадение
            updateLinePosition(i, Constans.START_UPDATE_LINE, Constans.NO_POSITION_MOVE)
        }
        notifyDataSetChanged()
    }

    //Обновление одного итема, а заним проверяется итемы на совпадения вокруг
    fun updateAdapterPosition(ListBitmap: ArrayList<Bitmap>, ListNumber: ArrayList<Int>, ListPosition: ArrayList<Int> , position: Int, positionMoveFinish: Int) {
        mainArrayView[position].arrayBitmap[0] = ListBitmap[0]
        mainArrayView[position].arrayBitmap[1] = ListBitmap[1]
        mainArrayView[position].arrayBitmap[2] = ListBitmap[2]
        mainArrayView[position].arrayBitmap[3] = ListBitmap[3]
        mainArrayView[position].arrayBitmap[4] = ListBitmap[4]
        mainArrayView[position].arrayBitmap[5] = ListBitmap[5]
        mainArrayView[position].arrayNumber[0] = ListNumber[0]
        mainArrayView[position].arrayNumber[1] = ListNumber[1]
        mainArrayView[position].arrayNumber[2] = ListNumber[2]
        mainArrayView[position].arrayNumber[3] = ListNumber[3]
        mainArrayView[position].arrayNumber[4] = ListNumber[4]
        mainArrayView[position].arrayNumber[5] = ListNumber[5]
        mainArrayView[position].arrayPosition[0] = ListPosition[0]
        mainArrayView[position].arrayPosition[1] = ListPosition[1]
        mainArrayView[position].arrayPosition[2] = ListPosition[2]
        mainArrayView[position].arrayPosition[3] = ListPosition[3]
        mainArrayView[position].arrayPosition[4] = ListPosition[4]
        mainArrayView[position].arrayPosition[5] = ListPosition[5]
        notifyItemChanged(position, null)
        endGameCheck(position)
        //NO_START_UPDATE_LINE - означает что обновляется не при первом запуске
        //positionMoveFinish - позиция куда нужно возращать итемc
        updateLinePosition(position, Constans.NO_START_UPDATE_LINE, positionMoveFinish)
    }



    fun updateLinePosition(position: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "positionMoveFinish $positionMoveFinish")
        when(position){
            0->{
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusNine(position, flagStartUpdateLine, positionMoveFinish)
            }
            1,2,3,4,5,6,7->{
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusNine(position, flagStartUpdateLine, positionMoveFinish)
            }
            8->{
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusNine(position, flagStartUpdateLine, positionMoveFinish)
            }
            9, 18, 27, 36, 45, 54, 63, 72, 81, 90, 99, 108, 117, 126 -> {
                minusNine(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusNine(position, flagStartUpdateLine, positionMoveFinish)
            }
            17, 26, 35, 44, 53, 62, 71, 80, 89, 98, 107, 116, 125, 134 -> {
                minusNine(position, flagStartUpdateLine, positionMoveFinish)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusNine(position, flagStartUpdateLine, positionMoveFinish)
            }
            135 -> {
                minusNine(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
            }
            136, 137, 138, 139, 140, 141, 142 -> {
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusNine(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
            }
            143 -> {
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusNine(position, flagStartUpdateLine, positionMoveFinish)
            }else ->{
            minusOne(position, flagStartUpdateLine, positionMoveFinish)
            plusNine(position, flagStartUpdateLine, positionMoveFinish)
            plusOne(position, flagStartUpdateLine, positionMoveFinish)
            minusNine(position, flagStartUpdateLine, positionMoveFinish)
        }
        }
    }

    fun plusOne(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 1) {
                updateLineRightPlusOne(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineRightPlusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineRightPlusOne(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun minusOne(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 1) {
                updateLineLeftMinusOne(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineLeftMinusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineLeftMinusOne(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun plusNine(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+9].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+9].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 9) {
                updateLineBottomPlusNine(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineBottomPlusNine(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineBottomPlusNine(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun minusNine(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-9].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-9].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 9) {
                updateLineTopMinusNine(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineTopMinusNine(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineTopMinusNine(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun updateLineTopMinusNine(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Если картинки совпадают то plus - false
        if (plus){
            mainArrayView[positionUpdate-9].arrayLine[3] = 0
            mainArrayView[positionUpdate].arrayLine[1]= 0
        } else {
            mainArrayView[positionUpdate-9].arrayLine[3] = 1
            mainArrayView[positionUpdate].arrayLine[1]= 1
            //Log.d("MyLog", "UpdateLineTop $positionUpdate")
        }

        if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE){
            updateLineNoImage.updateLineNoImage = false
            //Unit - Убирает анимацию мигания при обновлении итема
            notifyItemChanged(positionUpdate, Unit)
            if(positionUpdate-9 != positionMoveFinish) notifyItemChanged(positionUpdate-9, Unit)
            updateLinePositionTwo(positionUpdate - 9, positionMoveFinish)
        }
    }
    fun updateLineLeftMinusOne(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (plus){
            mainArrayView[positionUpdate-1].arrayLine[2] = 0
            mainArrayView[positionUpdate].arrayLine[0] = 0

        } else {
            mainArrayView[positionUpdate-1].arrayLine[2] = 1
            mainArrayView[positionUpdate].arrayLine[0] = 1
            //Log.d("MyLog", "UpdateLineLeft $positionUpdate")
        }

        if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
            updateLineNoImage.updateLineNoImage = false
            notifyItemChanged(positionUpdate, Unit)
            if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate - 1, Unit)
            updateLinePositionTwo(positionUpdate - 1, positionMoveFinish)
        }
    }
    fun updateLineRightPlusOne(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (plus){
            mainArrayView[positionUpdate+1].arrayLine[0] = 0
            mainArrayView[positionUpdate].arrayLine[2] = 0
        }else {
            mainArrayView[positionUpdate+1].arrayLine[0] = 1
            mainArrayView[positionUpdate].arrayLine[2] = 1
            //Log.d("MyLog", "UpdateLineRight $positionUpdate")
        }

        if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
            updateLineNoImage.updateLineNoImage = false
            notifyItemChanged(positionUpdate, Unit)
            if(positionUpdate+1 != positionMoveFinish) {
                notifyItemChanged(positionUpdate + 1, Unit)
            }
            updateLinePositionTwo(positionUpdate + 1, positionMoveFinish)
        }
    }
    fun updateLineBottomPlusNine(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "UpdateLineBottom $positionUpdate")
        if (plus){
            mainArrayView[positionUpdate+9].arrayLine[1] = 0
            mainArrayView[positionUpdate].arrayLine[3] = 0
        } else {
            mainArrayView[positionUpdate+9].arrayLine[1] = 1
            mainArrayView[positionUpdate].arrayLine[3] = 1
        }

        if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
            updateLineNoImage.updateLineNoImage = false
            notifyItemChanged(positionUpdate, Unit)
            if(positionUpdate+9 != positionMoveFinish) notifyItemChanged(positionUpdate + 9, Unit)
            updateLinePositionTwo(positionUpdate + 9, positionMoveFinish)
        }
    }

    fun updateLinePositionTwo(position: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "Count $position")
        when(position){
            0->{
                plusOneTwo(position, positionMoveFinish)
                plusNineTwo(position, positionMoveFinish)
            }
            1,2,3,4,5,6,7->{
                plusOneTwo(position, positionMoveFinish)
                minusOneTwo(position, positionMoveFinish)
                plusNineTwo(position, positionMoveFinish)
            }
            8->{
                minusOneTwo(position, positionMoveFinish)
                plusNineTwo(position, positionMoveFinish)
            }
            9, 18, 27, 36, 45, 54, 63, 72, 81, 90, 99, 108, 117, 126 -> {
                minusNineTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
                plusNineTwo(position, positionMoveFinish)
            }
            17, 26, 35, 44, 53, 62, 71, 80, 89, 98, 107, 116, 125, 134 -> {
                minusNineTwo(position, positionMoveFinish)
                minusOneTwo(position, positionMoveFinish)
                plusNineTwo(position, positionMoveFinish)
            }
            135 -> {
                minusNineTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
            }
            136, 137, 138, 139, 140, 141, 142 -> {
                minusOneTwo(position, positionMoveFinish)
                minusNineTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
            }
            143 -> {
                minusOneTwo(position, positionMoveFinish)
                minusNineTwo(position, positionMoveFinish)
            }else ->{
            minusOneTwo(position, positionMoveFinish)
            plusNineTwo(position, positionMoveFinish)
            plusOneTwo(position, positionMoveFinish)
            minusNineTwo(position, positionMoveFinish)
        }
        }
    }

    fun plusOneTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 1) {
                updateLineRightPlusOneTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineRightPlusOneTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineRightPlusOneTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun minusOneTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 1) {
                updateLineLeftMinusOneTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineLeftMinusOneTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineLeftMinusOneTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun plusNineTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+9].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+9].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 9) {
                updateLineBottomPlusNineTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineBottomPlusNineTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineBottomPlusNineTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun minusNineTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-9].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-9].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 9) {
                updateLineTopMinusNineTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineTopMinusNineTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineTopMinusNineTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun updateLineTopMinusNineTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus){
            mainArrayView[positionUpdate-9].arrayLine[3] = 0
            mainArrayView[positionUpdate].arrayLine[1]= 0
        } else {
            mainArrayView[positionUpdate-9].arrayLine[3] = 1
            mainArrayView[positionUpdate].arrayLine[1]= 1
            //Log.d("MyLog", "UpdateLineTop $positionUpdate")
        }
        if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate-9 != positionMoveFinish) notifyItemChanged(positionUpdate-9, Unit)
    }
    fun updateLineLeftMinusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus){
            mainArrayView[positionUpdate-1].arrayLine[2] = 0
            mainArrayView[positionUpdate].arrayLine[0] = 0

        } else {
            mainArrayView[positionUpdate-1].arrayLine[2] = 1
            mainArrayView[positionUpdate].arrayLine[0] = 1
            //Log.d("MyLog", "UpdateLineLeft $positionUpdate")
        }
        if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate-1, Unit)
    }
    fun updateLineRightPlusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus){
            mainArrayView[positionUpdate+1].arrayLine[0] = 0
            mainArrayView[positionUpdate].arrayLine[2] = 0
        }else {
            mainArrayView[positionUpdate+1].arrayLine[0] = 1
            mainArrayView[positionUpdate].arrayLine[2] = 1
           // Log.d("MyLog", "UpdateLineRight $positionUpdate")
        }
        if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate+1, Unit)
    }
    fun updateLineBottomPlusNineTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "UpdateLineBottom $positionUpdate")
        if (plus){
            mainArrayView[positionUpdate+9].arrayLine[1] = 0
            mainArrayView[positionUpdate].arrayLine[3] = 0
        } else {
            mainArrayView[positionUpdate+9].arrayLine[1] = 1
            mainArrayView[positionUpdate].arrayLine[3] = 1
        }
        if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate+9 != positionMoveFinish) notifyItemChanged(positionUpdate+9, Unit)
    }

    //Проверка на то, собрал ли полностью картинку
    fun endGameCheck(positionUpdate:Int){
        var count = 0
        for (i in 0 until mainArrayView.size) {
            if (mainArrayView[i].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
                //Log.d("MyLog", "yes")
                if(i == mainArrayView[i].arrayPosition[0]){
                    count++
                    //Log.d("MyLog", "count $count")
                }else{
                    break
                }
            }else{
                break
            }
        }
        if(count == mainArrayView.size){

            clickScaleItemInterface.imageIsCollected(mainArrayView[0].arrayNumber[0])
        }
    }

    fun transferArrayAdapterToFrag(position: Int, clickScaleItemInterface: ClickScaleItemInterface,
                                   coordinate: List<Float>, coordinateBackStart: List<Float>,
                                   positionMove: Int){
        //Log.d("MyLog", "transfer $position")
        clickScaleItemInterface.transferMoveArray(mainArrayView[position].arrayBitmap[0],
            mainArrayView[position].arrayBitmap[1],
            mainArrayView[position].arrayBitmap[2],
            mainArrayView[position].arrayBitmap[3],
            mainArrayView[position].arrayBitmap[4],
            mainArrayView[position].arrayBitmap[5],
            mainArrayView[position].arrayNumber[0],
            mainArrayView[position].arrayNumber[1],
            mainArrayView[position].arrayNumber[2],
            mainArrayView[position].arrayNumber[3],
            mainArrayView[position].arrayNumber[4],
            mainArrayView[position].arrayNumber[5],
            mainArrayView[position].arrayPosition[0],
            mainArrayView[position].arrayPosition[1],
            mainArrayView[position].arrayPosition[2],
            mainArrayView[position].arrayPosition[3],
            mainArrayView[position].arrayPosition[4],
            mainArrayView[position].arrayPosition[5],
            position, coordinate, coordinateBackStart, positionMove)
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

    fun countPlus(count: Int){
        count +1
    }
    interface ClickScaleItemInterface{
        fun clickScaleItem( b0 : Bitmap, b1 : Bitmap, b2 : Bitmap, b3 : Bitmap, b4 : Bitmap,
                            b5 : Bitmap, n0: Int, n1: Int, n2: Int, n3: Int, n4: Int, n5: Int,
                            p0 : Int, p1 : Int, p2 : Int, p3 : Int, p4 : Int, p5 : Int,
                            position: Int, itemView: View, imItem: ImageView)

        fun moveItem(b0 : Bitmap, b1 : Bitmap, b2 : Bitmap, b3 : Bitmap, b4 : Bitmap,
                     b5 : Bitmap, n0: Int, n1: Int, n2: Int, n3: Int, n4: Int, n5: Int,
                     p0 : Int, p1 : Int, p2 : Int, p3 : Int, p4 : Int, p5 : Int,
                     position: Int, itemView: View, imItem: ImageView)

        fun transferMoveArray(b0 : Bitmap, b1 : Bitmap, b2 : Bitmap, b3 : Bitmap, b4 : Bitmap,
                              b5 : Bitmap,  n0: Int, n1: Int, n2: Int, n3: Int, n4: Int, n5: Int,
                              p0 : Int, p1 : Int, p2 : Int, p3 : Int, p4 : Int, p5 : Int,
                              position: Int, coordinate: List<Float>,
                              coordinateBackStart: List<Float>, positionMove: Int)

        // Если картинка собрана, отправляем с какой позиции кубика картинка собрана,
        // что бы обновить в drawerLayout, и потом подсветить собраную картинку.
        fun imageIsCollected (positionImageCollected: Int)

        fun countPlus (count:Int)
    }
}