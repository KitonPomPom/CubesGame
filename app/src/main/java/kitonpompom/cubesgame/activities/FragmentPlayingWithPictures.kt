package kitonpompom.cubesgame.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.databinding.FragmentPlayingWithPicturesBinding

class FragmentPlayingWithPictures : Fragment() {

    lateinit var binding: FragmentPlayingWithPicturesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayingWithPicturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    companion object {

        fun newInstance(){}
    }
}