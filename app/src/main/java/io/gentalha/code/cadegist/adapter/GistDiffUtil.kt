package io.gentalha.code.cadegist.adapter

import androidx.recyclerview.widget.DiffUtil
import io.gentalha.code.cadegist.model.Gist

class GistDiffUtil : DiffUtil.ItemCallback<Gist>() {

    override fun areItemsTheSame(oldItem: Gist, newItem: Gist): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gist, newItem: Gist): Boolean {
        return oldItem == newItem
    }
}