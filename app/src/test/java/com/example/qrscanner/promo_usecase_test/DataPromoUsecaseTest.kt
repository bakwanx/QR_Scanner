package com.example.qrscanner.promo_usecase_test

import com.example.qrscanner.presentation.home.model.Formats
import com.example.qrscanner.presentation.home.model.ImageSize
import com.example.qrscanner.presentation.home.model.Img
import com.example.qrscanner.presentation.home.model.PromoModel

class DataPromoUsecaseTest {
    fun dataTest() : ArrayList<PromoModel>{
        val dataTest: ArrayList<PromoModel> = ArrayList<PromoModel>()
        dataTest.add(
            PromoModel(
                8,
                "",
                "2021-04-14T03:35:09.233Z",
                "2021-04-14T03:35:07.506Z",
                "2021-07-31T14:48:46.361Z",
                "",
                "BNI Credit Card",
                "Potongan langsung (diskon) Rp 150.000,- untuk minimal transaksi Rp 1.000.000, kuota 15 transaksi pertama per hari.\n- Berlaku tiap Kamis dan Jumat.\n- Berlaku untuk pembelian Tiket Sriwijaya Air dan NAM Air di Website dan Mobile Apps Sriwijaya Air\n- Potongan harga langsung diperoleh ketika nomor Kartu BNI dimasukkan (tanpa kode promo)\n- Syarat dan ketentuan berlaku\nInfo lebih lanjut hubungi BNI Call 1500046",
                "-6.203606",
                "106.803022",
                "17",
                "2021-04-13",
                "Pejompongan",
                14,
                Img(
                    3,
                    "bni-credit-card-banner-fitur-mbanking-small.jpg",
                    "",
                    "",
                    825,
                    361,
                    formats = Formats(
                        ImageSize(
                            ".jpg",
                            "https://strapi-jkt-digi46.s3.ap-southeast-3.amazonaws.com/small_bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c.jpg",
                            "small_bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c",
                            "image/jpeg",
                            "small_bni-credit-card-banner-fitur-mbanking-small.jpg",
                            "",
                            18.45,
                            500,
                            219
                        ),
                        ImageSize(
                            ".jpg",
                            "https://strapi-jkt-digi46.s3.ap-southeast-3.amazonaws.com/medium_bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c.jpg",
                            "medium_bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c",
                            "image/jpeg",
                            "medium_bni-credit-card-banner-fitur-mbanking-small.jpg",
                            "",
                            33.43,
                            750,
                            328
                        ),
                        ImageSize(
                            ".jpg",
                            "https://strapi-jkt-digi46.s3.ap-southeast-3.amazonaws.com/thumbnail_bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c.jpg",
                            "thumbnail_bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c",
                            "image/jpeg",
                            "thumbnail_bni-credit-card-banner-fitur-mbanking-small.jpg",
                            "",
                            6.15,
                            245,
                            107
                        )
                    ),
                    "bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c",
                    ".jpg",
                    "image/jpeg",
                    39.9,
                    "https://strapi-jkt-digi46.s3.ap-southeast-3.amazonaws.com/bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c.jpg",
                    "",
                    "aws-s3",
                    "",
                    "2021-04-02T19:17:54.427Z",
                    "2021-04-02T19:17:54.453Z"
                ),

                )
        )
        return dataTest
    }
}