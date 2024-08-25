package com.lukninja.nodeexplorer.service.model

import com.google.gson.annotations.SerializedName

data class City(
    val es: String?,
    val de: String?,
    val en: String?,
    val fr: String?,
    val ja: String?,
    @SerializedName("pt-BR")
    val ptBR: String?,
    val ru: String?
)