package fit.wenchao.kotlinplayground.dao.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import fit.wenchao.kotlinplayground.dao.po.UserPO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper : BaseMapper<UserPO>



