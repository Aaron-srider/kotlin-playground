package fit.wenchao.kotlinplayground.dao.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("account_book")
data class ExpenditurePO(
    @TableId(value = "id", type = IdType.AUTO)
    var id: Int = 0,
    var value: String = "",
    var name: String = "",
) {
}