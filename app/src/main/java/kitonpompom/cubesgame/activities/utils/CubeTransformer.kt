package kitonpompom.cubesgame.activities.utils

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2


class CubeTransformer : ViewPager2.PageTransformer { //Анимация кубика для вьюпейджера2
    override fun transformPage(view: View, position: Float) {
        //if position = 0 (current image) pivot on x axis is on the right, else if
        // position > 0, (next image) pivot on x axis is on the left (origin of the axis)
       val with = view.width.toFloat()
        view.pivotX = (if (position <= 0)
            with as Float
            else 0.0f) as Float
        view.pivotY = view.height * 0.5f

        //it rotates with 90 degrees multiplied by current position
        view.rotationY = 90f * position
    }
}