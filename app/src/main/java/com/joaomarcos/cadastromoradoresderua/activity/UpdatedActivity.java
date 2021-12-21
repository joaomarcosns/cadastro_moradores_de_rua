package com.joaomarcos.cadastromoradoresderua.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.joaomarcos.cadastromoradoresderua.R;
import com.joaomarcos.cadastromoradoresderua.model.MoradorDeRua;

public class UpdatedActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editOrientacaoSexual;
    private EditText editDataNascimento;
    private EditText editRaca;

    private Button cadastrar;

    private RadioGroup editSexo;
    private RadioButton sexoEscolhido;

    private MoradorDeRua moradorDeRua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated);
        iniciarComponente();
        recuperDados();
    }

    private void recuperDados() {
        moradorDeRua = (MoradorDeRua) getIntent().getSerializableExtra("moradorDeRua");
        editNome.setText(moradorDeRua.getNome());
        editRaca.setText(moradorDeRua.getRaca());
        if (moradorDeRua.getSexo().equals("Masculino")) {
            ((RadioButton)editSexo.getChildAt(0)).setChecked(true);
        }else {
            ((RadioButton)editSexo.getChildAt(1)).setChecked(true);
        }
        editOrientacaoSexual.setText(moradorDeRua.getOrientacaoSexual());
        editDataNascimento.setText(moradorDeRua.getDataNascimento());
        atualizar();
    }

    private void atualizar() {
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString();
                String orientacaoSexual = editOrientacaoSexual.getText().toString();
                String dataNascimento = editDataNascimento.getText().toString();
                String raca = editRaca.getText().toString();
                int sexo = editSexo.getCheckedRadioButtonId();

                if (sexo < 0 || nome.isEmpty() || orientacaoSexual.isEmpty() || dataNascimento.isEmpty() || raca.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Todos os campos são OBRIGATÓRIOS", Toast.LENGTH_LONG).show();
                }else {
                    sexoEscolhido = findViewById(sexo);
                    String sexoFim = sexoEscolhido.getText().toString();
                    MoradorDeRua mDR = new MoradorDeRua();
                    mDR.setNome(nome);
                    mDR.setSexo(sexoFim);
                    mDR.setOrientacaoSexual(orientacaoSexual);
                    mDR.setRaca(raca);
                    mDR.setDataNascimento(dataNascimento);

                    FirebaseFirestore.getInstance().collection("moradores_de_rua")
                            .document(moradorDeRua.getId())
                            .set(mDR)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                                    Toast.makeText(getApplicationContext(), "Atualização realizado com sucesso", Toast.LENGTH_LONG).show();
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Error", e.getMessage());
                        }
                    });
                }
            }
        });
    }

    private void iniciarComponente() {
        editNome = findViewById(R.id.editNome);
        editOrientacaoSexual = findViewById(R.id.editOrientacaoSexual);
        editDataNascimento = findViewById(R.id.editDataNascimento);
        editRaca = findViewById(R.id.editRaca);
        cadastrar = findViewById(R.id.cadastrar);
        editSexo = findViewById(R.id.editSexo);
    }
}