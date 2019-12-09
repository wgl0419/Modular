package cn.leo.modular

import cn.leo.base.base.BaseModelFragment
import cn.leo.base.support.actionBar
import cn.leo.base.support.setActionBarTitle
import cn.leo.frame.support.singleClick
import cn.leo.frame.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author : ling luo
 * @date : 2019-12-04
 */
class TestFragment : BaseModelFragment<TestModel>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onInitialize() {
        super.onInitialize()
        actionBar("", "菜单") {
            toast("点击菜单")
        }

        tvTest.singleClick {
            model.setTitle("标题变了")
        }
    }

    override fun onInitObserve() {
        super.onInitObserve()

        //最简单的订阅方法，左侧返回值 和 右侧所需参数一致，加号 链接双方，左侧结果直接给右侧
        model::setTitle + ::setActionBarTitle

    }

    private fun setTitle(title: String) {
        setActionBarTitle(title)
    }

    override fun hasActionBar(): Boolean {
        return true
    }

}