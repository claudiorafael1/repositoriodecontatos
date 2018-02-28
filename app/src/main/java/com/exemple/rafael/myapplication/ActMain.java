package com.exemple.rafael.myapplication;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.exemple.rafael.myapplication.database.ClienteOpenHelper;

public class ActMain extends AppCompatActivity {


    private SQLiteDatabase conexao;
    private ClienteOpenHelper coh;
    private ConstraintLayout layoutContentMain;

    private void criarConexao(){
        try{
          coh  = new  ClienteOpenHelper(this);
          conexao=coh.getWritableDatabase();

            Snackbar.make(layoutContentMain, R.string.message_conexao_criada_com_sucesso,Snackbar.LENGTH_LONG).setAction("OK",null).show();
        }
        catch(SQLException ex){
            AlertDialog.Builder dlg= new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
layoutContentMain=(ConstraintLayout)findViewById(R.id.LayoutContentMain);
criarConexao();
fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Intent it = new Intent(ActMain.this,ActCadCliente.class);
                startActivity(it);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
