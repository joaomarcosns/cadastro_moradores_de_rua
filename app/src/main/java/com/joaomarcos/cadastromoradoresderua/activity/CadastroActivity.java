package com.joaomarcos.cadastromoradoresderua.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.joaomarcos.cadastromoradoresderua.R;
import com.joaomarcos.cadastromoradoresderua.model.MoradorDeRua;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editOrientacaoSexual;
    private EditText editDataNascimento;
    private EditText editRaca;

    private Button cadastrar;

    private RadioGroup editSexo;
    private RadioButton sexoEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        iniciarComponente();
        validarCampos();
    }

    private void validarCampos() {
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString();
                String orientacaoSexual = editOrientacaoSexual.getText().toString();
                String dataNascimento = editDataNascimento.getText().toString();
                String raca = editRaca.getText().toString();
                int sexo = editSexo.getCheckedRadioButtonId();

                if (sexo < 0 || nome.isEmpty() || orientacaoSexual.isEmpty() || dataNascimento.isEmpty() || raca.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Todos os camopos são OBRIGATORIOS", Toast.LENGTH_LONG).show();
                }else {
                    sexoEscolhido = findViewById(sexo);
                    String sexoFim = sexoEscolhido.getText().toString();

                    cadastrarBanco(nome, orientacaoSexual, dataNascimento, raca, sexoFim);
                }


            }
        });
    }

    private void cadastrarBanco(String nome,String orientacaoSexual, String dataNascimento, String raca, String sexoFim) {

        MoradorDeRua moradorDeRua = new MoradorDeRua(nome, orientacaoSexual, dataNascimento, raca, sexoFim);
        FirebaseFirestore.getInstance().collection("moradores_de_rua").add(moradorDeRua)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i("TESTE", documentReference.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("TESTE", e.toString());
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