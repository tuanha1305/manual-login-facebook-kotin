package vn.vccorp.tuanhaanh.loginfbkotin.activitys

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import vn.vccorp.tuanhaanh.loginfbkotin.R
import vn.vccorp.tuanhaanh.loginfbkotin.constant.Constant


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init
        webView = findViewById(R.id.webViewLogin)

        val webview = WebView(this)
//        val mWebSettings = webview.settings
//        mWebSettings.savePassword = false
//        mWebSettings.saveFormData = false

        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                val uri = Uri.parse(url)
                val server = uri.authority

                if (server == Constant.SERVER) {
                    val urlBuilder = url!!.replace("#".toRegex(), "fb_")
                    val uriBuilder = Uri.parse(urlBuilder)
                    val accessToken =
                        uriBuilder.getQueryParameter(Constant.ACCESS_TOKEN)
                    val dataAccessExpirationTime =
                        uriBuilder.getQueryParameter(Constant.DATA_AET)
                    val expiresIn =
                        uriBuilder.getQueryParameter(Constant.EI)
                    //Log.d(TAG, "access_token: "+accessToken);
                    val intent =
                        Intent(applicationContext, ResultLoginActivity::class.java)
                    intent.putExtra(Constant.ACCESS_TOKEN, accessToken)
                    intent.putExtra(Constant.DATA_AET, dataAccessExpirationTime)
                    intent.putExtra(Constant.EI, expiresIn)
                    startActivity(intent)
                    finish()
                }
            }
        }

        webView.loadUrl(Constant.URL_REQUEST)
    }
}
