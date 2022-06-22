fun sumRateMasterMaestro(sumMonth: Int = 0, amount: Int): Int {
    val sumMonthCurr = sumMonth + amount
    val discountMonth = 75000_00 //лимит в месяц после которого берут процент по "MasterCard" и "Maestro"
    val rateTaxMasterMaestro = 0.006 //процент свыше месячного лимита по "MasterCard" и "Maestro"
    val fixTaxMasterMaestro = 20_00 //добавка свыше месячного лимита при переводах по "MasterCard" и "Maestro"
    return if (sumMonthCurr < discountMonth) 0 else ((sumMonthCurr-discountMonth) * rateTaxMasterMaestro + fixTaxMasterMaestro).toInt()
}

fun sumRateVisaMir(amount: Int): Int {
    val rateTaxVisaMir = 0.0075 //процент при переводах по "Visa" и "Мир"
    val fixTaxVisaMir = 35_00 //минимальная комиссия при переводах по "Visa" и "Мир"
    val summRate: Int
    if (amount * rateTaxVisaMir < fixTaxVisaMir) {
        summRate = fixTaxVisaMir
    } else {
        summRate = (amount * rateTaxVisaMir).toInt()
    }
    return summRate
}

fun sumRateVkPay(amount: Int): Int {
    val summRate: Int = 0 //в последствие здесь может быть логика рассчета комиссии
    return summRate
}

fun checkTransferAllCard(
    amountDay: Int = 0,
    dayLimit: Int,
    sumMonth: Int = 0,
    limitPullMonth: Int,
    amount: Int,
): Boolean {
    val sumMonthCurr = sumMonth + amount
    return when {
        (amount + amountDay > dayLimit) -> {
            println("Превышен дневной лимит перевода по картам ${dayLimit/100} руб.")
            false
        }
        (sumMonthCurr > limitPullMonth) -> {
            println("Превышен месячный лимит на перевод ${limitPullMonth/100} руб.")
            false
        }
        else -> true
    }

}

fun checkTransferVKpay(sumMonth: Int = 0, amount: Int): Boolean {
    val sumMonthCurr = sumMonth + amount
    val limitVkpayMonth = 40000_00 //месячный лимит на перевод по картам VkPay
    val vKCurrPay = 15000_00 //лимит на текущий перевод по картам VkPay
    return when {
        (sumMonthCurr > limitVkpayMonth) -> {
            println("Превышен месячный лимит перевода по карте VKpay ${limitVkpayMonth/100} руб.")
            false
        }
        (amount > vKCurrPay) -> {
            println("Превышен лимит на текущий перевод по карте VKpay ${vKCurrPay/100} руб.")
            false
        }
        else -> true
    }
}

fun main() {
    val sumMonth: Int = 78000_00 //общая сумма переводов за текущий месяц без учета комиссий
    val typeCard: String = "Visa" //тип карты отправителя
    val amount: Int = 500_00 //сумма превода
    val dayLimit: Int = 150_000_00 //дневной лимит перевода в день по всем картам, кроме VK pay
    val limitPullMonth = 600_000_00 //месячный лимит на отправку по всем картам, кроме VK pay
    val amountDay = 1000_00 //сумма переводов за текущий день
    var result: Int = -1 //вычисленная комиссия за перевод
    println("Пытаемся перевести сумму ${amount/100} рублей по карте $typeCard")
    println("Сумма переводов за сегодня ${amountDay/100} руб, за текущий месяц  ${sumMonth/100} руб")
    when (typeCard) {
        "MasterCard", "Maestro" -> if (checkTransferAllCard(amountDay, dayLimit, sumMonth, limitPullMonth, amount))
            result = sumRateMasterMaestro(sumMonth, amount)

        "Visa", "Мир" -> if (checkTransferAllCard(amountDay, dayLimit, sumMonth, limitPullMonth, amount))
            result = sumRateVisaMir(amount)

        "Vk Pay" -> if (checkTransferVKpay(sumMonth, amount)) result = sumRateVkPay(amount)
        else -> result = -1
    }
    if (result >= 0) {
        println("Комиссия за перевод составит: $result копеек")
    } else println("Перевод невозможен!")
}

