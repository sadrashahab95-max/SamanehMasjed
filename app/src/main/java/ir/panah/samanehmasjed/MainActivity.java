package ir.panah.samanehmasjed;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int NAVY = Color.rgb(7,29,59);
    private static final int TEAL = Color.rgb(22,184,176);
    private WebView webView;
    private LinearLayout root;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        showHome();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private void showHome() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(28, 36, 28, 36);
        root.setBackgroundColor(NAVY);

        TextView mark = text("م", 38, Color.WHITE, true);
        mark.setBackground(shape(TEAL, 100));
        LinearLayout.LayoutParams mp = new LinearLayout.LayoutParams(92,92);
        mp.gravity = Gravity.CENTER;
        root.addView(mark, mp);

        TextView title = text("سامانه مسجد", 28, Color.WHITE, true);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(-1,-2);
        tp.topMargin = 22;
        root.addView(title,tp);

        TextView sub = text("به سامانه خوش آمدید\nلطفاً نقش خود را انتخاب کنید", 16, Color.rgb(205,220,235), false);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1,-2);
        sp.topMargin = 12;
        sp.bottomMargin = 42;
        root.addView(sub,sp);

        addRole("مدیران و مربیان", "ورود به پنل مدیریت و مربیان", "https://panah-shop.ir/#!/masajed");
        addRole("دانش‌آموزان", "ورود به پنل دانش‌آموزی", "https://panah-shop.ir/#!/teeneg");

        TextView footer = text("سامانه یکپارچه خدمات مسجد", 12, Color.rgb(150,175,200), false);
        LinearLayout.LayoutParams fp = new LinearLayout.LayoutParams(-1,-2);
        fp.topMargin = 34;
        root.addView(footer,fp);

        setContentView(root);
    }

    private void addRole(String title, String desc, String url) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(20,18,20,18);
        card.setBackground(shape(Color.WHITE, 24));

        TextView heading = text(title,20,NAVY,true);
        heading.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        card.addView(heading);

        TextView detail = text(desc,13,Color.DKGRAY,false);
        detail.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams dp = new LinearLayout.LayoutParams(-1,-2);
        dp.topMargin = 5;
        card.addView(detail,dp);

        Button button = new Button(this);
        button.setText("ورود به بخش");
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        button.setBackground(shape(TEAL,18));

        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1,52);
        bp.topMargin = 14;
        card.addView(button,bp);

        button.setOnClickListener(v -> openPanel(url));

        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1,-2);
        cp.bottomMargin = 18;
        root.addView(card,cp);
    }

    private void openPanel(String url) {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.WHITE);

        TextView bar = text("سامانه مسجد   |   بازگشت",16,Color.WHITE,true);
        bar.setBackgroundColor(NAVY);
        bar.setPadding(12,12,12,12);
        bar.setOnClickListener(v -> showHome());
        root.addView(bar,new LinearLayout.LayoutParams(-1,52));

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        root.addView(webView,new LinearLayout.LayoutParams(-1,0,1));
        setContentView(root);
        webView.loadUrl(url);
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else if (webView != null) {
            showHome();
        } else {
            super.onBackPressed();
        }
    }
}
