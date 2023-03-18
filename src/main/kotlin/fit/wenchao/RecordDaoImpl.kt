package fit.wenchao

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import fit.wenchao.kotlinplayground.dao.mapper.RecordMapper
import fit.wenchao.kotlinplayground.dao.po.RecordPO
import fit.wenchao.kotlinplayground.dao.repo.RecordDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class RecordDaoImpl: RecordDao, ServiceImpl<fit.wenchao.kotlinplayground.dao.mapper.RecordMapper, fit.wenchao.kotlinplayground.dao.po.RecordPO>() {

    @Autowired
    lateinit var recordMapper: RecordMapper
    override fun list(): MutableList<RecordPO> {
        return recordMapper.selectList(null)
    }

}