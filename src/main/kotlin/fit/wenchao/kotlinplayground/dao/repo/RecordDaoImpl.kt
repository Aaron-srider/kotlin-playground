package fit.wenchao.kotlinplayground.dao.repo

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import fit.wenchao.kotlinplayground.dao.mapper.RecordMapper
import fit.wenchao.kotlinplayground.dao.po.RecordPO
import org.springframework.stereotype.Repository

@Repository
class RecordDaoImpl: RecordDao, ServiceImpl<RecordMapper, RecordPO>() {
}