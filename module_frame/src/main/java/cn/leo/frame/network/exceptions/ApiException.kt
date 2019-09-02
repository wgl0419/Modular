package cn.leo.frame.network.exceptions

/**
 * @author : ling luo
 * @date : 2019-04-26
 */
class ApiException : Exception {
    /*错误码*/
    @get:CodeException.CodeEp
    var code: Int = 0
    /*显示的信息*/
    var displayMessage: String? = null

    constructor(e: Throwable) : super(e)

    constructor(cause: Throwable, @CodeException.CodeEp code: Int, showMsg: String)
            : super(showMsg, cause) {
        this.code = code
        displayMessage = showMsg
    }
}
