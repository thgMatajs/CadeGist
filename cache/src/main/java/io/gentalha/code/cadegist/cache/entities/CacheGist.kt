package io.gentalha.code.cadegist.cache.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.model.Owner

@Entity(tableName = "gists")
data class CacheGist(
    @PrimaryKey
    override val id: String,
    @Embedded(prefix = "owner_")
    override val owner: CacheOwner,
    override var isFavorite: Boolean
) : Gist

fun Gist.toCache() = CacheGist(
    this.id,
    this.owner.toCache(),
    this.isFavorite
)