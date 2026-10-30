
package ir.panah.samanehmasjed;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.webkit.*;
import android.widget.*;

public class MainActivity extends Activity {

    private static final int NAVY = Color.rgb(5, 20, 43);
    private static final int NAVY2 = Color.rgb(11, 48, 70);
    private static final int TEAL = Color.rgb(24, 184, 174);
    private static final int GOLD = Color.rgb(211, 174, 105);
    private static final int CREAM = Color.rgb(248, 246, 237);
    private static final int WHITE = Color.WHITE;

    private WebView webView;
    private FrameLayout screen;

    private int dp(float v) {
        return (int) (v * getResources().getDisplayMetrics().density + 0.5f);
    }

    private GradientDrawable shape(int color, int radius, int strokeColor) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        if (strokeColor != Color.TRANSPARENT) {
            d.setStroke(dp(1), strokeColor);
        }
        return d;
    }

    private GradientDrawable gradient(int a, int b, int radius) {
        GradientDrawable d = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{a, b}
        );
        d.setCornerRadius(dp(radius));
        d.setStroke(dp(1), Color.argb(100, 211, 174, 105));
        return d;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setTextDirection(View.TEXT_DIRECTION_RTL);
        t.setTypeface(Typeface.create("sans-serif", bold ?
                Typeface.BOLD : Typeface.NORMAL));
        t.setIncludeFontPadding(true);
        return t;
    }

    private LinearLayout.LayoutParams lp(int w, int h) {
        return new LinearLayout.LayoutParams(
                w < 0 ? w : dp(w),
                h < 0 ? h : dp(h)
        );
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(NAVY);
        getWindow().setNavigationBarColor(NAVY);
        showHome();
    }

    private void showHome() {
        if (webView != null) {
            webView.stopLoading();
            webView.destroy();
            webView = null;
        }

        screen = new FrameLayout(this);
        screen.addView(new IslamicBackground(),
                new FrameLayout.LayoutParams(-1, -1));

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setVerticalScrollBarEnabled(false);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER_HORIZONTAL);
        content.setPadding(dp(22), dp(25), dp(22), dp(25));

        scroll.addView(content);
        screen.addView(scroll, new FrameLayout.LayoutParams(-1, -1));

        // نشان مرکزی
        FrameLayout emblem = new FrameLayout(this);
        GradientDrawable emblemBg = gradient(
                Color.rgb(17, 75, 91), NAVY, 100);
        emblem.setBackground(emblemBg);
        emblem.setElevation(dp(8));

        TextView emblemText = text("۞", 57, GOLD, false);
        emblem.addView(emblemText, new FrameLayout.LayoutParams(-1, -1));

        LinearLayout.LayoutParams ep = lp(104, 104);
        ep.gravity = Gravity.CENTER;
        content.addView(emblem, ep);

        TextView title = text("سامانه مسجد", 31, WHITE, true);
        LinearLayout.LayoutParams tp = lp(-1, -2);
        tp.topMargin = dp(15);
        content.addView(title, tp);

        TextView subtitle = text(
                "ارتباط، آموزش، تعالی",
                15, Color.rgb(190, 220, 218), false);
        LinearLayout.LayoutParams sp = lp(-1, -2);
        sp.topMargin = dp(5);
        content.addView(subtitle, sp);

        TextView ornament = text("✦  ━━━━━━━━━  ✦", 16, GOLD, false);
        LinearLayout.LayoutParams op = lp(-1, 42);
        op.topMargin = dp(10);
        content.addView(ornament, op);

        TextView instruction = text(
                "لطفاً نقش خود را انتخاب کنید",
                17, WHITE, true);
        LinearLayout.LayoutParams ip = lp(-1, -2);
        ip.topMargin = dp(5);
        ip.bottomMargin = dp(20);
        content.addView(instruction, ip);

        addRoleCard(
                content,
                "مدیران و مربیان",
                "دسترسی به بخش مدیریت و برنامه‌های آموزشی",
                "مدیریت و هدایت",
                true,
                "https://panah-shop.ir/#!/masajed"
        );

        addRoleCard(
                content,
                "دانش‌آموزان",
                "دسترسی به محتوای آموزشی و فعالیت‌های فرهنگی",
                "یادگیری و رشد",
                false,
                "https://panah-shop.ir/#!/teeneg"
        );

        TextView footer = text(
                "مسجد، خانهٔ دل‌ها",
                15, Color.rgb(221, 195, 145), false);
        LinearLayout.LayoutParams fp = lp(-1, 48);
        fp.topMargin = dp(8);
        content.addView(footer, fp);

        setContentView(screen);
    }

    private void addRoleCard(
            LinearLayout parent,
            String headingText,
            String description,
            String smallTitle,
            boolean dark,
            String url
    ) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER_HORIZONTAL);
        card.setPadding(dp(20), dp(18), dp(20), dp(20));

        if (dark) {
            card.setBackground(gradient(
                    Color.rgb(15, 67, 88),
                    Color.rgb(7, 31, 54), 24));
        } else {
            card.setBackground(gradient(
                    Color.rgb(255, 253, 246),
                    Color.rgb(231, 243, 237), 24));
        }

        card.setElevation(dp(7));

        // نشان تصویری هندسی
        FrameLayout iconFrame = new FrameLayout(this);
        iconFrame.setBackground(shape(
                dark ? Color.rgb(7, 39, 60) : Color.rgb(220, 238, 230),
                60,
                dark ? GOLD : Color.rgb(178, 147, 91)
        ));

        RoleSymbol symbol = new RoleSymbol(dark);
        iconFrame.addView(symbol, new FrameLayout.LayoutParams(-1, -1));

        LinearLayout.LayoutParams iconLp = lp(106, 106);
        iconLp.gravity = Gravity.CENTER;
        card.addView(iconFrame, iconLp);

        TextView heading = text(
                headingText, 21, dark ? WHITE : NAVY, true);
        LinearLayout.LayoutParams hp = lp(-1, -2);
        hp.topMargin = dp(13);
        card.addView(heading, hp);

        TextView tag = text(
                smallTitle, 13,
                dark ? Color.rgb(226, 195, 133) :
                        Color.rgb(13, 125, 122),
                true
        );
        LinearLayout.LayoutParams tagp = lp(-1, -2);
        tagp.topMargin = dp(5);
        card.addView(tag, tagp);

        TextView detail = text(
                description, 14,
                dark ? Color.rgb(195, 220, 226) :
                        Color.rgb(65, 91, 91),
                false
        );
        detail.setMaxLines(2);
        LinearLayout.LayoutParams dp = lp(-1, -2);
        dp.topMargin = dp(9);
        card.addView(detail, dp);

        TextView button = text(
                "ورود به بخش     ←", 16, WHITE, true);
        button.setBackground(gradient(
                Color.rgb(21, 177, 166),
                Color.rgb(5, 117, 133), 28));

        LinearLayout.LayoutParams bp = lp(-1, 52);
        bp.topMargin = dp(18);
        card.addView(button, bp);

        button.setOnClickListener(v -> openPanel(url));
        card.setOnClickListener(v -> openPanel(url));

        LinearLayout.LayoutParams cp = lp(-1, -2);
        cp.bottomMargin = dp(18);
        parent.addView(card, cp);
    }

    private class RoleSymbol extends View {
        private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final boolean dark;

        RoleSymbol(boolean dark) {
            super(MainActivity.this);
            this.dark = dark;
        }

        private void line(Canvas c, int color, float width) {
            p.setColor(color);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(width);
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        protected void onDraw(Canvas c) {
            super.onDraw(c);
            float s = Math.min(getWidth(), getHeight()) / 100f;
            c.save();
            c.scale(s, s);

            int main = dark ? GOLD : Color.rgb(13, 119, 117);
            int secondary = dark ? TEAL : NAVY2;

            // قاب محرابی
            line(c, main, 2.2f);
            Path arch = new Path();
            arch.moveTo(18, 83);
            arch.lineTo(18, 39);
            arch.quadTo(18, 21, 39, 18);
            arch.lineTo(50, 8);
            arch.lineTo(61, 18);
            arch.quadTo(82, 21, 82, 39);
            arch.lineTo(82, 83);
            c.drawPath(arch, p);

            // کتاب باز
            p.setColor(secondary);
            p.setStyle(Paint.Style.FILL);
            Path book = new Path();
            book.moveTo(24, 55);
            book.quadTo(38, 49, 49, 57);
            book.lineTo(49, 78);
            book.quadTo(36, 70, 24, 76);
            book.close();
            c.drawPath(book, p);

            Path book2 = new Path();
            book2.moveTo(76, 55);
            book2.quadTo(62, 49, 51, 57);
            book2.lineTo(51, 78);
            book2.quadTo(64, 70, 76, 76);
            book2.close();
            c.drawPath(book2, p);

            line(c, main, 1.7f);
            c.drawLine(50, 57, 50, 79, p);
            c.drawLine(31, 60, 43, 62, p);
            c.drawLine(57, 62, 69, 60, p);

            // ستارهٔ بالای کتاب
            p.setColor(main);
            p.setStyle(Paint.Style.FILL);
            Path star = new Path();
            star.moveTo(50, 27);
            star.lineTo(54, 36);
            star.lineTo(64, 37);
            star.lineTo(57, 44);
            star.lineTo(59, 54);
            star.lineTo(50, 49);
            star.lineTo(41, 54);
            star.lineTo(43, 44);
            star.lineTo(36, 37);
            star.lineTo(46, 36);
            star.close();
            c.drawPath(star, p);

            c.restore();
        }
    }

    private class IslamicBackground extends View {
        private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        IslamicBackground() {
            super(MainActivity.this);
        }

        @Override
        protected void onDraw(Canvas c) {
            super.onDraw(c);

            float w = getWidth();
            float h = getHeight();

            p.setShader(new LinearGradient(
                    0, 0, w, h,
                    Color.rgb(13, 58, 78),
                    NAVY,
                    Shader.TileMode.CLAMP
            ));
            c.drawRect(0, 0, w, h, p);
            p.setShader(null);

            // قوس معماری بزرگ
            p.setColor(Color.argb(35, 211, 174, 105));
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(dp(2));

            RectF arch = new RectF(
                    w * .12f, -h * .13f,
                    w * .88f, h * .43f);
            c.drawArc(arch, 180, 180, false, p);

            // نقوش هندسی
            p.setColor(Color.argb(25, 40, 206, 188));
            p.setStrokeWidth(dp(1));

            float unit = dp(35);
            for (float x = 0; x < w + unit; x += unit) {
                for (float y = 0; y < h + unit; y += unit) {
                    c.drawLine(x, y, x + unit / 2, y + unit / 2, p);
                    c.drawLine(x + unit / 2, y + unit / 2,
                            x + unit, y, p);
                }
            }

            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.argb(15, 211, 174, 105));
            c.drawCircle(w * .5f, h * .42f, w * .43f, p);
        }
    }

    private void openPanel(String url) {
        screen = new FrameLayout(this);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundColor(WHITE);

        TextView bar = text(
                "سامانه مسجد       ← بازگشت",
                16, WHITE, true);
        bar.setBackgroundColor(NAVY);
        bar.setOnClickListener(v -> showHome());

        container.addView(bar, lp(-1, 55));

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        container.addView(webView,
                new LinearLayout.LayoutParams(-1, 0, 1));

        screen.addView(container);
        setContentView(screen);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else if (webView != null) {
            showHome();
        } else {
            super.onBackPressed();
        }
    }
}
