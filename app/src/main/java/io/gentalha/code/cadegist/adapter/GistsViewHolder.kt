package io.gentalha.code.cadegist.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Precision
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.model.Gist

class GistsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val gistIv: ImageView = itemView.findViewById(R.id.gistItemIv)
    private val gistOwnerName: TextView = itemView.findViewById(R.id.gistItemTvName)
    private val gistBtnFavorite: CheckBox = itemView.findViewById(R.id.gistItemBtnFavorite)
    private val gistItem: ConstraintLayout = itemView.findViewById(R.id.gistItem)

    fun bind(
        gist: Gist?,
        itemClick: (gist: Gist) -> Unit,
        favoriteClick: (gist: Gist) -> Unit
    ) {

        gist?.apply {
            gistIv.load(owner.avatarUrl) {
                crossfade(true)
                size(130, 130)
                precision(Precision.AUTOMATIC)
            }
            gistOwnerName.text = owner.name
            gistBtnFavorite.apply {
                isChecked = isFavorite

            }
            gistBtnFavorite.setOnClickListener {
                favoriteClick.invoke(this)
            }

            gistItem.setOnClickListener {
                itemClick.invoke(this)
            }

        }
    }

}