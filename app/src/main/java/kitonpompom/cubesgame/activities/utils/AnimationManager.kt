package kitonpompom.cubesgame.activities.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.widget.ImageView

//Класс для анимации увелечения и уменьшения, анимация тени
object AnimationManager {

    fun animationShadowScalePlus(view: ImageView){
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f)
        ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
            duration = 400
            start()
        }
    }

    fun animationShadowScaleMinus(view: ImageView, rcView : CustomRecycleView, ){
        val scaleXShadow = PropertyValuesHolder.ofFloat(View.SCALE_X, (rcView.width/3f) / view.width)
        val scaleYShadow = PropertyValuesHolder.ofFloat(View.SCALE_Y, (rcView.width/3f) / view.width)
        ObjectAnimator.ofPropertyValuesHolder(view, scaleXShadow, scaleYShadow).apply {
            duration = 400
            start()
        }
    }


}