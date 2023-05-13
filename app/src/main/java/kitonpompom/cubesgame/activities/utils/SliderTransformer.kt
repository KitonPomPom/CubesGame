package kitonpompom.cubesgame.activities.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import kitonpompom.cubesgame.R
import java.lang.Math.abs

//Пэйджтрансформер для вью пейджера2, что бы картинки стояли друг за другом и при перетягивании улетали за экран
//Вариант с затемненными картинками
/*class SliderTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {
        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.46f
        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f
    }

    override fun transformPage(page: View, position: Float) {

        page.apply {
            ViewCompat.setElevation(page, -kotlin.math.abs(position))
            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
            when (position)  {
                in 0f..offscreenPageLimit - 1f -> {
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                }
                else -> {
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                }
            }

            val recommendedMovieIV: ImageView = findViewById(R.id.id_imView_Offline)
            if (position <= -1.0f || position >= 1.0f) {
                recommendedMovieIV.alpha = 0.5f
            } else if (position == 0.5f) {
                recommendedMovieIV.alpha = 1.0f
            } else if (position < 0.5f) {
                recommendedMovieIV.alpha = 1.0f - kotlin.math.abs(position)
            }
        }
    }
}*/
//Пэйджтрансформер для вью пейджера2, что бы картинки стояли друг за другом и при перетягивании улетали за экран
//Вариант с полупрозрачными картинками
class SliderTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {

        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.2f

        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .3f
        private const val DEFAULT_ALPHA = 1f

    }

    override fun transformPage(page: View, position: Float) {

        page.apply {

            ViewCompat.setElevation(page, -kotlin.math.abs(position))

            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
            val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA

            when {
                position <= 0f -> {
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA + position
                }
                position <= offscreenPageLimit - 1 -> {
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                    alpha = alphaFactor
                }
                else -> {
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA
                }
            }
        }
    }
}