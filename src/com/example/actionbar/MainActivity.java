package com.example.actionbar;

import android.R.color;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.MutableShort;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button hide,show;
	String url;
	EditText et;
	ImageButton ib;
	WebView webview;
	View v;
	ProgressBar progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		getActionBar().setDisplayUseLogoEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6E6E6E")));
		getActionBar().setDisplayShowCustomEnabled(true);
		
	   progress = (ProgressBar)findViewById(R.id.progressBar);
	   progress.setVisibility(v.GONE);
	   progress.setMax(100);
	   
	   progress.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FAFAFA")));
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.mycustomactionbar, null);
		
		
		
		
		ib = (ImageButton)v.findViewById(R.id.ibrefresh);
        et = (EditText)v.findViewById(R.id.etSearch); 
         
        
        getActionBar().setCustomView(v);
        
         
         
         
        webview = (WebView)findViewById(R.id.webview);
      //  webview.setWebViewClient(new MyWebClient());
        webview.getSettings().setJavaScriptEnabled(true); 
      //  webview.getSettings().setLoadWithOverviewMode(true);
       // webview.getSettings().setUseWideViewPort(true);   
        
        
         ib.setOnClickListener(this);
        
         
       et.setOnClickListener(this);
        
        
     	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
 
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.action_prevItem:
			if(webview.canGoBack())
			{
				webview.goBack();
			}
		 return true;
		
		
		case R.id.action_nextIten:
			if(webview.canGoForward())
			{
				webview.goForward();
			}
			
		case R.id.action_bookmark:
			
			if(url!=null)
			{
				Toast.makeText(this, "Not Empty", Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(this, "Emply", Toast.LENGTH_LONG).show();
			}
	       
			return true;
		}
		return onOptionsItemSelected(item);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		  case R.id.ibrefresh:
			url = et.getText().toString();
			webview.setWebViewClient(new WebviewClass());
			webview.loadUrl("http://"+url);
		
		  break;
		  
		  case R.id.etSearch:
			  Toast.makeText(this, "Enabled", Toast.LENGTH_LONG);
			  break;
		}
		
	}
	
	public class WebviewClass extends WebViewClient{

		String myurl;
		
		/* (non-Javadoc)
		 * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
	
			return super.shouldOverrideUrlLoading(view, url);
		}

		/* (non-Javadoc)
		 * @see android.webkit.WebViewClient#onPageFinished(android.webkit.WebView, java.lang.String)
		 */
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			
			String myurl = view.getUrl();
			
			if(myurl!=null)
			{
			 System.out.println(myurl);
			 et.setText(myurl);
			}	    
			
			progress.setVisibility(view.INVISIBLE);
		}

		/* (non-Javadoc)
		 * @see android.webkit.WebViewClient#onPageStarted(android.webkit.WebView, java.lang.String, android.graphics.Bitmap)
		 */
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			progress.setVisibility(view.VISIBLE);
            for(int i=1;i<=100;i=i+20)
            {
			progress.setProgress(i);
            }
			super.onPageStarted(view, url, favicon);
		}
		
		
		
		
	}	
	

}
