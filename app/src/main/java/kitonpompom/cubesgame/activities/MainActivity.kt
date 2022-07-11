package kitonpompom.cubesgame.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            setBottomNavListener()
    }

    private fun setBottomNavListener(){
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

    }
}