package io.gentalha.code.cadegist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.model.Gist

class FavoriteGistsAdapter(
    private val itemClick: (gist: Gist) -> Unit,
    private val favoriteClick: (gist: Gist) -> Unit
) : ListAdapter<Gist, GistsViewHolder>(GistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gist_layout, parent, false)
        return GistsViewHolder(view)
    }

    override fun onBindViewHolder(holder: GistsViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            itemClick,
            favoriteClick
        )
    }
}