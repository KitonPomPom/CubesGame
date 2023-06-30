package kitonpompom.cubesgame.activities.utils

import android.animation.ObjectAnimator
import android.graphics.Path
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.fragment.app.FragmentActivity
import com.plattysoft.leonids.ParticleSystem
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.dialogs.FinishCongratulationDialog
import java.util.Timer
import java.util.TimerTask

//Класс для показа анимации если собрали одну картинку
object FinishAnimationCongratulation {
    var timer: Timer? = null

    fun animationFinishStart(
        viewRcView: View,
        viewFinishAnimation: LinearLayout,
        dialogFinishCongratulationDialog:FinishCongratulationDialog,
        act: FragmentActivity, colorAnimation: Boolean) {
        //Получаем координаты положения РЦВью
        val rcViewX = viewRcView.x
        val rcViewY = viewRcView.y
        //Подымаем Вью на которой будет показана анимация
        viewFinishAnimation.elevation = 1f
        //Передаем Вью на которой будет анимация положения начала
        viewFinishAnimation.x = rcViewX
        viewFinishAnimation.y = rcViewY
        //Создаем патчи где указываем начало и конец движения
        //moveTo - Начальная точка движения
        //lineTo - Конечная точка движения
        val path = Path().apply {
            moveTo(rcViewX, rcViewY)
            lineTo(rcViewX + viewRcView.width, rcViewY)
        }
        val path2 = Path().apply {
            moveTo(rcViewX + viewRcView.width, rcViewY)
            lineTo(rcViewX + viewRcView.width, rcViewY + viewRcView.height)
        }
        val path3 = Path().apply {
            moveTo(rcViewX + viewRcView.width, rcViewY + viewRcView.height)
            lineTo(rcViewX, rcViewY + viewRcView.height)
        }
        val path4 = Path().apply {
            moveTo(rcViewX, rcViewY + viewRcView.height)
            lineTo(rcViewX, rcViewY)
        }
        //анимация перемещения кубика которого тянули по координатам
        ObjectAnimator.ofFloat(viewFinishAnimation, View.X, View.Y, path).apply {
            doOnStart {

                startTimer(act, viewFinishAnimation, colorAnimation)
                //doAnimation(activity as FragmentActivity, binding.layFragPlayPwpEasy.idViewFinishAnimation)
            }
            duration = 900
            start()
            doOnEnd {
                ObjectAnimator.ofFloat(
                    viewFinishAnimation,
                    View.X,
                    View.Y,
                    path2
                ).apply {
                    duration = 800
                    start()
                    doOnEnd {
                        ObjectAnimator.ofFloat(
                            viewFinishAnimation,
                            View.X,
                            View.Y,
                            path3
                        ).apply {
                            duration = 900
                            start()
                            doOnEnd {
                                ObjectAnimator.ofFloat(
                                    viewFinishAnimation,
                                    View.X,
                                    View.Y,
                                    path4
                                ).apply {
                                    duration = 800
                                    start()
                                    doOnEnd {
                                        timer?.cancel()
                                        dialogFinishCongratulationDialog.createFinishCongratulationDialog(act)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    //Создаем таймер который с определенным периодом будет запускать анимацию
    fun startTimer(activity: FragmentActivity, viewFinishAnimation: LinearLayout, colorAnimation: Boolean){
        timer = Timer()
        timer?.schedule(object : TimerTask(){
            override fun run() {
                //Так как таймер запускается во второстепенном потоке
                //Запускаем измениния на экране только на первостепенном потоке
                activity.runOnUiThread{
                    doAnimation(activity, viewFinishAnimation, colorAnimation)
                }
            }
        //Задержка 0, период 5
        }, 0,5)
    }

    fun doAnimation(act: FragmentActivity, view: View, colorAnimation: Boolean): ParticleSystem {
        var starColor = view.context.getDrawable(R.drawable.star_black)
        if(!colorAnimation)
            starColor =  view.context.getDrawable(R.drawable.star_white)
        //val ps = ParticleSystem(activity as FragmentActivity, 100, R.drawable.star_pink, 400L)
        val ps = ParticleSystem(act, 3, starColor, 500L)
        ps.setSpeedRange(0.1f, 0.25f)
        ps.setScaleRange(0.7f, 1.3f)
        ps.setSpeedRange(0.1f, 0.25f)
        ps.setAcceleration(0.0001f, 90)
        ps.setRotationSpeedRange(90f, 180f)
        ps.setFadeOut(50, AccelerateInterpolator())
        ps.setStartTime(3000L)
        //ps.emit(1,1,100)
        ps.oneShot(view, 3)
        //ps.emit(view, 5)
        //particlesPerSecond - кол-во звездочек в секунду (Частиц)
        //maxParticles - кол-во частиц
        //timeToLive - Время жизни частицын
        //setSpeedRange - диапазон скоростей(не меняется)
        //setScaleRange - размер частиц
        //setAcceleration - ускорение, угол
        //setRotationSpeedRange - диапазаон вращения
        //setFadeOut - затухание
        return ps
    }
}