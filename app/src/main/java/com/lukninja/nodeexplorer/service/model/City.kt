package com.lukninja.nodeexplorer.service.model

import com.google.gson.annotations.SerializedName

data class City(
    val es: String? = null,
    val de: String? = null,
    val en: String? = null,
    val fr: String? = null,
    val ja: String? = null,
    @SerializedName("pt-BR")
    val ptBR: String? = null,
    val ru: String? = null
)