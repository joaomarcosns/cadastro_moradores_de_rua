package com.joaomarcos.cadastromoradoresderua.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cadastro_moradores_de_rua.model.MoradorDeRua;
import com.example.cadastro_moradores_de_rua.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<MoradorDeRua> moradorDeRuaArrayList;

    public MyAdapter(Context context, ArrayList<MoradorDeRua> moradorDeRuaArrayList) {
        this.context = context;
        this.moradorDeRuaArrayList = moradorDeRuaArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_moradores, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MoradorDeRua morador = moradorDeRuaArrayList.get(position);

        holder.textNome.setText(morador.getNome());
        holder.textData.setText(morador.getDataNascimento());
        holder.textEndereco.setText(morador.getOrientacaoSexual());

    }

    @Override
    public int getItemCount() {
        return moradorDeRuaArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textNome, textData, textEndereco;
        private Button btnDeletar, btnEditar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.text_nome);
            textData = itemView.findViewById(R.id.text_data);
            textEndereco = itemView.findViewById(R.id.text_endereco);
            btnDeletar = itemView.findViewById(R.id.btn_deletar);
//            btnEditar = itemView.findViewById(R.id.btn_editar);
        }
    }
}

