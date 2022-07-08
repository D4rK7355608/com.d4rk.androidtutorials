package com.d4rk.androidtutorials.ui.android.buttons
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.ui.android.buttons.tabs.TabCodeFragment
import com.d4rk.androidtutorials.ui.android.buttons.tabs.TabLayoutFragment
import com.google.android.material.tabs.TabLayout
import com.kieronquinn.monetcompat.app.MonetCompatActivity
class ButtonsCodeActivity: MonetCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        val tabs : TabLayout = findViewById(R.id.tabs)
        val viewpager : ViewPager = findViewById(R.id.viewpager)
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TabCodeFragment(), getString(R.string.code_kotlin))
        adapter.addFragment(TabLayoutFragment(), getString(R.string.layout_xml))
        viewPager.adapter = adapter
    }
    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }
        override fun getCount(): Int {
            return mFragmentList.size
        }
        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}