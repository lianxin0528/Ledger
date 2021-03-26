package space.lianxin.ledger.component.main.ui

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import space.lianxin.base.extention.gone
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.constants.arouter.RouterStart
import space.lianxin.comm.ui.activity.TitleActivity
import space.lianxin.ledger.R
import space.lianxin.ledger.databinding.ActivityMainBinding

/**
 * ===========================================
 * 主界面Activity
 *
 * @author: lianxin
 * @date: 2021/3/21 14:48
 * ===========================================
 */
@Route(path = RouterConstants.App.MainActivity)
class MainActivity : TitleActivity<ActivityMainBinding>() {

    override fun initContentBinding() = ActivityMainBinding.inflate(layoutInflater)

    private val fragments = ArrayList<Fragment>() // 子页面列表
    private var currentFragment: Fragment? = null // 选中的fragment

    private var mExitTime: Long = 0 // 点击退出的时间

    override fun initView() {
        super.initView()
        binding.titleBar.root.gone()
        initFragments()
        initNavigationView()
    }

    override fun initData() {
        super.initData()
        switchFragment(0)
    }

    /** 初始化fragment */
    private fun initFragments() {
        fragments.add(RouterStart.getFragment(RouterConstants.Mine.MineFragment))
        fragments.add(RouterStart.getFragment(RouterConstants.Mine.MineFragment))
        fragments.add(RouterStart.getFragment(RouterConstants.Mine.MineFragment))
    }

    override fun onResume() {
        super.onResume()
        ActivityUtils.finishOtherActivities(MainActivity::class.java)
    }

    override fun onBackPressed() {
        closeApp()
    }

    /** 关闭app */
    private fun closeApp() {
        if (System.currentTimeMillis() - mExitTime > 2 * TimeConstants.SEC) {
            ToastUtils.showShort(R.string.comm_exit_app)
            mExitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }

    /** 初始化底部导航栏 */
    private fun initNavigationView() {
        viewBind.naviView.apply {
            itemIconTintList = null
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_SELECTED
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigationFlow -> switchFragment(0)
                    R.id.navigationChart -> switchFragment(1)
                    R.id.navigationMine -> switchFragment(2)
                }
                return@setOnNavigationItemSelectedListener true
            }
        }
    }

    /** 切换主界面的fragment */
    private fun switchFragment(index: Int) {
        if (fragments.isEmpty() || index !in 0 until fragments.size) {
            return
        }
        val fragment: Fragment = fragments[index]
        if (fragment == currentFragment) {
            return
        }
        val tran = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            tran.add(R.id.container, fragment)
        }
        currentFragment?.let {
            tran.hide(it).show(fragment).commit()
        } ?: tran.show(fragment).commit()
        currentFragment = fragment
    }

}