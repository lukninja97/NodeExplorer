package com.lukninja.nodeexplorer.service.model

import com.google.gson.annotations.SerializedName

data class Country(
    val de: String?,
    val en: String?,
    val es: String?,
    val fr: String?,
    val ja: String?,
    @SerializedName("pt-BR")
    val ptBR: String?,
    val ru: String?,
    @SerializedName("zh-CN")
    val zhCN: String?
)