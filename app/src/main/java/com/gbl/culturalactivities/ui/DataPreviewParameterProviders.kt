package com.gbl.culturalactivities.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gbl.culturalactivities.entity.CulturalActivity

class SingleItemPreviewParameterProvider : PreviewParameterProvider<CulturalActivity> {
    override val values: Sequence<CulturalActivity> = sequenceOf(

        object : CulturalActivity() {
            override val id = 1
            override val name = "Тёмные воды"
            override val description =
                "Спецпоказ фильма. Ёсими с пятилетней дочерью живёт в мрачном многоквартирном доме. \n" +
                        "Их преследуют капающая с потолка и сочащаяся из стен тёмная вода \n" +
                        "и маленькая красная сумка, принадлежавшая девочке, которая пропала \n" +
                        "при загадочных обстоятельствах два года назад."
            override val place = "Ленфильм"
            override val link = "https://www.lenfilm.ru/service/Kinoteatr-Lenfilm/"
            override val endingDate = 1719273600000
            override val dateOfVisit = 1719273600000
        },
        object : CulturalActivity() {
            override val id = 0
            override val name = "ARS VIVENDI"
            override val description = "Франс Снейдерс и фламандский натюрморт XVII века"
            override val place = "Эрмитаж"
            override val link =
                "https://hermitagemuseum.org/wps/portal/hermitage/what-s-on/temp_exh/2024/barocco/!ut/p/z1/pVJNU8IwFPwtHnrs5DX9SDnG4ljATkEppbkwaQ0ljm2hRMV_b-rAweFr1JySzO57-_YtYmiOWM3fZcmVbGr-qt8Z8xYxpZ5lBzCMByMCNLaSuwEZ4PGAoPQbAGcOBcR-8H3qA713x1GUPOB4ZO_5FwDscv8ZYoi91iXK2rfuui7kM8qElS891y_MghMwHQK26XPhmdjmPY6fc-LYpEMXtVqrFcpWoq2k4qUwoGhqJWplgNitZC47H7YGKFGtm5a3nwZgwI4BOW-bomiO9B8JZJftSTsZJypM3TCOZwEOnD3gQo1MayCLB7C8sOdYQ98N-0DxbBSEDrGgh1H6LsUHSuqmrfROn37pU3jocG7KCZzqkAbRYpLcPWru3_ZzZSzrn2MNr0VLZ1--bDaM6px0odgpNP97UHQ13EZBVGqRXK1MWS_1Z4dB8wNmXSVJ5duVOYcXt6wW_dtI5P7HdFml_pbe3HwBaNI6Gg!!/dz/d5/L2dBISEvZ0FBIS9nQSEh/?lng=ru"
            override val endingDate = 1725753600000L
            override val dateOfVisit = 1721088000000L
        },
        object : CulturalActivity() {
            override val id = 2
            override val name = "Куплю гараж"
            override val description =
                "Экспозиция покажет, как заветные 18 м² личного пространства становились \n" +
                        "местом самовыражения и территорией свободы миллионов людей, и как \n" +
                        "разворачивалась история гаражной культуры в России. "
            override val place = "Севкабель Порт"
            override val link = "https://sevcableport.ru/ru/afisha/kuplyu-garazh"
            override val endingDate = 1727568000000
            override val dateOfVisit = null
        },
        object : CulturalActivity() {
            override val id = 3
            override val name = "Прогуляться"
            override val description = ""
            override val place = ""
            override val link = ""
            override val endingDate = null
            override val dateOfVisit = null
        },
    )
}

class ListPreviewParameterProvider : PreviewParameterProvider<List<CulturalActivity>> {
    override val values: Sequence<List<CulturalActivity>> = sequenceOf(
        listOf(
            object : CulturalActivity() {
                override val id = 1
                override val name = "Тёмные воды"
                override val description =
                    "Спецпоказ фильма. Ёсими с пятилетней дочерью живёт в мрачном многоквартирном доме. \n" +
                            "Их преследуют капающая с потолка и сочащаяся из стен тёмная вода \n" +
                            "и маленькая красная сумка, принадлежавшая девочке, которая пропала \n" +
                            "при загадочных обстоятельствах два года назад."
                override val place = "Ленфильм"
                override val link = "https://www.lenfilm.ru/service/Kinoteatr-Lenfilm/"
                override val endingDate = 1719273600000
                override val dateOfVisit = 1719273600000
            },
            object : CulturalActivity() {
                override val id = 0
                override val name = "ARS VIVENDI"
                override val description = "Франс Снейдерс и фламандский натюрморт XVII века"
                override val place = "Эрмитаж"
                override val link =
                    "https://hermitagemuseum.org/wps/portal/hermitage/what-s-on/temp_exh/2024/barocco/!ut/p/z1/pVJNU8IwFPwtHnrs5DX9SDnG4ljATkEppbkwaQ0ljm2hRMV_b-rAweFr1JySzO57-_YtYmiOWM3fZcmVbGr-qt8Z8xYxpZ5lBzCMByMCNLaSuwEZ4PGAoPQbAGcOBcR-8H3qA713x1GUPOB4ZO_5FwDscv8ZYoi91iXK2rfuui7kM8qElS891y_MghMwHQK26XPhmdjmPY6fc-LYpEMXtVqrFcpWoq2k4qUwoGhqJWplgNitZC47H7YGKFGtm5a3nwZgwI4BOW-bomiO9B8JZJftSTsZJypM3TCOZwEOnD3gQo1MayCLB7C8sOdYQ98N-0DxbBSEDrGgh1H6LsUHSuqmrfROn37pU3jocG7KCZzqkAbRYpLcPWru3_ZzZSzrn2MNr0VLZ1--bDaM6px0odgpNP97UHQ13EZBVGqRXK1MWS_1Z4dB8wNmXSVJ5duVOYcXt6wW_dtI5P7HdFml_pbe3HwBaNI6Gg!!/dz/d5/L2dBISEvZ0FBIS9nQSEh/?lng=ru"
                override val endingDate = 1725753600000L
                override val dateOfVisit = 1721088000000L
            },
            object : CulturalActivity() {
                override val id = 2
                override val name = "Куплю гараж"
                override val description =
                    "Экспозиция покажет, как заветные 18 м² личного пространства становились \n" +
                            "местом самовыражения и территорией свободы миллионов людей, и как \n" +
                            "разворачивалась история гаражной культуры в России. "
                override val place = "Севкабель Порт"
                override val link = "https://sevcableport.ru/ru/afisha/kuplyu-garazh"
                override val endingDate = 1727568000000
                override val dateOfVisit = null
            },
            object : CulturalActivity() {
                override val id = 3
                override val name = "Прогуляться"
                override val description = ""
                override val place = ""
                override val link = ""
                override val endingDate = null
                override val dateOfVisit = null
            },
        ),
        emptyList()
    )
}