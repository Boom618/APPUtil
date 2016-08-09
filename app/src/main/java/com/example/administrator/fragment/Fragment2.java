package com.example.administrator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.apputils.R;
import com.example.administrator.view.FlowLayout;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class Fragment2 extends Fragment {

    private FlowLayout mFlowLayout;


    final String[] mVals = new String[]
            { "Hello", "Android", "Weclome Hi ", "Android", "Weclome", "Button ImageView",
                    "Android", "Weclome Hello", "Button Text", "TextView" };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);

        mFlowLayout = (FlowLayout) view.findViewById(R.id.id_flow);

        for (int i = 0; i < mVals.length; i++)
        {

            final TextView tv = (TextView) inflater.inflate(R.layout.activity_flow_text,
                    mFlowLayout, false);
            tv.setText(mVals[i]);
            tv.setTag(i);
            mFlowLayout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num  = (int) tv.getTag();
                    String string = tv.getText().toString();
                    ArrayList<String> list = new ArrayList<>();
                    list.add(string);
                }
            });
        }

        return view;
    }
}
