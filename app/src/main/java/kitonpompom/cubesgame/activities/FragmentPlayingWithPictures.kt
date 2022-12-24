package kitonpompom.cubesgame.activities

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ItemTouchHelper
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.activities.data.dataArrayPositionAndNumberBitmap
import kitonpompom.cubesgame.activities.data.dataPosNumBit
import kitonpompom.cubesgame.activities.utils.*
import kitonpompom.cubesgame.databinding.FragmentPlayingWithPicturesBinding
import kotlinx.coroutines.*
import java.lang.Math.abs
import java.util.Collections.shuffle
import kotlin.properties.Delegates
import kotlin.random.Random


class FragmentPlayingWithPictures : Fragment(), AdapterFragPWP.ClickScaleItemInterface {

    lateinit var binding: FragmentPlayingWithPicturesBinding
    private val dataModel: DataModel by activityViewModels()
    private var job: Job? = null
    private val adapter: AdapterFragPWP? = AdapterFragPWP(this)
    private val swipeCallback = ItemTouchMoveAndSwipe(adapter!!)
    private val touchHelper = ItemTouchHelper(swipeCallback)
    var arrayBitmap = ArrayList<Bitmap>()//Массив с картинками
    var arrayNumber = ArrayList<Int>()//Массив с номерами картинок на кубике
    var arrayPosition = ArrayList<Int>()//Массив с изночальными позициями картинок на кубике
    var arrayBitmapMoveReturn = ArrayList<Bitmap>()//Массив с картинками
    var arrayNumberMoveReturn = ArrayList<Int>()//Массив с номерами на кубике
    var arrayPositionMoveReturn = ArrayList<Int>()//Массив с изночальными позициями картинок на кубике
    var positionClickOpen = 0
    var positionClick = 0// Записываем позицию на которую нажали
    var positionMove = 0// Записываем позицию которую потянули
    var openItemScale = false //true - Открыт\увеличен itemScale
    var arrayInt = arrayOf(0,1,2,3,4,5)
    lateinit var itemViewGlobal: View
    lateinit var imItemGlobal: ImageView
    val noClick = ClickableState() //Объект для блокировки ActionUp в Move
    val noClickBack = ClickableStateBack() //Объект для блокировки ActionUp в Move для обратного движения
    var imMoveHeight by Delegates.notNull<Int>()
    var imMoveWidth by Delegates.notNull<Int>()

    private var x1: Float = 0.0f
    private var x2: Float = 0.0f
    private var y1: Float = 0.0f
    private var y2: Float = 0.0f
    var noReplaySwipe = true
    var noClickItemScale = true
    var clickMoveAdapter = false //проверка - сработал ли мув в адаптере, если да, разрешать перетягивать


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayingWithPicturesBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()

