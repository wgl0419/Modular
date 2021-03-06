package cn.leo.modular

import cn.leo.base.arouter.pages.PagesHome
import cn.leo.base.base.BaseModelActivity
import cn.leo.base.bean.WechatUserBean
import cn.leo.base.model.WechatModel
import cn.leo.base.support.SmartRefreshHelper
import cn.leo.base.support.actionBar
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.layout_smart_refresh_list.*

@Route(path = PagesHome.homeMain2Activity)
class Main2Activity :
    BaseModelActivity<WechatModel>(),
    SmartRefreshHelper.IView<WechatUserBean> {

    private val mAdapter by lazy { TestAdapter() }

    private val mHelper by SmartRefreshHelper()

    override fun getLayoutRes(): Int {
        return R.layout.activity_main2
    }

    override fun onInitView() {
        actionBar("SmartRefreshHelper")
        mHelper.statusConfig()
        mHelper.getData()
    }

    override fun getModel(): SmartRefreshHelper.ISource<WechatUserBean> {
        return model
    }

    override fun getAdapter(): SmartRefreshHelper.IAdapter<WechatUserBean> {
        return mAdapter
    }

    override fun getSmartRefresh(): SmartRefreshLayout {
        return smartRefresh
    }
}
