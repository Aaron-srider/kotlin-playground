package fit.wenchao.kotlinplayground.basic.lateinit

class Bean{

    var age: Int = 0

    var name: String= ""

    lateinit var addresses: List<String>

    override fun toString(): String {
        return "Bean(age=$age, name='$name', addresses=$addresses)"
    }

}

fun main() {

    doInit();

    dontInit();

}

fun dontInit() {
    var bean = Bean();
    println(bean)
}

fun doInit() {
    var bean = Bean();

    var addresses = ArrayList<String>();

    addresses.add("hello");
    addresses.add("world");
    addresses.add("you");
    addresses.add("me");

    // you have to init a lateinit property before using it. Otherwise UninitializedPropertyAccessException
    // will be thrown
    bean.addresses = addresses


    println(bean)
}
