package com.pi.ela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.pi.dao.PerguntaDao;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imv = (ImageView) findViewById(R.id.jogar);
        imv.setOnClickListener(this);
        imv = findViewById(R.id.verMais);
        imv.setOnClickListener(this);
        //PerguntaDao.criarTabela(this);
        //PerguntaDao.inserir(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jogar:
                Intent jogar = new Intent(MainActivity.this, JogoActivity.class);
                startActivity(jogar);
                break;
            case R.id.verMais:
                Intent verMais = new Intent(MainActivity.this, SobreActivity.class);
                startActivity(verMais);
        }
    }

}
