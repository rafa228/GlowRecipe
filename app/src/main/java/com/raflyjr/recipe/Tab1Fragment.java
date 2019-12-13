package com.raflyjr.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Tab1Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        ImageButton btn_order = (ImageButton) view.findViewById( R.id.btn_order );
        btn_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent order = new Intent (getActivity(), Order.class);
                startActivity( order );
            }
        });

        ImageButton btn_picture = (ImageButton) view.findViewById( R.id.btn_picture );
        btn_picture.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent picture = new Intent (getActivity(), Picture.class);
                startActivity( picture );
            }
        });
        return view;
    }

}
