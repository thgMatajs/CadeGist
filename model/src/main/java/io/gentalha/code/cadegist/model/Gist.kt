package io.gentalha.code.cadegist.model

interface Gist {
    val id: String
    val owner: Owner
    val isFavorite: Boolean
}