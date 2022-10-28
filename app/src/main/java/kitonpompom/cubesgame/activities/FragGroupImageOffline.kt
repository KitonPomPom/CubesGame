package kitonpompom.cubesgame.activities

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.DataModel
import kitonpompom.cubesgame.activities.utils.ImageManager
import kitonpompom.cubesgame.databinding.FragmentFragGroupImageOfflineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FragGroupImageOffline : Fragment(), AdapterFragOfflineGroupImage.OpenFragPlayInterface {

    lateinit var binding : FragmentFragGroupImageOfflineBinding

    lateinit var pLauncher : ActivityResultLauncher<String>

    private var adapterFragOfflineGroupImage: AdapterFragOfflineGroupImage? = null

    var arrayHeadCity: ArrayList<String> = ArrayList()
    var arrayHeadAuto: ArrayList<String> = ArrayList()
    var arrayHeadFish: ArrayList<String> = ArrayList()
    var arrayHeadAnimal: ArrayList<String> = ArrayList()

    private val dataModel: DataModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragGroupImageOfflineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //registerPermissionListener()
        //chekPermission()






        arrayHeadCity.clear()
        arrayHeadAuto.clear()
        arrayHeadFish.clear()
        arrayHeadAnimal.clear()
        arrayHeadCity.add(ArraysContent.headListCity[0])
        arrayHeadCity.add(ArraysContent.headListCity[1])
        arrayHeadAuto.add(ArraysContent.headListAuto[0])
        arrayHeadAuto.add(ArraysContent.headListAuto[1])
        arrayHeadFish.add(ArraysContent.headListFish[0])
        arrayHeadFish.add(ArraysContent.headListFish[1])
        arrayHeadAnimal.add(ArraysContent.headListAnimal[0])
        arrayHeadAnimal.add(ArraysContent.headListAnimal[1])

        binding.idRcViewFragOfflineGroup.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)




        //val uri1: Uri = Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city1)
        //val uri2: Uri = Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city2)
        //val uri3: Uri = Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city3)
        //val uri1: Uri = Uri.parse("android.resource://kitonpompom.cubesgame/drawable/city1")
        //val uri2: Uri = Uri.parse("android.resource://kitonpompom.cubesgame/drawable/city2")
        //val uri3: Uri = Uri.parse("android.resource://kitonpompom.cubesgame/drawable/city3")

        val listCity1: ArrayList<Uri> = ArrayList()
        listCity1.clear()
        listCity1.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city1))
        listCity1.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city2))
        listCity1.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city3))
        listCity1.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city4))
        listCity1.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city5))
        listCity1.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city6))
        //listCity1.add(R.drawable.city1.toString())
        //listCity1.add(R.drawable.city2.toString())
        //listCity1.add(R.drawable.city3.toString())
        //listCity1.add(R.drawable.city4.toString())
        //listCity1.add(R.drawable.city5.toString())
        //listCity1.add(R.drawable.city6.toString())

        val listCity2: ArrayList<Uri> = ArrayList()
        listCity2.clear()
        listCity2.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city21))
        listCity2.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city22))
        listCity2.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city23))
        listCity2.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city24))
        listCity2.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city25))
        listCity2.add (Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.city26))
        //listCity2.add(R.drawable.city21.toString())
        //listCity2.add(R.drawable.city22.toString())
        //listCity2.add(R.drawable.city23.toString())
        //listCity2.add(R.drawable.city24.toString())
        //listCity2.add(R.drawable.city25.toString())
        //listCity2.add(R.drawable.city26.toString())

        val listAnimal1: ArrayList<Uri> = ArrayList()
        listAnimal1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal11))
        listAnimal1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal12))
        listAnimal1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal13))
        listAnimal1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal14))
        listAnimal1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal15))
        listAnimal1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal16))

        val listAnimal2: ArrayList<Uri> = ArrayList()
        listAnimal2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal21))
        listAnimal2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal22))
        listAnimal2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal23))
        listAnimal2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal24))
        listAnimal2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal25))
        listAnimal2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.animal26))

        val listFish1: ArrayList<Uri> = ArrayList()
        listFish1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish11))
        listFish1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish12))
        listFish1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish13))
        listFish1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish14))
        listFish1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish15))
        listFish1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish16))

        val listFish2: ArrayList<Uri> = ArrayList()
        listFish2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish21))
        listFish2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish22))
        listFish2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish23))
        listFish2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish24))
        listFish2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish25))
        listFish2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.fish26))


        val listAuto1: ArrayList<Uri> = ArrayList()
        listAuto1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto11))
        listAuto1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto12))
        listAuto1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto13))
        listAuto1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto14))
        listAuto1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto15))
        listAuto1.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto16))

        val listAuto2: ArrayList<Uri> = ArrayList()
        listAuto2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto21))
        listAuto2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto22))
        listAuto2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto23))
        listAuto2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto24))
        listAuto2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto25))
        listAuto2.add(Uri.parse("android.resource://kitonpompom.cubesgame/" + R.drawable.auto26))

        adapterFragOfflineGroupImage = AdapterFragOfflineGroupImage(this)
        binding.idRcViewFragOfflineGroup.adapter = adapterFragOfflineGroupImage
        //og.d("MyLog", "Размер listCity1: ${listCity1.size}")

        val allCategoryCity: MutableList<AllCategories> = ArrayList()
        val allCategoryAnimal: MutableList<AllCategories> = ArrayList()
        val allCategoryFish: MutableList<AllCategories> = ArrayList()
        val allCategoryAuto: MutableList<AllCategories> = ArrayList()

        CoroutineScope(Dispatchers.Main).launch {

            allCategoryCity.clear()
            allCategoryCity.add(AllCategories("Один",ImageManager.imageResize(activity as AppCompatActivity, listCity1)))
            allCategoryCity.add(AllCategories("Два",ImageManager.imageResize(activity as AppCompatActivity, listCity2)))


            allCategoryAnimal.add(AllCategories("Один",ImageManager.imageResize(activity as AppCompatActivity, listAnimal1)))
            allCategoryAnimal.add(AllCategories("Два",ImageManager.imageResize(activity as AppCompatActivity, listAnimal2)))


            allCategoryFish.add(AllCategories("Один",ImageManager.imageResize(activity as AppCompatActivity, listFish1)))
            allCategoryFish.add(AllCategories("Два",ImageManager.imageResize(activity as AppCompatActivity, listFish2)))


            allCategoryAuto.add(AllCategories("Один",ImageManager.imageResize(activity as AppCompatActivity, listAuto1)))
            allCategoryAuto.add(AllCategories("Два",ImageManager.imageResize(activity as AppCompatActivity, listAuto2)))
            adapterFragOfflineGroupImage!!.updateAdapter(allCategoryCity)
        }








        binding.idTabeLayGroupOffline.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryCity)
                    1 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryAnimal)
                    2 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryFish)
                    3 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryAuto)
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
    }

    companion object {
        fun newInstance(){}
    }

    override fun openFragPlayWithPicturesInterface(mainArray: ArrayList<Bitmap>) {
            dataModel.listBitmapForAdapterFragPWP.value = mainArray
            findNavController().navigate(R.id.action_fragGroupImageOffline_to_fragmentPlayingWithPictures)

    }

    fun chekPermission() {
        when {
            ContextCompat.checkSelfPermission(
                activity as AppCompatActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(activity as AppCompatActivity, "Разрешение получено", Toast.LENGTH_LONG).show()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)->{
                Toast.makeText(activity as AppCompatActivity, "Разрешение небыло получено", Toast.LENGTH_LONG).show()
            }
            else -> {
                pLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    fun registerPermissionListener(){
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                Toast.makeText(activity as AppCompatActivity, "Разрешение было получено", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(activity as AppCompatActivity, "Не получено разрешение", Toast.LENGTH_LONG).show()
            }
        }
    }


}