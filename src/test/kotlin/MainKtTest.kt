import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun sumRateMasterMaestro_noRate() {
        val sumMons = 5000_00
        val amount = 2000_00
        val result = sumRateMasterMaestro(sumMons, amount)
        assertEquals(0, result)
    }

    @Test
    fun sumRateMasterMaestro() {
       val sumMons = 74000_00
        val amount = 2000_00
        val result = sumRateMasterMaestro(sumMons, amount)
        assertEquals(23_00, result)
    }

    @Test
    fun sumRateVisaMir_fixTax() {
        val amount = 1000_00
        val result = sumRateVisaMir(amount)
        assertEquals(35_00, result)
    }
    @Test
    fun sumRateVisaMir_WithRate() {
        val amount = 5000_00
        val result = sumRateVisaMir(amount)
        assertEquals(37_50, result)
    }

    @Test
    fun sumRateVkPay_0() {
        val amount = 1000_00
        val result = sumRateVkPay(amount)
        assertEquals(0, result)
    }

    @Test
    fun checkTransferAllCard_true() {
        val amountDay = 10_000_00
        val dayLimit = 150_000_00
        val sumMonth = 500_000_00
        val limitPullMonth = 600_000_00
        val amount = 50_000_00
        val result = checkTransferAllCard(amountDay, dayLimit, sumMonth, limitPullMonth, amount)
        assertEquals(true, result)
    }

    @Test
    fun checkTransferAllCard_overDay() {
        val amountDay = 10_000_00
        val dayLimit = 150_000_00
        val sumMonth = 50_000_00
        val limitPullMonth = 600_000_00
        val amount = 200_000_00
        val result = checkTransferAllCard(amountDay, dayLimit, sumMonth, limitPullMonth, amount)
        assertEquals(false, result)
    }

    @Test
    fun checkTransferAllCard_overMonth() {
        val amountDay = 10_000_00
        val dayLimit = 150_000_00
        val sumMonth = 500_000_00
        val limitPullMonth = 600_000_00
        val amount = 100_001_00
        val result = checkTransferAllCard(amountDay, dayLimit, sumMonth, limitPullMonth, amount)
        assertEquals(false, result)
    }

    @Test
    fun checkTransferVKpay_true() {
        val sumMonth = 30_000_00
        val amount = 1000_00
        val result = checkTransferVKpay(sumMonth, amount)
        assertEquals(true, result)
    }
    @Test
    fun checkTransferVKpay_overMonth() {
        val sumMonth = 39_000_00
        val amount = 1500_00
        val result = checkTransferVKpay(sumMonth, amount)
        assertEquals(false, result)
    }
    @Test
    fun checkTransferVKpay_overPay() {
        val sumMonth = 20_000_00
        val amount = 15500_00
        val result = checkTransferVKpay(sumMonth, amount)
        assertEquals(false, result)
    }

    @Test
    fun checkMain_pay() {
        main()
    }
}