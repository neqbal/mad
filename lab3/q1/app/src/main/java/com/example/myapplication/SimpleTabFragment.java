package com.example.myapplication;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SimpleTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        TextView tv = new TextView(getContext());
        tv.setText("This is a simple Tab layout demo");
        tv.setTextSize(20);
        return tv;
    }
}
