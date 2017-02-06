package org.example.tanmay.droidvbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ListViewIntent extends AppCompatActivity {

    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_intent);



        wb = (WebView)findViewById(R.id.webView);

        Bundle b= getIntent().getExtras();
        String l = b.getString("link");

        wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wb.loadUrl(l);
        wb.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }
}
