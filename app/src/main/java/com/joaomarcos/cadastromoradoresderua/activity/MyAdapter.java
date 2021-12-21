package com.joaomarcos.cadastromoradoresderua.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.joaomarcos.cadastromoradoresderua.R;
import com.joaomarcos.cadastromoradoresderua.model.MoradorDeRua;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<MoradorDeRua> moradorDeRuaArrayList;
    MoradorDeRua moradorDeRua;

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
        holder.textOrientacao.setText(morador.getOrientacaoSexual());
        holder.textRaca.setText(morador.getRaca());

    }

    @Override
    public int getItemCount() {
        return moradorDeRuaArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textNome, textData, textEndereco, textOrientacao, textRaca;
        private ImageView btnDeletar, btnEditar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.text_nome);
            textData = itemView.findViewById(R.id.text_data);
            textEndereco = itemView.findViewById(R.id.text_endereco);
            textOrientacao = itemView.findViewById(R.id.text_orientacao);
            textRaca = itemView.findViewById(R.id.text_raca);
            btnDeletar = itemView.findViewById(R.id.btn_deletar);
            btnEditar = itemView.findViewById(R.id.btn_editar);


            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moradorDeRua = moradorDeRuaArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(context, UpdatedActivity.class);
                    intent.putExtra("moradorDeRua", moradorDeRua);
                    context.startActivity(intent);
                }
            });

            btnDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
                    builder.setTitle("Atenção");
                    builder.setMessage("Tem certeza que deseja excluir ?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            moradorDeRua = moradorDeRuaArrayList.get(getAdapterPosition());
                            FirebaseFirestore.getInstance().collection("moradores_de_rua")
                                    .document(moradorDeRua.getId()).delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(context,"Morador(a) foi excluído(a) com sucesso!",Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(context, ListActivity.class);
                                                context.startActivity(intent);
                                            }
                                        }
                                    });
                        }
                    });

                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();

                }
            });
        }


        @Override
        public void onClick(View v) {

        }
    }
}

