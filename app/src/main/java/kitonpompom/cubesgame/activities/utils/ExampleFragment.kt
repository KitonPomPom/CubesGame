package kitonpompom.cubesgame.activities.utils

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.TextView
import androidx.annotation.IntDef
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


class ExampleFragment : Fragment() {
/*    @IntDef(
        NONE,
        MOVE,
        CUBE,
        FLIP,

    )
    annotation class AnimationStyle


    @IntDef(NODIR, UP, DOWN, LEFT, RIGHT)
    annotation class AnimationDirection

    val NODIR = 0
    val UP = 1
    val DOWN = 2
    val LEFT = 3
    val RIGHT = 4

    private val DURATION: Long = 500

    @AnimationStyle
    private var sAnimationStyle = CUBE

    @Bind(R.id.textAnimationStyle)
    var mTextAnimationStyle: TextView? = null

    fun newInstance(@AnimationDirection direction: Int): ExampleFragment? {
        val f = ExampleFragment()
        f.arguments = Bundle()
        f.arguments!!.putInt("direction", direction)
        return f
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.content_anim, container, false)
        val color: Int = Color.rgb(
            Math.floor(Math.random() * 128).toInt() + 64,
            Math.floor(Math.random() * 128).toInt() + 64,
            Math.floor(Math.random() * 128).toInt() + 64
        )
        view.setBackgroundColor(color)
        ButterKnife.bind(this, view)
        setAnimationStyleText()
        return view
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        when (sAnimationStyle) {
            MOVE -> when (arguments!!.getInt("direction")) {
                UP -> return MoveAnimation.create(MoveAnimation.UP, enter, DURATION)
                DOWN -> return MoveAnimation.create(MoveAnimation.DOWN, enter, DURATION)
                LEFT -> return MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION)
                RIGHT -> return MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION)
            }
            CUBE -> when (arguments!!.getInt("direction")) {
                UP -> return CubeAnimation.create(CubeAnimation.UP, enter, DURATION)
                DOWN -> return CubeAnimation.create(CubeAnimation.DOWN, enter, DURATION)
                LEFT -> return CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION)
                RIGHT -> return CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION)
            }
            FLIP -> when (arguments!!.getInt("direction")) {
                UP -> return FlipAnimation.create(FlipAnimation.UP, enter, DURATION)
                DOWN -> return FlipAnimation.create(FlipAnimation.DOWN, enter, DURATION)
                LEFT -> return FlipAnimation.create(FlipAnimation.LEFT, enter, DURATION)
                RIGHT -> return FlipAnimation.create(FlipAnimation.RIGHT, enter, DURATION)
            }

            Companion.NONE -> {}
        }
        return null
    }

    @OnClick(R.id.buttonUp)
    fun onButtonUp() {
        arguments!!.putInt("direction", UP)
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.layout_main, ExampleFragment.newInstance(UP))
        ft.commit()
    }

    @OnClick(R.id.buttonDown)
    fun onButtonDown() {
        arguments!!.putInt("direction", DOWN)
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.layout_main, ExampleFragment.newInstance(DOWN))
        ft.commit()
    }

    @OnClick(R.id.buttonLeft)
    fun onButtonLeft() {
        arguments!!.putInt("direction", LEFT)
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.layout_main, ExampleFragment.newInstance(LEFT))
        ft.commit()
    }

    @OnClick(R.id.buttonRight)
    fun onButtonRight() {
        arguments!!.putInt("direction", RIGHT)
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.layout_main, ExampleFragment.newInstance(RIGHT))
        ft.commit()
    }

    @OnClick(R.id.textAnimationStyle)
    fun switchAnimationStyle(view: View?) {
        @AnimationStyle val styles: IntArray
        styles = intArrayOf(
            MOVE,
            CUBE,
            FLIP,
            PUSHPULL
        )
        for (i in 0 until styles.size - 1) {
            if (styles[i] == sAnimationStyle) {
                setAnimationStyle(styles[i + 1])
                return
            }
        }
        setAnimationStyle(MOVE)
    }

    fun setAnimationStyle(@AnimationStyle style: Int) {
        if (sAnimationStyle != style) {
            sAnimationStyle = style
            setAnimationStyleText()
            Snackbar.make(view!!, "Animation Style is Changed", Snackbar.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setAnimationStyleText() {
        when (sAnimationStyle) {
            Companion.NONE -> mTextAnimationStyle!!.text = "None"
            MOVE -> mTextAnimationStyle!!.text = "Move"
            CUBE -> mTextAnimationStyle!!.text = "Cube"
            FLIP -> mTextAnimationStyle!!.text = "Flip"
            PUSHPULL -> mTextAnimationStyle!!.text = "Push/Pull"
            SIDES -> mTextAnimationStyle!!.text = "Sides"
            CUBEMOVE -> mTextAnimationStyle!!.text = "Cube/Move"
            MOVECUBE -> mTextAnimationStyle!!.text = "Move/Cube"
            PUSHMOVE -> mTextAnimationStyle!!.text = "Push/Move"
            MOVEPULL -> mTextAnimationStyle!!.text = "Move/Pull"
            FLIPMOVE -> mTextAnimationStyle!!.text = "Flip/Move"
            MOVEFLIP -> mTextAnimationStyle!!.text = "Move/Flip"
            FLIPCUBE -> mTextAnimationStyle!!.text = "Flip/Cube"
            CUBEFLIP -> mTextAnimationStyle!!.text = "Cube/Flip"
        }
    }

    companion object {
        const val NONE = 0
        const val MOVE = 1
        const val CUBE = 2
        const val FLIP = 3
        const val PUSHPULL = 4
        const val SIDES = 5
        const val CUBEMOVE = 6
        const val MOVECUBE = 7
        const val PUSHMOVE = 8
        const val MOVEPULL = 9
        const val FLIPMOVE = 10
        const val MOVEFLIP = 11
        const val FLIPCUBE = 12
        const val CUBEFLIP = 13
    }
*/
}