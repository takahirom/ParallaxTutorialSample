package com.kogitune.parallaxtutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by takam on 2015/07/01.
 */
public class TutorialFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tutorial_fragment, container, false);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new TutorialPageFragment();
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                int pageWidth = page.getWidth();
                final View backgroundView = page.findViewById(R.id.background);
                ViewCompat.setTranslationX(backgroundView, pageWidth * 0.5f * position);
                final View objectView = page.findViewById(R.id.object);
                ViewCompat.setAlpha(objectView, (1.0f - Math.abs(position)));
            }
        });

        return view;
    }

    public static class TutorialPageFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                return null;
            }
            return inflater.inflate(R.layout.tutorial_page, container, false);
        }
    }
}
