package io.gentalha.code.cadegist.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.gentalha.code.cadegist.model.Gist

class RemoteGist(
    @SerializedName("id")
    override val id: String,
    @SerializedName("owner")
    override val owner: RemoteOwner,
    @Expose
    override var isFavorite: Boolean
) : Gist