package fit.wenchao.kotlinplayground.controller

import fit.wenchao.kotlinplayground.dao.repo.RecordDao
import fit.wenchao.kotlinplayground.model.JsonResult
import lombok.extern.slf4j.Slf4j
import org.apache.ibatis.annotations.ResultMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@Slf4j
@RequestMapping("/API")
class RecordController {

    @Autowired
    lateinit var recordDao: RecordDao

    @GetMapping("/records")
    fun getRecords(): JsonResult {
        val list = recordDao.list()
        return JsonResult.ok(list)
    }
}