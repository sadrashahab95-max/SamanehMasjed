
package ir.panah.samanehmasjed;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.webkit.*;
import android.widget.*;

public class MainActivity extends Activity {

    private static final int NAVY = Color.rgb(5, 24, 49);
    private static final int NAVY2 = Color.rgb(10, 51, 78);
    private static final int TEAL = Color.rgb(22, 184, 176);
    private static final int GOLD = Color.rgb(211, 174, 105);
    private static final int WHITE = Color.WHITE;

    private WebView webView;
    private FrameLayout screen;

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(NAVY);
        getWindow().setNavigationBarColor(NAVY);
        getWindow().getDecorView().setSystemUiVisibility(0);
        showHome();
    }

    private GradientDrawable gradient(int top, int bottom, int radius) {
        GradientDrawable d = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{top, bottom}
        );
        d.setCornerRadius(dp(radius));
        d.setStroke(dp(1), Color.argb(100, 40, 205, 190));
        return d;
    }

    private GradientDrawable solid(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        return d;
    }

    private TextView makeText(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setIncludeFontPadding(true);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setTextDirection(View.TEXT_DIRECTION_RTL);
        return t;
    }

    private LinearLayout.LayoutParams params(int width, int height) {
        return new LinearLayout.LayoutParams(
                width < 0 ? width : dp(width),
                height < 0 ? height : dp(height)
        );
    }

    private void showHome() {
        if (webView != null) {
            webView.stopLoading();
            webView.destroy();
            webView = null;
        }

        screen = new FrameLayout(this);

        // پس زمینه
        DecorativeBackground background = new DecorativeBackground();
        screen.addView(background, new FrameLayout.LayoutParams(
                -1, -1
        ));

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setClipToPadding(false);
        scroll.setVerticalScrollBarEnabled(false);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER_HORIZONTAL);
        content.setPadding(dp(22), dp(35), dp(22), dp(25));

        scroll.addView(content);
        screen.addView(scroll, new FrameLayout.LayoutParams(-1, -1));

        // نشان اصلی
        TextView logo = makeText("۞", 55, TEAL, false);
        logo.setBackground(gradient(NAVY2, NAVY, 100));

        LinearLayout.LayoutParams lp = params(100, 100);
        lp.gravity = Gravity.CENTER;
        content.addView(logo, lp);

        // عنوان
        TextView title = makeText("سامانه مسجد", 32, WHITE, true);
        LinearLayout.LayoutParams titleParams = params(-1, -2);
        titleParams.topMargin = dp(17);
        content.addView(title, titleParams);

        TextView subtitle = makeText(
                "ارتباط، آموزش، تعالی",
                16,
                Color.rgb(180, 224, 225),
                false
        );
        LinearLayout.LayoutParams subParams = params(-1, -2);
        subParams.topMargin = dp(8);
        content.addView(subtitle, subParams);

        // جداکننده تزئینی
        TextView ornament = makeText("──────  ✧  ──────", 17, GOLD, false);
        LinearLayout.LayoutParams op = params(-1, 40);
        op.topMargin = dp(13);
        content.addView(ornament, op);

        TextView instruction = makeText(
                "لطفاً نقش خود را انتخاب کنید",
                17,
                Color.rgb(195, 218, 230),
                true
        );
        LinearLayout.LayoutParams ip = params(-1, -2);
        ip.topMargin = dp(10);
        ip.bottomMargin = dp(25);
        content.addView(instruction, ip);

        // کارت مدیران و مربیان
        addRoleCard(
                content,
                "مدیران و مربیان",
                "دسترسی به بخش مدیریت و برنامه‌های آموزشی",
                "cleric",
                true,
                "https://panah-shop.ir/#!/masajed"
        );

        // کارت دانش آموزان
        addRoleCard(
                content,
                "دانش‌آموزان",
                "دسترسی به محتوای آموزشی و فعالیت‌های فرهنگی",
                "student",
                false,
                "https://panah-shop.ir/#!/teeneg"
        );

        TextView footer = makeText(
                "مسجد، خانهٔ دل‌ها",
                16,
                Color.rgb(220, 196, 145),
                false
        );

        LinearLayout.LayoutParams fp = params(-1, 50);
        fp.topMargin = dp(22);
        content.addView(footer, fp);

        setContentView(screen);
    }

    private void addRoleCard(
            LinearLayout parent,
            String title,
            String description,
            String iconType,
            boolean dark,
            String url
    ) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER_HORIZONTAL);
        card.setPadding(dp(20), dp(20), dp(20), dp(20));

        if (dark) {
            card.setBackground(gradient(
                    Color.rgb(12, 62, 91),
                    NAVY,
                    25
            ));
        } else {
            card.setBackground(gradient(
                    Color.rgb(255, 255, 250),
                    Color.rgb(230, 246, 243),
                    25
            ));
        }

        // آیکون اختصاصی
        RoleIcon icon = new RoleIcon(iconType, dark);
        LinearLayout.LayoutParams iconParams = params(112, 112);
        iconParams.gravity = Gravity.CENTER;
        card.addView(icon, iconParams);

        int textColor = dark ? WHITE : NAVY;

        TextView heading = makeText(title, 21, textColor, true);
        LinearLayout.LayoutParams hp = params(-1, -2);
        hp.topMargin = dp(12);
        card.addView(heading, hp);

        TextView detail = makeText(
                description,
                14,
                dark ? Color.rgb(183, 218, 229) :
                        Color.rgb(55, 91, 105),
                false
        );

        detail.setMaxLines(2);

        LinearLayout.LayoutParams detailParams = params(-1, -2);
        detailParams.topMargin = dp(9);
        card.addView(detail, detailParams);

        // دکمه ورود
        TextView button = makeText("ورود به بخش   ←", 16, WHITE, true);
        button.setBackground(gradient(
                Color.rgb(14, 172, 171),
                Color.rgb(4, 113, 142),
                24
        ));

        LinearLayout.LayoutParams bp = params(-1, 52);
        bp.topMargin = dp(19);
        card.addView(button, bp);

        button.setOnClickListener(v -> openPanel(url));

        card.setElevation(dp(8));

        LinearLayout.LayoutParams cp = params(-1, -2);
        cp.bottomMargin = dp(20);
        parent.addView(card, cp);
    }

    // تصویرسازی برداری روحانی و دانش آموز
    private class RoleIcon extends View {

        private final String type;
        private final boolean dark;
        private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        RoleIcon(String type, boolean dark) {
            super(MainActivity.this);
            this.type = type;
            this.dark = dark;
        }

        private void paint(Canvas c, int color, Paint.Style style, float width) {
            p.setColor(color);
            p.setStyle(style);
            p.setStrokeWidth(width);
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            float s = Math.min(getWidth(), getHeight()) / 120f;
            canvas.save();
            canvas.scale(s, s);

            int primary = dark ? Color.rgb(25, 205, 192) : NAVY2;
            int skin = Color.rgb(242, 208, 177);

            // قاب محرابی
            paint(canvas, primary, Paint.Style.STROKE, 2.2f);
            Path arch = new Path();
            arch.moveTo(15, 105);
            arch.lineTo(15, 48);
            arch.quadTo(15, 30, 38, 25);
            arch.lineTo(60, 7);
            arch.lineTo(82, 25);
            arch.quadTo(105, 30, 105, 48);
            arch.lineTo(105, 105);
            canvas.drawPath(arch, p);

            if (type.equals("cleric")) {
                // بدن و عبا
                paint(canvas, dark ? Color.rgb(10, 104, 128) :
                        Color.rgb(15, 75, 100), Paint.Style.FILL, 1);
                Path robe = new Path();
                robe.moveTo(34, 104);
                robe.quadTo(37, 75, 49, 70);
                robe.lineTo(71, 70);
                robe.quadTo(85, 78, 88, 104);
                robe.close();
                canvas.drawPath(robe, p);

                // صورت
                paint(canvas, skin, Paint.Style.FILL, 1);
                canvas.drawOval(43, 34, 77, 76, p);

                // عمامه
                paint(canvas, WHITE, Paint.Style.FILL, 1);
                canvas.drawOval(37, 22, 83, 48, p);
                canvas.drawRoundRect(40, 31, 80, 42, 7, 7, p);

                // ریش
                paint(canvas, Color.rgb(45, 48, 54), Paint.Style.FILL, 1);
                Path beard = new Path();
                beard.moveTo(43, 58);
                beard.quadTo(60, 76, 77, 58);
                beard.quadTo(76, 83, 60, 85);
                beard.quadTo(44, 82, 43, 58);
                canvas.drawPath(beard, p);

                // چشم ها
                paint(canvas, NAVY, Paint.Style.FILL, 1);
                canvas.drawCircle(52, 53, 1.7f, p);
                canvas.drawCircle(68, 53, 1.7f, p);

                // کتاب در دست
                paint(canvas, GOLD, Paint.Style.STROKE, 2);
                canvas.drawLine(60, 79, 60, 102, p);
            } else {
                // بدن دانش آموز
                paint(canvas, Color.rgb(20, 145, 165),
                        Paint.Style.FILL, 1);
                canvas.drawRoundRect(35, 67, 85, 105, 12, 12, p);

                // صورت
                paint(canvas, skin, Paint.Style.FILL, 1);
                canvas.drawOval(43, 28, 77, 69, p);

                // مو
                paint(canvas, Color.rgb(48, 57, 69),
                        Paint.Style.FILL, 1);
                canvas.drawArc(41, 23, 79, 55, 180, 180, true, p);

                // کتاب باز
                paint(canvas, WHITE, Paint.Style.FILL, 1);
                Path book = new Path();
                book.moveTo(18, 80);
                book.lineTo(59, 88);
                book.lineTo(59, 108);
                book.lineTo(18, 99);
                book.close();
                canvas.drawPath(book, p);

                book.reset();
                book.moveTo(102, 80);
                book.lineTo(61, 88);
                book.lineTo(61, 108);
                book.lineTo(102, 99);
                book.close();
                canvas.drawPath(book, p);

                paint(canvas, GOLD, Paint.Style.STROKE, 1.5f);
                canvas.drawLine(60, 88, 60, 108, p);
            }

            canvas.restore();
        }
    }

    // پس زمینه با نقوش هندسی
    private class DecorativeBackground extends View {

        private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        DecorativeBackground() {
            super(MainActivity.this);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            float w = getWidth();
            float h = getHeight();

            p.setShader(new LinearGradient(
                    0, 0, w, h,
                    NAVY2, NAVY,
                    Shader.TileMode.CLAMP
            ));
            canvas.drawRect(0, 0, w, h, p);
            p.setShader(null);

            p.setColor(Color.argb(22, 31, 207, 191));
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(dp(1));

            float unit = dp(28);

            for (float x = 0; x < w + unit; x += unit) {
                for (float y = 0; y < h + unit; y += unit) {
                    canvas.drawLine(x, y, x + unit / 2, y + unit / 2, p);
                    canvas.drawLine(x + unit / 2, y + unit / 2,
                            x + unit, y, p);
                }
            }

            p.setColor(Color.argb(35, 211, 174, 105));
            p.setStrokeWidth(dp(2));
            canvas.drawArc(
                    -w * .6f, -dp(120), w * 1.5f, dp(300),
                    0, 180, false, p
            );
        }
    }

    private void openPanel(String url) {
        screen = new FrameLayout(this);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundColor(Color.WHITE);

        TextView bar = makeText(
                "سامانه مسجد     ← بازگشت",
                16,
                WHITE,
                true
        );

        bar.setBackgroundColor(NAVY);
        bar.setOnClickListener(v -> showHome());

        container.addView(bar, params(-1, 55));

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
