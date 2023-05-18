package kitonpompom.cubesgame.activities

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Path
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.activities.data.dataArrayPositionAndNumberBitmap
import kitonpompom.cubesgame.activities.data.dataPosNumBit
import kitonpompom.cubesgame.activities.dialogs.ProgressDialog
import kitonpompom.cubesgame.activities.utils.*
import kitonpompom.cubesgame.databinding.DrawerLayoutPwpTwoBinding
import kotlinx.coroutines.*
import java.lang.Math.abs


class FragmentPlayingWithPicturesTwo : Fragment(), AdapterFragPWPHard.ClickScaleItemInterface {

    lateinit var binding: DrawerLayoutPwpTwoBinding
    private val dataModel: DataModel by activityViewModels()
    private var job: Job? = null
    private val adapterHard: AdapterFragPWPHard? = AdapterFragPWPHard(this)
    //private val adapterMedium: AdapterFragPWPMedium? = AdapterFragPWPMedium(this)
    //private val swipeCallback = ItemTouchMoveAndSwipe(adapter!!)
    //private val touchHelper = ItemTouchHelper(swipeCallback)
    lateinit var image1: ImageView
    lateinit var image2: ImageView
    lateinit var image3: ImageView
    lateinit var image4: ImageView
    lateinit var image5: ImageView
    lateinit var image6: ImageView
    lateinit var linLayImage1: LinearLayout
    lateinit var linLayImage2: LinearLayout
    lateinit var linLayImage3: LinearLayout
    lateinit var linLayImage4: LinearLayout
    lateinit var linLayImage5: LinearLayout
    lateinit var linLayImage6: LinearLayout
    var arrayBitmap = ArrayList<Bitmap>()//Массив с картинками
    var arrayNumber = ArrayList<Int>()//Массив с номерами картинок на кубике
    var arrayPosition = ArrayList<Int>()//Массив с изначальными позициями картинок на кубике
    var arrayCollectedImage = ArrayList<Int>()//Массив с значениями картинок которые собрали, для отображения в драйвер лэоут
    var arrayBitmapMoveReturn = ArrayList<Bitmap>()//Массив с картинками
    var arrayNumberMoveReturn = ArrayList<Int>()//Массив с номерами на кубике
    var arrayPositionMoveReturn = ArrayList<Int>()//Массив с изначальными позициями картинок на кубике
    var positionClickOpen = 0
    var positionClick = 0// Записываем позицию на которую нажали
    var positionMove = 0// Записываем позицию которую потянули
    var openItemScale = false //true - Открыт\увеличен itemScale
    val durationAnimationCubeSpeed: Long = 300
    //var arrayInt = arrayOf(0,1,2,3,4,5)
    lateinit var itemViewGlobal: View //педаем сюда вью, что бы была возможность использовать по всему классу
    lateinit var imItemGlobal: ImageView //педаем сюда имадж вью, что бы была возможность использовать по всему классу
    val noClick = ClickableState() //Объект для блокировки ActionUp в Move
    val noClickBack = ClickableStateBack() //Объект для блокировки ActionUp в Move для обратного движения
    //var imMoveHeight by Delegates.notNull<Int>()
    //var imMoveWidth by Delegates.notNull<Int>()

    var optionDifficulty: Int? = null

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
        binding = DrawerLayoutPwpTwoBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView() //инициализируем адаптер и рцВью
        initHeaderDrawerLayout() //инициализируем хидер в драйвер лайоуте
        var mediaPlayerCubeFalling = MediaPlayer.create(activity as AppCompatActivity, R.raw.knopka_klik_myagkii_blizkii_priglushennyii)


