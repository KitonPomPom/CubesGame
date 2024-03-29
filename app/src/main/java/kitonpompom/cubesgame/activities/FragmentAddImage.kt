package kitonpompom.cubesgame.activities


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.ak1.pix.helpers.PixBus
import io.ak1.pix.helpers.PixEventCallback
import io.ak1.pix.utility.ARG_PARAM_PIX
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.dialogs.DifficultyOptionDialog
import kitonpompom.cubesgame.activities.dialogs.InterfaceDifficultyOptionDialog
import kitonpompom.cubesgame.activities.dialogs.ProgressDialog
import kitonpompom.cubesgame.activities.utils.Constans
import kitonpompom.cubesgame.activities.utils.ImageManager
import kitonpompom.cubesgame.activities.utils.ImagePicker
import kitonpompom.cubesgame.activities.utils.Interface
import kitonpompom.cubesgame.databinding.FragmentAddImageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//3 Фрагмент добовления картинок с камеры
class FragmentAddImage() : Fragment(), AdapterAddFragment.CallbackArrayImageSizeInterface, InterfaceDifficultyOptionDialog {

    lateinit var binding: FragmentAddImageBinding
    private var tempStart = 0 //Переменная для одноразового запуска камеры с NavigationView
    private var adapter: AdapterAddFragment? = AdapterAddFragment(this)
    var arrayImageSize: Int = 0 //переменная для отоброжения кол-ва загруженых картинок в адаптер
    var pixUpdateAdapter: Int = 0 //для одноразового запуска из пикскэлбэк
    val mainArray = ArrayList<Uri>()
    private val dataModel: DataModel by activityViewModels()
    lateinit var addValueListBitmap: Interface.AddValueListBitmap
    val mainArrayBitmap = ArrayList<Bitmap>()
    val difficultyOptionDialog = DifficultyOptionDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Принимаем фото с фрагмента с камерой и галереей
        PixBus.results(coroutineScope = CoroutineScope(Dispatchers.Main)) {

            when (it.status) {
                PixEventCallback.Status.SUCCESS -> {
                    delay(300L)
                    val dialog = ProgressDialog.createProgressDialog(context as Activity, Constans.FRAGMENT_ADD_IMAGE)// Запускаем диалог с прогрес баром загрузки
                    if(pixUpdateAdapter == 1) {
                        delay(150L)
                        adapter?.updateAdapter(ImageManager.imageResize(activity as AppCompatActivity, it.data))
                        dialog.dismiss()// Выключаем диалог с прогрес баром загрузки
                        mainArray.addAll(it.data)

                    }
                    pixUpdateAdapter = 0
                }
                PixEventCallback.Status.BACK_PRESSED -> {
                    Toast.makeText(activity as AppCompatActivity, "Назад 2", Toast.LENGTH_LONG).show()
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.vpImages.adapter = adapter
        binding.idRcImHor.layoutManager = LinearLayoutManager(activity as AppCompatActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.idRcImHor.adapter = adapter

        addValueListBitmap = adapter!!

        if(tempStart == 0){//Проверяем запускалась ли камера с NavigationView
            tempStart = 1
            starCamera()
        }

        //Нажимаем кнопку сохранить
        binding.idBtSaveImage.setOnClickListener(){
            if(arrayImageSize == 6){
                addValueListBitmap.addValueViewModelListBitmap(dataModel)//Интер. через который
                //мы передаем в адаптер dataModel - в который передаются 6 bitmap из адаптера.
                difficultyOptionDialog.createDifficultyOptionDialogAddImage(activity as AppCompatActivity)
                //findNavController().navigate(R.id.action_fragment_add_image_to_fragmentPlayingWithPicturesTwo)
            }else{
                Toast.makeText(activity, "Загрузите шесть изображений", Toast.LENGTH_LONG).show()
            }
        }

        //Кнопка нажатия добавить картинку
        binding.idBtAddImage.setOnClickListener(){
            pixUpdateAdapter = 1
            var count = 6 - arrayImageSize
            var bundle = bundleOf(ARG_PARAM_PIX to ImagePicker.getOptions(count))
            findNavController().navigate(R.id.action_fragment_add_image_to_cameraFragment, bundle)
        }

    }

    fun starCamera(){ //Запустить фрагмент с камерой и галереей
        pixUpdateAdapter = 1
        var bundle = bundleOf(ARG_PARAM_PIX to ImagePicker.getOptions(6))
        findNavController().navigate(R.id.action_fragment_add_image_to_cameraFragment, bundle)
    }

    override fun callbackArrayImageSize(sizeArray: Int) {
        arrayImageSize = sizeArray
        binding.tvUploaded.text = "$arrayImageSize из 6"
        if(arrayImageSize == 6){
            binding.idBtAddImage.visibility = View.GONE
        }else{
            binding.idBtAddImage.visibility = View.VISIBLE
        }
    }

    override fun deleteImage() {
        arrayImageSize --
        binding.tvUploaded.text = "$arrayImageSize из 6"
        if(arrayImageSize == 6){
            binding.idBtAddImage.visibility = View.GONE
        }else{
            binding.idBtAddImage.visibility = View.VISIBLE
        }
    }

    override fun interfaceDifficultyOptionDialog(
        optionDifficulty: Int,
        mainArray: ArrayList<Bitmap>
    ) {
        //Заглушка, спользуется в FragOffline
    }

    override fun interfaceDifficultyOptionDialogAddImage(optionDifficulty: Int) {
        when (optionDifficulty) {
            Constans.EASY -> findNavController().navigate(R.id.action_fragment_add_image_to_fragmentPlayingWithPicturesEasy)
            Constans.MEDIUM -> findNavController().navigate(R.id.action_fragment_add_image_to_fragmentPlayingWithPicturesMedium)
            Constans.HARD -> findNavController().navigate(R.id.action_fragment_add_image_to_fragmentPlayingWithPicturesTwo)
        }
    }


}
