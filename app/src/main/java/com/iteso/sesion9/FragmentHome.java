package com.iteso.sesion9;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.ItemProductControl;
import com.iteso.sesion9.tools.Constant;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    private AdapterProduct mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemProduct> myDataSet;
    private RecyclerView recyclerView;


    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ItemProductControl itemProductControl = new ItemProductControl();
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.fragment_home_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        myDataSet = itemProductControl.getItemProductsByCategory(2, dh);
        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

}
