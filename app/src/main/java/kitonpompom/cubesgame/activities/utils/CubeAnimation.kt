package kitonpompom.cubesgame.activities.utils

import android.view.animation.Transformation
import androidx.annotation.IntDef

//Класс анимации вращения кубика
open class CubeAnimation(direction: Int, enter: Boolean, duration: Long) : ViewPropertyAnimation() {
    @IntDef(UP, DOWN, LEFT, RIGHT)
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class Direction


    @Direction
    protected var mDirection = 0
    protected var mEnter = false


    /**
     * Create new Animation.
     * @param direction Direction of animation
     * @param enter true for Enter / false for Exit
     * @param duration Duration of Animation
     * @return
     */

    init{
        mDirection = direction
        mEnter = enter
        setDuration(duration)
    }

    private class VerticalCubeAnimation(@Direction direction: Int, enter: Boolean, duration: Long) :
        CubeAnimation(direction, enter, duration) {

        override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
            super.initialize(width, height, parentWidth, parentHeight)
            mPivotX = width * 0.5f
            mPivotY = if (mEnter == (mDirection == Companion.UP)) 0.0f else height.toFloat()
            mCameraZ = -height * 0.015f
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            var value = if (mEnter) interpolatedTime - 1.0f else interpolatedTime
            if (mDirection == DOWN) value *= -1.0f
            mRotationX = value * 90.0f
            mTranslationY = -value * mHeight
            super.applyTransformation(interpolatedTime, t)
            if (t != null) {
                applyTransformation(t)
            }
        }
    }

    private class HorizontalCubeAnimation(
        @Direction direction: Int,
        enter: Boolean,
        duration: Long
    ) :
        CubeAnimation(direction, enter, duration) {
        override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
            super.initialize(width, height, parentWidth, parentHeight)
            mPivotX = if (mEnter == (mDirection == LEFT)) 0.0f else width.toFloat()
            mPivotY = height * 0.5f
            mCameraZ = -width * 0.015f
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            var value = if (mEnter) interpolatedTime - 1.0f else interpolatedTime
            if (mDirection == RIGHT) value *= -1.0f
            mRotationY = -value * 90.0f
            mTranslationX = -value * mWidth
            super.applyTransformation(interpolatedTime, t)
            if (t != null) {
                applyTransformation(t)
            }
        }
    }

    companion object {
        const val UP = 1
        const val DOWN = 2
        const val LEFT = 3
        const val RIGHT = 4

        fun create(@Direction direction: Int, enter: Boolean, duration: Long): CubeAnimation
        {
            return when (direction) {
                UP, DOWN -> VerticalCubeAnimation(direction, enter, duration)
                LEFT, RIGHT -> HorizontalCubeAnimation(direction, enter, duration)
                else -> HorizontalCubeAnimation(direction, enter, duration)
            }
        }

    }


}