package lv.st.sbogdano.stobjekti.internal.util

fun <T> lazyThreadSafetyNone(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

fun lksToLatLng(in_X: Double, in_Y: Double): Array<Double> {

// konstantes
    val n1 = 0.00167922038638372
    val n2 = n1 * n1
    val n3 = n1 * n2
    val n4 = n2 * n2
    val G = 111132.952547919
    val FalseNm = -6000000.0
    val FalseEm = 500000.0
    val K0 = 0.9996
    val a = 6378137.0
    val nLongCMerid = 24

    val F = 298.257223563
    val f = 1 / F
    val e2 = 2 * f - f * f

    // apr&#275;&#311;ins
    val E4 = in_X - FalseNm
    val F4 = E4 / K0
    val H4 = in_Y - FalseEm
    val I4 = H4 / K0
    val J4 = (F4 * Math.PI) / (G * 180)
    val K4 = J4 * 2
    val L4 = J4 * 4
    val M4 = J4 * 6
    val N4 = J4 * 8
    val X4 = ((3 * n1 / 2.0) - (27 * n3 / 32.0)) * Math.sin(K4)
    val AC4 = ((21 * n2 / 16.0) - (55 * n4 / 32.0)) * Math.sin(L4)
    val AH4 = (151 * n3) * Math.sin(M4) / 96.0
    val AM4 = 1097 * n4 * Math.sin(N4) / 512.0
    val AR4 = J4 + X4 + AC4 + AH4 + AM4
    val AS4 = Math.sin(AR4)
    val AT4 = 1 / Math.cos(AR4)
    val BA4 = a / Math.pow((1 - e2 * AS4 * AS4), 0.5)
    val BL4 = Math.tan(AR4)
    val AZ4 = a * (1 - e2) / Math.pow((1 - e2 * AS4 * AS4), 1.5)
    val BB4 = I4 / BA4
    val BC4 = BB4 * BB4
    val BD4 = BB4 * BC4
    val BE4 = BC4 * BC4
    val BF4 = BE4 * BB4
    val BG4 = BD4 * BD4
    val BH4 = BB4 * BG4
    val BM4 = BL4 * BL4
    val BN4 = BL4 * BM4
    val BO4 = BM4 * BM4
    val BQ4 = BN4 * BN4
    val BR4 = BA4 / AZ4
    val BS4 = BR4 * BR4
    val BT4 = BS4 * BR4
    val BU4 = BS4 * BS4
    val CE4 = -((BL4 / (K0 * AZ4)) * BB4 * H4 / 2.0)
    val CJ4 = (BL4 / (K0 * AZ4)) * (BD4 * H4 / 24) * (-4 * BS4 + 9 * BR4 * (1 - BM4) + 12 * BM4)
    val CO4 =
            -(BL4 / (K0 * AZ4)) * (BF4 * H4 / 720.0) * (8 * BU4 * (11 - 24 * BM4) - 12 * BT4 * (21 - 71 * BM4) + 15 * BS4 * (15 - 98 * BM4 + 15 * BO4) + 180 * BR4 * (5 * BM4 - 3 * BO4) + 360 * BO4)
    val CT4 = (BL4 / (K0 * AZ4)) * (BH4 * H4 / 40320.0) * (1385 + 3633 * BM4 + 4095 * BO4 + 1575 * BQ4)
    val CY4 = AR4 + CE4 + CJ4 + CO4 + CT4
    val CX4 = (CY4 / Math.PI) * 180
    val CU4 = Math.floor(CX4)
    val CV4 = Math.abs(Math.floor((CX4 - CU4) * 60))
    val CW4 = Math.abs((CX4 - CU4 - (CV4 / 60.0)) * 3600)
    val EC4 = (nLongCMerid / 180.0) * Math.PI
    val EH4 = AT4 * BB4
    val EM4 = -AT4 * (BD4 / 6.0) * (BR4 + 2 * BM4)
    val ER4 = AT4 * (BF4 / 120.0) * (-4 * BT4 * (1 - 6 * BM4) + BS4 * (9 - 68 * BM4) + 72 * BR4 * BM4 + 24 * BO4)
    val EW4 = -AT4 * (BH4 / 5040.0) * (61 + 662 * BM4 + 1320 * BO4 + 720 * BQ4)
    val FB4 = EC4 + EH4 + EM4 + ER4 + EW4
    val FA4 = (FB4 / Math.PI) * 180
    val EX4 = Math.floor(FA4)
    val EY4 = Math.abs(Math.floor((FA4 - EX4) * 60))
    val EZ4 = Math.abs((FA4 - EX4 - (EY4 / 60.0)) * 3600)

    // rezultata v&#275;rt&#299;bu pie&#353;&#311;ir&#353;ana
    val out_dBSec = Math.round(CW4 * 100.0) / 100.0

    val out_nLMin = EY4
    val out_dLSec = Math.round(EZ4 * 100.0) / 100.0

    val lat = CU4 + (CV4 * 60 + out_dBSec) / 3600
    val lon = EX4 + (out_nLMin * 60 + out_dLSec) / 3600

    return arrayOf(lat, lon)
}