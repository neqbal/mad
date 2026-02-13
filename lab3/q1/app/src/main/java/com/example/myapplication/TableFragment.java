package com.example.myapplication;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;

public class TableFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_table, container, false);
    }
}
