@file:Suppress("DEPRECATION")
package com.d4rk.androidtutorials.ui.android.buttons
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.ui.android.buttons.tabs.ButtonsTabCodeFragment
import com.d4rk.androidtutorials.ui.android.buttons.tabs.ButtonsTabLayoutFragment
import com.google.android.material.tabs.TabLayout
class ButtonsCodeActivity: AppCompatActivity() {
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
        adapter.addFragment(ButtonsTabCodeFragment(), getString(R.string.code_kotlin))
        adapter.addFragment(ButtonsTabLayoutFragment(), getString(R.string.layout_xml))
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