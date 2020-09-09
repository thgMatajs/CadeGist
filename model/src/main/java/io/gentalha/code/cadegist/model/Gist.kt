package io.gentalha.code.cadegist.model

abstract class Gist(
    val id: String,
    val owner: Owner,
    val isFavorite: Boolean
)