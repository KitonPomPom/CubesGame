package kitonpompom.cubesgame.activities

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.activities.utils.*
import kitonpompom.cubesgame.databinding.FragmentPlayingWithPicturesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Math.abs


class FragmentPlayingWithPictures : Fragment(), AdapterFragPWP.ClickScaleItemInterface {

    lateinit var binding: FragmentPlayingWithPicturesBinding
    private val dataModel: DataModel by activityViewModels()
    private var job: Job? = null
    private val adapter: AdapterFragPWP? = AdapterFragPWP(this)
    private val swipeCallback = ItemTouchMoveAndSwipe(adapter!!)
    private val touchHelper = ItemTouchHelper(swipeCallback)
    var arrayBitmap = ArrayList<Bitmap>()
    var positionClickOpen = 0
    var positionClick = 0
    var openItemScale = false //true - Открыт\увеличен itemScale
    lateinit var itemViewGlobal: View
    lateinit var imItemGlobal: ImageView

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
                    tempListBitmap.add(dataArrayBitmap(arrayPosition,arrayNumber,arrayBitmap))
                }
                adapter?.updateAdapter(tempListBitmap)
            }
        }

        binding.idRcViewFragPWP.setOnTouchListener(){ viewRc, eventRc ->
            Log.d("MyLog", "idRcViewFragPWP.setOnTouchListener $clickMoveAdapter")
            when (eventRc.action) {
                MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                    Log.d("MyLog", "ACTION_DOWN")

                    //x1 = eventRc.x //Позиция по оси Х куда нажали
                    //y1 = eventRc.y //Позиция по оси Y куда нажали
                    //noReplaySwipe = true
                }
                MotionEvent.ACTION_MOVE -> {

                    if(clickMoveAdapter && arrayBitmap.isNotEmpty()) { //проверка на то, сработал ли в адаптере onTouch и пустой ли масс. с битмапами
                        Log.d("MyLog", "ACTION_MOVE $clickMoveAdapter")
                        binding.idImViewMove.elevation = 1.0f
                        binding.idImViewMove.visibility = View.VISIBLE
                        binding.idImViewMove.x = eventRc.x - imItemGlobal.width / 2f
                        binding.idImViewMove.y = eventRc.y
                    }

                }
                MotionEvent.ACTION_UP -> {

                    if (clickMoveAdapter && arrayBitmap.isNotEmpty()) {

                        x2 = eventRc.x
                        y2 = eventRc.y
                        adapter?.updateAdapterPosition(
                            arrayBitmap,
                            MoveItemScale.moveItemRc(x2, y2, viewRc)
                        )
                        binding.idImViewMove.visibility = View.GONE
                    }
                    clickMoveAdapter = false
                    Log.d("MyLog", "OpenItemScale: $openItemScale")
                    if (!openItemScale) {//Чистим только когда итемScale закрыт
                        arrayBitmap.clear()
                    }
                    Log.d("MyLog", "Position move: ${MoveItemScale.moveItemRc(x2,y2,viewRc)}")
                }


            }
            return@setOnTouchListener false
        }


        binding.idImViewScale.setOnTouchListener(){ v, event ->
            Log.d("MyLog", "idImViewScale.setOnTouchListener")
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
                                arrayBitmap[4], arrayBitmap[5],positionClick, itemViewGlobal)
                        }
                    }

                }
            }
            return@setOnTouchListener true

        }


        binding.idImViewScale2.setOnTouchListener { v, event ->
            Log.d("MyLog", "idImViewScale2.setOnTouchListener")
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
                                arrayBitmap[4], arrayBitmap[5],positionClick, itemViewGlobal)

                        }
                    }

                }
            }
            return@setOnTouchListener true
        }
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
        position: Int,
        itemView: View,
        imItem: ImageView
    ) {
        Log.d("MyLog", "clickScaleItem")
        binding.idImViewScale.elevation = 1f
        itemViewGlobal = itemView
        imItemGlobal = imItem
        positionClick = position
        if (openItemScale){
            //запускается когда итем уже увеличен, и закрывает предыдущий итем и запускает новый
            animObjectMinus(positionClickOpen, b0,b1,b2,b3,b4,b5, positionClick, itemView)
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
            openItemScale = true
            adapter?.noMove?.noMoveIfOpenScale = false
            arrayBitmap.clear()
            arrayBitmap.add(b0)
            arrayBitmap.add(b1)
            arrayBitmap.add(b2)
            arrayBitmap.add(b3)
            arrayBitmap.add(b4)
            arrayBitmap.add(b5)
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
        position: Int,
        itemView: View,
        imItem: ImageView
    ) {
        //binding.idImViewMove.elevation = 1f
        clickMoveAdapter = true
        itemViewGlobal = itemView
        imItemGlobal = imItem
        itemView.visibility = View.GONE
        binding.idImViewMove.setImageBitmap(b0)
        arrayBitmap.clear()
        arrayBitmap.add(b0)
        arrayBitmap.add(b1)
        arrayBitmap.add(b2)
        arrayBitmap.add(b3)
        arrayBitmap.add(b4)
        arrayBitmap.add(b5)
        Log.d("MyLog", "MoveItem $clickMoveAdapter")
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
                        b3 : Bitmap, b4 : Bitmap, b5 : Bitmap, positionClick: Int, itemView: View){
        adapter?.updateAdapterPosition(arrayBitmap, positionClickOp)
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.idRcViewFragPWP.width/9f) / binding.idImViewScale.width)
        if (binding.idImViewScale.visibility == View.VISIBLE) {
                ObjectAnimator.ofPropertyValuesHolder(binding.idImViewScale, scaleX, scaleY).apply {
                    duration = 400
                    doOnStart {
                        Log.d("MyLog", "start animation minus scale")
                        adapter?.click?.clickable = false
                        noClickItemScale = false
                    }
                    start()
                    doOnEnd {
                        Log.d("MyLog", "end animation minus scale")
                        binding.idImViewScale.visibility = View.GONE
                        adapter?.click?.clickable = true
                        //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                        noClickItemScale = true
                        // Часть запускается если был не закрыт итем
                        if(openItemScale){
                            Log.d("MyLog", "2end animation minus scale")
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