        dataModel.listBitmapForAdapterFragPWP.observe(activity as LifecycleOwner) {
            job = CoroutineScope(Dispatchers.Main).launch {
                val arrayCroppedImage1 = (ImageManager.croppedImage(it[0]))
                val arrayCroppedImage2 = (ImageManager.croppedImage(it[1]))
                val arrayCroppedImage3 = (ImageManager.croppedImage(it[2]))
                val arrayCroppedImage4 = (ImageManager.croppedImage(it[3]))
                val arrayCroppedImage5 = (ImageManager.croppedImage(it[4]))
                val arrayCroppedImage6 = (ImageManager.croppedImage(it[5]))

                val tempList = ArrayList<List<Bitmap>>()
                var tempListBitmap = ArrayList<dataArrayBitmap>()
                var tempListBitmapFinish = ArrayList<dataArrayBitmap>()
                var arrayPositionAndNumber0 = ArrayList<dataArrayPositionAndNumberBitmap>()
                val arrayPositionAndNumber1 = ArrayList<dataArrayPositionAndNumberBitmap>()
                val arrayPositionAndNumber2 = ArrayList<dataArrayPositionAndNumberBitmap>()
                val arrayPositionAndNumber3 = ArrayList<dataArrayPositionAndNumberBitmap>()
                val arrayPositionAndNumber4 = ArrayList<dataArrayPositionAndNumberBitmap>()
                val arrayPositionAndNumber5 = ArrayList<dataArrayPositionAndNumberBitmap>()

                tempList.add(arrayCroppedImage1)
                tempList.add(arrayCroppedImage2)
                tempList.add(arrayCroppedImage3)
                tempList.add(arrayCroppedImage4)
                tempList.add(arrayCroppedImage5)
                tempList.add(arrayCroppedImage6)

                for(index in 0..143){
                    val arrayPosition = ArrayList<Int>()
                    val arrayNumber = ArrayList<Int>()
                    val arrayBitmap = ArrayList<Bitmap>()
                    //Log.d("MyLog", "!!!!")
                    for(i in 0..5) {
                        arrayPosition.add(index)
                        arrayNumber.add(i)
                        arrayBitmap.add(tempList[i][index])
                    }
                    val arrayLine = ArrayList<Int>()
                    for(i in 0..3){
                        arrayLine.add(1)
                    }
                    tempListBitmap.add(dataArrayBitmap(arrayPosition,arrayNumber,arrayBitmap,arrayLine))
                }


                for (index in tempListBitmap) {
                    //Log.d("MyLog", "do ${index}")
                    arrayPositionAndNumber0.add(dataArrayPositionAndNumberBitmap(index.arrayPosition[0],index.arrayNumber[0]))
                    arrayPositionAndNumber1.add(dataArrayPositionAndNumberBitmap(index.arrayPosition[1],index.arrayNumber[1]))
                    arrayPositionAndNumber2.add(dataArrayPositionAndNumberBitmap(index.arrayPosition[2],index.arrayNumber[2]))
                    arrayPositionAndNumber3.add(dataArrayPositionAndNumberBitmap(index.arrayPosition[3],index.arrayNumber[3]))
                    arrayPositionAndNumber4.add(dataArrayPositionAndNumberBitmap(index.arrayPosition[4],index.arrayNumber[4]))
                    arrayPositionAndNumber5.add(dataArrayPositionAndNumberBitmap(index.arrayPosition[5],index.arrayNumber[5]))
                }

                val arrayPositionAndNumberBitmap = ArrayList<ArrayList<dataArrayPositionAndNumberBitmap>>()
                arrayPositionAndNumberBitmap.add(arrayPositionAndNumber0)
                arrayPositionAndNumberBitmap.add(arrayPositionAndNumber1)
                arrayPositionAndNumberBitmap.add(arrayPositionAndNumber2)
                arrayPositionAndNumberBitmap.add(arrayPositionAndNumber3)
                arrayPositionAndNumberBitmap.add(arrayPositionAndNumber4)
                arrayPositionAndNumberBitmap.add(arrayPositionAndNumber5)



                shuffleArrayDataArrayPositionAndNumberBitmap(arrayPositionAndNumber0)

                //val shuffleArrayPositionAndNumberBitmap0 = shuffleArrayDataArrayPositionAndNumberBitmap(arrayPositionAndNumber0)
                val shuffleArrayPositionAndNumberBitmap1 = shuffleArrayDataArrayPositionAndNumberBitmap(arrayPositionAndNumber1)
                val shuffleArrayPositionAndNumberBitmap2 = shuffleArrayDataArrayPositionAndNumberBitmap(arrayPositionAndNumber2)
                val shuffleArrayPositionAndNumberBitmap3 = shuffleArrayDataArrayPositionAndNumberBitmap(arrayPositionAndNumber3)
                val shuffleArrayPositionAndNumberBitmap4 = shuffleArrayDataArrayPositionAndNumberBitmap(arrayPositionAndNumber4)
                val shuffleArrayPositionAndNumberBitmap5 = shuffleArrayDataArrayPositionAndNumberBitmap(arrayPositionAndNumber5)

                val arrayShuffleArrayPositionAndNumberBitmap = ArrayList<ArrayList<dataArrayPositionAndNumberBitmap>>()
                //arrayShuffleArrayPositionAndNumberBitmap.add(shuffleArrayPositionAndNumberBitmap0)
                arrayShuffleArrayPositionAndNumberBitmap.add(arrayPositionAndNumber0)
                arrayShuffleArrayPositionAndNumberBitmap.add(shuffleArrayPositionAndNumberBitmap1)
                arrayShuffleArrayPositionAndNumberBitmap.add(shuffleArrayPositionAndNumberBitmap2)
                arrayShuffleArrayPositionAndNumberBitmap.add(shuffleArrayPositionAndNumberBitmap3)
                arrayShuffleArrayPositionAndNumberBitmap.add(shuffleArrayPositionAndNumberBitmap4)
                arrayShuffleArrayPositionAndNumberBitmap.add(shuffleArrayPositionAndNumberBitmap5)

                for(index in 0..143){
                    val tArrayDataArrayBitmap = ArrayList<dataArrayBitmap>()
                    val tDataPosNumBit = ArrayList<dataPosNumBit>()
                    val arrayPosition = ArrayList<Int>()
                    val arrayNumber = ArrayList<Int>()
                    val arrayBitmap = ArrayList<Bitmap>()
                    for(i in 0..5){
                        arrayPosition.add(tempListBitmap[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayPosition].arrayPosition[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayNumber])
                        arrayNumber.add(tempListBitmap[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayPosition].arrayNumber[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayNumber])
                        arrayBitmap.add(tempListBitmap[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayPosition].arrayBitmap[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayNumber])
                        //arrayPosition.add(tempListBitmap[arrayPositionAndNumberBitmap[i][index].arrayPosition].arrayPosition[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayNumber])
                        //arrayNumber.add(tempListBitmap[arrayPositionAndNumberBitmap[i][index].arrayPosition].arrayNumber[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayNumber])
                        //arrayBitmap.add(tempListBitmap[arrayPositionAndNumberBitmap[i][index].arrayPosition].arrayBitmap[arrayShuffleArrayPositionAndNumberBitmap[i][index].arrayNumber])
                    }

                    var num = shuffleIntArray(arrayNumber)
                    val arrayPositionTemp = ArrayList<Int>()
                    val arrayNumberTemp = ArrayList<Int>()
                    val arrayBitmapTemp = ArrayList<Bitmap>()
                    for(i in num){
                        arrayPositionTemp.add(arrayPosition[i])
                        arrayNumberTemp.add(arrayNumber[i])
                        arrayBitmapTemp.add(arrayBitmap[i])
                    }

                    val arrayLine = ArrayList<Int>()
                    for(i in 0..3){
                        arrayLine.add(1)
                    }
                    //Log.d("MyLog", "do $num")
                    //tArrayDataArrayBitmap.add(tDataPosNumBit)
                    //tempListBitmapFinish.add(dataArrayBitmap(arrayPosition,arrayNumber,arrayBitmap))
                    tempListBitmapFinish.add(dataArrayBitmap(arrayPositionTemp,arrayNumberTemp,arrayBitmapTemp,arrayLine))
                }

                //Log.d("MyLog", "arrayPositionAndNumber0 ${arrayShuffleArrayPositionAndNumberBitmap[0][0].arrayPosition}")

                adapter?.updateAdapter(tempListBitmap)
                //adapter?.updateAdapter(tempListBitmapFinish)
            }
        }