        //получаем картинки и пилим их на куски, потом перемешиваем и отправляем в адаптер
        dataModel.listBitmapForAdapterFragPWP.observe(activity as LifecycleOwner) {
            job = CoroutineScope(Dispatchers.Main).launch {
                //Включаем диалог с загрузкой перед началом игры
                delay(300L)
                val dialog = ProgressDialog.createProgressDialog(activity as Activity, Constans.FRAGMENT_PLAYING_WITH_PICTURE)
                //Создаем массивы с нарезаными картинками
                val arrayCroppedImage1 = (ImageManagerTwo.croppedImageHard(it[0]))
                val arrayCroppedImage2 = (ImageManagerTwo.croppedImageHard(it[1]))
                val arrayCroppedImage3 = (ImageManagerTwo.croppedImageHard(it[2]))
                val arrayCroppedImage4 = (ImageManagerTwo.croppedImageHard(it[3]))
                val arrayCroppedImage5 = (ImageManagerTwo.croppedImageHard(it[4]))
                val arrayCroppedImage6 = (ImageManagerTwo.croppedImageHard(it[5]))
                //Если при первом запуске массив arrayCollectedImage пустой, то заполняем нулями
                //А Если не пустой то заполняем резултаты из сохранений
                if(arrayCollectedImage.isEmpty()) {
                    for (i in 0..6) {
                        arrayCollectedImage.add(0)
                    }
                }

                //Загружаем картинки в драйвер лайоут
                image1.setImageBitmap(it[0])
                image2.setImageBitmap(it[1])
                image3.setImageBitmap(it[2])
                image4.setImageBitmap(it[3])
                image5.setImageBitmap(it[4])
                image6.setImageBitmap(it[5])

                //Отрисовываем рамки в зависимости от значений массива arrayCollectedImage
                collectedImageVisible(arrayCollectedImage)

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

                    for(index in 0..59){
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

                for(index in 0..59) {
                    val tArrayDataArrayBitmap = ArrayList<dataArrayBitmap>()
                    val tDataPosNumBit = ArrayList<dataPosNumBit>()
                    val arrayPosition = ArrayList<Int>()
                    val arrayNumber = ArrayList<Int>()
                    val arrayBitmap = ArrayList<Bitmap>()
                    for (i in 0..5) {
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
                    for (i in num) {
                        arrayPositionTemp.add(arrayPosition[i])
                        arrayNumberTemp.add(arrayNumber[i])
                        arrayBitmapTemp.add(arrayBitmap[i])
                    }

                    val arrayLine = ArrayList<Int>()
                    for (i in 0..3) {
                        arrayLine.add(1)
                    }
                    //Log.d("MyLog", "do $num")
                    //tArrayDataArrayBitmap.add(tDataPosNumBit)
                    //tempListBitmapFinish.add(dataArrayBitmap(arrayPosition,arrayNumber,arrayBitmap))
                    tempListBitmapFinish.add(dataArrayBitmap(arrayPositionTemp, arrayNumberTemp, arrayBitmapTemp, arrayLine))
                }
                    Log.d("MyLog", "tempListBitmapFinish0 ${tempListBitmapFinish[0].arrayBitmap.size}")
                Log.d("MyLog", "tempListBitmapFinish1 ${tempListBitmapFinish[0].arrayPosition.size}")
                Log.d("MyLog", "tempListBitmapFinish2 ${tempListBitmapFinish[0].arrayNumber.size}")
                //Log.d("MyLog", "arrayPositionAndNumber0 ${arrayShuffleArrayPositionAndNumberBitmap[0][0].arrayPosition}")

                //adapterHard?.updateAdapter(tempListBitmap)// не перемешеный
                adapterHard?.updateAdapter(tempListBitmapFinish) // перемешеный

                //Выключаем диалог после того как все загрузилось
                dialog.dismiss()
                //Открываем драйвер лэоут
                binding.idDrawerLayout.openDrawer(GravityCompat.START)
            }
        }



        //слушатель нажатия на всю РцВью
        binding.layFragPlayPwpTwo.idRcViewFragPWP.setOnTouchListener(){ viewRc, eventRc ->
            when (eventRc.action) {
                MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                    //x1 = eventRc.x //Позиция по оси Х куда нажали
                    //y1 = eventRgc.y //Позиция по оси Y куда нажали
                    //noReplaySwipe = true
                }

                MotionEvent.ACTION_MOVE -> {
                    if(clickMoveAdapter && arrayBitmap.isNotEmpty() && !openItemScale) { //проверка на то, сработал ли в адаптере onTouch и пустой ли масс. с битмапами
                        //idImViewMove - кубик который тянем
                        //idImViewMove2 - кубик который возращается на старое место
                        //Log.d("MyLog", "ACTION_MOVE clickMoveAdapter $clickMoveAdapter")
                        binding.layFragPlayPwpTwo.idImViewMove2.visibility = View.GONE
                        binding.layFragPlayPwpTwo.idImViewMove.elevation = 1.0f
                        binding.layFragPlayPwpTwo.idImViewMove.visibility = View.VISIBLE
                        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
                        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
                        //анимация увелечения кубика когда начали тянуть и он появился в руке
                        ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewMove, scaleX, scaleY).apply {
                            duration = 50
                            start()
                        }
                        //передаем координаты кубику в зависимости где стоит палец на экране, что бы он тянулся за пальцем
                        itemViewGlobal.visibility = View.GONE
                        binding.layFragPlayPwpTwo.idImViewMove.x = eventRc.x - imItemGlobal.width / 2f
                        binding.layFragPlayPwpTwo.idImViewMove.y = eventRc.y
                        //binding.idImViewMove2.visibility = View.GONE
                        //binding.idImViewMove2.x = eventRc.x - imItemGlobal.width / 2f
                        //binding.idImViewMove2.y = eventRc.y
                    }

                }
                MotionEvent.ACTION_UP -> {

                    if (clickMoveAdapter && arrayBitmap.isNotEmpty() && noClick.clickable && noClickBack.clickable  && !openItemScale) {
                        x2 = eventRc.x
                        y2 = eventRc.y
                        //Если позиция над которой отпустили кубик не равна 144 ???
                        if (MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc) != 60) {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                val rcViewX = binding.layFragPlayPwpTwo.idRcViewFragPWP.x
                                val rcViewY = binding.layFragPlayPwpTwo.idRcViewFragPWP.y
                                //массив с двумя координатами по x и y для кубика который тянем,
                                // куда будет становится кубик перед тем как начать уменьшаться
                                val coordinateImMove = MoveItemScaleTwo.movingItemScaleHard(MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc),
                                    binding.layFragPlayPwpTwo.idRcViewFragPWP.width, binding.layFragPlayPwpTwo.idRcViewFragPWP.height,
                                    binding.layFragPlayPwpTwo.idImViewMove.width, binding.layFragPlayPwpTwo.idImViewMove.height)
                                //массив с двумя координатами по x и y для кубика который возращается,
                                // куда будет становится кубик перед тем как начать уменьшаться
                                val coordinateImMove2 = MoveItemScaleTwo.movingItemScaleHard(MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc),
                                    binding.layFragPlayPwpTwo.idRcViewFragPWP.width, binding.layFragPlayPwpTwo.idRcViewFragPWP.height,
                                    binding.layFragPlayPwpTwo.idImViewMove2.width, binding.layFragPlayPwpTwo.idImViewMove2.height)
                                val coordinatePositionBackImMove2 = MoveItemScaleTwo.movingItemScaleHard(positionMove,
                                    binding.layFragPlayPwpTwo.idRcViewFragPWP.width, binding.layFragPlayPwpTwo.idRcViewFragPWP.height,
                                    binding.layFragPlayPwpTwo.idImViewMove2.width, binding.layFragPlayPwpTwo.idImViewMove2.height)
                                val coordinateBackImMove2 = listOf(coordinatePositionBackImMove2[0].toFloat()+rcViewX,
                                    coordinatePositionBackImMove2[1].toFloat()+rcViewY)
                                val coordinateBackStartImMove2 = listOf(coordinateImMove2[0].toFloat()+rcViewX,coordinateImMove2[1].toFloat()+rcViewY)//координаты стартовой точки для обратного полета ImMove2
                                adapterHard?.transferArrayAdapterToFrag(MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc),
                                        this, coordinateBackImMove2, coordinateBackStartImMove2, positionMove) // для возращения нового итема на старое место
                                //патч с координатами для анимции движения кубика на центр перед уменьшением
                                val path = Path().apply {
                                    moveTo(binding.layFragPlayPwpTwo.idImViewMove.x,binding.layFragPlayPwpTwo.idImViewMove.y)
                                    lineTo(coordinateImMove[0].toFloat()+rcViewX,coordinateImMove[1].toFloat()+rcViewY)
                                }
                                //анимация перемещения кубика которого тянули по координатам
                                ObjectAnimator.ofFloat(binding.layFragPlayPwpTwo.idImViewMove, View.X, View.Y, path).apply {
                                    doOnStart {
                                        adapterHard?.noMove?.noMoveIfOpenScale = false
                                        adapterHard?.click?.clickable = false
                                        noClick.clickable = false
                                    }
                                    duration = 200
                                    start()
                                    doOnEnd {
                                        adapterHard?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc),positionMove)
                                        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewMove.width)
                                        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewMove.width)
                                        //Когда кубик стал на место для уменьшения, начинается
                                        //анимация уменьшения кубика на свое место
                                        ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewMove, scaleX, scaleY).apply {
                                            duration = 220
                                            start()
                                            doOnEnd {
                                                binding.layFragPlayPwpTwo.idImViewMove.visibility = View.GONE
                                                arrayBitmap.clear()
                                                arrayNumber.clear()
                                                adapterHard?.noMove?.noMoveIfOpenScale = true
                                                adapterHard?.click?.clickable = true
                                                noClick.clickable = true
                                                clickMoveAdapter = false
                                                //mediaPlayerCubeFalling.start()
                                            }
                                        }
                                    }
                                }

                            }else{ // Без анимации, если версия андройд меньше заданой
                                adapterHard?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition,
                                        MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc), positionMove)
                                val coordinate = MoveItemScaleTwo.movingItemScaleHard(MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc),
                                    binding.layFragPlayPwpTwo.idRcViewFragPWP.width, binding.layFragPlayPwpTwo.idRcViewFragPWP.height,
                                    binding.layFragPlayPwpTwo.idImViewMove.width, binding.layFragPlayPwpTwo.idImViewMove.height)
                                val rcViewX = binding.layFragPlayPwpTwo.idRcViewFragPWP.x
                                val rcViewY = binding.layFragPlayPwpTwo.idRcViewFragPWP.y
                                val coordinateBack = listOf(coordinate[0].toFloat()+rcViewX, coordinate[1].toFloat()+rcViewY)
                                val coordinateBackStart = listOf(coordinate[0].toFloat()+rcViewX,
                                    coordinate[1].toFloat()+rcViewY)//координаты стартовой точки для обратного полета ImMove2
                                adapterHard?.transferArrayAdapterToFrag(MoveItemScaleTwo.moveItemRcHard(x2, y2, viewRc),
                                        this, coordinateBack, coordinateBackStart, positionMove)
                                binding.layFragPlayPwpTwo.idImViewMove.visibility = View.GONE
                                clickMoveAdapter = false

                                if (!openItemScale) {//Чистим только когда итемScale закрыт
                                    arrayBitmap.clear()
                                    arrayNumber.clear()
                                }
                            }

                        } else{
                            //Анимация если вытянули кубик за приделы поля
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                val rcViewX = binding.layFragPlayPwpTwo.idRcViewFragPWP.x
                                val rcViewY = binding.layFragPlayPwpTwo.idRcViewFragPWP.y
                                //массив с координатами x и y, куда возращать кубик (старое место)
                                val coordinate = MoveItemScaleTwo.movingItemScaleHard(positionMove, binding.layFragPlayPwpTwo.idRcViewFragPWP.width,
                                    binding.layFragPlayPwpTwo.idRcViewFragPWP.height,
                                    binding.layFragPlayPwpTwo.idImViewMove.width,
                                    binding.layFragPlayPwpTwo.idImViewMove.height)
                                //патч с координатами для анимации перемещения
                                val path = Path().apply {
                                    moveTo(binding.layFragPlayPwpTwo.idImViewMove.x, binding.layFragPlayPwpTwo.idImViewMove.y) //откуда
                                    lineTo(coordinate[0].toFloat()+rcViewX,coordinate[1].toFloat()+rcViewY) //куда
                                }
                                //анимация перемещения кубика на старое место
                                ObjectAnimator.ofFloat(binding.layFragPlayPwpTwo.idImViewMove, View.X, View.Y, path).apply {
                                    doOnStart {
                                        adapterHard?.noMove?.noMoveIfOpenScale = false
                                        adapterHard?.click?.clickable = false
                                        noClick.clickable = false
                                    }
                                duration = 300
                                start()
                                    doOnEnd {
                                        adapterHard?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, positionMove, Constans.NO_POSITION_MOVE)
                                        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewMove.width)
                                        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewMove.width)
                                        //анимация уменьшен кубика когда он прилетел на место
                                        ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewMove, scaleX, scaleY).apply {
                                            duration = 220
                                            start()
                                            doOnEnd {
                                                //mediaPlayerCubeFalling.isPlaying
                                                //mediaPlayerCubeFalling.start()
                                                binding.layFragPlayPwpTwo.idImViewMove.visibility = View.GONE
                                                arrayBitmap.clear()
                                                arrayNumber.clear()
                                                adapterHard?.noMove?.noMoveIfOpenScale = true
                                                adapterHard?.click?.clickable = true
                                                noClick.clickable = true
                                                clickMoveAdapter = false
                                            }
                                        }
                                    }
                                }
                            }else{
                                //если версия андройд меньше, пез анимации перемещения(вытянули за пределы)
                                binding.layFragPlayPwpTwo.idImViewMove.visibility = View.GONE
                                adapterHard?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, positionMove, Constans.NO_POSITION_MOVE)
                                clickMoveAdapter = false
                            }
                        }
                    }
                }
            }
            //передаем false для того что бы другие слушатели нажатий тоже срабатывали
            return@setOnTouchListener false
        }

        //Слушатель нажатия на увеличеный итем, если на кубике первая картинка
        binding.layFragPlayPwpTwo.idImViewScale.setOnTouchListener(){ v, event ->
            //Не давать нажимать пока идет анимация
            if(noClickItemScale){
                val minDistance = 15
                val minDistanceUpDown = 7

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                        x1 = event.x //Позиция по оси Х куда нажали
                        y1 = event.y //Позиция по оси Y куда нажали
                        noReplaySwipe = true
                    }
                    //Определеяем в каком направлении вращать кубик
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
                            //Запуск анимации уменьшения
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

        //Слушатель нажатия на увеличеный итем, если на кубике вторая картинка
        binding.layFragPlayPwpTwo.idImViewScale2.setOnTouchListener { v, event ->
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
                    //Определеяем в каком направлении вращать кубик
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
                            //Заупуск анимации уменьшения кубика
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

    //Метод перемешивания позиций и номеров перед началом игры
    fun shuffleArrayDataArrayPositionAndNumberBitmap(arraylist: ArrayList<dataArrayPositionAndNumberBitmap>):ArrayList<dataArrayPositionAndNumberBitmap>{
        //val shuffleTemp: ArrayList<dataArrayPositionAndNumberBitmap> = Arraylist.shuffle()
        arraylist.shuffle()
        return arraylist
    }

    //Метод перемешивая
    fun shuffleIntArray(num: ArrayList<Int>):ArrayList<Int>{
        val shuffleTemp: ArrayList<Int> = num.shuffled() as ArrayList<Int>
        //day.arrayBitmap.shuffle()
        return shuffleTemp
    }

    fun initHeaderDrawerLayout(){
        image1 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.id_im_game_1)
        image2 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.id_im_game_2)
        image3 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.id_im_game_3)
        image4 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.id_im_game_4)
        image5 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.id_im_game_5)
        image6 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.id_im_game_6)
        linLayImage1 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.lin_im_1)
        linLayImage2 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.lin_im_2)
        linLayImage3 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.lin_im_3)
        linLayImage4 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.lin_im_4)
        linLayImage5 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.lin_im_5)
        linLayImage6 = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.lin_im_6)
    }
    /*
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
    }*/

    //Инициализация менеджера и присваивания рцвью адаптера
    @SuppressLint("ClickableViewAccessibility")
    fun initRcView(){
        binding.layFragPlayPwpTwo.idRcViewFragPWP.layoutManager = CustomGridLayoutManagerHard(activity as AppCompatActivity)
        binding.layFragPlayPwpTwo.idRcViewFragPWP.adapter = adapterHard
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.layoutManager = CustomGridLayoutManagerHard(activity as AppCompatActivity)
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.layoutManager = CustomGridLayoutManagerMedium(activity as AppCompatActivity)
        //adapter?.setHasStableIds(true)
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.adapter = adapterHard
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.adapter = adapterMedium
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
        binding.layFragPlayPwpTwo.idImViewScale.elevation = 1f
        itemViewGlobal = itemView
        imItemGlobal = imItem
        positionClick = position
        binding.layFragPlayPwpTwo.idImViewMove2.visibility = View.GONE //Прячем картинку которая прилетает обратно и находится поднизом
        if (openItemScale){
            //запускается когда итем уже увеличен, и закрывает предыдущий итем и запускает новый
            animObjectMinus(positionClickOpen, b0,b1,b2,b3,b4,b5,n0,n1,n2,n3,n4,n5, p0,p1,p2,p3,p4,p5, positionClick, itemView)
        }else {
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
            //Log.d("MyLog", "Запустили просто scale итем")
            //запускается если ничего небыло открыто
            itemView.visibility = View.INVISIBLE
            positionClickOpen = position
            val withRc = binding.layFragPlayPwpTwo.idRcViewFragPWP.width
            val heightRc = binding.layFragPlayPwpTwo.idRcViewFragPWP.height
            val withIm = binding.layFragPlayPwpTwo.idImViewScale.width
            val heightIm = binding.layFragPlayPwpTwo.idImViewScale.height
            //Log.d("MyLog", "position $position")
            //меняем координаты появления scaleView
            var paramsScale = binding.layFragPlayPwpTwo.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
            var paramsScale2 = binding.layFragPlayPwpTwo.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
            val coordinates =
                MoveItemScaleTwo.movingItemScaleHard(position, withRc, heightRc, withIm, heightIm)
            paramsScale.leftMargin = coordinates[0]
            paramsScale.topMargin = coordinates[1]
            paramsScale2.leftMargin = coordinates[0]
            paramsScale2.topMargin = coordinates[1]
            binding.layFragPlayPwpTwo.idImViewScale.layoutParams = paramsScale
            binding.layFragPlayPwpTwo.idImViewScale2.layoutParams = paramsScale2

            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE

            binding.layFragPlayPwpTwo.idImViewScale.setImageBitmap(b0)
            animObjectPlus(position)
            openItemScale = true //открыт/увеличен scaleItem
            adapterHard?.noMove?.noMoveIfOpenScale = false
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

    //Если начинаем тянуть кубик
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
        //itemView.visibility = View.GONE
        binding.layFragPlayPwpTwo.idImViewMove.setImageBitmap(b0)
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
    }

    //Запускается когда нужно на место вернуть старый кубик
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
        //Проверяем, опускаем ли мы кубик на новое место
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
            binding.layFragPlayPwpTwo.idImViewMove2.visibility = View.VISIBLE
            //Возращаем к маленькому размеру возращающийся кубик
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
            //Анимация уменьшения
            ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewMove2, scaleX, scaleY).apply {
                duration = 10
                start()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.layFragPlayPwpTwo.idImViewMove2.setImageBitmap(b0)
                //Вводим координаты откуда и куда будет возвращаться кубик
                val path = Path().apply {
                    moveTo(coordinateBackStart[0], coordinateBackStart[1])
                    //Log.d("MyLog", "position $position")
                    when (positionMove) {
                        0 -> {
                            lineTo(
                                coordinate[0] + (binding.layFragPlayPwpTwo.idRcViewFragPWP.width / 6f - binding.layFragPlayPwpTwo.idImViewMove2.width) / 2f,
                                coordinate[1] + (binding.layFragPlayPwpTwo.idRcViewFragPWP.height / 10f - binding.layFragPlayPwpTwo.idImViewMove2.height) / 2
                            )
                        }
                        1, 2, 3, 4 -> {
                            lineTo(
                                coordinate[0],
                                coordinate[1] + (binding.layFragPlayPwpTwo.idRcViewFragPWP.height / 10f - binding.layFragPlayPwpTwo.idImViewMove2.height) / 2
                            )
                        }
                        5 -> {
                            lineTo(
                                coordinate[0] - (binding.layFragPlayPwpTwo.idRcViewFragPWP.width / 6f - binding.layFragPlayPwpTwo.idImViewMove2.width) / 2f,
                                coordinate[1] + (binding.layFragPlayPwpTwo.idRcViewFragPWP.height / 10f - binding.layFragPlayPwpTwo.idImViewMove2.height) / 2
                            )
                        }
                        6, 12, 18, 24, 30, 36, 42, 48 -> {
                            lineTo(
                                coordinate[0] + (binding.layFragPlayPwpTwo.idRcViewFragPWP.width / 6f - binding.layFragPlayPwpTwo.idImViewMove2.width) / 2f,
                                coordinate[1]
                            )
                        }
                        11, 17, 23, 29, 35, 41, 47, 53 -> {
                            lineTo(
                                coordinate[0] - (binding.layFragPlayPwpTwo.idRcViewFragPWP.width / 6f - binding.layFragPlayPwpTwo.idImViewMove2.width) / 2f,
                                coordinate[1]
                            )
                        }
                        54 -> {
                            lineTo(
                                coordinate[0] + (binding.layFragPlayPwpTwo.idRcViewFragPWP.width / 6f - binding.layFragPlayPwpTwo.idImViewMove2.width) / 2f,
                                coordinate[1] - (binding.layFragPlayPwpTwo.idRcViewFragPWP.height / 10f - binding.layFragPlayPwpTwo.idImViewMove2.height) / 2
                            )
                        }
                        55, 56, 57, 58 -> {
                            lineTo(
                                coordinate[0],
                                coordinate[1] - (binding.layFragPlayPwpTwo.idRcViewFragPWP.height / 10f - binding.layFragPlayPwpTwo.idImViewMove2.height) / 2
                            )
                        }
                        59 -> {
                            lineTo(
                                coordinate[0] - (binding.layFragPlayPwpTwo.idRcViewFragPWP.width / 6f - binding.layFragPlayPwpTwo.idImViewMove2.width) / 2f,
                                coordinate[1] - (binding.layFragPlayPwpTwo.idRcViewFragPWP.height / 10f - binding.layFragPlayPwpTwo.idImViewMove2.height) / 2
                            )
                        }
                        else -> {
                            lineTo(coordinate[0], coordinate[1])
                        }
                    }
                }
                //Анимация перемещения возврата кубика
                ObjectAnimator.ofFloat(binding.layFragPlayPwpTwo.idImViewMove2, View.X, View.Y, path).apply {
                    duration = 450
                    doOnStart {
                        adapterHard?.noMoveBack?.noMoveIfOpenScale = false
                        adapterHard?.clickBack?.clickable = false
                        noClickBack.clickable = false
                    }
                    start()
                    doOnEnd {
                        val scaleX = PropertyValuesHolder.ofFloat(
                            View.SCALE_X,
                            (binding.layFragPlayPwpTwo.idRcViewFragPWP.width / 6f) / binding.layFragPlayPwpTwo.idImViewMove2.width
                        )
                        val scaleY = PropertyValuesHolder.ofFloat(
                            View.SCALE_Y,
                            (binding.layFragPlayPwpTwo.idRcViewFragPWP.height / 10f) / binding.layFragPlayPwpTwo.idImViewMove2.height
                        )
                        //Анимация увелечения кубика который вернулся
                        ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewMove2, scaleX, scaleY)
                            .apply {
                                duration = 350
                                start()
                                doOnEnd {
                                    adapterHard?.updateAdapterPosition(arrayBitmapMoveReturn, arrayNumberMoveReturn, arrayPositionMoveReturn,
                                        positionMove, 999)
                                    adapterHard?.noMoveBack?.noMoveIfOpenScale = true
                                    adapterHard?.clickBack?.clickable = true
                                    noClickBack.clickable = true
                                    //binding.idImViewMove2.visibility = View.GONE
                                    //Log.d("MyLog", "coordinateBackImMove2 ${binding.idImViewMove2.y}")
                                }
                            }
                    }
                }
                //Если версия сдк меньше, без анимации возращения
            } else {
                adapterHard?.updateAdapterPosition(arrayBitmapMoveReturn, arrayNumberMoveReturn,
                        arrayPositionMoveReturn, positionMove, Constans.NO_POSITION_MOVE)
            }
        }
    }

    //После обновления позиции в адаптере происходит проверка собрана картинка, если да то сюда приходит позиция, какую картинку собрали
    override fun imageIsCollected(positionImageCollected: Int) {
        arrayCollectedImage[positionImageCollected] = 1
        collectedImageVisible(arrayCollectedImage)
        // Открыть драйвер лэйоут что бы показать какую картинку собрал
        binding.idDrawerLayout.openDrawer(GravityCompat.START)
        Log.d("MyLog", "Finish $positionImageCollected")
    }
    //Не используется
    override fun countPlus(count: Int) {
        //adapter?.countStart?.count?.plus(2)
    }

    //Если сделали короткий action_move и сработало только в адаптере
    override fun actionMoveAndActionUP(position: Int) {
        clickMoveAdapter = false
        adapterHard?.updateOnePosition(position)
        arrayBitmap.clear()
        arrayNumber.clear()
        arrayPosition.clear()
    }

    fun collectedImageVisible(arrayCollected: ArrayList<Int>){
        if (arrayCollected[0] == 1) linLayImage1.setBackgroundColor(activity!!.getColor(R.color.green_main)) else linLayImage1.setBackgroundColor(activity!!.getColor(R.color.white))
        if (arrayCollected[1] == 1) linLayImage2.setBackgroundColor(activity!!.getColor(R.color.green_main)) else linLayImage2.setBackgroundColor(activity!!.getColor(R.color.white))
        if (arrayCollected[2] == 1) linLayImage3.setBackgroundColor(activity!!.getColor(R.color.green_main)) else linLayImage3.setBackgroundColor(activity!!.getColor(R.color.white))
        if (arrayCollected[3] == 1) linLayImage4.setBackgroundColor(activity!!.getColor(R.color.green_main)) else linLayImage4.setBackgroundColor(activity!!.getColor(R.color.white))
        if (arrayCollected[4] == 1) linLayImage5.setBackgroundColor(activity!!.getColor(R.color.green_main)) else linLayImage5.setBackgroundColor(activity!!.getColor(R.color.white))
        if (arrayCollected[5] == 1) linLayImage6.setBackgroundColor(activity!!.getColor(R.color.green_main)) else linLayImage6.setBackgroundColor(activity!!.getColor(R.color.white))
    }

    //Метод анимации увелечения кубика если просто нажали
    fun animObjectPlus(position: Int){
        //Определяем координаты начала увелечения в зависимости от позиции на которую нажали
        when(position) {
            0 -> {
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = 0f
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = 0f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = 0f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = 0f
            }
            1,2,3,4 ->{
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = 0f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = 0f
            }
            5 -> {
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = 0f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = 0f
            }
            6, 12, 18, 24, 30, 36, 42, 48 ->{
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = 0f
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = 0f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
            }
            11, 17, 23, 29, 35, 41, 47, 53 ->{
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
            }
            54 -> {
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = 0f
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = 0f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
            }
            55, 56, 57, 58 ->{
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width) / 2f
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
            }
            59 ->{
                binding.layFragPlayPwpTwo.idImViewScale.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale2.pivotX = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
                binding.layFragPlayPwpTwo.idImViewScale2.pivotY = (binding.layFragPlayPwpTwo.idImViewScale.width.toFloat())
            }else -> {
            binding.layFragPlayPwpTwo.idImViewScale.pivotX = binding.layFragPlayPwpTwo.idImViewScale.width / 2f
            binding.layFragPlayPwpTwo.idImViewScale.pivotY = binding.layFragPlayPwpTwo.idImViewScale.width / 2f
            binding.layFragPlayPwpTwo.idImViewScale2.pivotX = binding.layFragPlayPwpTwo.idImViewScale.width / 2f
            binding.layFragPlayPwpTwo.idImViewScale2.pivotY = binding.layFragPlayPwpTwo.idImViewScale.width / 2f
            }
        }

        binding.layFragPlayPwpTwo.idImViewScale.scaleX = (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewScale.width
        binding.layFragPlayPwpTwo.idImViewScale.scaleY = (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewScale.width
        binding.layFragPlayPwpTwo.idImViewScale2.scaleX = (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewScale.width
        binding.layFragPlayPwpTwo.idImViewScale2.scaleY = (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewScale.width
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
        //val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f)
        //Анимация увелечения кубика для вращения
        ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewScale, scaleX, scaleY).apply {
            duration = 400
            doOnStart {
                //Начало анимации
                //touchHelper.attachToRecyclerView(null)
                adapterHard?.click?.clickable = false
                noClickItemScale = false
            }
            start()
           doOnEnd {
               //Log.d("MyLog", "end animation plus")
               adapterHard?.click?.clickable = true
               noClickItemScale = true
               //binding.idImViewScale.isClickable = false
               //binding.idImViewScale2.isClickable = false
           }
        }
    }

    //Метод уменьшения анимации
    fun animObjectMinus(positionClickOp: Int, b0 : Bitmap, b1 : Bitmap, b2 : Bitmap,
                        b3 : Bitmap, b4 : Bitmap, b5 : Bitmap, n0: Int , n1: Int, n2: Int, n3: Int, n4: Int, n5: Int,
                        p0: Int , p1: Int, p2: Int, p3: Int, p4: Int, p5: Int,
                        positionClick: Int, itemView: View){
        adapterHard?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, positionClickOp, Constans.NO_POSITION_MOVE)
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewScale.width)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.layFragPlayPwpTwo.idRcViewFragPWP.width/6f) / binding.layFragPlayPwpTwo.idImViewScale.width)
        //Определяем какую из двух картинок уменьшать, в зависимости от того какая картинка видна
        if (binding.layFragPlayPwpTwo.idImViewScale.visibility == View.VISIBLE) {
            //Анимация уменьшения кубика который крутили
                ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewScale, scaleX, scaleY).apply {
                    duration = 400
                    doOnStart {
                        //Log.d("MyLog", "start animation minus scale")
                        adapterHard?.click?.clickable = false
                        noClickItemScale = false
                    }
                    start()
                    doOnEnd {
                        //Log.d("MyLog", "end animation minus scale")
                        binding.layFragPlayPwpTwo.idImViewScale.visibility = View.GONE
                        adapterHard?.click?.clickable = true
                        //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                        //noClickItemScale = true
                        // Часть запускается если был не закрыт итем
                        if(openItemScale){
                            //Log.d("MyLog", "2end animation minus scale")
                            itemView.visibility = View.INVISIBLE
                            positionClickOpen = positionClick
                            val withRc = binding.layFragPlayPwpTwo.idRcViewFragPWP.width
                            val heightRc = binding.layFragPlayPwpTwo.idRcViewFragPWP.height
                            val withIm = binding.layFragPlayPwpTwo.idImViewScale.width
                            val heightIm = binding.layFragPlayPwpTwo.idImViewScale.height
                            //Log.d("MyLog", "position $position")
                            //меняем координаты появления scaleView
                            var paramsScale = binding.layFragPlayPwpTwo.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                            var paramsScale2 = binding.layFragPlayPwpTwo.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
                            val coordinates =
                                MoveItemScaleTwo.movingItemScaleHard(positionClick, withRc, heightRc, withIm, heightIm)
                            paramsScale.leftMargin = coordinates[0]
                            paramsScale.topMargin = coordinates[1]
                            paramsScale2.leftMargin = coordinates[0]
                            paramsScale2.topMargin = coordinates[1]
                            binding.layFragPlayPwpTwo.idImViewScale.layoutParams = paramsScale
                            binding.layFragPlayPwpTwo.idImViewScale2.layoutParams = paramsScale2

                            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
                            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE

                            binding.layFragPlayPwpTwo.idImViewScale.setImageBitmap(b0)
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
                            adapterHard?.noMove?.noMoveIfOpenScale = true
                        }
                    }
                }
        }else{
            //Анимация уменьшения второй картинки если она на кубике видна в данный мемент
                ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpTwo.idImViewScale2, scaleX, scaleY).apply {
                    duration = 400
                    doOnStart {
                        //Log.d("MyLog", "start animation minus scale2")
                        adapterHard?.click?.clickable = false
                        noClickItemScale = false
                    }
                    start()
                    doOnEnd {
                        //Log.d("MyLog", "end animation minus scale2")
                        binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE
                        adapterHard?.click?.clickable = true
                        //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                        //noClickItemScale = true
                        // Часть запускается если был не закрыт итем
                        if(openItemScale){
                            //Log.d("MyLog", "2end animation minus scale2")
                            itemView.visibility = View.INVISIBLE
                            positionClickOpen = positionClick
                            val withRc = binding.layFragPlayPwpTwo.idRcViewFragPWP.width
                            val heightRc = binding.layFragPlayPwpTwo.idRcViewFragPWP.height
                            val withIm = binding.layFragPlayPwpTwo.idImViewScale.width
                            val heightIm = binding.layFragPlayPwpTwo.idImViewScale.height
                            //Log.d("MyLog", "position $position")
                            //меняем координаты появления scaleView
                            var paramsScale = binding.layFragPlayPwpTwo.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                            var paramsScale2 = binding.layFragPlayPwpTwo.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
                            val coordinates =
                                MoveItemScaleTwo.movingItemScaleHard(positionClick, withRc, heightRc, withIm, heightIm)
                            paramsScale.leftMargin = coordinates[0]
                            paramsScale.topMargin = coordinates[1]
                            paramsScale2.leftMargin = coordinates[0]
                            paramsScale2.topMargin = coordinates[1]
                            binding.layFragPlayPwpTwo.idImViewScale.layoutParams = paramsScale
                            binding.layFragPlayPwpTwo.idImViewScale2.layoutParams = paramsScale2

                            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
                            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE

                            binding.layFragPlayPwpTwo.idImViewScale.setImageBitmap(b0)
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
                            adapterHard?.noMove?.noMoveIfOpenScale = true
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
        binding.layFragPlayPwpTwo.idImViewScale.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale.scaleY = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleY = 1.0f
        if(binding.layFragPlayPwpTwo.idImViewScale.visibility == View.VISIBLE) {
            binding.layFragPlayPwpTwo.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(1, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(1, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.GONE
        }else{
            binding.layFragPlayPwpTwo.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(1, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(1, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE
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
        binding.layFragPlayPwpTwo.idImViewScale.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale.scaleY = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleY = 1.0f
        if(binding.layFragPlayPwpTwo.idImViewScale.visibility == View.VISIBLE) {
            binding.layFragPlayPwpTwo.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(2, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(2, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.GONE
        }else{
            binding.layFragPlayPwpTwo.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(2, false, durationAnimationCubeSpeed ))
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(2, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE
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
        binding.layFragPlayPwpTwo.idImViewScale.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale.scaleY = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleY = 1.0f
        if(binding.layFragPlayPwpTwo.idImViewScale.visibility == View.VISIBLE) {
            binding.layFragPlayPwpTwo.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(4, false, durationAnimationCubeSpeed ))
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(4, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.GONE
        }else{
            binding.layFragPlayPwpTwo.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(4, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(4, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE
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
        binding.layFragPlayPwpTwo.idImViewScale.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale.scaleY = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleX = 1.0f
        binding.layFragPlayPwpTwo.idImViewScale2.scaleY = 1.0f
        if(binding.layFragPlayPwpTwo.idImViewScale.visibility == View.VISIBLE) {
            binding.layFragPlayPwpTwo.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(3, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(3, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.GONE
        }else{
            binding.layFragPlayPwpTwo.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpTwo.idImViewScale.visibility = View.VISIBLE
            binding.layFragPlayPwpTwo.idImViewScale2.startAnimation(CubeAnimation.create(3, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale.startAnimation(CubeAnimation.create(3, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpTwo.idImViewScale2.visibility = View.GONE
        }
    }
}