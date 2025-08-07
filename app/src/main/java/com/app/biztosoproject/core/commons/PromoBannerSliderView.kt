//package com.app.biztosoproject.core.commons
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.widget.FrameLayout
//import androidx.viewpager2.widget.ViewPager2
//import com.app.biztosoproject.R
//import com.app.biztosoproject.data.models.PromoSlider
//import com.app.biztosoproject.presentation.adapters.PromoSliderAdapter
//import com.google.android.material.tabs.TabLayout
//import com.google.android.material.tabs.TabLayoutMediator
//
//
//class PromoBannerSliderView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyle: Int = 0
//) : FrameLayout(context, attrs, defStyle) {
//
//    private val viewPager: ViewPager2
//    private val tabLayout: TabLayout
//    private val adapter = PromoSliderAdapter()
//
//    init {
//        LayoutInflater.from(context).inflate(R.layout.view_carousal_slider, this, true)
//        viewPager = findViewById(R.id.carouselViewPager)
//        tabLayout = findViewById(R.id.tabDots)
//        viewPager.adapter = adapter
//    }
//
//    fun setSliderItems(items: List<PromoSlider>) {
//        adapter.submitList(items)
//
//        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
//
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                tab.setCustomView(R.layout.tab_selected)
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//                tab.setCustomView(R.layout.tab_unselected)
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })
//
//        for (i in 0 until tabLayout.tabCount) {
//            val tab = tabLayout.getTabAt(i)
//            tab?.setCustomView(if (i == 0) R.layout.tab_selected else R.layout.tab_unselected)
//        }
//    }
//
//}