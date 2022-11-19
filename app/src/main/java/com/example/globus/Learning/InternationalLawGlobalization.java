package com.example.globus.Learning;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.globus.R;
import com.example.globus.quizDB.QuizGlobalizationUN;
import com.example.globus.quizzes.QuizEconomy;
import com.example.globus.quizzes.QuizUN;

public class InternationalLawGlobalization extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    public final static String Message = "com.example.globus.MESSAGE";
    Button btnQuiz;
    private ProgressDialog progressBar;
    private TextView[] mDots;
    ImageView backbutton;
    ImageView zoomSDG;
    private Animator currentAnimator;

    private int shortAnimationDuration;

    ImageView thumb1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globalization_concepts);

        mSlideViewPager = (ViewPager) findViewById(R.id.viewPagerGlobalization);
        mDotLayout = (LinearLayout) findViewById(R.id.dots);

        mSlideViewPager.setAdapter(new MyAdapter());
        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        ImageView backbutton = findViewById(R.id.btnBack);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public class MyAdapter extends PagerAdapter {

        LayoutInflater layoutInflater;

        int layouts[] = {R.layout.international_page1, R.layout.international_page2, R.layout.international_page9
                , R.layout.international_page3, R.layout.international_page4, R.layout.international_page5
                , R.layout.international_page6, R.layout.international_page7, R.layout.international_page8
                , R.layout.international_page10, R.layout.international_page11, R.layout.international_page12};

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == (ConstraintLayout) o;
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.international_page1, container, false);
            View view2 = layoutInflater.inflate(R.layout.international_page2, container, false);
            View view3 = layoutInflater.inflate(R.layout.international_page9, container, false);
            View view4 = layoutInflater.inflate(R.layout.international_page3, container, false);
            View view5 = layoutInflater.inflate(R.layout.international_page4, container, false);
            View view6 = layoutInflater.inflate(R.layout.international_page5, container, false);
            View view7 = layoutInflater.inflate(R.layout.international_page6, container, false);
            View view8 = layoutInflater.inflate(R.layout.international_page7, container, false);
            View view9 = layoutInflater.inflate(R.layout.international_page8, container, false);
            View view10 = layoutInflater.inflate(R.layout.international_page10, container, false);
            View view11 = layoutInflater.inflate(R.layout.international_page11, container, false);
            View view12 = layoutInflater.inflate(R.layout.international_page12, container, false);
            View[] viewarr = {view, view2, view3, view4, view5, view6, view7, view8, view9, view10, view11, view12};

            container.addView(viewarr[position]);
            return viewarr[position];
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout) (object));
        }
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[12];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorGrey));

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 7) {
                thumb1View = findViewById(R.id.thumb_button_1);
                Glide.with(InternationalLawGlobalization.this).load(R.drawable.sdg17).into(thumb1View);
                thumb1View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        zoomImageFromThumb(thumb1View, R.drawable.sdg17);
                    }
                });
                shortAnimationDuration = getResources().getInteger(
                        android.R.integer.config_shortAnimTime);
                final ImageView expandedImageView = (ImageView) findViewById(
                        R.id.expanded_image);
                OrientationEventListener orientationEventListener = new OrientationEventListener(InternationalLawGlobalization.this) {
                    @Override
                    public void onOrientationChanged(int orientation) {
                        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                            int epsilon = 10;
                            int leftLandscape = 90;
                            int rightLandscape = 270;
                            if (expandedImageView.getVisibility() == View.GONE) {
                                thumb1View.performClick();
                            }
                        }
                    }

                    private boolean epsilonCheck(int a, int b, int epsilon) {
                        return a > b - epsilon && a < b + epsilon;
                    }
                };
                orientationEventListener.enable();
            } else if (position == 11) {
                btnQuiz = findViewById(R.id.btnTakeQuiz);
                btnQuiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //To show button click
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            }
                        }, 400);

                        progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                        progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                        progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                        progressBar.setProgress(0);//attributes
                        progressBar.setMax(100);//attributes
                        progressBar.show();//show the progress bar
                        //This handler will add a delay of 3 seconds
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Intent start to open the navigation drawer activity
                                progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                                Intent intent = new Intent(InternationalLawGlobalization.this, QuizUN.class);
                                intent.putExtra(Message, "c1");
                                startActivity(intent);
                            }
                        }, 2000);
                    }
                });
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }


        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        Glide.with(InternationalLawGlobalization.this).load(R.drawable.sdg17).into(expandedImageView);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(shortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentAnimator = null;
            }
        });
        set.start();
        currentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                if (currentAnimator != null) {
                    currentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(shortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        currentAnimator = null;
                    }
                });
                set.start();
                currentAnimator = set;
            }
        });
    }

}
