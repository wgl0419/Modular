package cn.leo.base.model

import cn.leo.frame.network.MModel
import cn.leo.base.net.Apis
import cn.leo.base.net.Urls

/**
 * @author : ling luo
 * @date : 2019-07-03
 */
open class BaseModel : MModel<Apis>() {

    override fun getBaseUrl(): String {
        return Urls.BASE_URL
    }

}