package com.joaomarcos.cadastromoradoresderua.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastro_moradores_de_rua.R;

public class DeletarActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editOrientacaoSexual;
    private EditText editDataNascimento;
    private EditText editRaca;

    private Button deletar;

    private RadioGroup editSexo;
    private RadioButton sexoEscolhido;
    AlertDialog.Builder alerta_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar);
        deletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alerta_dialog.setTitle("Atenção");
                alerta_dialog.setMessage("Tem certeza que deseja excluir " + editNome + "?");
                alerta_dialog.setCancelable(true);
                alerta_dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       

                    }
                });
                alerta_dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alerta_dialog.show();
            }
        });
    }
}