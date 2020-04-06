package com.nullbyte.covid_19support.ui;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.adapters.EmergencyNumberAdapter;
import com.nullbyte.covid_19support.models.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class EmergencyNumFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ContactModel> mStatesNumList = new ArrayList<>();



    public EmergencyNumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_emergency_num, container, false);

        initList();

        recyclerView = view.findViewById(R.id.rv_emergency);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new EmergencyNumberAdapter(mStatesNumList);
        recyclerView.setAdapter(mAdapter);


        return view;
    }
    public void initList()
    {
        mStatesNumList.add(new ContactModel("Andhra Pradesh","0866-2410978"));
        mStatesNumList.add(new ContactModel("Arunachal Pradesh","9436055743"));
        mStatesNumList.add(new ContactModel("Assam","6913347770"));
        mStatesNumList.add(new ContactModel("Bihar","104"));
        mStatesNumList.add(new ContactModel("Chhattisgarh","104"));
        mStatesNumList.add(new ContactModel("Goa","104"));
        mStatesNumList.add(new ContactModel("Gujarat","104"));
        mStatesNumList.add(new ContactModel("Haryana","8558893911"));
        mStatesNumList.add(new ContactModel("Himachal Pradesh","104"));
        mStatesNumList.add(new ContactModel("Jharkhand","104"));
        mStatesNumList.add(new ContactModel("Karnataka","104"));
        mStatesNumList.add(new ContactModel("Kerala","0471-2552056"));
        mStatesNumList.add(new ContactModel("Madhya Pradesh ","104"));
        mStatesNumList.add(new ContactModel("Maharashtra","020-26127394"));
        mStatesNumList.add(new ContactModel("Manipur","3852411668"));
        mStatesNumList.add(new ContactModel("Meghalaya","108"));
        mStatesNumList.add(new ContactModel("Mizoram","102"));
        mStatesNumList.add(new ContactModel("Nagaland","7005539653"));
        mStatesNumList.add(new ContactModel("Odisha","9439994859"));
        mStatesNumList.add(new ContactModel("Punjab","104"));
        mStatesNumList.add(new ContactModel("Rajasthan","0141-2225624"));
        mStatesNumList.add(new ContactModel("Sikkim","104"));
        mStatesNumList.add(new ContactModel("Tamil Nadu","044-29510500"));
        mStatesNumList.add(new ContactModel("Telangana","104"));
        mStatesNumList.add(new ContactModel("Tripura","0381-2315879"));
        mStatesNumList.add(new ContactModel("Uttarakhand","104"));
        mStatesNumList.add(new ContactModel("Uttar Pradesh","18001805145"));
        mStatesNumList.add(new ContactModel("West Bengal ","1800313444222, 03323412600"));
        mStatesNumList.add(new ContactModel("Andaman and Nicobar Islands","03192-232102"));
        mStatesNumList.add(new ContactModel("Chandigarh","9779558282"));
        mStatesNumList.add(new ContactModel("Dadra and Nagar Haveli and Daman & Diu","104"));
        mStatesNumList.add(new ContactModel("Delhi","011-22307145"));
        mStatesNumList.add(new ContactModel("Jammu & Kashmir","01912520982, 0194-2440283"));
        mStatesNumList.add(new ContactModel("Ladakh","01982256462"));
        mStatesNumList.add(new ContactModel("Lakshadweep","104"));
        mStatesNumList.add(new ContactModel("Puducherry","104"));
    }
}
