package fit.wenchao.kotlinplayground.dao.repo

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Repository

@Repository
class UserDaoImpl: UserDao, ServiceImpl<fit.wenchao.kotlinplayground.dao.mapper.UserMapper, fit.wenchao.kotlinplayground.dao.po.UserPO>() {
}