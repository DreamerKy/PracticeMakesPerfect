package com.example.dreams;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by likaiyu on 2019/7/23.
 *
 */

public class NewsFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        TextView textView = new TextView(getActivity());
        textView.setText(title);
        super.onViewCreated(view, savedInstanceState);

    }
    public static NewsFragment getInstance(String title){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }
}
