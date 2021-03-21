package space.lianxin.ledger.component.main.ui

import space.lianxin.comm.ui.activity.TitleActivity
import space.lianxin.ledger.databinding.ActivityMainBinding

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/21 14:48
 * ===========================================
 */
class MainActivity : TitleActivity<ActivityMainBinding>() {

    override fun initContentBinding() = ActivityMainBinding.inflate(layoutInflater)


}