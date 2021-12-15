package com.joaomarcos.cadastromoradoresderua.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.joaomarcos.cadastromoradoresderua.R;
import com.joaomarcos.cadastromoradoresderua.model.MoradorDeRua;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<MoradorDeRua> moradorDeRuaArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.id_list_moradores);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        moradorDeRuaArrayList = new ArrayList<MoradorDeRua>();
        myAdapter = new MyAdapter(ListActivity.this, moradorDeRuaArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("moradores_de_rua").orderBy("nome", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                moradorDeRuaArrayList.add(dc.getDocument().toObject(MoradorDeRua.class));
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}