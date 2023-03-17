package fit.wenchao.kotlinplayground.basic.nullsafty

class Bean {
}

fun main() {
    val firstName = "Tom"
    val secondName = "Michael"
    val names: List<String?> = listOf(firstName, null, secondName)

    var res = listOf<String?>()
    for (item in names) {
//        println(item?.length)
        item?.let { res = res.plus(it) }
    }

    for (item in names) {
        item?.run { println(this)
            res = res.plus(this) }
    }

    println(res.size)
}