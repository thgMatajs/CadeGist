package io.gentalha.code.cadegist.remote.model

import com.google.gson.annotations.SerializedName
import io.gentalha.code.cadegist.model.Owner

class RemoteOwner(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("avatar_url")
    override val avatarUrl: String?,
    @SerializedName("login")
    override val name: String
) : Owner