package kitonpompom.cubesgame.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import io.ak1.pix.helpers.PixBus
import io.ak1.pix.helpers.PixEventCallback
import io.ak1.pix.helpers.setupScreen
import io.ak1.pix.helpers.showStatusBar
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.R.id.fragmentPlayingWithPictures
import kitonpompom.cubesgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //supportActionBar?.hide()
        //setupScreen()
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.navController
            setupWithNavController(binding.idBotNavView, navController)

        //При открытии фрагмента с игрой прячем Navigation View Bottom
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.fragmentPlayingWithPicturesTwo -> binding.idBotNavView.visibility = View.GONE
                    R.id.fragmentPlayingWithPicturesMedium -> binding.idBotNavView.visibility = View.GONE
                    R.id.fragmentPlayingWithPicturesEasy -> binding.idBotNavView.visibility = View.GONE
                else -> binding.idBotNavView.visibility = View.VISIBLE
                }
            }



        PixBus.results {
            if (it.status == PixEventCallback.Status.SUCCESS) {
                //Log.d("MyLog", "Запустилось")
                showStatusBar()
                navController.navigateUp()
            }
        }
            //setBottomNavListener()
    }

    override fun onBackPressed() {

        if (navController.currentDestination == navController.graph[R.id.CameraFragment])
            PixBus.onBackPressedEvent()
        else
            super.onBackPressed()
    }


    /*private fun setBottomNavListener(){
        binding.idBotNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.id_group_im_offline->{
                    Log.d("MyLog", "Offline")
                }
                R.id.id_group_im_online->{
                    Log.d("MyLog", "Online")
                }
                R.id.id_add_im->{
                    Log.d("MyLog", "Add")
                }
                R.id.id_friends->{
                    Log.d("MyLog", "Friends")
                }
                R.id.id_my_page->{
                    Log.d("MyLog", "Page")
                }
            }
            true
        }
    }*/
}
