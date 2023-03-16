package fit.wenchao.kotlinplayground.dao.po;

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data

@TableName("user")
@Data
class UserPO(
        @TableId(value = "id", type = IdType.AUTO)
        var id: Int,
        var name: String,
        var age: Int
)




