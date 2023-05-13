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

class AdapterFragPWPHard(val clickScaleItemInterface: ClickScaleItemInterface): RecyclerView.Adapter<AdapterFragPWPHard.ImageHolder>(), ItemTouchMoveAndSwipe.ItemTouchDragAdapterPWP{
    var mainArrayView = ArrayList<dataArrayBitmap>()
    var arrayListBitmap = ArrayList<ArrayList<Bitmap>>()
    var countStart = CountStartLinearVisible()
    var click =  ClickableState()
    var clickBack =  ClickableStateBack()
    var clickUpdateLine = ClickableStateUpdateLine() //Для блокировки перетягивания и увелечения во время обновления линий
    var noMove = NoMoveIfOpenScale()
    var noMoveBack = NoMoveIfOpenScaleBack()
    var updateLineNoImage = UpdateLineNoImage()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rc_playing_with_pictures_hard, parent,false)
        Log.d("MyLog", "view.with: ${view.findViewById<View>(R.id.id_item_play_with_pictures_one).width}")
        return AdapterFragPWPHard.ImageHolder(view, this, parent.id, click, noMove, clickBack,clickUpdateLine, noMoveBack, updateLineNoImage)
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


    class ImageHolder (itemView : View, val adapter: AdapterFragPWPHard, id: Int, var clickk: ClickableState, var noMovee: NoMoveIfOpenScale,
                       var clickkBack: ClickableStateBack, var clickkUpdateLine: ClickableStateUpdateLine, var noMoveeBack: NoMoveIfOpenScaleBack, var updateLineNoImagee: UpdateLineNoImage ) : RecyclerView.ViewHolder(itemView)  {
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
            var actionMoveCheck = false
            var actionNoClickOnTouchIfTouchOnMove = false

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
                    if (!actionNoClickOnTouchIfTouchOnMove) {
                        //Log.d("MyLog", "clicker ${noMovee.noMoveIfOpenScale} ${noMoveeBack.noMoveIfOpenScale}")
                        if (!noMovee.noMoveIfOpenScale && noMoveeBack.noMoveIfOpenScale) {
                            if (clickk.clickable && clickkBack.clickable && clickkUpdateLine.clickable) {
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
                                    adapterPosition, itemView, imItemOne
                                )
                            }
                        }
                    }
                }

                imItemOne.setOnTouchListener(){ viewRc, eventRc ->
                    //Log.d("MyLog", "1 touchAdapter no move ${noMovee.noMoveIfOpenScale}")
                    if (noMovee.noMoveIfOpenScale && noMoveeBack.noMoveIfOpenScale){ //Не запускаем если открыт увеличеный Итем
                        if (clickk.clickable && clickkBack.clickable && clickkUpdateLine.clickable) {// Не запускаем пока идет анимация

                            val minDistance = 23
                            val minDistanceUpDown = 15
                            when (eventRc.action) {
                                MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                                    actionMoveCheck = false
                                    x1 = eventRc.x //Позиция по оси Х куда нажали
                                    y1 = eventRc.y //Позиция по оси Y куда нажали
                                    noReplaySwipe = true
                                }
                                MotionEvent.ACTION_MOVE -> {
                                    actionMoveCheck = true
                                    actionNoClickOnTouchIfTouchOnMove = true
                                    x2 = eventRc.x
                                    y2 = eventRc.y
                                    var deltaX: Float = x2 - x1
                                    var deltaY: Float = y2 - y1
                                    if(noReplaySwipe){
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
                                    /*Log.d("MyLog", "!!! touchAdapter")
                                    if (Math.abs(deltaX) > minDistance && noReplaySwipe){
                                        if(x2 > x1){
                                            Log.d("MyLog", "rightAdapter")
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
                                            Log.d("MyLog", "leftAdapter")
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
                                            Log.d("MyLog", "downAdapter")
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
                                            Log.d("MyLog", "upAdapter")
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
                                    }*/
                            }
                                MotionEvent.ACTION_UP -> {
                                    actionNoClickOnTouchIfTouchOnMove = true
                                   // x2 = eventRc.x
                                   // y2 = eventRc.y
                                    noReplaySwipe = false
                                   // var deltaX: Float = x2 - x1
                                   // var deltaY: Float = y2 - y1
                                   // if (Math.abs(deltaX) < minDistance && Math.abs(deltaY) < minDistanceUpDown){
                                        //Log.d("MyLog", "Click Adapter")
                                   // }
                                    if (clickk.clickable && clickkBack.clickable && clickkUpdateLine.clickable) {
                                        if(!actionMoveCheck) {
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
                                                adapterPosition, itemView, imItemOne
                                            )
                                        }else{
                                            clickScaleItemInterface.actionMoveAndActionUP(adapterPosition)
                                            /*clickScaleItemInterface.clickScaleItem(
                                                item.arrayBitmap[0], item.arrayBitmap[1],
                                                item.arrayBitmap[2], item.arrayBitmap[3],
                                                item.arrayBitmap[4], item.arrayBitmap[5],
                                                item.arrayNumber[0], item.arrayNumber[1],
                                                item.arrayNumber[2], item.arrayNumber[3],
                                                item.arrayNumber[4], item.arrayNumber[5],
                                                item.arrayPosition[0], item.arrayPosition[1],
                                                item.arrayPosition[2], item.arrayPosition[3],
                                                item.arrayPosition[4], item.arrayPosition[5],
                                                adapterPosition, itemView, imItemOne
                                            )*/
                                        }
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

    //Обновляем только одну позицию, в том случае если потянули, картинка исчезла, а новая не успела загрузиться
    fun updateOnePosition(position: Int){
        notifyItemChanged(position, null)
    }



    fun updateLinePosition(position: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "positionMoveFinish $positionMoveFinish")
        when(position){
            0->{
                topEdge(position)
                leftEdge(position)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusSix(position, flagStartUpdateLine, positionMoveFinish)
            }
            1,2,3,4 ->{
                topEdge(position)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusSix(position, flagStartUpdateLine, positionMoveFinish)
            }
            5->{
                topEdge(position)
                rightEdge(position)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusSix(position, flagStartUpdateLine, positionMoveFinish)
            }
            6, 12, 18, 24, 30, 36, 42, 48 -> {
                leftEdge(position)
                minusSix(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusSix(position, flagStartUpdateLine, positionMoveFinish)
            }
            11, 17, 23, 29, 35, 41, 47, 53 -> {
                rightEdge(position)
                minusSix(position, flagStartUpdateLine, positionMoveFinish)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusSix(position, flagStartUpdateLine, positionMoveFinish)
            }
            54 -> {
                leftEdge(position)
                bottomEdge(position)
                minusSix(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
            }
            55, 56, 57, 58 -> {
                bottomEdge(position)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusSix(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
            }
            59 -> {
                rightEdge(position)
                bottomEdge(position)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusSix(position, flagStartUpdateLine, positionMoveFinish)
            }else ->{
                    minusOne(position, flagStartUpdateLine, positionMoveFinish)
                    plusSix(position, flagStartUpdateLine, positionMoveFinish)
                    plusOne(position, flagStartUpdateLine, positionMoveFinish)
                    minusSix(position, flagStartUpdateLine, positionMoveFinish)
            }
        }
    }

    fun plusOne(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 1) {
                when (mainArrayView[positionUpdate].arrayPosition[0]) {
                    5, 11, 17, 23, 29, 35, 41, 47, 53, 59 -> {
                        when (mainArrayView[positionUpdate].arrayPosition[0] + 1) {
                            6, 12, 18, 24, 30, 36, 42, 48, 54  -> {
                                updateLineRightPlusOne(
                                    false,
                                    positionUpdate,
                                    flagStartUpdateLine,
                                    positionMoveFinish)
                            }
                            else -> {
                                updateLineRightPlusOne(
                                    true,
                                    positionUpdate,
                                    flagStartUpdateLine,
                                    positionMoveFinish)
                            }
                        }
                    }
                    else -> {
                        updateLineRightPlusOne(
                            true,
                            positionUpdate,
                            flagStartUpdateLine,
                            positionMoveFinish)
                    }
                }
            }else {
                updateLineRightPlusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineRightPlusOne(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun minusOne(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]) {
            if (mainArrayView[positionUpdate - 1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 1) {
                when (mainArrayView[positionUpdate].arrayPosition[0]) {
                    6, 12, 18, 24, 30, 36, 42, 48, 54 -> {
                        when (mainArrayView[positionUpdate].arrayPosition[0] - 1) {
                            5, 11, 17, 23, 29, 35, 41, 47, 53, 59 -> {
                                updateLineLeftMinusOne(
                                    false,
                                    positionUpdate,
                                    flagStartUpdateLine,
                                    positionMoveFinish)
                            }
                            else -> {
                                updateLineLeftMinusOne(
                                    true,
                                    positionUpdate,
                                    flagStartUpdateLine,
                                    positionMoveFinish)
                            }
                        }
                    }
                    else -> {
                        updateLineLeftMinusOne(
                            true,
                            positionUpdate,
                            flagStartUpdateLine,
                            positionMoveFinish)
                    }
                }
            }else{
                updateLineLeftMinusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineLeftMinusOne(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }

    }

    fun plusSix(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+6].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+6].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 6) {
                updateLineBottomPlusSix(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineBottomPlusSix(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineBottomPlusSix(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun minusSix(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-6].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-6].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 6) {
                updateLineTopMinusSix(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineTopMinusSix(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineTopMinusSix(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun topEdge(positionUpdate: Int){
        if(mainArrayView[positionUpdate].arrayPosition[0] == positionUpdate){
            mainArrayView[positionUpdate].arrayLine[1] = 0
        }else{
            mainArrayView[positionUpdate].arrayLine[1] = 1
        }
        notifyItemChanged(positionUpdate, Unit)
    }

    fun leftEdge(positionUpdate: Int){
        if(mainArrayView[positionUpdate].arrayPosition[0] == positionUpdate){
            mainArrayView[positionUpdate].arrayLine[0] = 0
        }else{
            mainArrayView[positionUpdate].arrayLine[0] = 1
        }
        notifyItemChanged(positionUpdate, Unit)
    }

    fun rightEdge(positionUpdate: Int){
        if(mainArrayView[positionUpdate].arrayPosition[0] == positionUpdate){
            mainArrayView[positionUpdate].arrayLine[2] = 0
        }else{
            mainArrayView[positionUpdate].arrayLine[2] = 1
        }
        notifyItemChanged(positionUpdate, Unit)
    }

    fun bottomEdge(positionUpdate: Int){
        if(mainArrayView[positionUpdate].arrayPosition[0] == positionUpdate){
            mainArrayView[positionUpdate].arrayLine[3] = 0
        }else{
            mainArrayView[positionUpdate].arrayLine[3] = 1
        }
        notifyItemChanged(positionUpdate, Unit)
    }

    fun updateLineTopMinusSix(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Если картинки совпадают то plus - true и линия исчезает, 0 - исчезает, 1 - появляется
        if (plus){
            //Если цифра выполняем только тогда когда цифра должна измениться с 0 на 1 или 1 на 0
            if(mainArrayView[positionUpdate - 6].arrayLine[3] == 1 &&
                mainArrayView[positionUpdate].arrayLine[1] == 1) {
                mainArrayView[positionUpdate - 6].arrayLine[3] = 0
                mainArrayView[positionUpdate].arrayLine[1] = 0
                //Log.d("MyLog", "UpdateLineTop true $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE){
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    //Unit - Убирает анимацию мигания при обновлении итема
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)
                    updateLinePositionTwo(positionUpdate - 6, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate - 6].arrayLine[3] == 0 &&
                mainArrayView[positionUpdate].arrayLine[1] == 0) {
                mainArrayView[positionUpdate - 6].arrayLine[3] = 1
                mainArrayView[positionUpdate].arrayLine[1] = 1
                //Log.d("MyLog", "UpdateLineTop false $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE){
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    //Unit - Убирает анимацию мигания при обновлении итема
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)
                    updateLinePositionTwo(positionUpdate - 6, positionMoveFinish)
                }
            }
        }


    }
    fun updateLineLeftMinusOne(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (plus){
            if(mainArrayView[positionUpdate - 1].arrayLine[2] == 1 &&
                mainArrayView[positionUpdate].arrayLine[0] == 1) {
                mainArrayView[positionUpdate - 1].arrayLine[2] = 0
                mainArrayView[positionUpdate].arrayLine[0] = 0
                //Log.d("MyLog", "UpdateLineLeft true $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    clickUpdateLine.clickable = false
                    updateLineNoImage.updateLineNoImage = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate - 1, Unit)
                    updateLinePositionTwo(positionUpdate - 1, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate - 1].arrayLine[2] == 0 &&
                mainArrayView[positionUpdate].arrayLine[0] == 0) {
                mainArrayView[positionUpdate - 1].arrayLine[2] = 1
                mainArrayView[positionUpdate].arrayLine[0] = 1
                //Log.d("MyLog", "UpdateLineLeft false $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    clickUpdateLine.clickable = false
                    updateLineNoImage.updateLineNoImage = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate - 1, Unit)
                    updateLinePositionTwo(positionUpdate - 1, positionMoveFinish)
                }
            }
        }
    }

    fun updateLineRightPlusOne(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (plus){
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 1 &&
                mainArrayView[positionUpdate].arrayLine[2] == 1) {
                mainArrayView[positionUpdate + 1].arrayLine[0] = 0
                mainArrayView[positionUpdate].arrayLine[2] = 0
                //Log.d("MyLog", "UpdateLineRight true $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+1 != positionMoveFinish) {
                        notifyItemChanged(positionUpdate + 1, Unit)
                    }
                    updateLinePositionTwo(positionUpdate + 1, positionMoveFinish)
                }
            }
        }else {
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 0 &&
                mainArrayView[positionUpdate].arrayLine[2] == 0) {
                mainArrayView[positionUpdate + 1].arrayLine[0] = 1
                mainArrayView[positionUpdate].arrayLine[2] = 1
                //Log.d("MyLog", "UpdateLineRight false $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+1 != positionMoveFinish) {
                        notifyItemChanged(positionUpdate + 1, Unit)
                    }
                    updateLinePositionTwo(positionUpdate + 1, positionMoveFinish)
                }
            }
        }
    }

    fun updateLineBottomPlusSix(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "UpdateLineBottom $positionUpdate")
        if (plus){
            if(mainArrayView[positionUpdate + 6].arrayLine[1] == 1 &&
                mainArrayView[positionUpdate].arrayLine[3] == 1) {
                mainArrayView[positionUpdate + 6].arrayLine[1] = 0
                mainArrayView[positionUpdate].arrayLine[3] = 0
                //Log.d("MyLog", "UpdateLineBottom true $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+6 != positionMoveFinish) notifyItemChanged(positionUpdate + 6, Unit)
                    updateLinePositionTwo(positionUpdate + 6, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate + 6].arrayLine[1] == 0 &&
                mainArrayView[positionUpdate].arrayLine[3] == 0) {
                mainArrayView[positionUpdate + 6].arrayLine[1] = 1
                mainArrayView[positionUpdate].arrayLine[3] = 1
                //Log.d("MyLog", "UpdateLineBottom false $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+6 != positionMoveFinish) notifyItemChanged(positionUpdate + 6, Unit)
                    updateLinePositionTwo(positionUpdate + 6, positionMoveFinish)
                }
            }
        }
    }

    fun updateLinePositionTwo(position: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "Count $position")
        when(position){
            0->{
                plusOneTwo(position, positionMoveFinish)
                plusSixTwo(position, positionMoveFinish)
            }
            1,2,3,4 ->{
                plusOneTwo(position, positionMoveFinish)
                minusOneTwo(position, positionMoveFinish)
                plusSixTwo(position, positionMoveFinish)
            }
            5->{
                minusOneTwo(position, positionMoveFinish)
                plusSixTwo(position, positionMoveFinish)
            }
            6, 12, 18, 24, 30, 36, 42, 48 -> {
                minusSixTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
                plusSixTwo(position, positionMoveFinish)
            }
            11, 17, 23, 29, 35, 41, 47, 53 -> {
                minusSixTwo(position, positionMoveFinish)
                minusOneTwo(position, positionMoveFinish)
                plusSixTwo(position, positionMoveFinish)
            }
            54 -> {
                minusSixTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
            }
            55, 56, 57, 58 -> {
                minusOneTwo(position, positionMoveFinish)
                minusSixTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
            }
            59 -> {
                minusOneTwo(position, positionMoveFinish)
                minusSixTwo(position, positionMoveFinish)
            }else ->{
            minusOneTwo(position, positionMoveFinish)
            plusSixTwo(position, positionMoveFinish)
            plusOneTwo(position, positionMoveFinish)
            minusSixTwo(position, positionMoveFinish)
        }
        }
    }

    fun plusOneTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0]+1) {
                when (mainArrayView[positionUpdate].arrayPosition[0]) {
                    5, 11, 17, 23, 29, 35, 41, 47, 53, 59 -> {
                        when (mainArrayView[positionUpdate].arrayPosition[0]+1) {
                            6, 12, 18, 24, 30, 36, 42, 48, 54  -> {
                                updateLineRightPlusOneTwo(
                                    false,
                                    positionUpdate,
                                    positionMoveFinish)
                            }
                            else -> {
                                updateLineRightPlusOneTwo(
                                    true,
                                    positionUpdate,
                                    positionMoveFinish)
                            }
                        }
                    }
                    else -> {
                        updateLineRightPlusOneTwo(
                            true,
                            positionUpdate,
                            positionMoveFinish)
                    }
                }
            }else {
                updateLineRightPlusOneTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineRightPlusOneTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun minusOneTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0]-1) {
                when (mainArrayView[positionUpdate].arrayPosition[0]) {
                    6, 12, 18, 24, 30, 36, 42, 48, 54 -> {
                        when (mainArrayView[positionUpdate].arrayPosition[0]-1) {
                            5, 11, 17, 23, 29, 35, 41, 47, 53, 59 -> {
                                updateLineLeftMinusOneTwo(
                                    false,
                                    positionUpdate,
                                    positionMoveFinish)
                            }
                            else -> {
                                updateLineLeftMinusOneTwo(
                                    true,
                                    positionUpdate,
                                    positionMoveFinish)
                            }
                        }
                    }
                    else -> {
                        updateLineLeftMinusOneTwo(
                            true,
                            positionUpdate,
                            positionMoveFinish)
                    }
                }
            }else {
                updateLineLeftMinusOneTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineLeftMinusOneTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun plusSixTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+6].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+6].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 6) {
                updateLineBottomPlusSixTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineBottomPlusSixTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineBottomPlusSixTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun minusSixTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-6].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-6].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 6) {
                updateLineTopMinusSixTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineTopMinusSixTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineTopMinusSixTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun updateLineTopMinusSixTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus){
            if (mainArrayView[positionUpdate - 6].arrayLine[3] == 1 &&
                mainArrayView[positionUpdate].arrayLine[1] == 1) {
                    mainArrayView[positionUpdate - 6].arrayLine[3] = 0
                    mainArrayView[positionUpdate].arrayLine[1] = 0
                    if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)
                    clickUpdateLine.clickable = true
                    //Log.d("MyLog", "UpdateLineTopTwo true $positionUpdate")
            }
        } else {
            if (mainArrayView[positionUpdate - 6].arrayLine[3] == 0 &&
                mainArrayView[positionUpdate].arrayLine[1] == 0) {
                    mainArrayView[positionUpdate - 6].arrayLine[3] = 1
                    mainArrayView[positionUpdate].arrayLine[1] = 1
                    if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if (positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)
                    clickUpdateLine.clickable = true
                    //Log.d("MyLog", "UpdateLineTopTwo false $positionUpdate")
            }
        }
        /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)
        clickUpdateLine.clickable = true*/
    }

    fun updateLineLeftMinusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus){
            if ( mainArrayView[positionUpdate - 1].arrayLine[2] == 1 &&
                mainArrayView[positionUpdate].arrayLine[0] == 1) {
                    mainArrayView[positionUpdate - 1].arrayLine[2] = 0
                    mainArrayView[positionUpdate].arrayLine[0] = 0
                    if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate-1, Unit)
                    //clickUpdateLine.clickable = true
                    //Log.d("MyLog", "UpdateLineLeftTwo true $positionUpdate")
            }
        } else {
            if ( mainArrayView[positionUpdate - 1].arrayLine[2] == 0 &&
                mainArrayView[positionUpdate].arrayLine[0] == 0) {
                    mainArrayView[positionUpdate - 1].arrayLine[2] = 1
                    mainArrayView[positionUpdate].arrayLine[0] = 1
                    if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate-1, Unit)
                    //clickUpdateLine.clickable = true
                    //Log.d("MyLog", "UpdateLineLeftTwo false $positionUpdate")
            }
        }
        /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate-1, Unit)*/
        clickUpdateLine.clickable = true
    }
    fun updateLineRightPlusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus){
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 1 &&
                mainArrayView[positionUpdate].arrayLine[2] == 1) {
                    mainArrayView[positionUpdate + 1].arrayLine[0] = 0
                    mainArrayView[positionUpdate].arrayLine[2] = 0
                    if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate+1, Unit)
                    //clickUpdateLine.clickable = true
                    //Log.d("MyLog", "UpdateLineRightTwo true $positionUpdate")
            }
        }else {
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 0 &&
                mainArrayView[positionUpdate].arrayLine[2] == 0) {
                    mainArrayView[positionUpdate + 1].arrayLine[0] = 1
                    mainArrayView[positionUpdate].arrayLine[2] = 1
                    if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate+1, Unit)
                    //clickUpdateLine.clickable = true
                    //Log.d("MyLog", "UpdateLineRightTwo false $positionUpdate")
            }
        }
        /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate+1, Unit)*/
        clickUpdateLine.clickable = true
    }
    fun updateLineBottomPlusSixTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "UpdateLineBottom $positionUpdate")
        if (plus){
            if (mainArrayView[positionUpdate+6].arrayLine[1] == 1 && mainArrayView[positionUpdate].arrayLine[3] == 1) {
                mainArrayView[positionUpdate + 6].arrayLine[1] = 0
                mainArrayView[positionUpdate].arrayLine[3] = 0
                if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if(positionUpdate+6 != positionMoveFinish) notifyItemChanged(positionUpdate+6, Unit)
                //clickUpdateLine.clickable = true
                //Log.d("MyLog", "UpdateLineBottomTwo true $positionUpdate")
            }
        } else {
            if (mainArrayView[positionUpdate + 6].arrayLine[1] == 0 && mainArrayView[positionUpdate].arrayLine[3] == 0){
                mainArrayView[positionUpdate + 6].arrayLine[1] = 1
                mainArrayView[positionUpdate].arrayLine[3] = 1
                if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if(positionUpdate+6 != positionMoveFinish) notifyItemChanged(positionUpdate+6, Unit)
                //clickUpdateLine.clickable = true
                //Log.d("MyLog", "UpdateLineBottomTwo false $positionUpdate")
            }
        }
        /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate+6 != positionMoveFinish) notifyItemChanged(positionUpdate+6, Unit)*/
        clickUpdateLine.clickable = true
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
        //Если одновременно сработали ActionMove и actionUP в слушателе нажатия и картинка не успела потянуться
        fun actionMoveAndActionUP(position: Int)
    }
}