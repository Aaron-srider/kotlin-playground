package fit.wenchao.kotlinplayground.dao.po;

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data
import lombok.ToString

@TableName("user")
data class AccountPO(
    @TableId(value = "id", type = IdType.AUTO)
    private var id: Int = 0,
    var number: String = "",
    var ownerId: Int = 0
)





