package kitonpompom.cubesgame.activities.utils

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import io.ak1.pix.models.Flash
import io.ak1.pix.models.Mode
import io.ak1.pix.models.Options
import io.ak1.pix.models.Ratio
import io.ak1.pix.utility.ARG_PARAM_PIX


//Класс для открытия камеры и добавления фото. Устанавливаем тут параметры камеры и загружаемых фото.
object ImagePicker {

    fun getOptions(cont:Int): Options {
        val options = Options().apply {
            ratio = Ratio.RATIO_AUTO                                    //Image/video capture ratio
            count =
                cont                                                   //Number of images to restrict selection count
            spanCount = 4                                               //Number for columns in grid
            path =
                "Pix/Camera"                                         //Custom Path For media Storage
            isFrontFacing = true                                      //Front Facing camera on start
            mode =
                Mode.Picture                                            //Option to select only pictures or videos or both
            flash =
                Flash.Auto                                          //Option to select flash type
            preSelectedUrls = ArrayList<Uri>()                          //Pre selected Image Urls
        }
        return options
    }
    /*fun getImages(context: AppCompatActivity, imageCounter: Int, rCode: Int){
        val options = Options.init()
            .setRequestCode(rCode)
            .setCount(imageCounter)
            .setFrontfacing(false)
            .setMode(Options.Mode.Picture)
            .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)
            .setPath("pix/images");
        Pix.start(context, options);
    }*/
}