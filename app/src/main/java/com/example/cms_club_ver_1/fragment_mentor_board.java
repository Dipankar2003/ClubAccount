package com.example.cms_club_ver_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragment_mentor_board extends Fragment {
    public EditText ed_search;
    public RecyclerView recyclerView;
  //  public ArrayList<MainBoardPOJO> arrayList;
  public ArrayList<main> arrayList;
   // public MainBoardAdapter adapter;
   public mainAdapter adapter;
   public DatabaseReference database;
    public AppCompatButton btn_add_mentor;
    private String currentUser ="WSM";  //user.getCurrentuser();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mentor_board, container, false);
        ed_search = view.findViewById(R.id.ed_search);
        recyclerView = view.findViewById(R.id.rv);
        btn_add_mentor = view.findViewById(R.id.btn_mentor_add);

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        database= FirebaseDatabase.getInstance().getReference();

        adapter = new mainAdapter(arrayList, new OnMainBoardRowClickListener() {
            @Override
            public void onItemClick(MainBoardPOJO mainBoardPOJO) {
                Toast.makeText(getContext(),mainBoardPOJO.getName(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),mainBoardPOJO.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),EditActivity.class);
                intent.putExtra("CALLED_FROM",1);
                startActivity(intent);
            }
        });

        //
        database.child("Club").child(currentUser).child("Board").child("Mentor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    main main1=dataSnapshot.getValue(main.class);
                    arrayList.add(main1);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(adapter);

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_add_mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainBoardAddPage.class);
                intent.putExtra("CALLED_FROM",1);
                startActivity(intent);
            }
        });

        return view;
    }

    private void filter(String text) {
        ArrayList<main> filteredlist = new ArrayList<>();
        for (main item : arrayList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            adapter.filterList(filteredlist);
        }
    }
}