package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("WebView" + "Página cargada: " + url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                System.out.println("WebView" + "Error al cargar la página: " + error.getDescription());
            }
        });

        String pdfUrl = "https://web-deprueba.000webhostapp.com/comprobante_N0000.pdf";
        String pdfData = "<iframe src='http://docs.google.com/gview?embedded=true&url=" + pdfUrl + "' style='width:100%; height:100%;' frameborder='0'></iframe>";

        webView.loadData(pdfData, "text/html", "utf-8");
    }
}
