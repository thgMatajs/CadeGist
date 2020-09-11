package io.gentalha.code.cadegist.view.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import io.gentalha.code.cadegist.R
import io.gentalha.code.cadegist.custom_views.ViewStateNotifier
import io.gentalha.code.cadegist.model.Gist
import io.gentalha.code.cadegist.presentation.extensions.hide
import io.gentalha.code.cadegist.presentation.extensions.show
import io.gentalha.code.cadegist.presentation.viewmodel.GetGistDetailViewModel
import io.gentalha.code.common.extensions.handleExceptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class GistDetailActivity : AppCompatActivity() {

    private val getGistViewModel: GetGistDetailViewModel by viewModel()
    private val gistId: String by lazy {
        intent.getStringExtra("GIST_ID") ?: ""
    }

    private val progressBar: ProgressBar by lazy { findViewById(R.id.gistDetailProgressBar) }
    private val ownerName: TextView by lazy { findViewById(R.id.gistDetailTvOwnerName) }
    private val ownerIv: ImageView by lazy { findViewById(R.id.gistDetailIv) }
    private val stateNotifier: ViewStateNotifier by lazy { findViewById(R.id.gistDetailStateNotifier) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist_detail)
        setupLiveData()
    }

    private fun setupLiveData() {
        getGistViewModel.loadGistDetail(gistId)
        getGistViewModel.gistDetailLiveData.observe(this, {
            it.handle(
                onLoading = ::showLoading,
                onSuccess = ::handleSuccessResult,
                onError = ::handleError
            )
        })
    }

    private fun showLoading() {
        progressBar.show()
        ownerIv.hide()
        ownerName.hide()
        stateNotifier.hide()
    }

    private fun hideLoading() {
        progressBar.hide()
        ownerName.show()
        ownerIv.show()
    }

    private fun handleSuccessResult(gist: Gist?) {
        hideLoading()
        gist?.owner?.apply {
            ownerIv.load(avatarUrl)
            ownerName.text = name
        }
    }

    private fun handleError(error: Throwable?) {
        hideLoading()
        error?.handleExceptions(
            httpException = {
                stateNotifier.showHttpError { getGistViewModel.loadGistDetail(gistId) }
            },
            whitOutNetWorkException = {
                stateNotifier.showNoConnectionError { getGistViewModel.loadGistDetail(gistId) }
            },
            otherExceptions = {
                stateNotifier.showGenericError()
            }
        )
    }
}