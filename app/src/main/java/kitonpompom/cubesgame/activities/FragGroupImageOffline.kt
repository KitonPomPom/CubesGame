package kitonpompom.cubesgame.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.databinding.FragmentFragGroupImageOfflineBinding


class FragGroupImageOffline : Fragment() {

    lateinit var binding : FragmentFragGroupImageOfflineBinding

    private var adapterFragOfflineGroupImage: AdapterFragOfflineGroupImage? = null
    var arrayHeadCity: ArrayList<String> = ArrayList()
    var arrayHeadAuto: ArrayList<String> = ArrayList()
    var arrayHeadFish: ArrayList<String> = ArrayList()
    var arrayHeadAnimal: ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragGroupImageOfflineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayHeadCity.clear()
        arrayHeadAuto.clear()
        arrayHeadFish.clear()
        arrayHeadAnimal.clear()
        arrayHeadCity.add(ArraysContent.headListCity[0])
        arrayHeadCity.add(ArraysContent.headListCity[1])
        arrayHeadAuto.add(ArraysContent.headListAuto[0])
        arrayHeadAuto.add(ArraysContent.headListAuto[1])
        arrayHeadFish.add(ArraysContent.headListFish[0])
        arrayHeadFish.add(ArraysContent.headListFish[1])
        arrayHeadAnimal.add(ArraysContent.headListAnimal[0])
        arrayHeadAnimal.add(ArraysContent.headListAnimal[1])

        binding.idRcViewFragOfflineGroup.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        val categoryItemListCity1: MutableList<CategoryItem> = ArrayList()
        categoryItemListCity1.add(CategoryItem(1, R.drawable.city1))
        categoryItemListCity1.add(CategoryItem(1, R.drawable.city2))
        categoryItemListCity1.add(CategoryItem(1, R.drawable.city3))
        categoryItemListCity1.add(CategoryItem(1, R.drawable.city4))
        categoryItemListCity1.add(CategoryItem(1, R.drawable.city5))
        categoryItemListCity1.add(CategoryItem(1, R.drawable.city6))

        val categoryItemListCity2: MutableList<CategoryItem> = ArrayList()
        categoryItemListCity2.add(CategoryItem(2, R.drawable.city21))
        categoryItemListCity2.add(CategoryItem(2, R.drawable.city22))
        categoryItemListCity2.add(CategoryItem(2, R.drawable.city23))
        categoryItemListCity2.add(CategoryItem(2, R.drawable.city24))
        categoryItemListCity2.add(CategoryItem(2, R.drawable.city25))
        categoryItemListCity2.add(CategoryItem(2, R.drawable.city26))

        val categoryItemListAnimal1: MutableList<CategoryItem> = ArrayList()
        categoryItemListAnimal1.add(CategoryItem(1, R.drawable.animal11))
        categoryItemListAnimal1.add(CategoryItem(1, R.drawable.animal12))
        categoryItemListAnimal1.add(CategoryItem(1, R.drawable.animal13))
        categoryItemListAnimal1.add(CategoryItem(1, R.drawable.animal14))
        categoryItemListAnimal1.add(CategoryItem(1, R.drawable.animal15))
        categoryItemListAnimal1.add(CategoryItem(1, R.drawable.animal16))

        val categoryItemListAnimal2: MutableList<CategoryItem> = ArrayList()
        categoryItemListAnimal2.add(CategoryItem(2, R.drawable.animal21))
        categoryItemListAnimal2.add(CategoryItem(2, R.drawable.animal22))
        categoryItemListAnimal2.add(CategoryItem(2, R.drawable.animal23))
        categoryItemListAnimal2.add(CategoryItem(2, R.drawable.animal24))
        categoryItemListAnimal2.add(CategoryItem(2, R.drawable.animal25))
        categoryItemListAnimal2.add(CategoryItem(2, R.drawable.animal26))

        val categoryItemListFish1: MutableList<CategoryItem> = ArrayList()
        categoryItemListFish1.add(CategoryItem(1, R.drawable.fish11))
        categoryItemListFish1.add(CategoryItem(1, R.drawable.fish12))
        categoryItemListFish1.add(CategoryItem(1, R.drawable.fish13))
        categoryItemListFish1.add(CategoryItem(1, R.drawable.fish14))
        categoryItemListFish1.add(CategoryItem(1, R.drawable.fish15))
        categoryItemListFish1.add(CategoryItem(1, R.drawable.fish16))

        val categoryItemListFish2: MutableList<CategoryItem> = ArrayList()
        categoryItemListFish2.add(CategoryItem(2, R.drawable.fish21))
        categoryItemListFish2.add(CategoryItem(2, R.drawable.fish22))
        categoryItemListFish2.add(CategoryItem(2, R.drawable.fish23))
        categoryItemListFish2.add(CategoryItem(2, R.drawable.fish24))
        categoryItemListFish2.add(CategoryItem(2, R.drawable.fish25))
        categoryItemListFish2.add(CategoryItem(2, R.drawable.fish26))

        val categoryItemListAuto1: MutableList<CategoryItem> = ArrayList()
        categoryItemListAuto1.add(CategoryItem(1, R.drawable.auto11))
        categoryItemListAuto1.add(CategoryItem(1, R.drawable.auto12))
        categoryItemListAuto1.add(CategoryItem(1, R.drawable.auto13))
        categoryItemListAuto1.add(CategoryItem(1, R.drawable.auto14))
        categoryItemListAuto1.add(CategoryItem(1, R.drawable.auto15))
        categoryItemListAuto1.add(CategoryItem(1, R.drawable.auto16))

        val categoryItemListAuto2: MutableList<CategoryItem> = ArrayList()
        categoryItemListAuto2.add(CategoryItem(2, R.drawable.auto21))
        categoryItemListAuto2.add(CategoryItem(2, R.drawable.auto22))
        categoryItemListAuto2.add(CategoryItem(2, R.drawable.auto23))
        categoryItemListAuto2.add(CategoryItem(2, R.drawable.auto24))
        categoryItemListAuto2.add(CategoryItem(2, R.drawable.auto25))
        categoryItemListAuto2.add(CategoryItem(2, R.drawable.auto26))

        val allCategoryCity: MutableList<AllCategories> = ArrayList()
        allCategoryCity.add(AllCategories("Один",categoryItemListCity1))
        allCategoryCity.add(AllCategories("Два",categoryItemListCity2))

        val allCategoryAnimal: MutableList<AllCategories> = ArrayList()
        allCategoryAnimal.add(AllCategories("Один",categoryItemListAnimal1))
        allCategoryAnimal.add(AllCategories("Два",categoryItemListAnimal2))

        val allCategoryFish: MutableList<AllCategories> = ArrayList()
        allCategoryFish.add(AllCategories("Один",categoryItemListFish1))
        allCategoryFish.add(AllCategories("Два",categoryItemListFish2))

        val allCategoryAuto: MutableList<AllCategories> = ArrayList()
        allCategoryAuto.add(AllCategories("Один",categoryItemListAuto1))
        allCategoryAuto.add(AllCategories("Два",categoryItemListAuto2))

        adapterFragOfflineGroupImage = AdapterFragOfflineGroupImage()
        binding.idRcViewFragOfflineGroup.adapter = adapterFragOfflineGroupImage
        adapterFragOfflineGroupImage!!.updateAdapter(allCategoryCity)

        binding.idTabeLayGroupOffline.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryCity)
                    1 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryAnimal)
                    2 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryFish)
                    3 -> adapterFragOfflineGroupImage!!.updateAdapter(allCategoryAuto)
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
    }

    companion object {
        fun newInstance(){}
    }

}