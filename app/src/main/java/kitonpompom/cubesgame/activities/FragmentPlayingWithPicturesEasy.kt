package kitonpompom.cubesgame.activities

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Path
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.activities.data.dataArrayPositionAndNumberBitmap
import kitonpompom.cubesgame.activities.data.dataPosNumBit
import kitonpompom.cubesgame.activities.dialogs.FinishCongratulationDialog
import kitonpompom.cubesgame.activities.dialogs.HelpScoreDialog
import kitonpompom.cubesgame.activities.dialogs.InterfaceFinishCongratulationDialog
import kitonpompom.cubesgame.activities.dialogs.InterfaceHelpScoreDialog
import kitonpompom.cubesgame.activities.dialogs.InterfaceYesNoDialog
import kitonpompom.cubesgame.activities.dialogs.ProgressDialog
import kitonpompom.cubesgame.activities.dialogs.YesNoDialog
import kitonpompom.cubesgame.activities.utils.*
import kitonpompom.cubesgame.databinding.DrawerLayoutPwpEasyBinding
import kotlinx.coroutines.*
import java.lang.Math.abs
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class FragmentPlayingWithPicturesEasy : Fragment(), AdapterFragPWPEasy.ClickScaleItemInterface, InterfaceYesNoDialog, InterfaceFinishCongratulationDialog, InterfaceHelpScoreDialog {

    lateinit var binding: DrawerLayoutPwpEasyBinding
    private val dataModel: DataModel by activityViewModels()
    private var job: Job? = null
    private val dialogYesNo = YesNoDialog(this)
    private val dialogHelpScore = HelpScoreDialog(this)
    private val dialogCongratulationDialog = FinishCongratulationDialog(this)
    lateinit var dialogYesNoAlert: AlertDialog
    private var finishCloseDriver = false
    private var timer: CountDownTimer? = null
    private var blur = true


    var mainArrayView = ArrayList<dataArrayBitmap>() //Массив из адаптера со всеми данными по картинкам на экране
    var newDataArrayBitmapFromDoctor = ArrayList<dataArrayBitmap>() //Массив из адаптера со всеми данными по картинкам на экране для доктора
    var arrayInterval = arrayOf(60000,120000,180000)
    //private val adapterHard: AdapterFragPWPHard? = AdapterFragPWPHard(this)
    //private val adapterMedium: AdapterFragPWPMedium? = AdapterFragPWPMedium(this)
    lateinit var adapterEasy: AdapterFragPWPEasy
    //private val swipeCallback = ItemTouchMoveAndSwipe(adapter!!)
    //private val touchHelper = ItemTouchHelper(swipeCallback)
    var arrayImageViewImage = ArrayList<ImageView>() //Массив с ImageView которые находятся в NavigationView
    lateinit var linLayImage1: CardView
    lateinit var linLayImage2: CardView
    lateinit var linLayImage3: CardView
    lateinit var linLayImage4: CardView
    lateinit var linLayImage5: CardView
    lateinit var linLayImage6: CardView
    var arrayBlurBitmap = ArrayList<Bitmap>() //Массив с заблюреными битмапами
    var arrayBitmap = ArrayList<Bitmap>() //Массив с незаблюреными битмапами
    //lateinit var linLayParent: LinearLayout

    var arraySlicedBitmap = ArrayList<Bitmap>()//Массив с картинками
    var arrayNumber = ArrayList<Int>()//Массив с номерами картинок на кубике
    var arrayPosition = ArrayList<Int>()//Массив с изначальными позициями картинок на кубике
    var arrayCollectedImage = ArrayList<Int>()//Массив с значениями картинок которые собрали(рамки), для отображения в драйвер лэоут
    var arrayBitmapMoveReturn = ArrayList<Bitmap>()//Массив с картинками
    var arrayNumberMoveReturn = ArrayList<Int>()//Массив с номерами на кубике
    var arrayPositionMoveReturn = ArrayList<Int>()//Массив с изначальными позициями картинок на кубике
    var positionClickOpen = 0
    var positionClick = 0// Записываем позицию на которую нажали
    var positionMove = 0// Записываем позицию которую потянули
    var openItemScale = false //true - Открыт\увеличен itemScale
    var stopClickAnimationHelp = false //Для блокировки нажатия пока идет анимация помощи поворота кубиков
    val durationAnimationCubeSpeed: Long = 300
    //var arrayInt = arrayOf(0,1,2,3,4,5)
    lateinit var itemViewGlobal: View //передаем сюда вью на который нажали, что бы была возможность использовать по всему классу
    lateinit var itemViewGlobalMove: View //педаем сюда вью которое начали тянуть, что бы была возможность использовать по всему классу
    lateinit var imItemGlobal: ImageView //педаем сюда имадж вью, что бы была возможность использовать по всему классу
    lateinit var mediaPlayerCubeFalling: MediaPlayer
    val noClick = ClickableState() //Объект для блокировки ActionUp в Move
    val noClickBack = ClickableStateBack() //Объект для блокировки ActionUp в Move для обратного движения
    //var imMoveHeight by Delegates.notNull<Int>()
    //var imMoveWidth by Delegates.notNull<Int>()
    var colorLine = true // Цвет линий
    var originalDataArrayBitmap = ArrayList<dataArrayBitmap>()//Не перемешеный массив со всеми значениями
    var shuffleDataArrayBitmap = ArrayList<dataArrayBitmap>()//Перемешеный массив со всеми значениями
    var score: Int = 13

    var optionDifficulty: Int? = null

    private var x1: Float = 0.0f
    private var x2: Float = 0.0f
    private var y1: Float = 0.0f
    private var y2: Float = 0.0f
    var noReplaySwipe = true
    var noClickItemScale = true
    var clickMoveAdapter = false //проверка - сработал ли мув в адаптере, если да, разрешать перетягивать
    val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DrawerLayoutPwpEasyBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView() //инициализируем адаптер и рцВью
        val displayMetrics = DisplayMetrics()
        binding.layFragPlayPwpEasy.idTvScore.text = score.toString()
        //getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        //val windowManager =
          //  BaseApplication.getApplication().getSystemService(Context.WINDOW_SERVICE)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val sizeFullScreenWith = binding.layFragPlayPwpEasy.idConstLayPwP.height
        initHeaderDrawerLayout(height) //инициализируем хидер в драйвер лайоуте
        mediaPlayerCubeFalling = MediaPlayer.create(activity as AppCompatActivity, R.raw.milnii_puzir2)


        //получаем картинки и пилим их на куски, потом перемешиваем и отправляем в адаптер
        dataModel.listBitmapForAdapterFragPWP.observe(activity as LifecycleOwner) {
            job = CoroutineScope(Dispatchers.Main).launch {
                //Включаем диалог с загрузкой перед началом игры
                delay(300L)

                val dialog = ProgressDialog.createProgressDialog(
                    activity as Activity,
                    Constans.FRAGMENT_PLAYING_WITH_PICTURE
                )
                //Создаем массивы с нарезаными картинками
                val arrayCroppedImage1 = (ImageManagerTwo.croppedImageEasy(it[0]))
                val arrayCroppedImage2 = (ImageManagerTwo.croppedImageEasy(it[1]))
                val arrayCroppedImage3 = (ImageManagerTwo.croppedImageEasy(it[2]))
                val arrayCroppedImage4 = (ImageManagerTwo.croppedImageEasy(it[3]))
                val arrayCroppedImage5 = (ImageManagerTwo.croppedImageEasy(it[4]))
                val arrayCroppedImage6 = (ImageManagerTwo.croppedImageEasy(it[5]))
                //Если при первом запуске массив arrayCollectedImage пустой, то заполняем нулями
                //А Если не пустой то заполняем резултаты из сохранений
                if (arrayCollectedImage.isEmpty()) {
                    for (i in 0..6) {
                        arrayCollectedImage.add(0)
                    }
                }



                //image1.setImageBitmap(blurredBitmap) // устанавливаем размытый Bitmap в ImageView с id "image1"

                //Если blur=true то в navigationView добавляем картинки заблюреные
                if(blur){
                    //Получаем List<Bitmap> с размытыми Bitmap
                    arrayBlurBitmap = ImageManagerTwo.blurBitmap(it) as ArrayList<Bitmap>

                    //Загружаем картинки в драйвер лайоут
                    for (i in 0 until arrayImageViewImage.size - 1){
                        arrayImageViewImage[i].setImageBitmap(arrayBlurBitmap[i])
                    }
                }else{
                    for (i in 0 until arrayImageViewImage.size - 1){
                        arrayImageViewImage[i].setImageBitmap(it[i])
                    }

                }


                //val bitmap = it[0]
                //val blurredBitmap = Blurry.with(activity as FragmentActivity).from(bitmap).radius(25).sampling(2).capture().get()

                //Blurry.with(activity as FragmentActivity).radius(25).sampling(2).onto(binding.root)
                //Blurry.with(activity as FragmentActivity).from(bitmap).into(image1)

                //Инициализируем и передаем битмапы в класс

                for (i in it){
                    arrayBitmap.add(i)
                }
                /*imageBitmap1 = it[0]
                imageBitmap2 = it[1]
                imageBitmap3 = it[2]
                imageBitmap4 = it[3]
                imageBitmap5 = it[4]
                imageBitmap6 = it[5]*/


                //Отрисовываем рамки в зависимости от значений массива arrayCollectedImage
                collectedImageVisible(arrayCollectedImage)

                //Cоздаем массивы
                val tempList = ArrayList<List<Bitmap>>()

                tempList.add(arrayCroppedImage1)
                tempList.add(arrayCroppedImage2)
                tempList.add(arrayCroppedImage3)
                tempList.add(arrayCroppedImage4)
                tempList.add(arrayCroppedImage5)
                tempList.add(arrayCroppedImage6)

                //Создаем массив originalDataArrayBitmap<dataArrayBitmap> с позициями, номерами,
                // картинками и значениями линий (не перемешанный)
                for(index in 0 until arrayCroppedImage1.size){
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
                    originalDataArrayBitmap.add(dataArrayBitmap(arrayPosition,arrayNumber,arrayBitmap,arrayLine))
                }

                //Log.d("MyLog", "arrayPositionAndNumber0 ${arrayShuffleArrayPositionAndNumberBitmap[0][0].arrayPosition}")


                var shuffleDataArray = shuffleTempListBitmap(originalDataArrayBitmap)
                //Копируем для доктора массив со всеми значениями
                var shuffleDataArrayCopy = copyShuffleArrayBitmap(shuffleDataArray)
                shuffleDataArrayBitmap.clear()
                shuffleDataArrayBitmap.addAll(shuffleDataArrayCopy)
                //adapterEasy?.updateAdapter(originalDataArrayBitmap)// не перемешеный
                adapterEasy.updateAdapter(shuffleDataArray) // перемешеный

                //Выключаем диалог после того как все загрузилось
                dialog.dismiss()
                //Открываем драйвер лэоут
                binding.layFragPlayPwpEasy.idTvScore.visibility = View.VISIBLE
                binding.layFragPlayPwpEasy.idBtOpenDrawer.visibility = View.VISIBLE
                //перемешиваем массив с интервалами что бы запустить кубик удачи
                arrayInterval.shuffle()
                //запускаем таймер отсчета через сколько появится кубик удачи
                startCountDownTimerLuckyTime(arrayInterval[0].toLong())
                //Log.d("MyLog", "Start timer ${arrayInterval[0]}, ${arrayInterval[1]}, ${arrayInterval[2]}")
                //binding.layFragPlayPwpEasy.idCardImViewScale.elevation = -1f
            }
        }

        //Кнопка отрытия driverLayout
        binding.layFragPlayPwpEasy.idBtOpenDrawer.setOnClickListener(){
            if(!stopClickAnimationHelp) {
                //Если увиличен кубик, то сразу его уменьшить, а потом открыть меню
                if (openItemScale) {
                    openItemScale = false
                    animObjectMinus(
                        positionClickOpen,
                        arraySlicedBitmap[0],
                        arraySlicedBitmap[1],
                        arraySlicedBitmap[2],
                        arraySlicedBitmap[3],
                        arraySlicedBitmap[4],
                        arraySlicedBitmap[5],
                        arrayNumber[0],
                        arrayNumber[1],
                        arrayNumber[2],
                        arrayNumber[3],
                        arrayNumber[4],
                        arrayNumber[5],
                        arrayPosition[0],
                        arrayPosition[1],
                        arrayPosition[2],
                        arrayPosition[3],
                        arrayPosition[4],
                        arrayPosition[5],
                        positionClick,
                        itemViewGlobal,
                        Constans.NO_HELPSCORESTART
                    )
                }

                //Открыть DriverLayout
                binding.idDrawerLayout.openDrawer(GravityCompat.START)
            }
            /*val fr =  binding.layFragPlayPwpEasy.idRcViewFragPWP.findViewHolderForAdapterPosition(0)
            val itemOne = fr?.itemView?.findViewById<ImageView>(R.id.id_item_play_with_pictures_one)
            val itemView = fr?.itemView
            val adapter = binding.layFragPlayPwpEasy.idRcViewFragPWP.adapter as AdapterFragPWPEasy
            if (itemView != null  && itemOne != null) {
                adapter.helpScore(0, itemView, itemOne, Constans.HELPSCORESTART)
            }*/

            //startDirectionRotationCube(7, 4, mainArrayView,
            //binding, adapterEasy)

            //FinishAnimationCongratulation.animationFinishStart(binding.layFragPlayPwpEasy.idRcViewFragPWP,
            //binding.layFragPlayPwpEasy.idViewFinishAnimation, dialogCongratulationDialog, activity as FragmentActivity, colorLine)

        }

        binding.layFragPlayPwpEasy2.imBtExit.setOnClickListener(){
            Log.d("MyLog", "Слушатель imBtExit")
        }

        binding.layFragPlayPwpEasy2.imBtSound.setOnClickListener(){
            Log.d("MyLog", "Слушатель imBtSound")
        }

        binding.layFragPlayPwpEasy2.imBtShuffle.setOnClickListener(){
            Log.d("MyLog", "Слушатель imBtShuffle")
            dialogYesNoAlert = dialogYesNo.createYesNoDialog(activity as Activity, Constans.BT_SHUFFLE)

        }

        binding.layFragPlayPwpEasy2.imBtColorLine.setOnClickListener(){
            if(colorLine){
            adapterEasy.updateWhiteBlackLine(colorLine)
                colorLine = false
            }else{
                adapterEasy.updateWhiteBlackLine(colorLine)
            colorLine = true
            }
            binding.layFragPlayPwpEasy.idRcViewFragPWP.adapter = adapterEasy
            binding.idDrawerLayout.closeDrawer(GravityCompat.START)
            Log.d("MyLog", "Слушатель imBtColorLine")
        }


        binding.layFragPlayPwpEasy2.imBtHelp.setOnClickListener(){
            //Запускаем функцию в адаптере, которая через интерфейс запускает функцию helpScore
            //который передает весь масиив со всеми данными ArrayList<dataArrayBitmap>()
            // на наш фрагмен где мы и запускаем диалог выбора helpScore
            var score = binding.layFragPlayPwpEasy.idTvScore.text.toString().toInt()
            adapterEasy.helpScoreToFrag(score, Constans.HELPSCORESTART)
            finishCloseDriver = false
            binding.idDrawerLayout.closeDrawer(GravityCompat.START)


            Log.d("MyLog", "Слушатель imBtHelp")
        }

        binding.layFragPlayPwpEasy.idTvScore.setOnClickListener(){
            dialogYesNo.createYesNoDialog(activity as Activity, Constans.BT_ADS)
            //startCountDownTimer(5000)
            Log.d("MyLog", "Слушатель idTvScore")

        }

        binding.layFragPlayPwpEasy.cardTvLucky1.setOnClickListener(){
            score += 3
            binding.layFragPlayPwpEasy.idTvScore.text = score.toString()
            FinishAnimationCongratulation.doAnimationScore(activity as FragmentActivity, binding.layFragPlayPwpEasy.idTvScore, colorLine)
            timer?.onFinish()
            //Log.d("MyLog", "Слушатель tvLucky1")
        }

        binding.layFragPlayPwpEasy.cardTvLucky.setOnClickListener(){
            score += 3
            binding.layFragPlayPwpEasy.idTvScore.text = score.toString()
            FinishAnimationCongratulation.doAnimationScore(activity as FragmentActivity, binding.layFragPlayPwpEasy.idTvScore, colorLine)
            timer?.onFinish()
            //Log.d("MyLog", "Слушатель tvLucky")
        }

        //Слушатели нажатия на картинки в driverNavigation для увиличения картинок
        setOnTouchListener(binding.layFragPlayPwpEasy2.imView1)
        setOnTouchListener(binding.layFragPlayPwpEasy2.imView2)
        setOnTouchListener(binding.layFragPlayPwpEasy2.imView3)
        setOnTouchListener(binding.layFragPlayPwpEasy2.imView4)
        setOnTouchListener(binding.layFragPlayPwpEasy2.imView5)
        setOnTouchListener(binding.layFragPlayPwpEasy2.imView6)

        //Слушатель действия на драйвер лэоут (Закрытие\открытие)
        binding.idDrawerLayout.addDrawerListener(object : DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {
                stopClickAnimationHelp = true// Заблокировать нажатия на фрагменте
                adapterEasy.noClickForAnimationHelpScore.noClickForAnimationHelpScore = false// Заблокировать нажатия на адаптере
                //При открытии DriverLayout Сбрасываем настройки блокировки для стандартных
                //что бы можно было закрыть обратно свайпом справа на лево
                binding.idDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
                adapterEasy.helpScoreToFrag(score, Constans.DOCTOR)//Запускаем интерфейс в адаптере для запуска доктора
            }
            override fun onDrawerClosed(drawerView: View) {
                if(finishCloseDriver){
                    finishCloseDriver = false
                    dialogYesNoAlert = dialogYesNo.createYesNoDialog(activity as Activity, Constans.BT_SHUFFLE)
                }
                stopClickAnimationHelp = false// Разблокировать нажатия на фрагменте
                adapterEasy.noClickForAnimationHelpScore.noClickForAnimationHelpScore = true// Разблокировать нажатия на адаптере
                //По закрытии DriverLayout выключаем возможность открыть меню свайпом с лева на право
                binding.idDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            override fun onDrawerStateChanged(newState: Int) {
                //Toast.makeText(activity, "state: $newState", Toast.LENGTH_SHORT).show()
            }
        })



        //слушатель нажатия на всю РцВью
        binding.layFragPlayPwpEasy.idRcViewFragPWP.setOnTouchListener(){ viewRc, eventRc ->
            //Здесь мы устанавливаем слушатель касаний для определенного представления (view).
            // Внутри слушателя мы проверяем количество пальцев, задействованных в касании,
            // и если оно больше 1, то возвращаем true, чтобы отменить обработку события.
            if (eventRc.pointerCount > 1) {
                return@setOnTouchListener true
            }
            //Log.d("MyLog", "Слушатель RCView")
            when (eventRc.action) {
                MotionEvent.ACTION_DOWN -> { //Срабатывает когда коснулись экрана
                    //x1 = eventRc.x //Позиция по оси Х куда нажали
                    //y1 = eventRgc.y //Позиция по оси Y куда нажали
                    //noReplaySwipe = true
                    //Log.d("MyLog", "event.x ${eventRc.x}")
                    //Log.d("MyLog", "event.y ${eventRc.y}")
                }

                MotionEvent.ACTION_MOVE -> {
                    if(!stopClickAnimationHelp) { //проверка на то идет ли анимация помощи поворота кубиков
                        if (clickMoveAdapter && arraySlicedBitmap.isNotEmpty() && !openItemScale) { //проверка на то, сработал ли в адаптере onTouch и пустой ли масс. с битмапами
                            //idImViewMove - кубик который тянем
                            //idImViewMove2 - кубик который возращается на старое место
                            //Log.d("MyLog", "ACTION_MOVE clickMoveAdapter $clickMoveAdapter")
                            binding.layFragPlayPwpEasy.idCardImViewMove2.visibility = View.GONE
                            binding.layFragPlayPwpEasy.idCardImViewMove.elevation = 50f
                            binding.layFragPlayPwpEasy.idCardImViewMove2.elevation = 5f
                            binding.layFragPlayPwpEasy.idCardImViewMove.visibility = View.VISIBLE
                            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
                            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
                            //анимация увелечения кубика когда начали тянуть и он появился в руке
                            ObjectAnimator.ofPropertyValuesHolder(
                                binding.layFragPlayPwpEasy.idCardImViewMove,
                                scaleX,
                                scaleY
                            ).apply {
                                duration = 50
                                start()
                            }
                            //Log.d("MyLog", "event.x ${binding.layFragPlayPwpEasy.idRcViewFragPWP.x}")
                            //Log.d("MyLog", "event.y ${binding.layFragPlayPwpEasy.idRcViewFragPWP.y}")
                            //передаем координаты кубику в зависимости где стоит палец на экране, что бы он тянулся за пальцем
                            itemViewGlobalMove.visibility = View.GONE
                            //binding.layFragPlayPwpEasy.idImViewMove.x =
                            //    eventRc.x - imItemGlobal.width / 2f
                            binding.layFragPlayPwpEasy.idCardImViewMove.x =
                                eventRc.x + binding.layFragPlayPwpEasy.idRcViewFragPWP.x - imItemGlobal.width / 2f
                            binding.layFragPlayPwpEasy.idCardImViewMove.y = eventRc.y + binding.layFragPlayPwpEasy.idRcViewFragPWP.y - imItemGlobal.height / 2f
                            //binding.idImViewMove2.visibility = View.GONE
                            //binding.idImViewMove2.x = eventRc.x - imItemGlobal.width / 2f
                            //binding.idImViewMove2.y = eventRc.y
                        }
                    }
                }
                MotionEvent.ACTION_UP -> {

                    if(!stopClickAnimationHelp) {
                        if (clickMoveAdapter && arraySlicedBitmap.isNotEmpty() && noClick.clickable && noClickBack.clickable && !openItemScale) {
                            x2 = eventRc.x
                            y2 = eventRc.y
                            //Если позиция над которой отпустили кубик не равна 15 ???
                            if (MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc) != 15) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    val rcViewX = binding.layFragPlayPwpEasy.idRcViewFragPWP.x
                                    val rcViewY = binding.layFragPlayPwpEasy.idRcViewFragPWP.y
                                    //массив с двумя координатами по x и y для кубика который тянем,
                                    // куда будет становится кубик перед тем как начать уменьшаться
                                    val coordinateImMove = MoveItemScaleTwo.movingItemScaleEasy(
                                        MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc),
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.width,
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.height,
                                        binding.layFragPlayPwpEasy.idCardImViewMove.width,
                                        binding.layFragPlayPwpEasy.idCardImViewMove.height
                                    )
                                    //массив с двумя координатами по x и y для кубика который возращается,
                                    // куда будет становится кубик перед тем как начать уменьшаться
                                    val coordinateImMove2 = MoveItemScaleTwo.movingItemScaleEasy(
                                        MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc),
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.width,
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.height,
                                        binding.layFragPlayPwpEasy.idCardImViewMove2.width,
                                        binding.layFragPlayPwpEasy.idCardImViewMove2.height
                                    )
                                    val coordinatePositionBackImMove2 =
                                        MoveItemScaleTwo.movingItemScaleEasy(
                                            positionMove,
                                            binding.layFragPlayPwpEasy.idRcViewFragPWP.width,
                                            binding.layFragPlayPwpEasy.idRcViewFragPWP.height,
                                            binding.layFragPlayPwpEasy.idCardImViewMove2.width,
                                            binding.layFragPlayPwpEasy.idCardImViewMove2.height
                                        )
                                    val coordinateBackImMove2 = listOf(
                                        coordinatePositionBackImMove2[0].toFloat() + rcViewX,
                                        coordinatePositionBackImMove2[1].toFloat() + rcViewY
                                    )
                                    val coordinateBackStartImMove2 = listOf(
                                        coordinateImMove2[0].toFloat() + rcViewX,
                                        coordinateImMove2[1].toFloat() + rcViewY
                                    )//координаты стартовой точки для обратного полета ImMove2
                                    adapterEasy?.transferArrayAdapterToFrag(
                                        MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc),
                                        this,
                                        coordinateBackImMove2,
                                        coordinateBackStartImMove2,
                                        positionMove
                                    ) // для возращения нового итема на старое место
                                    //патч с координатами для анимции движения кубика на центр перед уменьшением
                                    val path = Path().apply {
                                        moveTo(
                                            binding.layFragPlayPwpEasy.idCardImViewMove.x,
                                            binding.layFragPlayPwpEasy.idCardImViewMove.y
                                        )
                                        lineTo(
                                            coordinateImMove[0].toFloat() + rcViewX,
                                            coordinateImMove[1].toFloat() + rcViewY
                                        )
                                    }
                                    //анимация перемещения кубика которого тянули по координатам
                                    ObjectAnimator.ofFloat(
                                        binding.layFragPlayPwpEasy.idCardImViewMove,
                                        View.X,
                                        View.Y,
                                        path
                                    ).apply {
                                        doOnStart {
                                            adapterEasy?.noMove?.noMoveIfOpenScale = false
                                            adapterEasy?.click?.clickable = false
                                            noClick.clickable = false
                                        }
                                        duration = 200
                                        start()
                                        doOnEnd {
                                            adapterEasy?.updateAdapterPosition(
                                                arraySlicedBitmap,
                                                arrayNumber,
                                                arrayPosition,
                                                MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc),
                                                positionMove)
                                            val scaleX = PropertyValuesHolder.ofFloat(
                                                View.SCALE_X,
                                                (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f) /
                                                        binding.layFragPlayPwpEasy.idCardImViewMove.width)
                                            val scaleY = PropertyValuesHolder.ofFloat(
                                                View.SCALE_Y,
                                                (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f) /
                                                        binding.layFragPlayPwpEasy.idCardImViewMove.width)
                                            //Когда кубик стал на место для уменьшения, начинается
                                            //анимация уменьшения кубика на свое место
                                            ObjectAnimator.ofPropertyValuesHolder(
                                                binding.layFragPlayPwpEasy.idCardImViewMove,
                                                scaleX,
                                                scaleY
                                            ).apply {
                                                duration = 300
                                                start()
                                                doOnEnd {
                                                    binding.layFragPlayPwpEasy.idCardImViewMove.visibility =
                                                        View.GONE
                                                    arraySlicedBitmap.clear()
                                                    arrayNumber.clear()
                                                    adapterEasy?.noMove?.noMoveIfOpenScale = true
                                                    adapterEasy?.click?.clickable = true
                                                    noClick.clickable = true
                                                    clickMoveAdapter = false
                                                }
                                            }
                                        }
                                    }

                                } else { // Без анимации, если версия андройд меньше заданой
                                    adapterEasy?.updateAdapterPosition(
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc),
                                        positionMove
                                    )
                                    val coordinate = MoveItemScaleTwo.movingItemScaleEasy(
                                        MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc),
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.width,
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.height,
                                        binding.layFragPlayPwpEasy.idCardImViewMove.width,
                                        binding.layFragPlayPwpEasy.idCardImViewMove.height
                                    )
                                    val rcViewX = binding.layFragPlayPwpEasy.idRcViewFragPWP.x
                                    val rcViewY = binding.layFragPlayPwpEasy.idRcViewFragPWP.y
                                    val coordinateBack = listOf(
                                        coordinate[0].toFloat() + rcViewX,
                                        coordinate[1].toFloat() + rcViewY
                                    )
                                    val coordinateBackStart = listOf(
                                        coordinate[0].toFloat() + rcViewX,
                                        coordinate[1].toFloat() + rcViewY
                                    )//координаты стартовой точки для обратного полета ImMove2
                                    adapterEasy?.transferArrayAdapterToFrag(
                                        MoveItemScaleTwo.moveItemRcEasy(x2, y2, viewRc),
                                        this, coordinateBack, coordinateBackStart, positionMove
                                    )
                                    binding.layFragPlayPwpEasy.idCardImViewMove.visibility = View.GONE
                                    clickMoveAdapter = false

                                    if (!openItemScale) {//Чистим только когда итемScale закрыт
                                        arraySlicedBitmap.clear()
                                        arrayNumber.clear()
                                    }
                                }

                            } else {
                                //Анимация если вытянули кубик за приделы поля
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    val rcViewX = binding.layFragPlayPwpEasy.idRcViewFragPWP.x
                                    val rcViewY = binding.layFragPlayPwpEasy.idRcViewFragPWP.y
                                    //массив с координатами x и y, куда возращать кубик (старое место)
                                    val coordinate = MoveItemScaleTwo.movingItemScaleEasy(
                                        positionMove,
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.width,
                                        binding.layFragPlayPwpEasy.idRcViewFragPWP.height,
                                        binding.layFragPlayPwpEasy.idCardImViewMove.width,
                                        binding.layFragPlayPwpEasy.idCardImViewMove.height
                                    )
                                    //патч с координатами для анимации перемещения
                                    val path = Path().apply {
                                        moveTo(
                                            binding.layFragPlayPwpEasy.idCardImViewMove.x,
                                            binding.layFragPlayPwpEasy.idCardImViewMove.y
                                        ) //откуда
                                        lineTo(
                                            coordinate[0].toFloat() + rcViewX,
                                            coordinate[1].toFloat() + rcViewY
                                        ) //куда
                                    }
                                    //анимация перемещения кубика на старое место
                                    ObjectAnimator.ofFloat(
                                        binding.layFragPlayPwpEasy.idCardImViewMove,
                                        View.X,
                                        View.Y,
                                        path
                                    ).apply {
                                        doOnStart {
                                            adapterEasy?.noMove?.noMoveIfOpenScale = false
                                            adapterEasy?.click?.clickable = false
                                            noClick.clickable = false
                                        }
                                        duration = 300
                                        start()
                                        doOnEnd {
                                            adapterEasy?.updateAdapterPosition(
                                                arraySlicedBitmap,
                                                arrayNumber,
                                                arrayPosition,
                                                positionMove,
                                                Constans.NO_POSITION_MOVE)
                                            val scaleX = PropertyValuesHolder.ofFloat(
                                                View.SCALE_X,
                                                (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f) /
                                                        binding.layFragPlayPwpEasy.idCardImViewMove.width)
                                            val scaleY = PropertyValuesHolder.ofFloat(
                                                View.SCALE_Y,
                                                (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f) /
                                                        binding.layFragPlayPwpEasy.idCardImViewMove.width)
                                            //анимация уменьшен кубика когда он прилетел на место
                                            ObjectAnimator.ofPropertyValuesHolder(
                                                binding.layFragPlayPwpEasy.idCardImViewMove,
                                                scaleX,
                                                scaleY
                                            ).apply {
                                                duration = 300
                                                start()
                                                doOnEnd {
                                                    binding.layFragPlayPwpEasy.idCardImViewMove.visibility =
                                                        View.GONE
                                                    arraySlicedBitmap.clear()
                                                    arrayNumber.clear()
                                                    adapterEasy?.noMove?.noMoveIfOpenScale = true
                                                    adapterEasy?.click?.clickable = true
                                                    noClick.clickable = true
                                                    clickMoveAdapter = false
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    //если версия андройд меньше, без анимации перемещения(вытянули за пределы)
                                    binding.layFragPlayPwpEasy.idCardImViewMove.visibility = View.GONE
                                    adapterEasy?.updateAdapterPosition(
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        positionMove,
                                        Constans.NO_POSITION_MOVE)
                                    clickMoveAdapter = false
                                }
                            }
                        }
                    }
                }
            }
            //передаем false для того что бы другие слушатели нажатий тоже срабатывали
            return@setOnTouchListener false
        }

        //Слушатель нажатия на увеличеный итем, если на кубике первая картинка
        binding.layFragPlayPwpEasy.idImViewScale.setOnTouchListener(){ v, event ->
            if(!stopClickAnimationHelp) {
                //Не давать нажимать пока идет анимация
                if (noClickItemScale) {
                    //Log.d("MyLog", "Слушатель ViewScale")
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
                            if (abs(deltaX) > minDistance && noReplaySwipe) {
                                if (x2 > x1) {
                                    DirectionRotationCubeManager.right(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                } else {
                                    DirectionRotationCubeManager.left(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                }
                            }
                            if (abs(deltaY) > minDistanceUpDown && noReplaySwipe) {
                                if (y2 > y1) {
                                    DirectionRotationCubeManager.down(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                } else {
                                    DirectionRotationCubeManager.up(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                }
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            x2 = event.x
                            y2 = event.y
                            var deltaX: Float = x2 - x1
                            var deltaY: Float = y2 - y1
                            if (abs(deltaX) < minDistance && abs(deltaY) < minDistanceUpDown) {
                                openItemScale = false
                                //Запуск анимации уменьшения
                                animObjectMinus(
                                    positionClickOpen,
                                    arraySlicedBitmap[0],
                                    arraySlicedBitmap[1],
                                    arraySlicedBitmap[2],
                                    arraySlicedBitmap[3],
                                    arraySlicedBitmap[4],
                                    arraySlicedBitmap[5],
                                    arrayNumber[0],
                                    arrayNumber[1],
                                    arrayNumber[2],
                                    arrayNumber[3],
                                    arrayNumber[4],
                                    arrayNumber[5],
                                    arrayPosition[0],
                                    arrayPosition[1],
                                    arrayPosition[2],
                                    arrayPosition[3],
                                    arrayPosition[4],
                                    arrayPosition[5],
                                    positionClick,
                                    itemViewGlobal,
                                    Constans.NO_HELPSCORESTART
                                )
                            }
                        }
                    }
                }
            }
            return@setOnTouchListener true
        }

        //Слушатель нажатия на увеличеный итем, если на кубике вторая картинка
        binding.layFragPlayPwpEasy.idImViewScale2.setOnTouchListener { v, event ->
            if(!stopClickAnimationHelp) {
                //Проверка, что бы нельзя нажать во время анимации
                if (noClickItemScale) {
                    //Log.d("MyLog", "Слушатель ViewScale2")
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
                            if (abs(deltaX) > minDistance && noReplaySwipe) {
                                if (x2 > x1) {
                                    DirectionRotationCubeManager.right(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                } else {
                                    DirectionRotationCubeManager.left(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                }
                            }
                            if (abs(deltaY) > minDistanceUpDown && noReplaySwipe) {
                                if (y2 > y1) {
                                    DirectionRotationCubeManager.down(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                } else {
                                    DirectionRotationCubeManager.up(
                                        0,
                                        arraySlicedBitmap,
                                        arrayNumber,
                                        arrayPosition,
                                        binding.layFragPlayPwpEasy.idImViewScale,
                                        binding.layFragPlayPwpEasy.idImViewScale2,
                                        binding.layFragPlayPwpEasy.idCardImViewScale,
                                        binding.layFragPlayPwpEasy.idCardImViewScale2,
                                        durationAnimationCubeSpeed,
                                        Constans.NO_HELPSCORESTART,
                                        adapterEasy,
                                        binding,
                                        999,
                                        999,
                                        999
                                    )
                                    noReplaySwipe = false
                                }
                            }

                        }

                        MotionEvent.ACTION_UP -> {
                            x2 = event.x
                            y2 = event.y
                            var deltaX: Float = x2 - x1
                            var deltaY: Float = y2 - y1
                            if (abs(deltaX) < minDistance && abs(deltaY) < minDistanceUpDown) {
                                openItemScale = false
                                //Запуск анимации уменьшения кубика
                                animObjectMinus(
                                    positionClickOpen,
                                    arraySlicedBitmap[0],
                                    arraySlicedBitmap[1],
                                    arraySlicedBitmap[2],
                                    arraySlicedBitmap[3],
                                    arraySlicedBitmap[4],
                                    arraySlicedBitmap[5],
                                    arrayNumber[0],
                                    arrayNumber[1],
                                    arrayNumber[2],
                                    arrayNumber[3],
                                    arrayNumber[4],
                                    arrayNumber[5],
                                    arrayPosition[0],
                                    arrayPosition[1],
                                    arrayPosition[2],
                                    arrayPosition[3],
                                    arrayPosition[4],
                                    arrayPosition[5],
                                    positionClick,
                                    itemViewGlobal,
                                    Constans.NO_HELPSCORESTART
                                )
                            }
                        }
                    }
                }
            }
            return@setOnTouchListener true
        }
    }

    //private fun blurBitmap(bitmap: Bitmap, radius: Float): Bitmap? {
    //    var blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
    //    blurredBitmap = StackBlur.blurNativelyPixels(blurredBitmap, radius.toInt(), true)
    //    return blurredBitmap
    //}

    //Метод копирования массива для доктора
    fun copyShuffleArrayBitmap(arrayList: ArrayList<dataArrayBitmap>) : ArrayList<dataArrayBitmap>{
        val shuffleDataArrayCopy = ArrayList<dataArrayBitmap>()
        for (item in arrayList) {
            val copyItem = item.copy(
                arrayNumber = ArrayList(item.arrayNumber),
                arrayBitmap = ArrayList(item.arrayBitmap),
                arrayPosition = ArrayList(item.arrayPosition)
            )
            shuffleDataArrayCopy.add(copyItem)
        }
        return shuffleDataArrayCopy
    }

    fun setOnTouchListener(imageView: ImageView) {
        imageView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val image = (imageView.drawable as BitmapDrawable).bitmap
                    binding.layFragPlayPwpEasy2.imDemonstrImage.setImageBitmap(image)
                    binding.layFragPlayPwpEasy2.imDemonstrImage.visibility = View.VISIBLE
                }
                MotionEvent.ACTION_UP -> {
                    binding.layFragPlayPwpEasy2.imDemonstrImage.visibility = View.GONE
                }
            }
            true
        }
    }

    //Перемешать массив основной массив ArrayList<dataArrayBitmap>
    fun shuffleTempListBitmap(tempListBitmap:ArrayList<dataArrayBitmap>):ArrayList<dataArrayBitmap>{
        val tempList = ArrayList<List<Bitmap>>()
        //var tempListBitmap = ArrayList<dataArrayBitmap>()
        var tempListBitmapFinish = ArrayList<dataArrayBitmap>()
        var arrayPositionAndNumber0 = ArrayList<dataArrayPositionAndNumberBitmap>()
        val arrayPositionAndNumber1 = ArrayList<dataArrayPositionAndNumberBitmap>()
        val arrayPositionAndNumber2 = ArrayList<dataArrayPositionAndNumberBitmap>()
        val arrayPositionAndNumber3 = ArrayList<dataArrayPositionAndNumberBitmap>()
        val arrayPositionAndNumber4 = ArrayList<dataArrayPositionAndNumberBitmap>()
        val arrayPositionAndNumber5 = ArrayList<dataArrayPositionAndNumberBitmap>()

        for (index in tempListBitmap) {
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

        for(index in 0 until tempListBitmap.size) {
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
            tempListBitmapFinish.add(
                dataArrayBitmap(
                    arrayPositionTemp,
                    arrayNumberTemp,
                    arrayBitmapTemp,
                    arrayLine
                )
            )
        }
        return tempListBitmapFinish
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

    fun initHeaderDrawerLayout(sizeHight:Int){
        //linLayParent = binding.headerForDrawerPwp.getHeaderView(0).findViewById(R.id.id_linear_parent)
        //linLayParent.layoutParams.height = 2340
        //linLayParent.requestLayout()
        //Log.d("MyLog", "sizeHight ${linLayParent.layoutParams.height}")

            //.load()


        arrayImageViewImage.add(binding.layFragPlayPwpEasy2.imView1)
        arrayImageViewImage.add(binding.layFragPlayPwpEasy2.imView2)
        arrayImageViewImage.add(binding.layFragPlayPwpEasy2.imView3)
        arrayImageViewImage.add(binding.layFragPlayPwpEasy2.imView4)
        arrayImageViewImage.add(binding.layFragPlayPwpEasy2.imView5)
        arrayImageViewImage.add(binding.layFragPlayPwpEasy2.imView6)

        linLayImage1 = binding.layFragPlayPwpEasy2.linLey1
        linLayImage2 = binding.layFragPlayPwpEasy2.linLey2
        linLayImage3 = binding.layFragPlayPwpEasy2.linLey3
        linLayImage4 = binding.layFragPlayPwpEasy2.linLey4
        linLayImage5 = binding.layFragPlayPwpEasy2.linLey5
        linLayImage6 = binding.layFragPlayPwpEasy2.linLey6
    }


    //Инициализация менеджера и присваивания рцвью адаптера
    @SuppressLint("ClickableViewAccessibility")
    fun initRcView(){

        adapterEasy = AdapterFragPWPEasy(this, activity as FragmentActivity)
        binding.layFragPlayPwpEasy.idRcViewFragPWP.layoutManager = CustomGridLayoutManagerEasy(activity as AppCompatActivity)
        binding.layFragPlayPwpEasy.idRcViewFragPWP.adapter = adapterEasy
        //Блокируем возможность открывать DriverLayout свайпом слева на право
        binding.idDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.layoutManager = CustomGridLayoutManagerHard(activity as AppCompatActivity)
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.layoutManager = CustomGridLayoutManagerMedium(activity as AppCompatActivity)
        //adapter?.setHasStableIds(true)
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.adapter = adapterHard
        //binding.layFragPlayPwpTwo.idRcViewFragPWP.adapter = adapterMedium
    }





    @RequiresApi(Build.VERSION_CODES.M)
    fun collectedImageVisible(arrayCollected: ArrayList<Int>){
        if (arrayCollected[0] == 1) linLayImage1.setCardBackgroundColor(requireActivity().getColor(R.color.green_main)) else linLayImage1.setCardBackgroundColor(requireActivity().getColor(R.color.grey))
        if (arrayCollected[1] == 1) linLayImage2.setCardBackgroundColor(requireActivity().getColor(R.color.green_main)) else linLayImage2.setCardBackgroundColor(requireActivity().getColor(R.color.grey))
        if (arrayCollected[2] == 1) linLayImage3.setCardBackgroundColor(requireActivity().getColor(R.color.green_main)) else linLayImage3.setCardBackgroundColor(requireActivity().getColor(R.color.grey))
        if (arrayCollected[3] == 1) linLayImage4.setCardBackgroundColor(requireActivity().getColor(R.color.green_main)) else linLayImage4.setCardBackgroundColor(requireActivity().getColor(R.color.grey))
        if (arrayCollected[4] == 1) linLayImage5.setCardBackgroundColor(requireActivity().getColor(R.color.green_main)) else linLayImage5.setCardBackgroundColor(requireActivity().getColor(R.color.grey))
        if (arrayCollected[5] == 1) linLayImage6.setCardBackgroundColor(requireActivity().getColor(R.color.green_main)) else linLayImage6.setCardBackgroundColor(requireActivity().getColor(R.color.grey))

        if(blur) {

            for(i in 0 until arrayCollected.size - 1){
                //Log.d("MyLog", "i $i")
                if (arrayCollected[i] == 1) arrayImageViewImage[i].setImageBitmap(arrayBitmap[i]) else arrayImageViewImage[i].setImageBitmap(arrayBlurBitmap[i])
            }

            /*if (arrayCollected[0] == 1) image1.setImageBitmap(arrayBitmap[0]) else image1.setImageBitmap(arrayBlurBitmap[0])
            if (arrayCollected[1] == 1) image2.setImageBitmap(arrayBitmap[1]) else image2.setImageBitmap(arrayBlurBitmap[1])
            if (arrayCollected[2] == 1) image3.setImageBitmap(arrayBitmap[2]) else image3.setImageBitmap(arrayBlurBitmap[2])
            if (arrayCollected[3] == 1) image4.setImageBitmap(arrayBitmap[3]) else image4.setImageBitmap(arrayBlurBitmap[3])
            if (arrayCollected[4] == 1) image5.setImageBitmap(arrayBitmap[4]) else image5.setImageBitmap(arrayBlurBitmap[4])
            if (arrayCollected[5] == 1) image6.setImageBitmap(arrayBitmap[5]) else image6.setImageBitmap(arrayBlurBitmap[5])*/
        }
    }

    private fun startCountDownTimer(timeMillis: Long){
        timer?.cancel()
        binding.layFragPlayPwpEasy.cardTvLucky1.visibility = View.VISIBLE
        binding.layFragPlayPwpEasy.tvLucky1.text = "5"
        timer = object : CountDownTimer(timeMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                if(binding.layFragPlayPwpEasy.cardTvLucky1.visibility == View.VISIBLE){
                    binding.layFragPlayPwpEasy.tvLucky.text = (millisUntilFinished / 1000).toDouble()
                        .roundToInt().toString()
                    binding.layFragPlayPwpEasy.cardTvLucky1.startAnimation(
                        CubeAnimation.create(
                            CubeAnimation.LEFT, false, 800
                        )
                    )
                    binding.layFragPlayPwpEasy.cardTvLucky.visibility = View.VISIBLE
                    binding.layFragPlayPwpEasy.cardTvLucky.startAnimation(
                        CubeAnimation.create(
                            CubeAnimation.LEFT, true, 800
                        )
                    )
                    binding.layFragPlayPwpEasy.cardTvLucky1.visibility = View.GONE
                //Log.d("MyLog", "millisUnFin : $millisUntilFinished")
                }else{
                    binding.layFragPlayPwpEasy.tvLucky1.text = (millisUntilFinished / 1000).toDouble()
                        .roundToInt().toString()
                    binding.layFragPlayPwpEasy.cardTvLucky.startAnimation(
                        CubeAnimation.create(
                            CubeAnimation.LEFT, false, 800
                        )
                    )
                    binding.layFragPlayPwpEasy.cardTvLucky1.visibility = View.VISIBLE
                    binding.layFragPlayPwpEasy.cardTvLucky1.startAnimation(
                        CubeAnimation.create(
                            CubeAnimation.LEFT, true, 800
                        )
                    )
                    binding.layFragPlayPwpEasy.cardTvLucky.visibility = View.GONE
                }
            }

            override fun onFinish() {
                timer?.cancel()
                if(binding.layFragPlayPwpEasy.cardTvLucky.visibility == View.VISIBLE) binding.layFragPlayPwpEasy.cardTvLucky.visibility = View.GONE
                else binding.layFragPlayPwpEasy.cardTvLucky1.visibility = View.GONE
                arrayInterval.shuffle()
                startCountDownTimerLuckyTime(arrayInterval[0].toLong())
                //
            }

        }.start()
    }

    private fun startCountDownTimerLuckyTime(timeMillis: Long){
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                //Log.d("MyLog", "timer Lucky: ${(millisUntilFinished / 1000).toDouble()
                //    .roundToInt()}")
            }

            override fun onFinish() {
                timer?.cancel()
                startCountDownTimer(5000)
                //Log.d("MyLog", "Finish timer Lucky")
            }

        }.start()
    }

    //Метод анимации увелечения кубика если просто нажали
    fun animObjectPlus(position: Int, directionRotation: Int, selectImage: Int,
                       openImage: Int, countCubeRotated: Int){

        //Определяем координаты начала увелечения в зависимости от позиции на которую нажали
        when(position) {
            0 -> {
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = 0f
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = 0f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = 0f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = 0f
            }
            1 ->{
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = 0f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = 0f
            }
            2 -> {
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = 0f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = 0f

            }
            3, 6, 9 ->{
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = 0f
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = 0f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
            }
            5, 8, 11 ->{
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
            }
            12 -> {
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = 0f
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = 0f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = 0f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
            }
            13 ->{
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width) / 2f
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
            }
            14 ->{
                //binding.layFragPlayPwpEasy.idImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
                binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = (binding.layFragPlayPwpEasy.idImViewScale.width.toFloat())
            }else -> {
            //binding.layFragPlayPwpEasy.idImViewScale.pivotX = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
            //binding.layFragPlayPwpEasy.idImViewScale.pivotY = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
            //binding.layFragPlayPwpEasy.idImViewScale2.pivotX = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
            //binding.layFragPlayPwpEasy.idImViewScale2.pivotY = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
            binding.layFragPlayPwpEasy.idCardImViewScale.pivotX = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
            binding.layFragPlayPwpEasy.idCardImViewScale.pivotY = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
            binding.layFragPlayPwpEasy.idCardImViewScale2.pivotX = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
            binding.layFragPlayPwpEasy.idCardImViewScale2.pivotY = binding.layFragPlayPwpEasy.idImViewScale.width / 2f
        }
        }
        // установка масштаба для изображения перед увилечением
        //binding.layFragPlayPwpEasy.idImViewScale.scaleX = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width
        //binding.layFragPlayPwpEasy.idImViewScale.scaleY = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width
        //binding.layFragPlayPwpEasy.idImViewScale2.scaleX = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width
        //binding.layFragPlayPwpEasy.idImViewScale2.scaleY = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width
        binding.layFragPlayPwpEasy.idCardImViewScale.scaleX = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idCardImViewScale.width
        binding.layFragPlayPwpEasy.idCardImViewScale.scaleY = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idCardImViewScale.width
        binding.layFragPlayPwpEasy.idCardImViewScale2.scaleX = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width
        binding.layFragPlayPwpEasy.idCardImViewScale2.scaleY = (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width

        // На сколько увеличиваем изоброжание относительно первоночальных заданых значений
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
        //Анимация увелечения кубика для вращения
        //ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idImViewScale, scaleX, scaleY).apply {
        ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idCardImViewScale, scaleX, scaleY).apply {
            duration = 400
            doOnStart {

                //Начало анимации
                //touchHelper.attachToRecyclerView(null)
                openItemScale = true //открыт/увеличен scaleItem
                adapterEasy?.click?.clickable = false //для блокировки нажатия на итем в адаптере пока запущена анимация
                noClickItemScale = false //для блокировки в фрагменте нажатия на увеличевующийся итем
                adapterEasy?.noMove?.noMoveIfOpenScale = false //для блокировки в адаптере возможности срабатывания ОнТач, блокируется из фрагмета с рцвью когда картинка увеличена
            }
            start()
            doOnEnd {
                //Log.d("MyLog", "end animation plus")
                adapterEasy?.click?.clickable = true
                noClickItemScale = true//Отключить блакировку нажатия на увеличивающийся итем после окончания анимации увелечения

                //Если в numberRotation пришел номер положения для вращения кубика, то исполнить. А если
                //заглушка Constans.NO_HELPSCORESTART, то не выполнять
                if (Constans.NO_HELPSCORESTART != directionRotation){
                    DirectionRotationCubeManager.rotationHelpScore(directionRotation, position, arraySlicedBitmap, arrayNumber, arrayPosition,
                        binding.layFragPlayPwpEasy.idImViewScale, binding.layFragPlayPwpEasy.idImViewScale2,
                        binding.layFragPlayPwpEasy.idCardImViewScale, binding.layFragPlayPwpEasy.idCardImViewScale2,
                        durationAnimationCubeSpeed, adapterEasy, binding, selectImage,
                        openImage, countCubeRotated)
                    openItemScale = false
                }
                //binding.idImViewScale.isClickable = false
                //binding.idImViewScale2.isClickable = false
            }
        }
    }

    //Метод уменьшения анимации
    fun animObjectMinus(positionClickOp: Int, b0 : Bitmap, b1 : Bitmap, b2 : Bitmap,
                        b3 : Bitmap, b4 : Bitmap, b5 : Bitmap, n0: Int, n1: Int, n2: Int, n3: Int, n4: Int, n5: Int,
                        p0: Int, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int,
                        positionClick: Int, itemViewGl: View, constHelpScore: Int){
        adapterEasy?.updateAdapterPosition(arraySlicedBitmap, arrayNumber, arrayPosition, positionClickOp, Constans.NO_POSITION_MOVE)
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width)


        //Определяем какую из двух картинок уменьшать, в зависимости от того какая картинка видна
        //if (binding.layFragPlayPwpEasy.idImViewScale.visibility == View.VISIBLE) {
        if (binding.layFragPlayPwpEasy.idCardImViewScale.visibility == View.VISIBLE) {
            //Анимация уменьшения кубика который крутили
            //ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idImViewScale, scaleX, scaleY).apply {
            ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idCardImViewScale, scaleX, scaleY).apply {
                duration = 400
                doOnStart {
                    //Log.d("MyLog", "start animation minus scale")
                    adapterEasy?.click?.clickable = false
                    noClickItemScale = false//Выключить возможность нажатия на увеличеный итем пока идет анимация уменьшения
                    //включится только когда заново увеличится картинка
                }
                start()
                doOnEnd {
                    //Log.d("MyLog", "end animation minus scale")
                    //binding.layFragPlayPwpEasy.idImViewScale.visibility = View.GONE
                    //binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
                    binding.layFragPlayPwpEasy.idCardImViewScale.visibility = View.GONE
                    binding.layFragPlayPwpEasy.idCardImViewScale2.visibility = View.GONE
                    adapterEasy?.click?.clickable = true
                    //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                    //noClickItemScale = true
                    // Часть запускается если был не закрыт итем
                    if(openItemScale){
                        //Log.d("MyLog", "2end animation minus scale")
                        itemViewGl.visibility = View.INVISIBLE
                        positionClickOpen = positionClick
                        val withRc = binding.layFragPlayPwpEasy.idRcViewFragPWP.width
                        val heightRc = binding.layFragPlayPwpEasy.idRcViewFragPWP.height
                        val withIm = binding.layFragPlayPwpEasy.idImViewScale.width
                        val heightIm = binding.layFragPlayPwpEasy.idImViewScale.height
                        //Log.d("MyLog", "position $position")
                        //меняем координаты появления scaleView
                        //var paramsScale = binding.layFragPlayPwpEasy.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                        //var paramsScale2 = binding.layFragPlayPwpEasy.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
                        var paramsScale = binding.layFragPlayPwpEasy.idCardImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                        var paramsScale2 = binding.layFragPlayPwpEasy.idCardImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
                        val coordinatesImageScale =
                            MoveItemScaleTwo.movingItemScaleEasy(positionClick, withRc, heightRc, withIm, heightIm)
                        paramsScale.leftMargin = coordinatesImageScale[0]
                        paramsScale.topMargin = coordinatesImageScale[1]
                        paramsScale2.leftMargin = coordinatesImageScale[0]
                        paramsScale2.topMargin = coordinatesImageScale[1]

                        //binding.layFragPlayPwpEasy.idImViewScale.layoutParams = paramsScale
                        //binding.layFragPlayPwpEasy.idImViewScale2.layoutParams = paramsScale2
                        binding.layFragPlayPwpEasy.idCardImViewScale.layoutParams = paramsScale
                        binding.layFragPlayPwpEasy.idCardImViewScale2.layoutParams = paramsScale2

                        //binding.layFragPlayPwpEasy.idImViewScale.visibility = View.VISIBLE
                        //binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
                        binding.layFragPlayPwpEasy.idCardImViewScale.visibility = View.VISIBLE
                        binding.layFragPlayPwpEasy.idCardImViewScale2.visibility = View.GONE

                        binding.layFragPlayPwpEasy.idImViewScale.setImageBitmap(b0)
                        // 999 - Заглушка
                        animObjectPlus(positionClickOpen, constHelpScore, 999, 999, 999)
                        openItemScale = true
                        arraySlicedBitmap.clear()
                        arraySlicedBitmap.add(b0)
                        arraySlicedBitmap.add(b1)
                        arraySlicedBitmap.add(b2)
                        arraySlicedBitmap.add(b3)
                        arraySlicedBitmap.add(b4)
                        arraySlicedBitmap.add(b5)
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
                        adapterEasy?.noMove?.noMoveIfOpenScale = true
                    }
                }
            }
        }else{
            //Анимация уменьшения второй картинки если она на кубике видна в данный мемент
            //ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idImViewScale2, scaleX, scaleY).apply {
            ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idCardImViewScale2, scaleX, scaleY).apply {
                duration = 400
                doOnStart {
                    //Log.d("MyLog", "start animation minus scale2")
                    adapterEasy?.click?.clickable = false
                    noClickItemScale = false//Выключить возможность нажатия на увеличеный итем пока идет анимация уменьшения
                    //включится только когда заново увеличится картинка
                }
                start()
                doOnEnd {
                    //Log.d("MyLog", "end animation minus scale2")
                    //binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
                    //binding.layFragPlayPwpEasy.idImViewScale.visibility = View.GONE
                    binding.layFragPlayPwpEasy.idCardImViewScale2.visibility = View.GONE
                    binding.layFragPlayPwpEasy.idCardImViewScale.visibility = View.GONE
                    adapterEasy?.click?.clickable = true
                    //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                    //noClickItemScale = true
                    // Часть запускается если был не закрыт итем
                    if(openItemScale) {
                        //Log.d("MyLog", "2end animation minus scale2")
                        itemViewGl.visibility = View.INVISIBLE
                        positionClickOpen = positionClick
                        val withRc = binding.layFragPlayPwpEasy.idRcViewFragPWP.width
                        val heightRc = binding.layFragPlayPwpEasy.idRcViewFragPWP.height
                        //val withIm = binding.layFragPlayPwpEasy.idImViewScale.width
                        //val heightIm = binding.layFragPlayPwpEasy.idImViewScale.height
                        val withIm = binding.layFragPlayPwpEasy.idCardImViewScale.width
                        val heightIm = binding.layFragPlayPwpEasy.idCardImViewScale.height
                        //Log.d("MyLog", "position $position")
                        //меняем координаты появления scaleView
                        //var paramsScale =
                        //    binding.layFragPlayPwpEasy.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                        //var paramsScale2 =
                        //    binding.layFragPlayPwpEasy.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
                        var paramsScale =
                            binding.layFragPlayPwpEasy.idCardImViewScale.layoutParams as ViewGroup.MarginLayoutParams
                        var paramsScale2 =
                            binding.layFragPlayPwpEasy.idCardImViewScale2.layoutParams as ViewGroup.MarginLayoutParams

                        val coordinates = MoveItemScaleTwo.movingItemScaleEasy(
                            positionClick,
                            withRc,
                            heightRc,
                            withIm,
                            heightIm
                        )

                        paramsScale.leftMargin = coordinates[0]
                        paramsScale.topMargin = coordinates[1]
                        paramsScale2.leftMargin = coordinates[0]
                        paramsScale2.topMargin = coordinates[1]
                        //binding.layFragPlayPwpEasy.idImViewScale.layoutParams = paramsScale
                        //binding.layFragPlayPwpEasy.idImViewScale2.layoutParams = paramsScale2
                        binding.layFragPlayPwpEasy.idCardImViewScale.layoutParams = paramsScale
                        binding.layFragPlayPwpEasy.idCardImViewScale2.layoutParams = paramsScale2
                        //binding.layFragPlayPwpEasy.idImViewScale.visibility = View.VISIBLE
                        //binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
                        binding.layFragPlayPwpEasy.idCardImViewScale.visibility = View.VISIBLE
                        binding.layFragPlayPwpEasy.idCardImViewScale2.visibility = View.GONE

                        binding.layFragPlayPwpEasy.idImViewScale.setImageBitmap(b0)
                        //999 - заглушка
                        animObjectPlus(positionClickOpen, constHelpScore, 999 , 999, 999)
                        openItemScale = true
                        arraySlicedBitmap.clear()
                        arraySlicedBitmap.add(b0)
                        arraySlicedBitmap.add(b1)
                        arraySlicedBitmap.add(b2)
                        arraySlicedBitmap.add(b3)
                        arraySlicedBitmap.add(b4)
                        arraySlicedBitmap.add(b5)
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
                        adapterEasy?.noMove?.noMoveIfOpenScale = true
                    }
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        timer?.cancel()
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
        imItem: ImageView,
        directionRotation: Int,
        selectImage: Int,
        openImage: Int,
        countCubeRotated: Int
    ) {
        //Log.d("MyLog", "clickScaleItem")
        //binding.layFragPlayPwpEasy.idImViewScale.elevation = 1f
        binding.layFragPlayPwpEasy.idCardImViewScale.elevation = 100f
        binding.layFragPlayPwpEasy.idCardImViewScale2.elevation = 100f
        //Log.d("MyLog", "b0: $b0, n0: $n0, p0: $p0")
        itemViewGlobal = itemView
        imItemGlobal = imItem
        positionClick = position
        binding.layFragPlayPwpEasy.idCardImViewMove2.visibility = View.GONE //Прячем картинку которая прилетает обратно и находится поднизом
        if (openItemScale){
            //Log.d("MyLog", "openItemScale = true")
            //запускается когда итем уже увеличен, и закрывает предыдущий итем и запускает новый
            animObjectMinus(positionClickOpen, b0,b1,b2,b3,b4,b5,n0,n1,n2,n3,n4,n5, p0,p1,p2,p3,p4,p5, positionClick, itemView, directionRotation)
        }else {
            //itemViewGlobal.visibility = View.INVISIBLE
            binding.layFragPlayPwpEasy.idCardImViewScale.visibility = View.VISIBLE
            //binding.layFragPlayPwpEasy.idImViewScale.visibility = View.VISIBLE
            //Log.d("MyLog", "Запустили просто scale итем")
            //запускается если ничего небыло открыто
            itemView.visibility = View.INVISIBLE
            positionClickOpen = position
            val withRc = binding.layFragPlayPwpEasy.idRcViewFragPWP.width
            val heightRc = binding.layFragPlayPwpEasy.idRcViewFragPWP.height
            //val withIm = binding.layFragPlayPwpEasy.idImViewScale.width
            //val heightIm = binding.layFragPlayPwpEasy.idImViewScale.height
            val withIm = binding.layFragPlayPwpEasy.idCardImViewScale.width
            val heightIm = binding.layFragPlayPwpEasy.idCardImViewScale.height
            //Log.d("MyLog", "position $position")
            //меняем координаты появления scaleView
            //var paramsScale = binding.layFragPlayPwpEasy.idImViewScale.layoutParams as ViewGroup.MarginLayoutParams
            //var paramsScale2 = binding.layFragPlayPwpEasy.idImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
            var paramsScale = binding.layFragPlayPwpEasy.idCardImViewScale.layoutParams as ViewGroup.MarginLayoutParams
            var paramsScale2 = binding.layFragPlayPwpEasy.idCardImViewScale2.layoutParams as ViewGroup.MarginLayoutParams
            val coordinates =
                MoveItemScaleTwo.movingItemScaleEasy(position, withRc, heightRc, withIm, heightIm)
            paramsScale.leftMargin = coordinates[0]
            paramsScale.topMargin = coordinates[1]
            paramsScale2.leftMargin = coordinates[0]
            paramsScale2.topMargin = coordinates[1]
            //binding.layFragPlayPwpEasy.idImViewScale.layoutParams = paramsScale
            binding.layFragPlayPwpEasy.idCardImViewScale.layoutParams = paramsScale
            //binding.layFragPlayPwpEasy.idImViewScale2.layoutParams = paramsScale2
            binding.layFragPlayPwpEasy.idCardImViewScale2.layoutParams = paramsScale2

            //binding.layFragPlayPwpEasy.idImViewScale.visibility = View.VISIBLE
            binding.layFragPlayPwpEasy.idCardImViewScale.visibility = View.VISIBLE
            //binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
            binding.layFragPlayPwpEasy.idCardImViewScale2.visibility = View.GONE

            binding.layFragPlayPwpEasy.idImViewScale.setImageBitmap(b0)
            animObjectPlus(position, directionRotation, selectImage, openImage, countCubeRotated)
            //openItemScale = true //открыт/увеличен scaleItem
            //adapterEasy?.noMove?.noMoveIfOpenScale = false
            arraySlicedBitmap.clear()
            arraySlicedBitmap.add(b0)
            arraySlicedBitmap.add(b1)
            arraySlicedBitmap.add(b2)
            arraySlicedBitmap.add(b3)
            arraySlicedBitmap.add(b4)
            arraySlicedBitmap.add(b5)
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
        //binding.layFragPlayPwpEasy.idCardImViewMove.elevation = 30f
        clickMoveAdapter = true
        itemViewGlobalMove = itemView
        imItemGlobal = imItem
        positionMove = position
        //itemView.visibility = View.GONE
        binding.layFragPlayPwpEasy.idImViewMove.setImageBitmap(b0)
        arraySlicedBitmap.clear()
        arraySlicedBitmap.add(b0)
        arraySlicedBitmap.add(b1)
        arraySlicedBitmap.add(b2)
        arraySlicedBitmap.add(b3)
        arraySlicedBitmap.add(b4)
        arraySlicedBitmap.add(b5)
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
            binding.layFragPlayPwpEasy.idCardImViewMove2.visibility = View.VISIBLE
            //Возращаем к маленькому размеру возращающийся кубик
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
            //Анимация уменьшения
            ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idCardImViewMove2, scaleX, scaleY).apply {
                duration = 10
                doOnStart {
                }
                start()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.layFragPlayPwpEasy.idImViewMove2.setImageBitmap(b0)
                //Вводим координаты откуда и куда будет возвращаться кубик
                val path = Path().apply {
                    moveTo(coordinateBackStart[0], coordinateBackStart[1])
                    //Log.d("MyLog", "position $position")
                    when (positionMove) {
                        0 -> {
                            lineTo(
                                coordinate[0] + (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f - binding.layFragPlayPwpEasy.idCardImViewMove2.width) / 2f,
                                coordinate[1] + (binding.layFragPlayPwpEasy.idRcViewFragPWP.height / 5f - binding.layFragPlayPwpEasy.idCardImViewMove2.height) / 2
                            )
                        }
                        1 -> {
                            lineTo(
                                coordinate[0],
                                coordinate[1] + (binding.layFragPlayPwpEasy.idRcViewFragPWP.height / 5f - binding.layFragPlayPwpEasy.idCardImViewMove2.height) / 2
                            )
                        }
                        2 -> {
                            lineTo(
                                coordinate[0] - (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f - binding.layFragPlayPwpEasy.idCardImViewMove2.width) / 2f,
                                coordinate[1] + (binding.layFragPlayPwpEasy.idRcViewFragPWP.height / 5f - binding.layFragPlayPwpEasy.idCardImViewMove2.height) / 2
                            )
                        }
                        3, 6, 9 -> {
                            lineTo(
                                coordinate[0] + (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f - binding.layFragPlayPwpEasy.idCardImViewMove2.width) / 2f,
                                coordinate[1]
                            )
                        }
                        5, 8, 11 -> {
                            lineTo(
                                coordinate[0] - (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f - binding.layFragPlayPwpEasy.idCardImViewMove2.width) / 2f,
                                coordinate[1]
                            )
                        }
                        12 -> {
                            lineTo(
                                coordinate[0] + (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f - binding.layFragPlayPwpEasy.idCardImViewMove2.width) / 2f,
                                coordinate[1] - (binding.layFragPlayPwpEasy.idRcViewFragPWP.height / 5f - binding.layFragPlayPwpEasy.idCardImViewMove2.height) / 2
                            )
                        }
                        13 -> {
                            lineTo(
                                coordinate[0],
                                coordinate[1] - (binding.layFragPlayPwpEasy.idRcViewFragPWP.height / 5f - binding.layFragPlayPwpEasy.idCardImViewMove2.height) / 2
                            )
                        }
                        14 -> {
                            lineTo(
                                coordinate[0] - (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f - binding.layFragPlayPwpEasy.idCardImViewMove2.width) / 2f,
                                coordinate[1] - (binding.layFragPlayPwpEasy.idRcViewFragPWP.height / 5f - binding.layFragPlayPwpEasy.idCardImViewMove2.height) / 2
                            )
                        }
                        else -> {
                            lineTo(coordinate[0], coordinate[1])
                        }
                    }
                }
                //Анимация перемещения возврата кубика
                ObjectAnimator.ofFloat(binding.layFragPlayPwpEasy.idCardImViewMove2, View.X, View.Y, path).apply {
                    duration = 450
                    doOnStart {
                        adapterEasy?.noMoveBack?.noMoveIfOpenScale = false
                        adapterEasy?.clickBack?.clickable = false
                        noClickBack.clickable = false
                    }
                    start()
                    doOnEnd {
                        val scaleX = PropertyValuesHolder.ofFloat(
                            View.SCALE_X, (binding.layFragPlayPwpEasy.idRcViewFragPWP.width / 3f) / binding.layFragPlayPwpEasy.idCardImViewMove2.width)
                        val scaleY = PropertyValuesHolder.ofFloat(
                            View.SCALE_Y, (binding.layFragPlayPwpEasy.idRcViewFragPWP.height / 5f) / binding.layFragPlayPwpEasy.idCardImViewMove2.height)
                        //Анимация увелечения кубика который вернулся
                        ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idCardImViewMove2, scaleX, scaleY)
                            .apply {
                                duration = 350
                                start()
                                doOnEnd {

                                    adapterEasy?.updateAdapterPosition(arrayBitmapMoveReturn, arrayNumberMoveReturn, arrayPositionMoveReturn, positionMove, 999)
                                    adapterEasy?.noMoveBack?.noMoveIfOpenScale = true
                                    adapterEasy?.clickBack?.clickable = true
                                    noClickBack.clickable = true
                                    //binding.layFragPlayPwpEasy.idImViewMove2.visibility = View.GONE
                                    //Log.d("MyLog", "coordinateBackImMove2 ${binding.idImViewMove2.y}")
                                }
                            }
                    }
                }
                //Если версия сдк меньше, без анимации возращения
            } else {
                adapterEasy?.updateAdapterPosition(arrayBitmapMoveReturn, arrayNumberMoveReturn, arrayPositionMoveReturn, positionMove, Constans.NO_POSITION_MOVE)
            }
        }
    }

    override fun soundEffect() {
        mediaPlayerCubeFalling.start()
    }

    //Интерфейс получения данных с adaptera, после нажатия на кнопку помощи и запуска диалога подсказки
    override fun helpScore(mainArrayViewTemp: ArrayList<dataArrayBitmap>, score: Int, flag: Int) {
        //Интерфейс возращает весь массив с данными, и в зависимости от того откуда запущен (помощь или доктор) по
        //константе запускаем или проверку или помощь
        //Проверка запускается после выдвижения бокового меню
        if(flag == Constans.HELPSCORESTART) {
            mainArrayView.clear()
            mainArrayView.addAll(mainArrayViewTemp)
            dialogHelpScore.createHelpScoreDialog(
                activity as FragmentActivity,
                arrayBitmap,
                arrayBlurBitmap,
                score,
                arrayCollectedImage,
                mainArrayView,
                blur
            )
        }else{
            newDataArrayBitmapFromDoctor.clear()
            newDataArrayBitmapFromDoctor.addAll(mainArrayViewTemp)
            //Доктор. Проверяем на совпадения элементы, если есть то запускаем replacingElementDoctor(index)
            for((index, value) in newDataArrayBitmapFromDoctor.withIndex()) {
                    for( n in index until newDataArrayBitmapFromDoctor.size) {
                       if (n < newDataArrayBitmapFromDoctor.size - 1) {
                           for (i in newDataArrayBitmapFromDoctor[n + 1].arrayBitmap) {
                               if (value.arrayBitmap[0] == i) {
                                   replacingElementDoctor(index)
                               }
                           }
                       }
                    }
            }
        }
    }

    //Метод - Доктор, замена повторяющегося элемента на тот который отстутствует если есть ошибка
    fun replacingElementDoctor(element: Int) {
        var notFound = true
        var notForce: Boolean
        for ((index, value) in shuffleDataArrayBitmap.withIndex()) {
            if (notFound) {
                notForce = true
                for ((ind, valu) in newDataArrayBitmapFromDoctor.withIndex()) {
                    for (per in valu.arrayBitmap) {
                        if (value.arrayBitmap[0] == per) {
                            notForce = false
                        }
                    }
                    if (ind == shuffleDataArrayBitmap.size - 1){
                        if (notForce){
                            notFound = false
                            adapterEasy.updateAdapterPosition(shuffleDataArrayBitmap[index].arrayBitmap, shuffleDataArrayBitmap[index].arrayNumber, shuffleDataArrayBitmap[index].arrayPosition, element, Constans.NO_POSITION_MOVE)
                        }
                    }
                }
            }
        }
    }

    //Интерфейс получения данных с adaptera, после того как кубик повернулся и нужно новый повернуть
    override fun helpScoreNext(
        selectImage: Int,
        openImage: Int,
        mainArrayViewTemp: ArrayList<dataArrayBitmap>,
        posRotation: Int,
        countCubeRotated: Int
    ) {
        //Запускаем если позиция которую хотим вращать меньше чем кол-во кубиков которое нужно вращать
        if (countCubeRotated != openImage) {
            mainArrayView.clear()
            mainArrayView.addAll(mainArrayViewTemp)
            HelpScoreManager.startDirectionRotationCube(
                selectImage,
                openImage,
                mainArrayView,
                binding,
                posRotation,
                countCubeRotated
            )
            score--
            binding.layFragPlayPwpEasy.idTvScore.text = score.toString()
        } else {
            score--
            binding.layFragPlayPwpEasy.idTvScore.text = score.toString()
            stopClickAnimationHelp = false// Разблокировать нажатия на фрагменте
            adapterEasy.noClickForAnimationHelpScore.noClickForAnimationHelpScore = true// Разаблокировать нажатия на адаптере
        }
    }

    //После обновления позиции в адаптере происходит проверка собрана картинка, если да то сюда приходит позиция, какую картинку собрали
    override fun imageIsCollected(positionImageCollected: Int) {
        finishCloseDriver = true
        arrayCollectedImage[positionImageCollected] = 1
        collectedImageVisible(arrayCollectedImage)
        FinishAnimationCongratulation.animationFinishStart(binding.layFragPlayPwpEasy.idRcViewFragPWP,
            binding.layFragPlayPwpEasy.idViewFinishAnimation, dialogCongratulationDialog, activity as FragmentActivity,colorLine)
        // Открыть драйвер лэйоут что бы показать какую картинку собрал
        //binding.idDrawerLayout.openDrawer(GravityCompat.START)
        //Log.d("MyLog", "Finish $positionImageCollected")
    }
    //Не используется
    override fun countPlus(count: Int) {
        //adapter?.countStart?.count?.plus(2)
    }

    //Если сделали короткий action_move и сработало только в адаптере
    override fun actionMoveAndActionUP(position: Int) {
        clickMoveAdapter = false
        adapterEasy?.updateOnePosition(position)
        arraySlicedBitmap.clear()
        arrayNumber.clear()
        arrayPosition.clear()
    }

    //Интерфейс проходной, для запуска функции внутри адаптера для обновления линий который уже исчезли, что бы
    // второй раз не срабатывала анимация уже на исчезнувших линиях
    override fun updateLineTwoNoAnimation(position: Int, numberLine: Int) {
        adapterEasy.updateLinePosTwo(position, numberLine)
    }

    //Интерфейс который срабатывает в диалоге Да\Нет
    override fun interfaceYesNoDialog(optionDifficulty: Int, nameButton: Int) {
        when (optionDifficulty){
            Constans.YES -> {
                when (nameButton) {
                    Constans.BT_SHUFFLE -> {
                        binding.idDrawerLayout.closeDrawer(GravityCompat.START)
                        //Копируем массив для доктора
                        var shuffleDataArray = shuffleTempListBitmap(originalDataArrayBitmap)
                        //Копируем для доктора массив со всеми значениями
                        var shuffleDataArrayCopy = copyShuffleArrayBitmap(shuffleDataArray)
                        shuffleDataArrayBitmap.clear()
                        shuffleDataArrayBitmap.addAll(shuffleDataArrayCopy)
                        adapterEasy?.updateAdapter(shuffleDataArray)
                        //dialogYesNoAlert.dismiss()
                    }
                    Constans.BT_ADS ->{
                        //Тут должна запуститься реклама
                        score += 1
                        binding.layFragPlayPwpEasy.idTvScore.text = score.toString()
                        FinishAnimationCongratulation.doAnimationScore(activity as FragmentActivity, binding.layFragPlayPwpEasy.idTvScore, colorLine)
                       // dialogYesNoAlert.dismiss()
                    }
                }
            }
            Constans.NO -> {
                //dialogYesNoAlert.dismiss()
            }
        }
    }


    override fun interfaceFinishCongratulationDialog(scor: Int) {
        score += scor
        binding.layFragPlayPwpEasy.idTvScore.text = score.toString()
        binding.idDrawerLayout.openDrawer(GravityCompat.START)
    }

    override fun interfaceHelpScoreDialog(
        selectImage: Int,
        openImage: Int,
        posRotationCube: Int,
        countCubeRotated: Int
    ) {
        binding.idDrawerLayout.closeDrawer(GravityCompat.START)
        //HelpScoreManager.startDirectionRotationCube(selectImage, openImage, mainArrayView,
        //binding, adapterEasy)
        //selectImage - выбраная картинка для поворота кубов
        //openImage - кол-во кубов которое нужно повернуть
        //mainArrayView - массив со всеми данными по полю типа  ArrayList<dataArrayBitmap>
        // posRotation - позиция которую вращаем
        HelpScoreManager.startDirectionRotationCube(selectImage, openImage, mainArrayView,
            binding, posRotationCube, countCubeRotated)
    }

    //интерфейс который вызывается из диалога помощи поворота кубиков
    //для блокировки нажатия на фрагменте пока идет анимация вращения кубиков
    override fun interfaceStopClickAnimationHelp() {
        stopClickAnimationHelp = true// Заблокировать нажатия на фрагменте
        adapterEasy.noClickForAnimationHelpScore.noClickForAnimationHelpScore = false// Заблокировать нажатия на адаптере
    }

    //Не используется
    override fun interfaceNoStopClickAnimationHelp() {
        TODO("Not yet implemented")
    }

}

