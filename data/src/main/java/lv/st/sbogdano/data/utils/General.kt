package lv.st.sbogdano.data.utils

fun getFormattedName(name: String): String {

    val regexAS = """(as\s|a/st.\s|as|a/st|a/st.)""".toRegex(RegexOption.IGNORE_CASE)
    val regexTP = """(tp\s|tp-|tp_|tp)""".toRegex(RegexOption.IGNORE_CASE)
    val regexSP = """(sp-|sp_|sp)""".toRegex(RegexOption.IGNORE_CASE)
    val regexFP = """(fp\s|fp-|fp_|fp)""".toRegex(RegexOption.IGNORE_CASE)
    val regexT = """(t\s|t-|t_|t)""".toRegex(RegexOption.IGNORE_CASE)

    return when (true) {
        name.take(2).equals("tp", true) -> regexTP.replace(name, "TP")
        name.first().equals('a', true) -> regexAS.replace(name, "A/st.")
        name.first().equals('s', true) -> regexSP.replace(name, "SP")
        name.first().equals('f', true) -> regexFP.replace(name, "FP")
        else -> regexT.replace(name, "T")
    }
}
