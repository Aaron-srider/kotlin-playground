package fit.wenchao.kotlinplayground.dao.repo

import com.baomidou.mybatisplus.extension.service.IService
import fit.wenchao.kotlinplayground.dao.po.AccountBookPO
import fit.wenchao.kotlinplayground.dao.po.AccountPO
import fit.wenchao.kotlinplayground.dao.po.RecordPO
import fit.wenchao.kotlinplayground.dao.po.UserPO
import org.apache.catalina.User

interface AccountBookDao: IService<AccountBookPO> {
}