package fit.wenchao.kotlinplayground.dao.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("account_book")
data class AccountBookPO(
    @TableId(value = "id", type = IdType.AUTO)
    private var id: Int = 0,
    var name: String = "",
    var ownerId: Int = 0,
    var createTime: String = "",
    var comment: String = "",
) {
}