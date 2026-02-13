package com.example.myapplication;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    String[] countries = {
            "American Samoa", "El Salvador", "Saint Helena",
            "Saint Kitts and Nevis", "Saint Lucia",
            "Samoa", "San Marino", "Saudi Arabia"
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ListView listView = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                countries
        );

        listView.setAdapter(adapter);

        return view;
    }
}
