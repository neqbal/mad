package com.example.myapplication;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;

public class GridFragment extends Fragment {

    int[] images = {
            R.drawable.images,
            R.drawable.images1,
            R.drawable.images2,
            R.drawable.d73ca5e9403f6be48294856614e1cd7f
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        GridView gridView = view.findViewById(R.id.gridView);

        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public Object getItem(int i) {
                return images[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup parent) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(images[i]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(
                        new GridView.LayoutParams(300, 300)
                );
                return imageView;
            }
        });

        return view;
    }
}
