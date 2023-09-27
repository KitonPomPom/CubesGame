package kitonpompom.cubesgame.activities //адаптер для фрагмента с картиками

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
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


class AdapterFragPWPEasy(val clickScaleItemInterface: ClickScaleItemInterface, val activity: FragmentActivity): RecyclerView.Adapter<AdapterFragPWPEasy.ImageHolder>(), ItemTouchMoveAndSwipe.ItemTouchDragAdapterPWP{
    var mainArrayView = ArrayList<dataArrayBitmap>()
    var countStart = CountStartLinearVisible()
    var click =  ClickableState()//класс для блокировки нажатия на итем в адаптере пока запущена анимация
    var clickBack =  ClickableStateBack()//класс для блокировки нажатия на итем в адаптере пока запущена анимация обратного движения
    var clickUpdateLine = ClickableStateUpdateLine() //Для блокировки перетягивания и увелечения во время обновления линий
    var noMove = NoMoveIfOpenScale()//Класс для блокировки в адаптере возможности срабатывания ОнТач, блокируется из фрагмета с рцвью когда картинка увеличена
    var noMoveBack = NoMoveIfOpenScaleBack()//Класс для пблокировки в адаптере возможности срабатывания ОнТач при обратной анимации, блокируется из фрагмета с рцвью
    var noClickForAnimationHelpScore = NoClickForAnimationHelpScore()//класс для блокировки обновления линий без обновления картинки
    var updateLineNoImage = UpdateLineNoImage()//класс для блокировки обновления линий без обновления картинки
    var colorLine = false //Цвет линий false - черный
    lateinit var view: View
    var colorAnimationStars = false //Цвет звездочек в анимации false - черный
    //var visibilityView = VisibilityViewAdapter()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        //Если colorLine false - загружаем черные линии, если true - белые
        if (!colorLine) {
             view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rc_playing_with_pictures_easy, parent, false)
            colorAnimationStars = false
        }else{
             view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rc_playing_with_pictures_easy_white, parent, false)
            colorAnimationStars = true
        }
            //Log.d("MyLog", "OnCreateViewHolder")
        return AdapterFragPWPEasy.ImageHolder(view, this, parent.id, click, noMove, clickBack,clickUpdateLine,
            noMoveBack, colorAnimationStars, noClickForAnimationHelpScore)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.setData(mainArrayView[position], clickScaleItemInterface, activity)
    }

    override fun getItemCount(): Int {
        return mainArrayView.size
    }

    //Не используем
    override fun onMove(startPos: Int, targetPos: Int) {
        val targetItem = mainArrayView[targetPos]
        mainArrayView[targetPos] = mainArrayView[startPos]
        mainArrayView[targetPos] = targetItem
        notifyItemMoved(startPos, targetPos)
    }


    class ImageHolder (itemView : View, val adapter: AdapterFragPWPEasy, id: Int, var clickk: ClickableState, var noMovee: NoMoveIfOpenScale,
                       var clickkBack: ClickableStateBack, var clickkUpdateLine: ClickableStateUpdateLine, var noMoveeBack: NoMoveIfOpenScaleBack,
                       val colorAnimation:Boolean, var noClickkForAnimationHelpScore: NoClickForAnimationHelpScore) : RecyclerView.ViewHolder(itemView)  {
        lateinit var imItemOne : ImageView
        lateinit var imItemTwo : ImageView
        lateinit var lineLeft :LinearLayout
        lateinit var lineTop :LinearLayout
        lateinit var lineRight :LinearLayout
        lateinit var lineBottom :LinearLayout
        //var vg = itemView.parent as ViewGroup

        @SuppressLint("ClickableViewAccessibility")
        fun setData ( item: dataArrayBitmap, clickScaleItemInterface: ClickScaleItemInterface, activity: FragmentActivity) {
            imItemOne = itemView.findViewById(R.id.id_item_play_with_pictures_one)
            imItemTwo = itemView.findViewById(R.id.id_item_play_with_pictures_two)
            lineLeft = itemView.findViewById(R.id.linLayLeft)
            lineTop = itemView.findViewById(R.id.linLayTop)
            lineRight = itemView.findViewById(R.id.linLayRight)
            lineBottom = itemView.findViewById(R.id.linLayBottom)

            itemView.visibility = View.VISIBLE


            imItemOne.setImageBitmap(item.arrayBitmap[0])

            var noReplaySwipe = true
            var actionMoveCheck = false
            var actionNoClickOnTouchIfTouchOnMove = false

            //Проверяем наличие линий и включаем анимацию
            if (item.arrayLine[0] == 1) {
                lineLeft.visibility = View.VISIBLE
            } else {
                lineLeft.visibility = View.INVISIBLE
                if(item.arrayLine[0] == 0) {
                    val runnable =
                        Runnable { doAnimation(activity, lineLeft, colorAnimation) } //Созд. рунабл для замедления.
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
                    val runnable = Runnable { doAnimation(activity, lineTop, colorAnimation) }
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
                    val runnable = Runnable { doAnimation(activity, lineRight, colorAnimation) }
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
                    val runnable = Runnable { doAnimation(activity, lineBottom, colorAnimation) }
                    itemView.postDelayed(runnable, 300L)
                    clickScaleItemInterface.soundEffect()
                    clickScaleItemInterface.updateLineTwoNoAnimation(adapterPosition, 3)
                    //doAnimation(activity, lineBottom)
                }
            }

                imItemOne.setOnClickListener {
                    //Log.d("MyLog", "setOnClickListener ItemOne actionNoClick $actionNoClickOnTouchIfTouchOnMove")
                    if (noClickkForAnimationHelpScore.noClickForAnimationHelpScore) {//Не запускаем если идёт анимация помощи вращения кубиков
                        if (!actionNoClickOnTouchIfTouchOnMove) {
                            if (!noMovee.noMoveIfOpenScale && noMoveeBack.noMoveIfOpenScale) {
                                if (clickk.clickable && clickkBack.clickable && clickkUpdateLine.clickable) {
                                    //Log.d("MyLog", "Слушатель ImItemOne1")
                                    // selectImage = 999 - потому что это заглушка, используется только из helpScore
                                    // openImage = 999 - потому что это заглушка, используется только из helpScore
                                    clickScaleItemInterface.clickScaleItem(
                                        item.arrayBitmap[0],
                                        item.arrayBitmap[1],
                                        item.arrayBitmap[2],
                                        item.arrayBitmap[3],
                                        item.arrayBitmap[4],
                                        item.arrayBitmap[5],
                                        item.arrayNumber[0],
                                        item.arrayNumber[1],
                                        item.arrayNumber[2],
                                        item.arrayNumber[3],
                                        item.arrayNumber[4],
                                        item.arrayNumber[5],
                                        item.arrayPosition[0],
                                        item.arrayPosition[1],
                                        item.arrayPosition[2],
                                        item.arrayPosition[3],
                                        item.arrayPosition[4],
                                        item.arrayPosition[5],
                                        adapterPosition,
                                        itemView,
                                        imItemOne,
                                        Constans.NO_HELPSCORESTART,
                                        999,
                                        999,
                                        999
                                    )
                                }
                            }
                        }
                    }
                }

                imItemOne.setOnTouchListener(){ viewRc, eventRc ->
                    //Log.d("MyLog", "imItemOne.setOnTouchListener")
                    if (noClickkForAnimationHelpScore.noClickForAnimationHelpScore) {//Не запускаем если идёт анимация помощи вращения кубиков
                        if (noMovee.noMoveIfOpenScale && noMoveeBack.noMoveIfOpenScale) { //Не запускаем если открыт увеличеный Итем
                            if (clickk.clickable && clickkBack.clickable && clickkUpdateLine.clickable) {// Не запускаем пока идет анимация
                                //Log.d("MyLog", "Слушатель  тач ImItemOne")
                                val minDistance = 23
                                val minDistanceUpDown = 15
                                when (eventRc.action) {
                                    MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                                        actionMoveCheck = false
                                        noReplaySwipe = true
                                    }

                                    MotionEvent.ACTION_MOVE -> {
                                        //Log.d("MyLog", "ACTION_MOVE")
                                        //actionMoveCheck = true
                                        actionNoClickOnTouchIfTouchOnMove = true
                                        if (noReplaySwipe) {
                                            clickScaleItemInterface.moveItem(
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
                                            noReplaySwipe = false
                                        }
                                    }

                                    MotionEvent.ACTION_UP -> {
                                        //Log.d("MyLog", "ACTION_UP")
                                        actionNoClickOnTouchIfTouchOnMove = true
                                        noReplaySwipe = false
                                        if (clickk.clickable && clickkBack.clickable && clickkUpdateLine.clickable) {
                                            if (!actionMoveCheck) {
                                                // selectImage = 999 - потому что это заглушка, используется только из helpScore
                                                // openImage = 999 - потому что это заглушка, используется только из helpScore
                                                clickScaleItemInterface.clickScaleItem(
                                                    item.arrayBitmap[0],
                                                    item.arrayBitmap[1],
                                                    item.arrayBitmap[2],
                                                    item.arrayBitmap[3],
                                                    item.arrayBitmap[4],
                                                    item.arrayBitmap[5],
                                                    item.arrayNumber[0],
                                                    item.arrayNumber[1],
                                                    item.arrayNumber[2],
                                                    item.arrayNumber[3],
                                                    item.arrayNumber[4],
                                                    item.arrayNumber[5],
                                                    item.arrayPosition[0],
                                                    item.arrayPosition[1],
                                                    item.arrayPosition[2],
                                                    item.arrayPosition[3],
                                                    item.arrayPosition[4],
                                                    item.arrayPosition[5],
                                                    adapterPosition,
                                                    itemView,
                                                    imItemOne,
                                                    Constans.NO_HELPSCORESTART,
                                                    999,
                                                    999,
                                                    999
                                                )
                                            } else {
                                                clickScaleItemInterface.actionMoveAndActionUP(
                                                    adapterPosition
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return@setOnTouchListener false
                }
        }



        //Анимация звездочек при исчезновении линий
        fun doAnimation(act: FragmentActivity, view: View, colorAnimation: Boolean) {
            var starColor = view.context.getDrawable(R.drawable.star_black)
            if(colorAnimation)
                starColor =  view.context.getDrawable(R.drawable.star_white)
            val ps = ParticleSystem(act, 100, starColor, 1000L)
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

    //Запускаем из HelpScoreManager и передаем значения на fragment картинок того кубика PosRotation который
    // нужно вращать по подсказке
    fun helpScore(posRotation:Int, itemView: View, imItemOneImage: ImageView, constHelpScore: Int, selectImage: Int, openImage: Int, countCubeRotated: Int){
        //constHelpScore - Если запустили для вращения то сюда приходит номер позиции на которой стоит нужная
        //нам картинка для вращения
        //posRotation - Номер кубика который будет вращаться
        clickScaleItemInterface.clickScaleItem(
            mainArrayView[posRotation].arrayBitmap[0], mainArrayView[posRotation].arrayBitmap[1],
            mainArrayView[posRotation].arrayBitmap[2], mainArrayView[posRotation].arrayBitmap[3],
            mainArrayView[posRotation].arrayBitmap[4], mainArrayView[posRotation].arrayBitmap[5],
            mainArrayView[posRotation].arrayNumber[0], mainArrayView[posRotation].arrayNumber[1],
            mainArrayView[posRotation].arrayNumber[2], mainArrayView[posRotation].arrayNumber[3],
            mainArrayView[posRotation].arrayNumber[4], mainArrayView[posRotation].arrayNumber[5],
            mainArrayView[posRotation].arrayPosition[0], mainArrayView[posRotation].arrayPosition[1],
            mainArrayView[posRotation].arrayPosition[2], mainArrayView[posRotation].arrayPosition[3],
            mainArrayView[posRotation].arrayPosition[4], mainArrayView[posRotation].arrayPosition[5],
            posRotation, itemView, imItemOneImage, constHelpScore, selectImage, openImage, countCubeRotated)

    }

    //Обновляем адаптер когда первый раз рисуется
    fun updateAdapter(newList : ArrayList<dataArrayBitmap>){ // функция обновляет адаптер
        Log.d("MyLog", "updateAdapter")
        mainArrayView.clear()
        mainArrayView.addAll(newList)
        for (i in 0 until mainArrayView.size) {
            //START_UPDATE_LINE - означает что обновляется при первом запуске
            //NO_POSITION_MOVE - заглушка что бы не проверять рядом стоящие элементы на совпадение
            updateLinePosition(i, Constans.START_UPDATE_LINE, Constans.NO_POSITION_MOVE)
        }
        notifyDataSetChanged()
    }

    fun updateWhiteBlackLine(color:Boolean){
        colorLine = color
        notifyDataSetChanged()
    }

    //Обновление одного итема, а заним проверяется итемы на совпадения вокруг
    fun updateAdapterPosition(ListBitmap: ArrayList<Bitmap>, ListNumber: ArrayList<Int>, ListPosition: ArrayList<Int> , position: Int, positionMoveFinish: Int) {
        Log.d("MyLog", "updateAdapterPosition $position")
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
        //visibilityView.visibilityView = true //Для того что бы вью появлялась только тогда когда обновили адаптер
        //NO_START_UPDATE_LINE - означает что обновляется не при первом запуске
        //positionMoveFinish - позиция куда нужно возращать итем
        //position - Основная позиция куда поставили кубик
        updateLinePosition(position, Constans.NO_START_UPDATE_LINE, positionMoveFinish)
    }


    //Обновляем только одну позицию, в том случае если потянули, картинка исчезла, а новая не успела загрузиться
    fun updateOnePosition(position: Int){
        Log.d("MyLog", "update One Position")
        notifyItemChanged(position, null)
    }

    fun updateLinePosition(position: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "positionMoveFinish $positionMoveFinish")
        when(position){
            0->{
                // Constans.UPDATE_LINE_ONE  - в методе Edge обновляем итем
                //после записи новых данных по линиям
                topEdge(position)
                leftEdge(position)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusThree(position, flagStartUpdateLine, positionMoveFinish)
            }
            1 ->{
                topEdge(position)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusThree(position, flagStartUpdateLine, positionMoveFinish)
            }
            2->{
                topEdge(position)
                rightEdge(position)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusThree(position, flagStartUpdateLine, positionMoveFinish)
            }
            3, 6, 9 -> {
                leftEdge(position)
                minusThree(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusThree(position, flagStartUpdateLine, positionMoveFinish)
            }
            5, 8, 11 -> {
                rightEdge(position)
                minusThree(position, flagStartUpdateLine, positionMoveFinish)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                plusThree(position, flagStartUpdateLine, positionMoveFinish)
            }
            12 -> {
                leftEdge(position)
                bottomEdge(position)
                minusThree(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
            }
            13 -> {
                bottomEdge(position)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusThree(position, flagStartUpdateLine, positionMoveFinish)
                plusOne(position, flagStartUpdateLine, positionMoveFinish)
            }
            14 -> {
                rightEdge(position)
                bottomEdge(position)
                minusOne(position, flagStartUpdateLine, positionMoveFinish)
                minusThree(position, flagStartUpdateLine, positionMoveFinish)
            }else ->{
                    minusOne(position, flagStartUpdateLine, positionMoveFinish)
                    plusThree(position, flagStartUpdateLine, positionMoveFinish)
                    plusOne(position, flagStartUpdateLine, positionMoveFinish)
                    minusThree(position, flagStartUpdateLine, positionMoveFinish)
            }
        }
    }

    //Проверяем на совпадение картинки справа
    fun plusOne(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Проверяем если совпал номер позиций картинок, если совпал то кубики с одной картинки
        if (mainArrayView[positionUpdate+1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            //Проверяем совпадение порядка картинок, если нашей картинке прибавить +1, то картинка справа стоит правильная
            if(mainArrayView[positionUpdate+1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 1) {
                //Проверяем если основная картинка имеет позицию 2,5,8,14, то картинка из крайнего правого ряда и для неё не нужно
                //проверять следующую картинку (+1) так как она с другого ряда
                when (mainArrayView[positionUpdate].arrayPosition[0]) {
                    2, 5, 8, 11, 14 -> {
                        //Если картинка справа с места 3,6,9,12 то на ней не нужно убирать линии, так как она со след. ряда
                        when (mainArrayView[positionUpdate].arrayPosition[0] + 1) {
                            3, 6, 9, 12  -> {
                                //Запускаем метод изменения линий справа
                                //false - Не удаляем линии
                                updateLineRightPlusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
                            }
                            else -> { // Удаляем линии
                                // true - удаляем линию
                                updateLineRightPlusOne(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
                            }
                        }
                    }
                    else -> { //Удаляем линию
                        updateLineRightPlusOne(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
                    }
                }
            }else {// Картинка по порядку не совпала, линию не стираем
                updateLineRightPlusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{//Номер картинки справа не совпал, линию не удалем
            updateLineRightPlusOne(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun minusOne(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]) {
            if (mainArrayView[positionUpdate - 1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 1) {
                when (mainArrayView[positionUpdate].arrayPosition[0]) {
                    3, 6, 9, 12 -> {
                        when (mainArrayView[positionUpdate].arrayPosition[0] - 1) {
                            2, 5, 8, 11, 14 -> {
                                updateLineLeftMinusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
                            }
                            else -> {
                                updateLineLeftMinusOne(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
                            }
                        }
                    }
                    else -> {
                        updateLineLeftMinusOne(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
                    }
                }
            }else{
                updateLineLeftMinusOne(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineLeftMinusOne(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }

    }

    fun plusThree(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+3].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+3].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 3) {
                updateLineBottomPlusThree(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineBottomPlusThree(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineBottomPlusThree(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
        }
    }

    fun minusThree(positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-3].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-3].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 3) {
                updateLineTopMinusThree(true, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }else {
                updateLineTopMinusThree(false, positionUpdate, flagStartUpdateLine, positionMoveFinish)
            }
        }else{
            updateLineTopMinusThree(false,positionUpdate, flagStartUpdateLine, positionMoveFinish)
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

    fun updateLineTopMinusThree(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        //Если картинки совпадают то plus - true и линия исчезает, 0 - исчезает, 1 - появляется
        if (plus){
            //Выполняем только тогда когда цифра должна измениться с 0 на 1 или 1 на 0
            if(mainArrayView[positionUpdate - 3].arrayLine[3] == 1 &&
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
                    if(positionUpdate-3 != positionMoveFinish) notifyItemChanged(positionUpdate-3, Unit)
                    updateLinePositionTwo(positionUpdate - 3, positionMoveFinish)
                }
            }else{
                mainArrayView[positionUpdate - 3].arrayLine[3] = 2
                mainArrayView[positionUpdate].arrayLine[1] = 2
                //Log.d("MyLog", "UpdateLineTop true $positionUpdate")
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    //Unit - Убирает анимацию мигания при обновлении итема
                    notifyItemChanged(positionUpdate, Unit)
                    if (positionUpdate - 3 != positionMoveFinish) notifyItemChanged(positionUpdate - 3, Unit)
                    updateLinePositionTwo(positionUpdate - 3, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate - 3].arrayLine[3] == 0 || mainArrayView[positionUpdate - 3].arrayLine[3] == 2 &&
                mainArrayView[positionUpdate].arrayLine[1] == 0 || mainArrayView[positionUpdate].arrayLine[1] == 2) {
                mainArrayView[positionUpdate - 3].arrayLine[3] = 1
                mainArrayView[positionUpdate].arrayLine[1] = 1
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE){
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    //Unit - Убирает анимацию мигания при обновлении итема
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate-3 != positionMoveFinish) notifyItemChanged(positionUpdate-3, Unit)
                    updateLinePositionTwo(positionUpdate - 3, positionMoveFinish)
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

    fun updateLineBottomPlusThree(plus: Boolean, positionUpdate: Int, flagStartUpdateLine: Int, positionMoveFinish: Int){
        if (plus){
            if(mainArrayView[positionUpdate + 3].arrayLine[1] == 1 &&
                mainArrayView[positionUpdate].arrayLine[3] == 1) {
                //mainArrayView[positionUpdate + 3].arrayLine[1] = 0
                //mainArrayView[positionUpdate].arrayLine[3] = 0
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+3 != positionMoveFinish) notifyItemChanged(positionUpdate + 3, Unit)
                    //positionUpdate - передаем позицию что бы снова не обновлять у соседнего кубика полоски
                    updateLinePositionTwo(positionUpdate + 3, positionMoveFinish)
                }
            }else{
                mainArrayView[positionUpdate + 3].arrayLine[1] = 2
                mainArrayView[positionUpdate].arrayLine[3] = 2
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+3 != positionMoveFinish) notifyItemChanged(positionUpdate + 3, Unit)
                    updateLinePositionTwo(positionUpdate + 3, positionMoveFinish)
                }
            }
        } else {
            if(mainArrayView[positionUpdate + 3].arrayLine[1] == 0 || mainArrayView[positionUpdate + 3].arrayLine[1] == 2 &&
                mainArrayView[positionUpdate].arrayLine[3] == 0 || mainArrayView[positionUpdate].arrayLine[3] == 2) {
                mainArrayView[positionUpdate + 3].arrayLine[1] = 1
                mainArrayView[positionUpdate].arrayLine[3] = 1
                if (flagStartUpdateLine == Constans.NO_START_UPDATE_LINE) {
                    updateLineNoImage.updateLineNoImage = false
                    clickUpdateLine.clickable = false
                    notifyItemChanged(positionUpdate, Unit)
                    if(positionUpdate+3 != positionMoveFinish) notifyItemChanged(positionUpdate + 3, Unit)
                    updateLinePositionTwo(positionUpdate + 3, positionMoveFinish)
                }
            }
        }
    }

    fun updateLinePositionTwo(position: Int, positionMoveFinish: Int){
        //Log.d("MyLog", "Count $position")
        when(position){
            0->{
                //topEdge(position, Constans.UPDATE_LINE_TWO)
                //leftEdge(position, Constans.UPDATE_LINE_TWO)
                plusOneTwo(position, positionMoveFinish)
                plusThreeTwo(position, positionMoveFinish)
            }
            1 ->{
                //topEdge(position, Constans.UPDATE_LINE_TWO)
                plusOneTwo(position, positionMoveFinish)
                minusOneTwo(position, positionMoveFinish)
                plusThreeTwo(position, positionMoveFinish)
            }
            2->{
                //topEdge(position, Constans.UPDATE_LINE_TWO)
                //rightEdge(position, Constans.UPDATE_LINE_TWO)
                minusOneTwo(position, positionMoveFinish)
                plusThreeTwo(position, positionMoveFinish)
            }
            3, 6, 9 -> {
                //leftEdge(position, Constans.UPDATE_LINE_TWO)
                minusThreeTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
                plusThreeTwo(position, positionMoveFinish)
            }
            5, 8, 11 -> {
                //rightEdge(position, Constans.UPDATE_LINE_TWO)
                minusThreeTwo(position, positionMoveFinish)
                minusOneTwo(position, positionMoveFinish)
                plusThreeTwo(position, positionMoveFinish)
            }
            12 -> {
                //leftEdge(position, Constans.UPDATE_LINE_TWO)
                //bottomEdge(position, Constans.UPDATE_LINE_TWO)
                minusThreeTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
            }
            13 -> {
                //bottomEdge(position, Constans.UPDATE_LINE_TWO)
                minusOneTwo(position, positionMoveFinish)
                minusThreeTwo(position, positionMoveFinish)
                plusOneTwo(position, positionMoveFinish)
            }
            14 -> {
                //rightEdge(position, Constans.UPDATE_LINE_TWO)
                //bottomEdge(position, Constans.UPDATE_LINE_TWO)
                minusOneTwo(position, positionMoveFinish)
                minusThreeTwo(position, positionMoveFinish)
            }else ->{
            minusOneTwo(position, positionMoveFinish)
            plusThreeTwo(position, positionMoveFinish)
            plusOneTwo(position, positionMoveFinish)
            minusThreeTwo(position, positionMoveFinish)
            }
        }
    }

    fun plusOneTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+1].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+1].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0]+1) {
                when (mainArrayView[positionUpdate].arrayPosition[0]) {
                    2, 5, 8, 11, 14 -> {
                        when (mainArrayView[positionUpdate].arrayPosition[0]+1) {
                            3, 6, 9, 12 -> {
                                updateLineRightPlusOneTwo(false, positionUpdate, positionMoveFinish)
                            }
                            else -> {
                                updateLineRightPlusOneTwo(true, positionUpdate, positionMoveFinish)
                            }
                        }
                    }
                    else -> {
                        updateLineRightPlusOneTwo(true, positionUpdate, positionMoveFinish)
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
                    3, 6, 9, 12 -> {
                        when (mainArrayView[positionUpdate].arrayPosition[0]-1) {
                            2, 5, 8, 11, 14 -> {
                                updateLineLeftMinusOneTwo(false, positionUpdate, positionMoveFinish)
                            }
                            else -> {
                                updateLineLeftMinusOneTwo(true, positionUpdate, positionMoveFinish)
                            }
                        }
                    }
                    else -> {
                        updateLineLeftMinusOneTwo(true, positionUpdate, positionMoveFinish)
                    }
                }
            }else {
                updateLineLeftMinusOneTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineLeftMinusOneTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun plusThreeTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate+3].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate+3].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] + 3) {
                updateLineBottomPlusThreeTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineBottomPlusThreeTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineBottomPlusThreeTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun minusThreeTwo(positionUpdate: Int, positionMoveFinish: Int){
        if (mainArrayView[positionUpdate-3].arrayNumber[0] == mainArrayView[positionUpdate].arrayNumber[0]){
            if(mainArrayView[positionUpdate-3].arrayPosition[0] == mainArrayView[positionUpdate].arrayPosition[0] - 3) {
                updateLineTopMinusThreeTwo(true, positionUpdate, positionMoveFinish)
            }else {
                updateLineTopMinusThreeTwo(false, positionUpdate, positionMoveFinish)
            }
        }else{
            updateLineTopMinusThreeTwo(false,positionUpdate, positionMoveFinish)
        }
    }

    fun updateLineTopMinusThreeTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int){
            if (plus) {
                if (mainArrayView[positionUpdate - 3].arrayLine[3] == 1 &&
                    mainArrayView[positionUpdate].arrayLine[1] == 1) {
                    mainArrayView[positionUpdate - 3].arrayLine[3] = 0
                    mainArrayView[positionUpdate].arrayLine[1] = 0
                    if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if (positionUpdate - 3 != positionMoveFinish) notifyItemChanged(positionUpdate - 3, Unit)
                } else {
                    mainArrayView[positionUpdate - 3].arrayLine[3] = 2
                    mainArrayView[positionUpdate].arrayLine[1] = 2
                    if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if (positionUpdate - 3 != positionMoveFinish) notifyItemChanged(positionUpdate - 3, Unit)
                }
            } else {
                if (mainArrayView[positionUpdate - 3].arrayLine[3] == 0 || mainArrayView[positionUpdate - 3].arrayLine[3] == 2 &&
                    mainArrayView[positionUpdate].arrayLine[1] == 0 || mainArrayView[positionUpdate].arrayLine[1] == 2) {
                    mainArrayView[positionUpdate - 3].arrayLine[3] = 1
                    mainArrayView[positionUpdate].arrayLine[1] = 1
                    if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if (positionUpdate - 3 != positionMoveFinish) notifyItemChanged(positionUpdate - 3, Unit)
                }
        }
        /*if(positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
        if(positionUpdate-6 != positionMoveFinish) notifyItemChanged(positionUpdate-6, Unit)*/
        clickUpdateLine.clickable = true
    }

    fun updateLineLeftMinusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int) {
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
    fun updateLineRightPlusOneTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int) {
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
    fun updateLineBottomPlusThreeTwo(plus: Boolean, positionUpdate: Int, positionMoveFinish: Int) {
            if (plus) {
                if (mainArrayView[positionUpdate + 3].arrayLine[1] == 1 &&
                    mainArrayView[positionUpdate].arrayLine[3] == 1
                ) {
                    mainArrayView[positionUpdate + 3].arrayLine[1] = 0
                    mainArrayView[positionUpdate].arrayLine[3] = 0
                    if (positionUpdate != positionMoveFinish) {
                        notifyItemChanged(positionUpdate, Unit)
                    }
                    if (positionUpdate + 3 != positionMoveFinish){
                        notifyItemChanged(positionUpdate + 3, Unit)
                    }
                } else {
                    mainArrayView[positionUpdate + 3].arrayLine[1] = 2
                    mainArrayView[positionUpdate].arrayLine[3] = 2
                    if (positionUpdate != positionMoveFinish){
                        notifyItemChanged(positionUpdate, Unit)
                    }
                    if (positionUpdate + 3 != positionMoveFinish){
                        notifyItemChanged(positionUpdate + 3, Unit)
                    }
                }
            } else {
                if (mainArrayView[positionUpdate + 3].arrayLine[1] == 0 || mainArrayView[positionUpdate + 3].arrayLine[1] == 2 &&
                    mainArrayView[positionUpdate].arrayLine[3] == 0 || mainArrayView[positionUpdate].arrayLine[3] == 2
                ) {
                    mainArrayView[positionUpdate + 3].arrayLine[1] = 1
                    mainArrayView[positionUpdate].arrayLine[3] = 1
                    if (positionUpdate != positionMoveFinish) notifyItemChanged(positionUpdate, Unit)
                    if (positionUpdate + 3 != positionMoveFinish) notifyItemChanged(positionUpdate + 3, Unit)
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

    //Запускаем с фрагмента для того что бы передать массив с картинками и открыть диалог помощи
    fun helpScoreToFrag(score: Int){
        clickScaleItemInterface.helpScore(mainArrayView, score)
    }

    //Запускаем из DirectionRotationCube когда нужно повернуть следующий кубик в помощи
    fun helpScoreToDirectionRotationCubeNext(selectImage: Int, openImage: Int, posRotation: Int, countCubeRotated: Int){
        clickScaleItemInterface.helpScoreNext(selectImage, openImage, mainArrayView, posRotation, countCubeRotated)
    }

    interface ClickScaleItemInterface{
        fun clickScaleItem(b0 : Bitmap, b1 : Bitmap, b2 : Bitmap, b3 : Bitmap, b4 : Bitmap,
                           b5 : Bitmap, n0: Int, n1: Int, n2: Int, n3: Int, n4: Int, n5: Int,
                           p0 : Int, p1 : Int, p2 : Int, p3 : Int, p4 : Int, p5 : Int,
                           position: Int, itemView: View, imItem: ImageView, numberRotation: Int, selectImage: Int, openImage: Int, countCubeRotated: Int)

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

        //Интерфейс который запускается когда нажали кнопку помощи, забирает массив с картинками и открывает
        //диалог с выбором картинок которые нужно повернуть

        fun helpScore(arrayList : ArrayList<dataArrayBitmap>, score:Int)
        //Интерфейс который запускается когда мы используем помощь, когда кубик повернулся и нужно повернуть следующий кубик
        fun helpScoreNext(selectImage: Int, openImage: Int, arrayList : ArrayList<dataArrayBitmap>,
                          posRotation: Int, countCubeRotated: Int)


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