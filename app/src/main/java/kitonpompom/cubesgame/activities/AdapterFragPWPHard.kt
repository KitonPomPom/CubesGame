package kitonpompom.cubesgame.activities //адаптер для фрагмента с картиками

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.plattysoft.leonids.ParticleSystem
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.activities.utils.*
import kotlin.collections.ArrayList

class AdapterFragPWPHard(val clickScaleItemInterface: ClickScaleItemInterface, val activity: FragmentActivity): RecyclerView.Adapter<AdapterFragPWPHard.ImageHolder>(), ItemTouchMoveAndSwipe.ItemTouchDragAdapterPWP{
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
        holder.setData(mainArrayView[position], clickScaleItemInterface, activity)
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
        fun setData ( item: dataArrayBitmap, clickScaleItemInterface: ClickScaleItemInterface, activity: FragmentActivity){
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

            if (item.arrayLine[0] == 1) {
                lineLeft.visibility = View.VISIBLE
            } else {
                lineLeft.visibility = View.INVISIBLE
                if(item.arrayLine[0] == 0) {
                    val runnable =
                        Runnable { doAnimation(activity, lineLeft) } //Созд. рунабл для замедления.
                    itemView.postDelayed(runnable, 300L)// Замедление (из View)
                    clickScaleItemInterface.soundEffect()
                    clickScaleItemInterface.updateLineTwoNoAnimation(adapterPosition, 0)
                }
            }

            if (item.arrayLine[1] == 1) {
                lineTop.visibility = View.VISIBLE
            } else{
                lineTop.visibility = View.INVISIBLE
                if(item.arrayLine[1] == 0) {
                    val runnable = Runnable { doAnimation(activity, lineTop) }
                    itemView.postDelayed(runnable, 300L)
                    clickScaleItemInterface.soundEffect()
                    clickScaleItemInterface.updateLineTwoNoAnimation(adapterPosition, 1)
                }
            }

            if (item.arrayLine[2] == 1){
                lineRight.visibility = View.VISIBLE
            } else{
                lineRight.visibility = View.INVISIBLE
                if(item.arrayLine[2] == 0) {
                    val runnable = Runnable { doAnimation(activity, lineRight) }
                    itemView.postDelayed(runnable, 300L)
                    clickScaleItemInterface.soundEffect()
                    clickScaleItemInterface.updateLineTwoNoAnimation(adapterPosition, 2)
                }
            }

            if (item.arrayLine[3] == 1) {
                lineBottom.visibility = View.VISIBLE
            } else{
                lineBottom.visibility = View.INVISIBLE
                if(item.arrayLine[3] == 0) {
                    val runnable = Runnable { doAnimation(activity, lineBottom) }
                    itemView.postDelayed(runnable, 300L)
                    clickScaleItemInterface.soundEffect()
                    clickScaleItemInterface.updateLineTwoNoAnimation(adapterPosition, 3)
                    //doAnimation(activity, lineBottom)
                }
            }

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
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return@setOnTouchListener false
                }
        }
        fun doAnimation(act: FragmentActivity, view: View) {
            val ps = ParticleSystem(act, 100, view.context.getDrawable(R.drawable.star_black), 1000L)
            ps.setSpeedRange(0.1f, 0.25f)
            ps.setScaleRange(0.7f, 1.3f)
            ps.setSpeedRange(0.1f, 0.25f)
            ps.setAcceleration(0.0001f, 90)
            ps.setRotationSpeedRange(90f, 180f)
            ps.setFadeOut(200, AccelerateInterpolator())
            ps.setStartTime(3000L)
            //ps.emit(1,1,100)
            ps.oneShot(view, 10)
            //ps.emit(binding.layFragPlayPwpEasy.idLinLineyka, 100)
            //particlesPerSecond - кол-во звездочек в секунду (Частиц)
            //maxParticles - кол-во частиц
            //timeToLive - Время жизни частицын
            //setSpeedRange - диапазон скоростей(не меняется)
            //setScaleRange - размер частиц
            //setAcceleration - ускорение, угол
            //setRotationSpeedRange - диапазаон вращения
            //setFadeOut - затухание
        }
    }

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
            //Если записана 1, то ставим 0
            if(mainArrayView[positionUpdate].arrayLine[1] == 1) {
                mainArrayView[positionUpdate].arrayLine[1] = 0
            }else{ //Если записано уже 0 или 2 или 3, то ставим 2
                mainArrayView[positionUpdate].arrayLine[1] = 2
            }
        }else{//Если позиция не совпала, то записываем 1
            mainArrayView[positionUpdate].arrayLine[1] = 1
        }
        notifyItemChanged(positionUpdate, Unit)
    }

    fun leftEdge(positionUpdate: Int){
        //Если позиция совпала то записываем либо 0 либо 2 и линия слева исчезает
        if(mainArrayView[positionUpdate].arrayPosition[0] == positionUpdate){
            //Если записана 1, то ставим 0
            if(mainArrayView[positionUpdate].arrayLine[0] == 1){
                mainArrayView[positionUpdate].arrayLine[0] = 0
            }else{ //Если записано уже 0 или 2 или 3, то ставим 2
                mainArrayView[positionUpdate].arrayLine[0] = 2
            }
        }else{ //Если позиция не совпала, то записываем 1
            mainArrayView[positionUpdate].arrayLine[0] = 1
        }
        notifyItemChanged(positionUpdate, Unit)
    }

    fun rightEdge(positionUpdate: Int){ //Проверка кубика крайнего справа на то стал ли он на свое место
        //Если позиция совпала то записываем либо 0 либо 2 и линия справа исчезает
        if(mainArrayView[positionUpdate].arrayPosition[0] == positionUpdate){
            //Если записана 1, то ставим 0
            if(mainArrayView[positionUpdate].arrayLine[2] == 1) {
                mainArrayView[positionUpdate].arrayLine[2] = 0
            }else{ //Если записано уже 0 или 2 или 3, то ставим 2
                mainArrayView[positionUpdate].arrayLine[2] = 2
            }
        }else{ //Если позиция не совпала, то записываем 1 и линия появляется
            mainArrayView[positionUpdate].arrayLine[2] = 1
        }
        notifyItemChanged(positionUpdate, Unit) //Обновить адаптер по позиции
    }

    fun bottomEdge(positionUpdate: Int){ //Проверка кубика нижнего ряда на то стал ли он на свое место
        //Если позиция совпала то записываем либо 0 либо 2 и линия справа исчезает
        if(mainArrayView[positionUpdate].arrayPosition[0] == positionUpdate){
            //Если записана 1, то ставим 0
            if(mainArrayView[positionUpdate].arrayLine[3] == 1) {
                mainArrayView[positionUpdate].arrayLine[3] = 0
            }else{ //Если записано уже 0 или 2 или 3, то ставим 2
                mainArrayView[positionUpdate].arrayLine[3] = 2
            }
        }else{
            mainArrayView[positionUpdate].arrayLine[3] = 1
        }
        notifyItemChanged(positionUpdate, Unit) //Обновить адаптер по позиции
    }

    fun updateLineTopMinusSix(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Если картинки совпадают то plus - true и линия исчезает, 0 - исчезает, 1 - появляется
        if (plus){
            //Выполняем только тогда когда цифра должна измениться с 0 на 1 или 1 на 0
            if(mainArrayView[positionUpdate - 6].arrayLine[3] == 1 &&
                mainArrayView[positionUpdate].arrayLine[1] == 1) {
                //mainArrayView[positionUpdate - 3].arrayLine[3] = 0
                //mainArrayView[positionUpdate].arrayLine[1] = 0
                // Если Constans.NO_START_UPDATE_LINE, запускаем не первый раз, если при первом запуске то не запускаем
                // индивидуальное обновление итема
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE){
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    //Unit - Убирает анимацию мигания при обновлении итема
                    notifyItemChanged(positionUpdate, Unit)
                    // Если позиция картинки сверхку не равна позиции возращающейся на старое место картинки, то
                    //обновляем позицию картинку сверху
                    if(positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)
                    updateLinePositionTwo(positionUpdate - 6, positionMoveFinish)
                }
            }else{
                mainArrayView[positionUpdate - 6].arrayLine[3] = 2
                mainArrayView[positionUpdate].arrayLine[1] = 2
                //Log.d("MyLog", "UpdateLineTop true $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    //Unit - Убирает анимацию мигания при обновлении итема
                    notifyItemChanged(positionUpdate, Unit)
                    if (positionUpdate - 6 != positionMoveFinish) notifyItemChanged(positionUpdate - 6, Unit)
                    updateLinePositionTwo(positionUpdate - 6, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate -6].arrayLine[3] == 0 || mainArrayView[positionUpdate - 6].arrayLine[3] == 2 &&
                mainArrayView[positionUpdate].arrayLine[1] == 0 || mainArrayView[positionUpdate].arrayLine[1] == 2) {
                mainArrayView[positionUpdate - 6].arrayLine[3] = 1
                mainArrayView[positionUpdate].arrayLine[1] = 1
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
                //mainArrayView[positionUpdate - 1].arrayLine[2] = 0
                //mainArrayView[positionUpdate].arrayLine[0] = 0
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    clickUpdateLine.clickable = false
                    updateLineNoImage.updateLineNoImage = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate - 1, Unit)
                    updateLinePositionTwo(positionUpdate - 1, positionMoveFinish)
                }
            }else{
                mainArrayView[positionUpdate - 1].arrayLine[2] = 2
                mainArrayView[positionUpdate].arrayLine[0] = 2
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    clickUpdateLine.clickable = false
                    updateLineNoImage.updateLineNoImage = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate - 1, Unit)
                    updateLinePositionTwo(positionUpdate - 1, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate - 1].arrayLine[2] == 0 || mainArrayView[positionUpdate - 1].arrayLine[2] == 2 &&
                mainArrayView[positionUpdate].arrayLine[0] == 0 || mainArrayView[positionUpdate].arrayLine[0] == 2) {
                mainArrayView[positionUpdate - 1].arrayLine[2] = 1
                mainArrayView[positionUpdate].arrayLine[0] = 1
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
        // plus true - удалить линии, false - Не удалять
        if (plus){
            //Если раньше записана 1, то ставим 0
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 1 &&
                mainArrayView[positionUpdate].arrayLine[2] == 1) {
                //mainArrayView[positionUpdate + 1].arrayLine[0] = 0
                //mainArrayView[positionUpdate].arrayLine[2] = 0
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate + 1, Unit)
                    updateLinePositionTwo(positionUpdate + 1, positionMoveFinish)
                }
            }else{
                mainArrayView[positionUpdate + 1].arrayLine[0] = 2
                mainArrayView[positionUpdate].arrayLine[2] = 2
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate + 1, Unit)
                    updateLinePositionTwo(positionUpdate + 1, positionMoveFinish)
                }
            }
        }else {
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 0 || mainArrayView[positionUpdate + 1].arrayLine[0] == 2 &&
                mainArrayView[positionUpdate].arrayLine[2] == 0 || mainArrayView[positionUpdate].arrayLine[2] == 2) {
                mainArrayView[positionUpdate + 1].arrayLine[0] = 1
                mainArrayView[positionUpdate].arrayLine[2] = 1
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate + 1, Unit)
                    updateLinePositionTwo(positionUpdate + 1, positionMoveFinish)
                }
            }
        }
    }

    fun updateLineBottomPlusSix(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (plus){
            if(mainArrayView[positionUpdate + 6].arrayLine[1] == 1 &&
                mainArrayView[positionUpdate].arrayLine[3] == 1) {
                //mainArrayView[positionUpdate + 3].arrayLine[1] = 0
                //mainArrayView[positionUpdate].arrayLine[3] = 0
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+6 != positionMoveFinish) notifyItemChanged(positionUpdate + 6, Unit)
                    //positionUpdate - передаем позицию что бы снова не обновлять у соседнего кубика полоски
                    updateLinePositionTwo(positionUpdate + 6, positionMoveFinish)
                }
            }else{
                mainArrayView[positionUpdate + 6].arrayLine[1] = 2
                mainArrayView[positionUpdate].arrayLine[3] = 2
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+6 != positionMoveFinish) notifyItemChanged(positionUpdate + 6, Unit)
                    updateLinePositionTwo(positionUpdate + 6, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate + 6].arrayLine[1] == 0 || mainArrayView[positionUpdate + 6].arrayLine[1] == 2 &&
                mainArrayView[positionUpdate].arrayLine[3] == 0 || mainArrayView[positionUpdate].arrayLine[3] == 2) {
                mainArrayView[positionUpdate + 6].arrayLine[1] = 1
                mainArrayView[positionUpdate].arrayLine[3] = 1
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
        if (plus) {
            if (mainArrayView[positionUpdate - 6].arrayLine[3] == 1 &&
                mainArrayView[positionUpdate].arrayLine[1] == 1) {
                mainArrayView[positionUpdate - 6].arrayLine[3] = 0
                mainArrayView[positionUpdate].arrayLine[1] = 0
                if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if (positionUpdate - 6 != positionMoveFinish) notifyItemChanged(positionUpdate - 6, Unit)
            } else {
                mainArrayView[positionUpdate - 6].arrayLine[3] = 2
                mainArrayView[positionUpdate].arrayLine[1] = 2
                if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if (positionUpdate - 6 != positionMoveFinish) notifyItemChanged(positionUpdate - 6, Unit)
            }
        } else {
            if (mainArrayView[positionUpdate - 6].arrayLine[3] == 0 || mainArrayView[positionUpdate - 6].arrayLine[3] == 2 &&
                mainArrayView[positionUpdate].arrayLine[1] == 0 || mainArrayView[positionUpdate].arrayLine[1] == 2) {
                mainArrayView[positionUpdate - 6].arrayLine[3] = 1
                mainArrayView[positionUpdate].arrayLine[1] = 1
                if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if (positionUpdate - 6 != positionMoveFinish) notifyItemChanged(positionUpdate - 6, Unit)
            }
        }
        /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)*/
        clickUpdateLine.clickable = true
    }

    fun updateLineLeftMinusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus) {
            if (mainArrayView[positionUpdate - 1].arrayLine[2] == 1 &&
                mainArrayView[positionUpdate].arrayLine[0] == 1) {
                mainArrayView[positionUpdate - 1].arrayLine[2] = 0
                mainArrayView[positionUpdate].arrayLine[0] = 0
                if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if (positionUpdate - 1 != positionMoveFinish) notifyItemChanged(positionUpdate - 1, Unit)
            } else {
                mainArrayView[positionUpdate - 1].arrayLine[2] = 2
                mainArrayView[positionUpdate].arrayLine[0] = 2
                if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if (positionUpdate - 1 != positionMoveFinish) notifyItemChanged(positionUpdate - 1, Unit)
            }
        } else {
            if (mainArrayView[positionUpdate - 1].arrayLine[2] == 0 || mainArrayView[positionUpdate - 1].arrayLine[2] == 2 &&
                mainArrayView[positionUpdate].arrayLine[0] == 0 || mainArrayView[positionUpdate].arrayLine[0] == 2
            ) {
                mainArrayView[positionUpdate - 1].arrayLine[2] = 1
                mainArrayView[positionUpdate].arrayLine[0] = 1
                if (positionUpdate != positionMoveFinish) {
                    notifyItemChanged(positionUpdate, Unit)
                }
                if (positionUpdate - 1 != positionMoveFinish) {
                    notifyItemChanged(positionUpdate - 1, Unit)
                }
            }
            /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
    if(positionUpdate-1 != positionMoveFinish) notifyItemChanged(positionUpdate-1, Unit)*/
        }
        clickUpdateLine.clickable = true
    }
    fun updateLineRightPlusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
        if (plus) {
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 1 &&
                mainArrayView[positionUpdate].arrayLine[2] == 1
            ) {
                mainArrayView[positionUpdate + 1].arrayLine[0] = 0
                mainArrayView[positionUpdate].arrayLine[2] = 0
                if (positionUpdate != positionMoveFinish){
                    notifyItemChanged(positionUpdate, Unit)
                }
                if (positionUpdate + 1 != positionMoveFinish){
                    notifyItemChanged(positionUpdate + 1, Unit)
                }
            } else {
                mainArrayView[positionUpdate + 1].arrayLine[0] = 2
                mainArrayView[positionUpdate].arrayLine[2] = 2
                if (positionUpdate != positionMoveFinish){
                    notifyItemChanged(positionUpdate, Unit)
                }
                if (positionUpdate + 1 != positionMoveFinish){
                    notifyItemChanged(positionUpdate + 1, Unit)
                }
            }
        } else {
            if (mainArrayView[positionUpdate + 1].arrayLine[0] == 0 || mainArrayView[positionUpdate + 1].arrayLine[0] == 2 &&
                mainArrayView[positionUpdate].arrayLine[2] == 0 || mainArrayView[positionUpdate].arrayLine[2] == 2
            ) {
                mainArrayView[positionUpdate + 1].arrayLine[0] = 1
                mainArrayView[positionUpdate].arrayLine[2] = 1
                if (positionUpdate != positionMoveFinish){
                    notifyItemChanged(positionUpdate, Unit)
                }
                if (positionUpdate + 1 != positionMoveFinish){
                    notifyItemChanged(positionUpdate + 1, Unit)
                }
            }
        }
        /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
    if(positionUpdate+1 != positionMoveFinish) notifyItemChanged(positionUpdate+1, Unit)*/
        clickUpdateLine.clickable = true
    }
    fun updateLineBottomPlusSixTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int) {
        if (plus) {
            if (mainArrayView[positionUpdate + 6].arrayLine[1] == 1 &&
                mainArrayView[positionUpdate].arrayLine[3] == 1
            ) {
                mainArrayView[positionUpdate + 6].arrayLine[1] = 0
                mainArrayView[positionUpdate].arrayLine[3] = 0
                if (positionUpdate != positionMoveFinish) {
                    notifyItemChanged(positionUpdate, Unit)
                }
                if (positionUpdate + 6 != positionMoveFinish){
                    notifyItemChanged(positionUpdate + 6, Unit)
                }
            } else {
                mainArrayView[positionUpdate + 6].arrayLine[1] = 2
                mainArrayView[positionUpdate].arrayLine[3] = 2
                if (positionUpdate != positionMoveFinish){
                    notifyItemChanged(positionUpdate, Unit)
                }
                if (positionUpdate + 6 != positionMoveFinish){
                    notifyItemChanged(positionUpdate + 6, Unit)
                }
            }
        } else {
            if (mainArrayView[positionUpdate + 6].arrayLine[1] == 0 || mainArrayView[positionUpdate + 6].arrayLine[1] == 2 &&
                mainArrayView[positionUpdate].arrayLine[3] == 0 || mainArrayView[positionUpdate].arrayLine[3] == 2
            ) {
                mainArrayView[positionUpdate + 6].arrayLine[1] = 1
                mainArrayView[positionUpdate].arrayLine[3] = 1
                if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                if (positionUpdate + 6 != positionMoveFinish) notifyItemChanged(positionUpdate + 6, Unit)
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

    //Обновляем в массиве позиции на которых уже отработала анимация на 2
    //для того что бы анимация не срабатывала второй раз на уже исчезнувших линиях
    //Запускается через интерфейс в фрагменте, запускается на итеме в адаптере
    fun updateLinePosTwo(pos: Int, numberLine: Int){
        mainArrayView[pos].arrayLine[numberLine] = 2
    }

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

        fun soundEffect()

        // Если картинка собрана, отправляем с какой позиции кубика картинка собрана,
        // что бы обновить в drawerLayout, и потом подсветить собраную картинку.
        fun imageIsCollected (positionImageCollected: Int)

        fun countPlus (count:Int)
        //Если одновременно сработали ActionMove и actionUP в слушателе нажатия и картинка не успела потянуться
        fun actionMoveAndActionUP(position: Int)

        //Метод для того что бы обновить в массиве линии которые уже срабатывали
        //После того как линия исчезла, записать в массив вместо нуля - двойку
        fun updateLineTwoNoAnimation(position: Int, numberLine:Int)
    }
}