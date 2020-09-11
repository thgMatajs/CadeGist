package io.gentalha.code.cadegist.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.presentation.extensions.hide
import io.gentalha.code.cadegist.presentation.extensions.show

class ViewStateNotifier @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val animation: LottieAnimationView by lazy {
        findViewById(R.id.stateNotifierAnimation)
    }
    private val title: TextView by lazy {
        findViewById(R.id.stateNotifierTitle)
    }
    private val description: TextView by lazy {
        findViewById(R.id.stateNotifierDescription)
    }
    private val btn: Button by lazy {
        findViewById(R.id.stateNotifierBtn)
    }

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.view_state_notifier, this, true)
    }


    fun showEmptyState(
        titleResId: Int,
        descriptionResId: Int
    ) {
        animation.setAnimation(R.raw.empty)
        title.text = context.getString(titleResId)
        description.text = context.getString(descriptionResId)
        btn.hide()
        this.show()
    }

    fun showNoConnectionError(
        retryClick: () -> Unit
    ) {
        animation.apply {
            setAnimation(R.raw.no_connection)
            repeatCount = 0
        }
        title.text = context.getString(R.string.no_connection_error_title)
        description.text = context.getString(R.string.no_connection_error_description)
        btn.setOnClickListener { retryClick.invoke() }
        this.show()
    }

    fun showGenericError() {
        animation.apply {
            setAnimation(R.raw.error)
            repeatCount = 0
        }
        title.text = context.getString(R.string.generic_error_title)
        description.text = context.getString(R.string.generic_error_description)
        this.show()
    }

    fun showHttpError(
        retryClick: () -> Unit
    ) {
        animation.apply {
            setAnimation(R.raw.error)
            repeatCount = 0
        }
        title.text = context.getString(R.string.generic_error_title)
        description.text = context.getString(R.string.http_error_description)
        btn.setOnClickListener { retryClick.invoke() }
        this.show()
    }

}