        binding.idRcViewFragPWP.setOnTouchListener(){ viewRc, eventRc ->
            //Log.d("MyLog", "idRcViewFragPWP.setOnTouchListener $clickMoveAdapter")
            when (eventRc.action) {
                MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                    //Log.d("MyLog", "ACTION_DOWN")

                    //x1 = eventRc.x //Позиция по оси Х куда нажали
                    //y1 = eventRc.y //Позиция по оси Y куда нажали
                    //noReplaySwipe = true
                }
                MotionEvent.ACTION_MOVE -> {

                    if(clickMoveAdapter && arrayBitmap.isNotEmpty()) { //проверка на то, сработал ли в адаптере onTouch и пустой ли масс. с битмапами
                        //Log.d("MyLog", "ACTION_MOVE $clickMoveAdapter")
                        binding.idImViewMove2.visibility = View.GONE
                        binding.idImViewMove.elevation = 1.0f
                        binding.idImViewMove.visibility = View.VISIBLE
                        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
                        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
                        ObjectAnimator.ofPropertyValuesHolder(binding.idImViewMove, scaleX, scaleY).apply {
                            duration = 50
                            start()
                        }
                        binding.idImViewMove.x = eventRc.x - imItemGlobal.width / 2f
                        binding.idImViewMove.y = eventRc.y
                        //binding.idImViewMove2.visibility = View.GONE
                        //binding.idImViewMove2.x = eventRc.x - imItemGlobal.width / 2f
                        //binding.idImViewMove2.y = eventRc.y
                    }

                }
                MotionEvent.ACTION_UP -> {

                    if (clickMoveAdapter && arrayBitmap.isNotEmpty() && noClick.clickable && noClickBack.clickable) {
                        x2 = eventRc.x
                        y2 = eventRc.y

                        if (MoveItemScale.moveItemRc(x2, y2, viewRc) != 144) {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                val rcViewX = binding.idRcViewFragPWP.x
                                val rcViewY = binding.idRcViewFragPWP.y
                                val coordinateImMove = MoveItemScale.movingItemScale(MoveItemScale.moveItemRc(x2, y2, viewRc), binding.idRcViewFragPWP.width, binding.idRcViewFragPWP.height,
                                    binding.idImViewMove.width, binding.idImViewMove.height)

                                val coordinateImMove2 = MoveItemScale.movingItemScale(MoveItemScale.moveItemRc(x2, y2, viewRc), binding.idRcViewFragPWP.width, binding.idRcViewFragPWP.height,
                                    binding.idImViewMove2.width, binding.idImViewMove2.height)
                                val coordinatePositionBackImMove2 = MoveItemScale.movingItemScale(positionMove, binding.idRcViewFragPWP.width, binding.idRcViewFragPWP.height,
                                    binding.idImViewMove2.width, binding.idImViewMove2.height)
                                val coordinateBackImMove2 = listOf(coordinatePositionBackImMove2[0].toFloat()+rcViewX,
                                    coordinatePositionBackImMove2[1].toFloat()+rcViewY)
                                val coordinateBackStartImMove2 = listOf(coordinateImMove2[0].toFloat()+rcViewX,coordinateImMove2[1].toFloat()+rcViewY)//координаты стартовой точки для обратного полета ImMove2

                                adapter?.transferArrayAdapterToFrag(MoveItemScale.moveItemRc(x2, y2, viewRc),
                                    this, coordinateBackImMove2, coordinateBackStartImMove2, positionMove) // для возращения нового итема на старое место
                                val path = Path().apply {
                                    moveTo(binding.idImViewMove.x,binding.idImViewMove.y)
                                    lineTo(coordinateImMove[0].toFloat()+rcViewX,coordinateImMove[1].toFloat()+rcViewY)
                                }
                                ObjectAnimator.ofFloat(binding.idImViewMove, View.X, View.Y, path).apply {
                                    doOnStart {
                                        adapter?.noMove?.noMoveIfOpenScale = false
                                        adapter?.click?.clickable = false
                                        noClick.clickable = false
                                    }
                                    duration = 200
                                    start()
                                    doOnEnd {
                                        adapter?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, MoveItemScale.moveItemRc(x2, y2, viewRc))
                                        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.idRcViewFragPWP.width/9f) / binding.idImViewMove.width)
                                        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.idRcViewFragPWP.width/9f) / binding.idImViewMove.width)
                                        ObjectAnimator.ofPropertyValuesHolder(binding.idImViewMove, scaleX, scaleY).apply {
                                            duration = 220
                                            start()
                                            doOnEnd {
                                                binding.idImViewMove.visibility = View.GONE
                                                arrayBitmap.clear()
                                                arrayNumber.clear()
                                                adapter?.noMove?.noMoveIfOpenScale = true
                                                adapter?.click?.clickable = true
                                                noClick.clickable = true
                                                clickMoveAdapter = false
                                            }
                                        }
                                    }
                                }

                            }else{ // Без анимации, если версия андройд меньше заданой
                                adapter?.updateAdapterPosition(
                                    arrayBitmap, arrayNumber, arrayPosition,
                                    MoveItemScale.moveItemRc(x2, y2, viewRc)
                                )
                                val coordinate = MoveItemScale.movingItemScale(MoveItemScale.moveItemRc(x2, y2, viewRc), binding.idRcViewFragPWP.width, binding.idRcViewFragPWP.height,
                                    binding.idImViewMove.width, binding.idImViewMove.height)
                                val rcViewX = binding.idRcViewFragPWP.x
                                val rcViewY = binding.idRcViewFragPWP.y
                                val coordinateBack = listOf(coordinate[0].toFloat()+rcViewX, coordinate[1].toFloat()+rcViewY)
                                val coordinateBackStart = listOf(coordinate[0].toFloat()+rcViewX,
                                    coordinate[1].toFloat()+rcViewY)//координаты стартовой точки для обратного полета ImMove2
                                adapter?.transferArrayAdapterToFrag(MoveItemScale.moveItemRc(x2, y2, viewRc),
                                    this, coordinateBack, coordinateBackStart, positionMove)
                                binding.idImViewMove.visibility = View.GONE
                                clickMoveAdapter = false

                                if (!openItemScale) {//Чистим только когда итемScale закрыт
                                    arrayBitmap.clear()
                                    arrayNumber.clear()
                                }
                            }

                        } else{
                            //Анимация если вытянули кубик за приделы поля
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                val rcViewX = binding.idRcViewFragPWP.x
                                val rcViewY = binding.idRcViewFragPWP.y
                                val coordinate = MoveItemScale.movingItemScale(positionMove, binding.idRcViewFragPWP.width, binding.idRcViewFragPWP.height,
                                    binding.idImViewMove.width, binding.idImViewMove.height)
                                //Log.d("MyLog", "sred ${((binding.idConstLayPwP.height - binding.idRcViewFragPWP.height)/2f)}")
                                val path = Path().apply {
                                    moveTo(binding.idImViewMove.x, binding.idImViewMove.y)
                                    lineTo(coordinate[0].toFloat()+rcViewX,coordinate[1].toFloat()+rcViewY)
                                }
                                ObjectAnimator.ofFloat(binding.idImViewMove, View.X, View.Y, path).apply {
                                    doOnStart {
                                        adapter?.noMove?.noMoveIfOpenScale = false
                                        adapter?.click?.clickable = false
                                        noClick.clickable = false
                                    }
                                    duration = 300
                                start()
                                    doOnEnd {
                                        adapter?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, positionMove)
                                        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.idRcViewFragPWP.width/9f) / binding.idImViewMove.width)
                                        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.idRcViewFragPWP.width/9f) / binding.idImViewMove.width)
                                            ObjectAnimator.ofPropertyValuesHolder(binding.idImViewMove, scaleX, scaleY).apply {
                                                duration = 220
                                                start()
                                                doOnEnd {
                                                    binding.idImViewMove.visibility = View.GONE
                                                    arrayBitmap.clear()
                                                    arrayNumber.clear()
                                                    adapter?.noMove?.noMoveIfOpenScale = true
                                                    adapter?.click?.clickable = true
                                                    noClick.clickable = true
                                                    clickMoveAdapter = false
                                                }
                                            }
                                    }
                                }
                            }else{
                                binding.idImViewMove.visibility = View.GONE
                                adapter?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, positionMove)
                                clickMoveAdapter = false
                            }
                        }
                    }
                }


            }
            return@setOnTouchListener false
        }


        binding.idImViewScale.setOnTouchListener(){ v, event ->
            //Log.d("MyLog", "idImViewScale.setOnTouchListener")
            if(noClickItemScale){
                val minDistance = 15
                val minDistanceUpDown = 7

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана

                        x1 = event.x //Позиция по оси Х куда нажали
                        y1 = event.y //Позиция по оси Y куда нажали
                        noReplaySwipe = true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        x2 = event.x
                        y2 = event.y
                        var deltaX: Float = x2 - x1
                        var deltaY: Float = y2 - y1
                        if (abs(deltaX) > minDistance && noReplaySwipe){
                            if(x2 > x1){
                                right()
                                noReplaySwipe = false
                            }else {
                                left()
                                noReplaySwipe = false
                            }
                        }
                        if (abs(deltaY) > minDistanceUpDown && noReplaySwipe){
                            if(y2 > y1){
                                down()
                                noReplaySwipe = false
                            }else {
                                up()
                                noReplaySwipe = false
                            }
                        }

                    }
                    MotionEvent.ACTION_UP -> {
                        x2 = event.x
                        y2 = event.y
                        var deltaX: Float = x2 - x1
                        var deltaY: Float = y2 - y1
                        if (abs(deltaX) < minDistance && abs(deltaY) < minDistanceUpDown){
                            openItemScale = false
                            animObjectMinus(positionClickOpen, arrayBitmap[0], arrayBitmap[1], arrayBitmap[2], arrayBitmap[3],
                                arrayBitmap[4], arrayBitmap[5], arrayNumber[0],  arrayNumber[1], arrayNumber[2], arrayNumber[3],
                                arrayNumber[4], arrayNumber[5], arrayPosition[0],  arrayPosition[1], arrayPosition[2], arrayPosition[3],
                                arrayPosition[4], arrayPosition[5],
                                positionClick, itemViewGlobal)
                        }
                    }

                }
            }
            return@setOnTouchListener true

        }


        binding.idImViewScale2.setOnTouchListener { v, event ->
            //Log.d("MyLog", "idImViewScale2.setOnTouchListener")
            //Проверка, что бы нельзя нажать во время анимации
            if(noClickItemScale){
                val maxSizeView = v.width
                val minDistance = 15
                val minDistanceUpDown = 7

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                        x1 = event.x //Позиция по оси Х куда нажали
                        y1 = event.y //Позиция по оси Y куда нажали
                        noReplaySwipe = true
                    }
                    MotionEvent.ACTION_MOVE -> {

                        x2 = event.x
                        y2 = event.y
                        var deltaX: Float = x2 - x1
                        var deltaY: Float = y2 - y1
                        if (abs(deltaX) > minDistance && noReplaySwipe){
                            if(x2 > x1){
                                right()
                                noReplaySwipe = false
                            }else {
                                left()
                                noReplaySwipe = false
                            }
                        }
                        if (abs(deltaY) > minDistanceUpDown && noReplaySwipe){
                            if(y2 > y1){
                                down()
                                noReplaySwipe = false
                            }else {
                                up()
                                noReplaySwipe = false
                            }
                        }

                    }
                    MotionEvent.ACTION_UP -> {
                        x2 = event.x
                        y2 = event.y
                        var deltaX: Float = x2 - x1
                        var deltaY: Float = y2 - y1
                        if (abs(deltaX) < minDistance && abs(deltaY) < minDistanceUpDown){
                            openItemScale = false
                            animObjectMinus(positionClickOpen, arrayBitmap[0], arrayBitmap[1], arrayBitmap[2], arrayBitmap[3],
                                arrayBitmap[4], arrayBitmap[5], arrayNumber[0],  arrayNumber[1], arrayNumber[2], arrayNumber[3],
                                arrayNumber[4], arrayNumber[5], arrayPosition[0],  arrayPosition[1], arrayPosition[2], arrayPosition[3],
                                arrayPosition[4], arrayPosition[5], positionClick, itemViewGlobal)

                        }
                    }

                }
            }
            return@setOnTouchListener true
        }
    }

    fun shuffleArrayDataArrayPositionAndNumberBitmap(arraylist: ArrayList<dataArrayPositionAndNumberBitmap>):ArrayList<dataArrayPositionAndNumberBitmap>{
        //val shuffleTemp: ArrayList<dataArrayPositionAndNumberBitmap> = Arraylist.shuffle()
        arraylist.shuffle()
        return arraylist
    }

    fun shuffleIntArray(num: ArrayList<Int>):ArrayList<Int>{
        val shuffleTemp: ArrayList<Int> = num.shuffled() as ArrayList<Int>
        //day.arrayBitmap.shuffle()
        return shuffleTemp
    }


    fun proverkaMassivaNaPovtor(arr: ArrayList<dataArrayPositionAndNumberBitmap>):Boolean{
        var re: Boolean = false
        for (i in arr.indices) {
            for (j in i + 1 until arr.size){
                if (arr[i] == arr[j]){
                    //Log.d("MyLog", "arrPovtor ${arr[j]}")
                    re = true
                }
            }
        }
        return re
    }

    @SuppressLint("ClickableViewAccessibility")
    fun initRcView(){
        //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP) // Привязывваем touchHelper к RcView
        binding.idRcViewFragPWP.layoutManager = CustomGridLayoutManager(activity as AppCompatActivity)
        //binding.idRcViewFragPWP.isNestedScrollingEnabled = false
        //binding.idRcViewFragPWP.layoutManager = GridLayoutManager(activity,9, LinearLayoutManager.VERTICAL, false)
        binding.idRcViewFragPWP.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        job?.cancel()
    }

    companion object {
        fun newInstance(){}
    }
    //Нажатие в адапетере на итем
    override fun clickScaleItem(
        b0: Bitmap,
        b1: Bitmap,
        b2: Bitmap,
        b3: Bitmap,
        b4: Bitmap,
        b5: Bitmap,
        n0: Int,
        n1: Int,
        n2: Int,
        n3: Int,
        n4: Int,
        n5: Int,
        p0: Int,
        p1: Int,
        p2: Int,
        p3: Int,
        p4: Int,
        p5: Int,
        position: Int,
        itemView: View,
        imItem: ImageView
    ) {
        //Log.d("MyLog", "clickScaleItem")
        binding.idImViewScale.elevation = 1f
        itemViewGlobal = itemView
        imItemGlobal = imItem
        positionClick = position
        if (openItemScale){
            //запускается когда итем уже увеличен, и закрывает предыдущий итем и запускает новый
            animObjectMinus(positionClickOpen, b0,b1,b2,b3,b4,b5,n0,n1,n2,n3,n4,n5, p0,p1,p2,p3,p4,p5, positionClick, itemView)
        }else {
            binding.idImViewScale.visibility = View.VISIBLE
            //Log.d("MyLog", "Запустили просто scale итем")
            //запускается если ничего небыло открыто
            itemView.visibility = View.INVISIBLE
            positionClickOpen = position
            val withRc = binding.idRcViewFragPWP.width
            val heightRc = binding.idRcViewFragPWP.height
            val withIm = binding.idImViewScale.width
            val heightIm = binding.idImViewScale.height
            //Log.d("MyLog", "position $position")
            //меняем координаты появления scaleView
            var paramsScale = binding.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
            var paramsScale2 = binding.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
            val coordinates =
                MoveItemScale.movingItemScale(position, withRc, heightRc, withIm, heightIm)
            paramsScale.leftMargin = coordinates[0]
            paramsScale.topMargin = coordinates[1]
            paramsScale2.leftMargin = coordinates[0]
            paramsScale2.topMargin = coordinates[1]
            binding.idImViewScale.layoutParams = paramsScale
            binding.idImViewScale2.layoutParams = paramsScale2

            binding.idImViewScale.visibility = View.VISIBLE
            binding.idImViewScale2.visibility = View.GONE

            binding.idImViewScale.setImageBitmap(b0)
            animObjectPlus(position)
            openItemScale = true //открыт/увеличен scaleItem
            adapter?.noMove?.noMoveIfOpenScale = false
            arrayBitmap.clear()
            arrayBitmap.add(b0)
            arrayBitmap.add(b1)
            arrayBitmap.add(b2)
            arrayBitmap.add(b3)
            arrayBitmap.add(b4)
            arrayBitmap.add(b5)
            arrayNumber.clear()
            arrayNumber.add(n0)
            arrayNumber.add(n1)
            arrayNumber.add(n2)
            arrayNumber.add(n3)
            arrayNumber.add(n4)
            arrayNumber.add(n5)
            arrayPosition.clear()
            arrayPosition.add(p0)
            arrayPosition.add(p1)
            arrayPosition.add(p2)
            arrayPosition.add(p3)
            arrayPosition.add(p4)
            arrayPosition.add(p5)

            //adapter?.clickable = true
        }
        /*}else{
            binding.idImViewScale2.setImageBitmap(b0)
            (AnimatorInflater.loadAnimator(
                activity as AppCompatActivity,
                R.animator.animation_cube_scale_start
            ) as ObjectAnimator).apply {
                target = binding.idImViewScale2
                start()
            }
        }*/

    }

    override fun moveItem(
        b0: Bitmap,
        b1: Bitmap,
        b2: Bitmap,
        b3: Bitmap,
        b4: Bitmap,
        b5: Bitmap,
        n0: Int,
        n1: Int,
        n2: Int,
        n3: Int,
        n4: Int,
        n5: Int,
        p0: Int,
        p1: Int,
        p2: Int,
        p3: Int,
        p4: Int,
        p5: Int,
        position: Int,
        itemView: View,
        imItem: ImageView
    ) {
        //binding.idImViewMove.elevation = 1f
        clickMoveAdapter = true
        itemViewGlobal = itemView
        imItemGlobal = imItem
        positionMove = position
        itemView.visibility = View.GONE
        binding.idImViewMove.setImageBitmap(b0)
        arrayBitmap.clear()
        arrayBitmap.add(b0)
        arrayBitmap.add(b1)
        arrayBitmap.add(b2)
        arrayBitmap.add(b3)
        arrayBitmap.add(b4)
        arrayBitmap.add(b5)
        arrayNumber.clear()
        arrayNumber.add(n0)
        arrayNumber.add(n1)
        arrayNumber.add(n2)
        arrayNumber.add(n3)
        arrayNumber.add(n4)
        arrayNumber.add(n5)
        arrayPosition.clear()
        arrayPosition.add(p0)
        arrayPosition.add(p1)
        arrayPosition.add(p2)
        arrayPosition.add(p3)
        arrayPosition.add(p4)
        arrayPosition.add(p5)
        //Log.d("MyLog", "MoveItem $clickMoveAdapter")
    }

    override fun transferMoveArray(
        b0: Bitmap,
        b1: Bitmap,
        b2: Bitmap,
        b3: Bitmap,
        b4: Bitmap,
        b5: Bitmap,
        n0: Int,
        n1: Int,
        n2: Int,
        n3: Int,
        n4: Int,
        n5: Int,
        p0: Int,
        p1: Int,
        p2: Int,
        p3: Int,
        p4: Int,
        p5: Int,
        position: Int,
        coordinate: List<Float>,
        coordinateBackStart: List<Float>,
        positionMove: Int
    ) {
        if(position != positionMove) {
            arrayBitmapMoveReturn.clear()
            arrayBitmapMoveReturn.add(b0)
            arrayBitmapMoveReturn.add(b1)
            arrayBitmapMoveReturn.add(b2)
            arrayBitmapMoveReturn.add(b3)
            arrayBitmapMoveReturn.add(b4)
            arrayBitmapMoveReturn.add(b5)
            arrayNumberMoveReturn.clear()
            arrayNumberMoveReturn.add(n0)
            arrayNumberMoveReturn.add(n1)
            arrayNumberMoveReturn.add(n2)
            arrayNumberMoveReturn.add(n3)
            arrayNumberMoveReturn.add(n4)
            arrayNumberMoveReturn.add(n5)
            arrayPositionMoveReturn.clear()
            arrayPositionMoveReturn.add(p0)
            arrayPositionMoveReturn.add(p1)
            arrayPositionMoveReturn.add(p2)
            arrayPositionMoveReturn.add(p3)
            arrayPositionMoveReturn.add(p4)
            arrayPositionMoveReturn.add(p5)
            binding.idImViewMove2.visibility = View.VISIBLE
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
            ObjectAnimator.ofPropertyValuesHolder(binding.idImViewMove2, scaleX, scaleY).apply {
                duration = 10
                start()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.idImViewMove2.setImageBitmap(b0)
                //Вводим координаты откуда и куда будет перемещение view
                val path = Path().apply {
                    moveTo(coordinateBackStart[0], coordinateBackStart[1])
                    //Log.d("MyLog", "position $position")
                    when (positionMove) {
                        0 -> {
                            lineTo(
                                coordinate[0] + (binding.idRcViewFragPWP.width / 9f - binding.idImViewMove2.width) / 2f,
                                coordinate[1] + (binding.idRcViewFragPWP.height / 16f - binding.idImViewMove2.height) / 2
                            )
                        }
                        1, 2, 3, 4, 5, 6, 7 -> {
                            lineTo(
                                coordinate[0],
                                coordinate[1] + (binding.idRcViewFragPWP.height / 16f - binding.idImViewMove2.height) / 2
                            )
                        }
                        8 -> {
                            lineTo(
                                coordinate[0] - (binding.idRcViewFragPWP.width / 9f - binding.idImViewMove2.width) / 2f,
                                coordinate[1] + (binding.idRcViewFragPWP.height / 16f - binding.idImViewMove2.height) / 2
                            )
                        }
                        9, 18, 27, 36, 45, 54, 63, 72, 81, 90, 99, 108, 117, 126 -> {
                            lineTo(
                                coordinate[0] + (binding.idRcViewFragPWP.width / 9f - binding.idImViewMove2.width) / 2f,
                                coordinate[1]
                            )
                        }
                        17, 26, 35, 44, 53, 62, 71, 80, 89, 98, 107, 116, 125, 134 -> {
                            lineTo(
                                coordinate[0] - (binding.idRcViewFragPWP.width / 9f - binding.idImViewMove2.width) / 2f,
                                coordinate[1]
                            )
                        }
                        135 -> {
                            lineTo(
                                coordinate[0] + (binding.idRcViewFragPWP.width / 9f - binding.idImViewMove2.width) / 2f,
                                coordinate[1] - (binding.idRcViewFragPWP.height / 16f - binding.idImViewMove2.height) / 2
                            )
                        }
                        136, 137, 138, 139, 140, 141, 142 -> {
                            lineTo(
                                coordinate[0],
                                coordinate[1] - (binding.idRcViewFragPWP.height / 16f - binding.idImViewMove2.height) / 2
                            )
                        }
                        143 -> {
                            lineTo(
                                coordinate[0] - (binding.idRcViewFragPWP.width / 9f - binding.idImViewMove2.width) / 2f,
                                coordinate[1] - (binding.idRcViewFragPWP.height / 16f - binding.idImViewMove2.height) / 2
                            )
                        }
                        else -> {
                            lineTo(coordinate[0], coordinate[1])
                        }
                    }

                }
                ObjectAnimator.ofFloat(binding.idImViewMove2, View.X, View.Y, path).apply {
                    duration = 450
                    doOnStart {
                        adapter?.noMoveBack?.noMoveIfOpenScale = false
                        adapter?.clickBack?.clickable = false
                        noClickBack.clickable = false
                    }
                    start()
                    doOnEnd {
                        val scaleX = PropertyValuesHolder.ofFloat(
                            View.SCALE_X,
                            (binding.idRcViewFragPWP.width / 9f) / binding.idImViewMove2.width
                        )
                        val scaleY = PropertyValuesHolder.ofFloat(
                            View.SCALE_Y,
                            (binding.idRcViewFragPWP.height / 16f) / binding.idImViewMove2.height
                        )
                        ObjectAnimator.ofPropertyValuesHolder(binding.idImViewMove2, scaleX, scaleY)
                            .apply {
                                duration = 350
                                start()
                                doOnEnd {
                                    adapter?.updateAdapterPosition(
                                        arrayBitmapMoveReturn, arrayNumberMoveReturn, arrayPositionMoveReturn,
                                        positionMove
                                    )
                                    adapter?.noMoveBack?.noMoveIfOpenScale = true
                                    adapter?.clickBack?.clickable = true
                                    noClickBack.clickable = true
                                    //binding.idImViewMove2.visibility = View.GONE
                                    //Log.d("MyLog", "coordinateBackImMove2 ${binding.idImViewMove2.y}")
                                }
                            }
                    }
                }
            } else {
                adapter?.updateAdapterPosition(arrayBitmapMoveReturn, arrayNumberMoveReturn,
                    arrayPositionMoveReturn, positionMove)
            }
        }
    }

    //После обновления позиции в адаптере происходит проверка какие линии потушить
    override fun updateLine(positionAdapterUpdate: Int) {
        //adapter?.updateLinePosition(positionAdapterUpdate)
    }

    override fun countPlus(count: Int) {

        //adapter?.countStart?.count?.plus(2)
    }

    fun animObjectPlus(position: Int){

        when(position) {
            0 -> {
                binding.idImViewScale.pivotX = 0f
                binding.idImViewScale.pivotY = 0f
                binding.idImViewScale2.pivotX = 0f
                binding.idImViewScale2.pivotY = 0f
            }
            1,2,3,4,5,6,7 ->{
                binding.idImViewScale.pivotX = (binding.idImViewScale.width) / 2f
                binding.idImViewScale.pivotY = 0f
                binding.idImViewScale2.pivotX = (binding.idImViewScale.width) / 2f
                binding.idImViewScale2.pivotY = 0f
            }
            8 -> {
                binding.idImViewScale.pivotX = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale.pivotY = 0f
                binding.idImViewScale2.pivotX = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale2.pivotY = 0f
            }
            9,18,27,36,45,54,63,72,81,90,99,108,117,126 ->{
                binding.idImViewScale.pivotX = 0f
                binding.idImViewScale.pivotY = (binding.idImViewScale.width) / 2f
                binding.idImViewScale2.pivotX = 0f
                binding.idImViewScale2.pivotY = (binding.idImViewScale.width) / 2f
            }
            17,26,35,44,53,62,71,80,89,98,107,116,125,134 ->{
                binding.idImViewScale.pivotX = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale.pivotY = (binding.idImViewScale.width) / 2f
                binding.idImViewScale2.pivotX = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale2.pivotY = (binding.idImViewScale.width) / 2f
            }
            135 -> {
                binding.idImViewScale.pivotX = 0f
                binding.idImViewScale.pivotY = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale2.pivotX = 0f
                binding.idImViewScale2.pivotY = (binding.idImViewScale.width.toFloat())
            }
            136,137,138,139,140,141,142 ->{
                binding.idImViewScale.pivotX = (binding.idImViewScale.width) / 2f
                binding.idImViewScale.pivotY = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale2.pivotX = (binding.idImViewScale.width) / 2f
                binding.idImViewScale2.pivotY = (binding.idImViewScale.width.toFloat())
            }
            143 ->{
                binding.idImViewScale.pivotX = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale.pivotY = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale2.pivotX = (binding.idImViewScale.width.toFloat())
                binding.idImViewScale2.pivotY = (binding.idImViewScale.width.toFloat())
            }else -> {
            binding.idImViewScale.pivotX = binding.idImViewScale.width / 2f
            binding.idImViewScale.pivotY = binding.idImViewScale.width / 2f
            binding.idImViewScale2.pivotX = binding.idImViewScale.width / 2f
            binding.idImViewScale2.pivotY = binding.idImViewScale.width / 2f
            }
        }

        binding.idImViewScale.scaleX = (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width
        binding.idImViewScale.scaleY = (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width
        binding.idImViewScale2.scaleX = (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width
        binding.idImViewScale2.scaleY = (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f)
        val allAnim =  ObjectAnimator.ofPropertyValuesHolder(binding.idImViewScale, scaleX, scaleY).apply {
            duration = 400
            doOnStart {
                //Log.d("MyLog", "start animation plus")
                //touchHelper.attachToRecyclerView(null)
                adapter?.click?.clickable = false
                noClickItemScale = false
            }
            start()

           doOnEnd {
               //Log.d("MyLog", "end animation plus")
               adapter?.click?.clickable = true
               noClickItemScale = true
               //binding.idImViewScale.isClickable = false
               //binding.idImViewScale2.isClickable = false
           }
        }
    }

    fun animObjectMinus(positionClickOp: Int, b0 : Bitmap, b1 : Bitmap, b2 : Bitmap,
                        b3 : Bitmap, b4 : Bitmap, b5 : Bitmap, n0: Int , n1: Int, n2: Int, n3: Int, n4: Int, n5: Int,
                        p0: Int , p1: Int, p2: Int, p3: Int, p4: Int, p5: Int,
                        positionClick: Int, itemView: View){
        adapter?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, positionClickOp)
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width)
        if (binding.idImViewScale.visibility == View.VISIBLE) {
                ObjectAnimator.ofPropertyValuesHolder(binding.idImViewScale, scaleX, scaleY).apply {
                    duration = 400
                    doOnStart {
                        //Log.d("MyLog", "start animation minus scale")
                        adapter?.click?.clickable = false
                        noClickItemScale = false
                    }
                    start()
                    doOnEnd {
                        //Log.d("MyLog", "end animation minus scale")
                        binding.idImViewScale.visibility = View.GONE
                        adapter?.click?.clickable = true
                        //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                        noClickItemScale = true
                        // Часть запускается если был не закрыт итем
                        if(openItemScale){
                            //Log.d("MyLog", "2end animation minus scale")
                            itemView.visibility = View.INVISIBLE
                            positionClickOpen = positionClick
                            val withRc = binding.idRcViewFragPWP.width
                            val heightRc = binding.idRcViewFragPWP.height
                            val withIm = binding.idImViewScale.width
                            val heightIm = binding.idImViewScale.height
                            //Log.d("MyLog", "position $position")
                            //меняем координаты появления scaleView
                            var paramsScale = binding.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                            var paramsScale2 = binding.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
                            val coordinates =
                                MoveItemScale.movingItemScale(positionClick, withRc, heightRc, withIm, heightIm)
                            paramsScale.leftMargin = coordinates[0]
                            paramsScale.topMargin = coordinates[1]
                            paramsScale2.leftMargin = coordinates[0]
                            paramsScale2.topMargin = coordinates[1]
                            binding.idImViewScale.layoutParams = paramsScale
                            binding.idImViewScale2.layoutParams = paramsScale2

                            binding.idImViewScale.visibility = View.VISIBLE
                            binding.idImViewScale2.visibility = View.GONE

                            binding.idImViewScale.setImageBitmap(b0)
                            animObjectPlus(positionClickOpen)
                            openItemScale = true
                            arrayBitmap.clear()
                            arrayBitmap.add(b0)
                            arrayBitmap.add(b1)
                            arrayBitmap.add(b2)
                            arrayBitmap.add(b3)
                            arrayBitmap.add(b4)
                            arrayBitmap.add(b5)
                            arrayNumber.clear()
                            arrayNumber.add(n0)
                            arrayNumber.add(n1)
                            arrayNumber.add(n2)
                            arrayNumber.add(n3)
                            arrayNumber.add(n4)
                            arrayNumber.add(n5)
                            arrayPosition.clear()
                            arrayPosition.add(p0)
                            arrayPosition.add(p1)
                            arrayPosition.add(p2)
                            arrayPosition.add(p3)
                            arrayPosition.add(p4)
                            arrayPosition.add(p5)
                            //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                            //adapter?.click?.clickable = true
                            //noClickItemScale = true
                        }else{
                            adapter?.noMove?.noMoveIfOpenScale = true
                        }
                    }
                }
        }else{
                ObjectAnimator.ofPropertyValuesHolder(binding.idImViewScale2, scaleX, scaleY).apply {
                    duration = 400
                    doOnStart {
                        //Log.d("MyLog", "start animation minus scale2")
                        adapter?.click?.clickable = false
                        noClickItemScale = false
                    }
                    start()
                    doOnEnd {
                        //Log.d("MyLog", "end animation minus scale2")
                        binding.idImViewScale2.visibility = View.GONE
                        adapter?.click?.clickable = true
                        //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                        noClickItemScale = true
                        // Часть запускается если был не закрыт итем
                        if(openItemScale){
                            //Log.d("MyLog", "2end animation minus scale2")
                            itemView.visibility = View.INVISIBLE
                            positionClickOpen = positionClick
                            val withRc = binding.idRcViewFragPWP.width
                            val heightRc = binding.idRcViewFragPWP.height
                            val withIm = binding.idImViewScale.width
                            val heightIm = binding.idImViewScale.height
                            //Log.d("MyLog", "position $position")
                            //меняем координаты появления scaleView
                            var paramsScale = binding.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                            var paramsScale2 = binding.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
                            val coordinates =
                                MoveItemScale.movingItemScale(positionClick, withRc, heightRc, withIm, heightIm)
                            paramsScale.leftMargin = coordinates[0]
                            paramsScale.topMargin = coordinates[1]
                            paramsScale2.leftMargin = coordinates[0]
                            paramsScale2.topMargin = coordinates[1]
                            binding.idImViewScale.layoutParams = paramsScale
                            binding.idImViewScale2.layoutParams = paramsScale2

                            binding.idImViewScale.visibility = View.VISIBLE
                            binding.idImViewScale2.visibility = View.GONE

                            binding.idImViewScale.setImageBitmap(b0)
                            animObjectPlus(positionClickOpen)
                            openItemScale = true
                            arrayBitmap.clear()
                            arrayBitmap.add(b0)
                            arrayBitmap.add(b1)
                            arrayBitmap.add(b2)
                            arrayBitmap.add(b3)
                            arrayBitmap.add(b4)
                            arrayBitmap.add(b5)
                            arrayNumber.clear()
                            arrayNumber.add(n0)
                            arrayNumber.add(n1)
                            arrayNumber.add(n2)
                            arrayNumber.add(n3)
                            arrayNumber.add(n4)
                            arrayNumber.add(n5)
                            arrayPosition.clear()
                            arrayPosition.add(p0)
                            arrayPosition.add(p1)
                            arrayPosition.add(p2)
                            arrayPosition.add(p3)
                            arrayPosition.add(p4)
                            arrayPosition.add(p5)

                            //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                            //adapter?.click?.clickable = true
                            //noClickItemScale = true
                        }else{
                            adapter?.noMove?.noMoveIfOpenScale = true
                        }
                    }
                }
        }

    }



    fun up(){
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[4]
        arrayBitmap[4] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[3]
        arrayBitmap[3] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[4]
        arrayNumber[4] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[3]
        arrayNumber[3] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[4]
        arrayPosition[4] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[3]
        arrayPosition[3] = tempPosition
        binding.idImViewScale.scaleX = 1.0f
        binding.idImViewScale.scaleY = 1.0f
        binding.idImViewScale2.scaleX = 1.0f
        binding.idImViewScale2.scaleY = 1.0f
        if(binding.idImViewScale.visibility == View.VISIBLE) {
            binding.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale2.visibility = View.VISIBLE
            binding.idImViewScale.startAnimation(CubeAnimation.create(1, false, 400))
            binding.idImViewScale2.startAnimation(CubeAnimation.create(1, true, 400))
            binding.idImViewScale.visibility = View.GONE
        }else{
            binding.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale.visibility = View.VISIBLE
            binding.idImViewScale2.startAnimation(CubeAnimation.create(1, false, 400))
            binding.idImViewScale.startAnimation(CubeAnimation.create(1, true, 400))
            binding.idImViewScale2.visibility = View.GONE
        }
    }

    fun down(){
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[3]
        arrayBitmap[3] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[4]
        arrayBitmap[4] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[3]
        arrayNumber[3] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[4]
        arrayNumber[4] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[3]
        arrayPosition[3] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[4]
        arrayPosition[4] = tempPosition
        binding.idImViewScale.scaleX = 1.0f
        binding.idImViewScale.scaleY = 1.0f
        binding.idImViewScale2.scaleX = 1.0f
        binding.idImViewScale2.scaleY = 1.0f
        if(binding.idImViewScale.visibility == View.VISIBLE) {
            binding.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale2.visibility = View.VISIBLE
            binding.idImViewScale.startAnimation(CubeAnimation.create(2, false, 400))
            binding.idImViewScale2.startAnimation(CubeAnimation.create(2, true, 400))
            binding.idImViewScale.visibility = View.GONE
        }else{
            binding.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale.visibility = View.VISIBLE
            binding.idImViewScale2.startAnimation(CubeAnimation.create(2, false, 400))
            binding.idImViewScale.startAnimation(CubeAnimation.create(2, true, 400))
            binding.idImViewScale2.visibility = View.GONE
        }
    }

    fun right(){
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[1]
        arrayBitmap[1] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[2]
        arrayBitmap[2] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[1]
        arrayNumber[1] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[2]
        arrayNumber[2] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[1]
        arrayPosition[1] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[2]
        arrayPosition[2] = tempPosition
        binding.idImViewScale.scaleX = 1.0f
        binding.idImViewScale.scaleY = 1.0f
        binding.idImViewScale2.scaleX = 1.0f
        binding.idImViewScale2.scaleY = 1.0f
        if(binding.idImViewScale.visibility == View.VISIBLE) {
            binding.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale2.visibility = View.VISIBLE
            binding.idImViewScale.startAnimation(CubeAnimation.create(4, false, 400))
            binding.idImViewScale2.startAnimation(CubeAnimation.create(4, true, 400))
            binding.idImViewScale.visibility = View.GONE
        }else{
            binding.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale.visibility = View.VISIBLE
            binding.idImViewScale2.startAnimation(CubeAnimation.create(4, false, 400))
            binding.idImViewScale.startAnimation(CubeAnimation.create(4, true, 400))
            binding.idImViewScale2.visibility = View.GONE
        }
    }

    fun left(){
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[2]
        arrayBitmap[2] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[1]
        arrayBitmap[1] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[2]
        arrayNumber[2] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[1]
        arrayNumber[1] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[2]
        arrayPosition[2] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[1]
        arrayPosition[1] = tempPosition
        binding.idImViewScale.scaleX = 1.0f
        binding.idImViewScale.scaleY = 1.0f
        binding.idImViewScale2.scaleX = 1.0f
        binding.idImViewScale2.scaleY = 1.0f
        if(binding.idImViewScale.visibility == View.VISIBLE) {
            binding.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale2.visibility = View.VISIBLE
            binding.idImViewScale.startAnimation(CubeAnimation.create(3, false, 400))
            binding.idImViewScale2.startAnimation(CubeAnimation.create(3, true, 400))
            binding.idImViewScale.visibility = View.GONE
        }else{
            binding.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.idImViewScale.visibility = View.VISIBLE
            binding.idImViewScale2.startAnimation(CubeAnimation.create(3, false, 400))
            binding.idImViewScale.startAnimation(CubeAnimation.create(3, true, 400))
            binding.idImViewScale2.visibility = View.GONE
        }
    }




}