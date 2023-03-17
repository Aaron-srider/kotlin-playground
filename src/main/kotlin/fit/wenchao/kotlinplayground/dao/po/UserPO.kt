package fit.wenchao.kotlinplayground.dao.po;

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
data class UserPO(
    @TableId(value = "id", type = IdType.AUTO)
    var id: Int = 0,
    var username: String = "",
    var password: String = ""
) {
}





