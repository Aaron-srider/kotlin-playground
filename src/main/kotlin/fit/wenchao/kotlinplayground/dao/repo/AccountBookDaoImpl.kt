package fit.wenchao.kotlinplayground.dao.repo

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import fit.wenchao.kotlinplayground.dao.mapper.AccountBookMapper
import fit.wenchao.kotlinplayground.dao.mapper.RecordMapper
import fit.wenchao.kotlinplayground.dao.po.AccountBookPO
import fit.wenchao.kotlinplayground.dao.po.RecordPO
import org.springframework.stereotype.Repository

@Repository
class AccountBookDaoImpl: AccountBookDao, ServiceImpl<AccountBookMapper, AccountBookPO>() {
}