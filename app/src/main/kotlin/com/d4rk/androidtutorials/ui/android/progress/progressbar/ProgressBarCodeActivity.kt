@file:Suppress("DEPRECATION")
package com.d4rk.androidtutorials.ui.android.progress.progressbar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.ui.android.progress.progressbar.tabs.ProgressBarTabCodeFragment
import com.d4rk.androidtutorials.ui.android.progress.progressbar.tabs.ProgressBarTabLayoutFragment
import com.google.android.material.tabs.TabLayout
class ProgressBarCodeActivity: AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.viewpager)
        setupViewPager()
        tabLayout.setupWithViewPager(viewPager)
    }
    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProgressBarTabCodeFragment(), getString(R.string.code_kotlin))
        adapter.addFragment(ProgressBarTabLayoutFragment(), getString(R.string.layout_xml))
        viewPager.adapter = adapter
    }
    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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