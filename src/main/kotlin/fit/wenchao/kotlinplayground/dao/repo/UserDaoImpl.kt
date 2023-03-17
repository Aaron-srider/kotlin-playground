package fit.wenchao.kotlinplayground.dao.repo

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import fit.wenchao.kotlinplayground.dao.mapper.RecordMapper
import fit.wenchao.kotlinplayground.dao.mapper.UserMapper
import fit.wenchao.kotlinplayground.dao.po.RecordPO
import fit.wenchao.kotlinplayground.dao.po.UserPO
import org.apache.catalina.User
import org.springframework.stereotype.Repository

@Repository
class UserDaoImpl: UserDao, ServiceImpl<UserMapper, UserPO>() {
}