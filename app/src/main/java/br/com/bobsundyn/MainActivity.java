package br.com.bobsundyn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText textNome;
    Button btnCadastrar;
    ListView listAlunos;
    ArrayList<String> alunos = new ArrayList<>();
    ArrayAdapter<String> adapterAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNome = findViewById(R.id.textNome);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        listAlunos = findViewById(R.id.listAlunos);

        adapterAlunos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listAlunos.setAdapter(adapterAlunos);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeDigitado = textNome.getText().toString();
                alunos.add(nomeDigitado);
                adapterAlunos.notifyDataSetChanged();
            }
        });

        listAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String alunoClicado = alunos.get(i);
                Toast.makeText(MainActivity.this, "Você clicou em " + alunoClicado, Toast.LENGTH_SHORT).show();
            }
        });

        listAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final String alunoClicado = alunos.get(i);
                Toast.makeText(MainActivity.this, "Você clicou em " + alunoClicado, Toast.LENGTH_SHORT).show();
                AlertDialog alerta = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Excluir")
                        .setMessage("Deseja excluir o item " + alunoClicado + "?")
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alunos.remove(alunoClicado);
                                adapterAlunos.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Operação cancelada", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alerta.show();
                return false;
            }
        });
    }
}