package space.lianxin.comm.ui.fragment

import android.view.View
import com.airbnb.epoxy.EpoxyRecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import space.lianxin.comm.databinding.CommFragmentListBinding

/**
 * ===========================================
 * 带刷新的列表Fragment基类。
 *
 * @author: lianxin
 * @date: 2021/3/21 13:31
 * ===========================================
 */
abstract class ComListFragment : ComMvRxEpoxyFragment<CommFragmentListBinding>() {

    override fun invalidate() {
        binding.recyclerView.requestModelBuild()
    }

    override fun initView(root: View?) {
        binding.recyclerView.setController(epoxyController)
        initRecyclerView(binding.recyclerView)
        initRefreshLayout(binding.refreshLayout)
    }

    protected open fun initRecyclerView(recyclerView: EpoxyRecyclerView) {}

    protected open fun initRefreshLayout(smartRefreshLayout: SmartRefreshLayout) {}

    protected fun finishRefresh() {
        binding.refreshLayout.finishRefresh()
    }

    protected fun autoRefresh() {
        binding.refreshLayout.autoRefresh()
    }

}