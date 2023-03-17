package fit.wenchao.kotlinplayground.dao.repo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RecordDaoTest{

    @Autowired
    lateinit var recordDao: RecordDao;

    @Test
    fun list() {
        println(recordDao.list())
    }
}

