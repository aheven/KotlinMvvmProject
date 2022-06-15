package heven.holt.mvvm.fragment

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import heven.holt.mvvm.R
import heven.holt.library.widget.webview.WebViewHelper

class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    private lateinit var webViewHelper: WebViewHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webContainer = view.findViewById<FrameLayout>(R.id.web_container)

        webViewHelper = WebViewHelper.with(webContainer)

        val url = "https://www.baidu.com"
        webViewHelper.loadUrl(url)
    }

    fun onBackPress(): Boolean {
        return webViewHelper.canGoBack()
    }

    override fun onResume() {
        super.onResume()
        webViewHelper.onResume()
    }

    override fun onPause() {
        super.onPause()
        webViewHelper.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webViewHelper.onDestroyView()
    }
}