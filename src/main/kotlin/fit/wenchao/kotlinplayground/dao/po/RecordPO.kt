package fit.wenchao.kotlinplayground.dao.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.math.BigDecimal

@TableName("record")
data class RecordPO(
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long = 0,
    var typeId: Int,
    var time: String,
    @TableField(value = "accountBook_id")
    var accountBookId: Int,
    var accountId: Int,
    var comment: String,
    var amount: BigDecimal
) {
}