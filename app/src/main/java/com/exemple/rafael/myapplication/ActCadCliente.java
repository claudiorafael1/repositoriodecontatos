package com.exemple.rafael.myapplication;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.exemple.rafael.myapplication.database.ClienteOpenHelper;
import com.exemple.rafael.myapplication.dominio.entidades.Cliente;
import com.exemple.rafael.myapplication.dominio.repositorios.ClienteRepositorio;

public class ActCadCliente extends AppCompatActivity {
private EditText edtNome;
private EditText edtEndereco;
private EditText edEmail;
private EditText edtTelefone;

    private SQLiteDatabase conexao;
    private ClienteOpenHelper coh;
    private ConstraintLayout layoutContentActCadCLiente;

        private Cliente cliente;
private ClienteRepositorio clienterepositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_cliente);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtNome=(EditText)findViewById(R.id.edtNome);
        edtEndereco=(EditText)findViewById(R.id.edtEndereco);
        edEmail=(EditText)findViewById(R.id.edtEmail);
        edtTelefone=(EditText)findViewById(R.id.edtTelefone);

        layoutContentActCadCLiente = (ConstraintLayout) findViewById(R.id.layoutContentActCadCLiente);
        criarConexao();
    }
public void confirma(){
cliente = new Cliente();
    if(validaCampos()==false){
        try {
            clienterepositorio.inserir(cliente);
            finish();
        }catch(SQLException ex){
            AlertDialog.Builder dlg= new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }
    }

}

    private void criarConexao(){
        try{
            coh  = new ClienteOpenHelper(this);
            conexao=coh.getWritableDatabase();

            Snackbar.make(layoutContentActCadCLiente, R.string.message_conexao_criada_com_sucesso,Snackbar.LENGTH_LONG).setAction("OK",null).show();
            clienterepositorio = new ClienteRepositorio(conexao);
        }
        catch(SQLException ex){
            AlertDialog.Builder dlg= new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }



    }
    private boolean validaCampos(){
      boolean res=false;

        String nome =edtNome.getText().toString();
        String endereco =edtEndereco.getText().toString();
        String email =edEmail.getText().toString();
        String telefone=edtTelefone.getText().toString();

        cliente.telefone=telefone;
        cliente.email=email;
        cliente.endereco=endereco;
        cliente.nome=nome;


        if(res=isCampoVazio(nome)){

            edtNome.requestFocus();
        } else
            if (res=isCampoVazio(endereco)) {

                edtEndereco.requestFocus();
            }else
            if(res=isCampoVazio(email)){

                edEmail.requestFocus();
            } else
                if (res=isCampoVazio(telefone)) {

                   edtTelefone.requestFocus();
                }

    if(res){
        AlertDialog.Builder dlg=new AlertDialog.Builder(this);
        dlg.setTitle(R.string.title_Aviso);
        dlg.setMessage(R.string.message_campos_invalidos_brancos);
        dlg.setNeutralButton(com.exemple.rafael.myapplication.R.string.lbl_ok,null);
        dlg.show();
            }

        return res;
    }
    private  boolean isCampoVazio(String valor){

        boolean result=(TextUtils.isEmpty(valor)||valor.trim().isEmpty());
        return  result;
    }

    private  boolean isEmailValido(String email){
        boolean resultado=(!isCampoVazio(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_cliente,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
int id=item.getItemId();
    switch (id){

        case R.id.action_ok:
         //   Toast.makeText(this,"Botão Ok Selecionado",Toast.LENGTH_SHORT).show();
            confirma();
            break;
        case R.id.action_cancelar:
           // Toast.makeText(this,"Botão Cancelar Selecionado",Toast.LENGTH_SHORT).show();
            finish();
            break;

    }
        return super.onOptionsItemSelected(item);
    }
}
