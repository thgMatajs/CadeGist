package io.gentalha.code.cadegist.cache.entities

import io.gentalha.code.cadegist.model.Owner

data class CacheOwner(
    override val id: Int,
    override val avatarUrl: String?,
    override val name: String
) : Owner


fun Owner.toCache() = CacheOwner(
    this.id,
    this.avatarUrl,
    this.name
)
