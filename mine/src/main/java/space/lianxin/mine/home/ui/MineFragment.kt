package space.lianxin.mine.home.ui

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.ui.fragment.ComMvRxEpoxyFragment
import space.lianxin.mine.databinding.MineFragmentMineBinding

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/25 20:59
 * ===========================================
 */
@Route(path = RouterConstants.Mine.MineFragment)
class MineFragment : ComMvRxEpoxyFragment<MineFragmentMineBinding>() {

    override fun inflateBinding() = MineFragmentMineBinding.inflate(layoutInflater)

    override fun initView(root: View?) {
    }

    override fun initData() {
    }

    override fun buildEpoxyController() = simpleController { }
}