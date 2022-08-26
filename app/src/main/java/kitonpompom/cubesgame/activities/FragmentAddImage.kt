package kitonpompom.cubesgame.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.databinding.FragmentAddImageBinding
import kitonpompom.cubesgame.databinding.FragmentFragGroupImageOnlineBinding

class FragmentAddImage : Fragment() {

    lateinit var binding: FragmentAddImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentAddImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = FragmentAddImage()
    }
}