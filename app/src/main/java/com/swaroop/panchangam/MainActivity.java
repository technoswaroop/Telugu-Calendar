package com.swaroop.panchangam;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/** Shows the bundled calendar page (assets/index.html). Works fully offline. */
public class MainActivity extends Activity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        web = new WebView(this);
        applyBars(false);

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);   // remembers the colour / dark switches and zoom level
        s.setAllowContentAccess(false);
        s.setTextZoom(100);             // keep the printed-style layout independent of the phone's font size

        // Lets the page tell the app when dark mode is switched, so the status and navigation bars follow.
        web.addJavascriptInterface(new Bridge(), "AndroidApp");

        // The app has no internet permission; refuse to navigate anywhere else.
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

        setContentView(web);

        if (savedInstanceState != null) {
            web.restoreState(savedInstanceState);
        } else {
            web.loadUrl("file:///android_asset/index.html");
        }
    }

    public class Bridge {
        @JavascriptInterface
        public void setDark(final boolean dark) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    applyBars(dark);
                }
            });
        }
    }

    private void applyBars(boolean dark) {
        int c = dark ? Color.parseColor("#14141C") : Color.WHITE;
        Window w = getWindow();
        w.setStatusBarColor(c);
        w.setNavigationBarColor(c);
        View dv = w.getDecorView();
        int f = dv.getSystemUiVisibility();
        int light = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        dv.setSystemUiVisibility(dark ? (f & ~light) : (f | light));
        if (web != null) {
            web.setBackgroundColor(c);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        web.saveState(outState);
    }

    @Override
    protected void onDestroy() {
        if (web != null) {
            web.destroy();
        }
        super.onDestroy();
    }
}
