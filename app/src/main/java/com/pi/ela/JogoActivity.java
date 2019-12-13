package com.pi.ela;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pi.classes.Game;
import com.pi.classes.OpcaoAdapter;
import com.pi.classes.Peca;
import com.pi.classes.Pergunta;
import com.pi.classes.Ponto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogoActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, DialogInterface.OnClickListener {
    private final String AZUL = "Azul";
    private final String VERMELHO = "Vermelho";
    private final String VERDE = "Verde";
    private final String AMARELO = "Amarelo";
    private Game ludo;
    private ImageView dadoAzul;
    private ImageView dadoVermelho;
    private ImageView dadoAmarelo;
    private ImageView dadoVerde;
    private Random dado;
    private int valor;
    private String dadoGirado;
    private boolean acertou;
    private Pergunta pergunta;
    // private AlertDialog fim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        ConstraintLayout espaco = findViewById(R.id.layoutSpace);

        int larguraTela = getWindowManager().getDefaultDisplay().getWidth();
        dado = new Random();
        ludo = new Game(this, larguraTela);
        ludo.setOnTouchListener(this);
        espaco.addView(ludo);


        dadoAzul = findViewById(R.id.imgDadoAzul);
        dadoAzul.setOnClickListener(this);
        dadoAzul.setImageDrawable(getDrawable(R.mipmap.dado6));

        dadoVermelho = findViewById(R.id.imgDadoVermelho);
        dadoVermelho.setOnClickListener(this);

        dadoAmarelo = findViewById(R.id.imgDadoAmarelo);
        dadoAmarelo.setOnClickListener(this);

        dadoVerde = findViewById(R.id.imgDadoVerde);
        dadoVerde.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ludo.comecar();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Ponto p = new Ponto((int) event.getX(), (int) event.getY());

            switch (dadoGirado) {

                case "Azul":
                    avancarPeca(p, ludo.getAzuis(), AZUL);
                    return true;
                case "Vermelho":
                    avancarPeca(p, ludo.getVermelhas(), VERMELHO);
                    return true;
                case "Amarelo":
                    avancarPeca(p, ludo.getAmarelas(), AMARELO);
                    return true;
                case "Verde":
                    avancarPeca(p, ludo.getVerdes(), VERDE);
                    return true;
            }
        }
//        Toast.makeText(this,event.getX()+"",Toast.LENGTH_SHORT).show();
        return false;

    }


    @Override
    public void onClick(View view) {
        if (valor == 0) {
            valor = dado.nextInt(6) + 1;
        }
        if (view == dadoAzul && ludo.getjDaVez() == 1) {
            dadoGirado = AZUL;
            mudarImgPeca(ludo.getAzuis(), valor, AZUL);
            mudarImgDado(dadoAzul, valor);
            testaTodasEmCasa(ludo.getAzuis());
        }
        if (view == dadoVermelho && ludo.getjDaVez() == 2) {
            dadoGirado = VERMELHO;
            mudarImgPeca(ludo.getVermelhas(), valor, VERMELHO);
            mudarImgDado(dadoVermelho, valor);
            testaTodasEmCasa(ludo.getVermelhas());
        }
        if (view == dadoVerde && ludo.getjDaVez() == 3) {
            dadoGirado = VERDE;
            mudarImgPeca(ludo.getVerdes(), valor, VERDE);
            mudarImgDado(dadoVerde, valor);
            testaTodasEmCasa(ludo.getVerdes());
        }
        if (view == dadoAmarelo && ludo.getjDaVez() == 4) {
            dadoGirado = AMARELO;
            mudarImgPeca(ludo.getAmarelas(), valor, AMARELO);
            mudarImgDado(dadoAmarelo, valor);
            testaTodasEmCasa(ludo.getAmarelas());
        }
    }

    private void mudarImgPeca(ArrayList<Peca> pecas, int valor, String cor) {
        for (Peca peca : pecas) {
            if (peca.getPonto().emCasa() && valor != 6) {
                continue;
            }
            if (peca.getPonto().finalizado()) {
                continue;
            }
            switch (cor) {
                case AZUL:
                    peca.setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3), 400, 400, true));
                    break;
                case VERMELHO:
                    peca.setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4), 400, 400, true));
                    break;
                case VERDE:
                    peca.setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2), 400, 400, true));
                    break;
                case AMARELO:
                    peca.setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1), 400, 400, true));
                    break;
            }
        }
    }

    private void mudarImgPeca(ArrayList<Peca> pecas, int cor) {
        pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), cor), 400, 400, true));
        pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), cor), 400, 400, true));
        pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), cor), 400, 400, true));
        pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), cor), 400, 400, true));


    }

    private void mudarImgDado(ImageView myImgView, int numero) {
        if (numero == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado1_shine, getApplicationContext().getTheme()));
            } else {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado1_shine));
            }

        }
        if (numero == 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado2_shine, getApplicationContext().getTheme()));
            } else {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado2_shine));
            }

        }
        if (numero == 3) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado3_shine, getApplicationContext().getTheme()));
            } else {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado3_shine));
            }

        }
        if (numero == 4) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado4_shine, getApplicationContext().getTheme()));
            } else {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado4_shine));
            }

        }
        if (numero == 5) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado5_shine, getApplicationContext().getTheme()));
            } else {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado5_shine));
            }

        }
        if (numero == 6) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado6_shine, getApplicationContext().getTheme()));
            } else {
                myImgView.setImageDrawable(getResources().getDrawable(R.mipmap.dado6_shine));
            }
        }
    }

    private void testaTodasEmCasa(ArrayList<Peca> pecas) {
        boolean todasEmCasa = true;
        for (Peca c : pecas) {
            if (!c.getPonto().emCasa()) {
                todasEmCasa = false;
            }
        }
        if (todasEmCasa && valor != 6) {
            passarAVez();
        } else {
            fazerPergunta("oiiiiiii");
        }
    }

    private void passarAVez() {
        ludo.proximoDaVez();
        dadoGirado = "";
        valor = 0;

        if (ludo.getjDaVez() == 2) {
            dadoVermelho.setImageDrawable(getDrawable(R.mipmap.dado6));
            mudarImgPeca(ludo.getAzuis(), R.mipmap.peca3_shine);
        } else if (ludo.getjDaVez() == 3) {
            dadoVerde.setImageDrawable(getDrawable(R.mipmap.dado6));
            mudarImgPeca(ludo.getVermelhas(), R.mipmap.peca4_shine);
        } else if (ludo.getjDaVez() == 4) {
            dadoAmarelo.setImageDrawable(getDrawable(R.mipmap.dado6));
            mudarImgPeca(ludo.getVerdes(), R.mipmap.peca2_shine);
        } else if (ludo.getjDaVez() == 1) {
            dadoAzul.setImageDrawable(getDrawable(R.mipmap.dado6));
            mudarImgPeca(ludo.getAmarelas(), R.mipmap.peca1_shine);
        }
    }

    private void avancarPeca(Ponto p, ArrayList<Peca> pecas, String cor) {

        for (Peca peca : pecas) {
            if (peca.equals(p)) {

                if (peca.getPonto().emCasa()) {
                    if (valor == 6) {
                        switch (cor) {
                            case AZUL:
                                this.ludo.definirPosicao(peca, Ponto.QAzul, Ponto.P2);
                                break;
                            case VERMELHO:
                                this.ludo.definirPosicao(peca, Ponto.QVermelho, Ponto.P6);
                                break;
                            case VERDE:
                                this.ludo.definirPosicao(peca, Ponto.QVerde, Ponto.P8);
                                break;
                            case AMARELO:
                                this.ludo.definirPosicao(peca, Ponto.QAmarelo, Ponto.P4);
                                break;
                        }

                        ludo.proximoDaVez();
                        dadoGirado = "";
                        valor = 0;
                        if (ludo.getjDaVez() == 2) {
                            mudarImgPeca(pecas, R.mipmap.peca3_shine);
//                            pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));
//                            pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));
//                            pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));
//                            pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));
                            dadoVermelho.setImageDrawable(getDrawable(R.mipmap.dado6));

                        } else if (ludo.getjDaVez() == 3) {
                            mudarImgPeca(pecas, R.mipmap.peca4_shine);
//                            pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
//                            pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
//                            pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
//                            pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
                            dadoVerde.setImageDrawable(getDrawable(R.mipmap.dado6));

                        } else if (ludo.getjDaVez() == 4) {
                            mudarImgPeca(pecas, R.mipmap.peca2_shine);
//                            pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
//                            pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
//                            pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
//                            pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
                            dadoAmarelo.setImageDrawable(getDrawable(R.mipmap.dado6));

                        } else if (ludo.getjDaVez() == 1) {
                            mudarImgPeca(pecas, R.mipmap.peca1_shine);
//                            pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
//                            pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
//                            pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
//                            pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
                            dadoAzul.setImageDrawable(getDrawable(R.mipmap.dado6));

                        }
                    }
                } else {
                    Ponto novaPosicao[] = new Ponto[]{
                            peca.getQuadrante(),
                            peca.getPonto()
                    };
                    for (int i = 1; i <= valor; i++) {
                        boolean movido = false;
                        if (cor == AZUL) {
                            //Para finalizar a peça
                            if (novaPosicao[0] == Ponto.QAzul && novaPosicao[1] == Ponto.P6) {
                                novaPosicao[1] = Ponto.FAzul;
                                break;
                            }//testa a penúltima posição
                            else if (novaPosicao[0] == Ponto.QAzul && novaPosicao[1] == Ponto.P5) {
                                novaPosicao[1] = Ponto.P6;
                                movido = true;
                            }//testa para entrar na trilha
                            else if (novaPosicao[0] == Ponto.QAzul && novaPosicao[1] == Ponto.P4) {
                                novaPosicao[1] = Ponto.P5;
                                movido = true;
                            }
                        } else if (cor == VERMELHO) {
                            //Para finalizar a peça
                            if (novaPosicao[0] == Ponto.QVermelho && novaPosicao[1] == Ponto.P8) {
                                novaPosicao[1] = Ponto.FVermelho;
                                break;
                            }//testa a penúltima posição
                            else if (novaPosicao[0] == Ponto.QVermelho && novaPosicao[1] == Ponto.P5) {
                                novaPosicao[1] = Ponto.P8;
                                movido = true;
                            }//testa para entrar na trilha
                            else if (novaPosicao[0] == Ponto.QVermelho && novaPosicao[1] == Ponto.P2) {
                                novaPosicao[1] = Ponto.P5;
                                movido = true;
                            }
                        } else if (cor == VERDE) {
                            //Para finalizar a peça
                            if (novaPosicao[0] == Ponto.QVerde && novaPosicao[1] == Ponto.P4) {
                                novaPosicao[1] = Ponto.FVerde;
                                break;
                            }//testa a penúltima posição
                            else if (novaPosicao[0] == Ponto.QVerde && novaPosicao[1] == Ponto.P5) {
                                novaPosicao[1] = Ponto.P4;
                                movido = true;
                            }//testa para entrar na trilha
                            else if (novaPosicao[0] == Ponto.QVerde && novaPosicao[1] == Ponto.P6) {
                                novaPosicao[1] = Ponto.P5;
                                movido = true;
                            }
                        } else if (cor == AMARELO) {
                            //Para finalizar a peça
                            if (novaPosicao[0] == Ponto.QAmarelo && novaPosicao[1] == Ponto.P2) {
                                novaPosicao[1] = Ponto.FAmarelo;
                                break;
                            }//testa a penúltima posição
                            else if (novaPosicao[0] == Ponto.QAmarelo && novaPosicao[1] == Ponto.P5) {
                                novaPosicao[1] = Ponto.P2;
                                movido = true;
                            }//testa para entrar na trilha
                            else if (novaPosicao[0] == Ponto.QAmarelo && novaPosicao[1] == Ponto.P8) {
                                novaPosicao[1] = Ponto.P5;
                                movido = true;
                            }
                        }

                        if (!movido) {
                            novaPosicao = Ponto.getProximo(novaPosicao[0], novaPosicao[1]);

                        }
                    }


                    ludo.proximoDaVez();
                    dadoGirado = "";
                    valor = 0;
                    this.ludo.definirPosicao(peca, novaPosicao[0], novaPosicao[1]);
                    if (ludo.getAzuis().get(0).getPonto() == Ponto.FAzul) {
                        if (ludo.getAzuis().get(1).getPonto() == Ponto.FAzul) {
                            if (ludo.getAzuis().get(2).getPonto() == Ponto.FAzul) {
                                if (ludo.getAzuis().get(3).getPonto() == Ponto.FAzul) {
                                    fimJogo("Azul");
                                }
                            }
                        }
                    }
                    if (ludo.getVermelhas().get(0).getPonto() == Ponto.FVermelho) {
                        if (ludo.getVermelhas().get(1).getPonto() == Ponto.FVermelho) {
                            if (ludo.getVermelhas().get(2).getPonto() == Ponto.FVermelho) {
                                if (ludo.getVermelhas().get(3).getPonto() == Ponto.FVermelho) {
                                    fimJogo("Vermelho");
                                }
                            }
                        }
                    }
                    if (ludo.getAmarelas().get(0).getPonto() == Ponto.FAmarelo) {
                        if (ludo.getAmarelas().get(1).getPonto() == Ponto.FAmarelo) {
                            if (ludo.getAmarelas().get(2).getPonto() == Ponto.FAmarelo) {
                                if (ludo.getAmarelas().get(3).getPonto() == Ponto.FAmarelo) {
                                    fimJogo("Amarelo");
                                }
                            }
                        }
                    }
                    if (ludo.getVerdes().get(0).getPonto() == Ponto.FVerde) {
                        if (ludo.getVerdes().get(1).getPonto() == Ponto.FVerde) {
                            if (ludo.getVerdes().get(2).getPonto() == Ponto.FVerde) {
                                if (ludo.getVerdes().get(3).getPonto() == Ponto.FVerde) {
                                    fimJogo("Verde");
                                }
                            }
                        }
                    }

                    if (ludo.getjDaVez() == 2) {
                        pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));
                        pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));
                        pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));
                        pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true));

                        dadoVermelho.setImageDrawable(getDrawable(R.mipmap.dado6));


                    } else if (ludo.getjDaVez() == 3) {
                        pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
                        pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
                        pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
                        pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true));
                        dadoVerde.setImageDrawable(getDrawable(R.mipmap.dado6));

                    } else if (ludo.getjDaVez() == 4) {
                        pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
                        pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
                        pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
                        pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true));
                        dadoAmarelo.setImageDrawable(getDrawable(R.mipmap.dado6));

                    } else if (ludo.getjDaVez() == 1) {
                        pecas.get(0).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
                        pecas.get(1).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
                        pecas.get(2).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
                        pecas.get(3).setImagem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true));
                        dadoAzul.setImageDrawable(getDrawable(R.mipmap.dado6));

                    }

                }
                // testa se houve peça comida

                if (!cor.equals(AZUL)) {
                    for (int i = 0; i < 4; i++) {
                        if (ludo.getAzuis().get(i).getPonto() == peca.getPonto() && ludo.getAzuis().get(i).getQuadrante() == peca.getQuadrante()) {
                            if (i == 0) {
                                this.ludo.definirPosicao(ludo.getAzuis().get(i), Ponto.CAZUL, Ponto.C1);
//
                            } else if (i == 1) {
                                this.ludo.definirPosicao(ludo.getAzuis().get(i), Ponto.CAZUL, Ponto.C2);
//
                            } else if (i == 2) {
                                this.ludo.definirPosicao(ludo.getAzuis().get(i), Ponto.CAZUL, Ponto.C3);
//
                            } else if (i == 3) {
                                this.ludo.definirPosicao(ludo.getAzuis().get(i), Ponto.CAZUL, Ponto.C4);
//
                            }
                        }
                    }
                }

                if (!cor.equals(VERMELHO)) {
                    for (int i = 0; i < 4; i++) {
                        if (ludo.getVermelhas().get(i).getPonto() == peca.getPonto() && ludo.getVermelhas().get(i).getQuadrante() == peca.getQuadrante()) {
                            if (i == 0) {
                                this.ludo.definirPosicao(ludo.getVermelhas().get(i), Ponto.CVERMELHO, Ponto.C1);
                            } else if (i == 1) {

                                this.ludo.definirPosicao(ludo.getVermelhas().get(i), Ponto.CVERMELHO, Ponto.C2);
                            } else if (i == 2) {

                                this.ludo.definirPosicao(ludo.getVermelhas().get(i), Ponto.CVERMELHO, Ponto.C3);
                            } else if (i == 3) {

                                this.ludo.definirPosicao(ludo.getVermelhas().get(i), Ponto.CVERMELHO, Ponto.C4);
                            }

                        }
                    }
                }
                if (!cor.equals(VERDE)) {
                    for (int i = 0; i < 4; i++) {
                        if (ludo.getVerdes().get(i).getPonto() == peca.getPonto() && ludo.getVerdes().get(i).getQuadrante() == peca.getQuadrante()) {
                            if (i == 0) {
                                this.ludo.definirPosicao(ludo.getVerdes().get(i), Ponto.CVERDE, Ponto.C1);
                            } else if (i == 1) {

                                this.ludo.definirPosicao(ludo.getVerdes().get(i), Ponto.CVERDE, Ponto.C2);
                            } else if (i == 2) {

                                this.ludo.definirPosicao(ludo.getVerdes().get(i), Ponto.CVERDE, Ponto.C3);
                            } else if (i == 3) {

                                this.ludo.definirPosicao(ludo.getVerdes().get(i), Ponto.CVERDE, Ponto.C4);
                            }
                        }
                    }
                }

                if (!cor.equals(AMARELO)) {
                    for (int i = 0; i < 4; i++) {
                        if (ludo.getAmarelas().get(i).getPonto() == peca.getPonto() && ludo.getAmarelas().get(i).getQuadrante() == peca.getQuadrante()) {
                            if (i == 0) {
                                this.ludo.definirPosicao(ludo.getAmarelas().get(i), Ponto.CAMARELO, Ponto.C1);
                            } else if (i == 1) {

                                this.ludo.definirPosicao(ludo.getAmarelas().get(i), Ponto.CAMARELO, Ponto.C2);
                            } else if (i == 2) {

                                this.ludo.definirPosicao(ludo.getAmarelas().get(i), Ponto.CAMARELO, Ponto.C3);
                            } else if (i == 3) {

                                this.ludo.definirPosicao(ludo.getAmarelas().get(i), Ponto.CAMARELO, Ponto.C4);
                            }

                        }
                    }
                }
            }
        }
    }

    private void fimJogo(String ganhador) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fim de Jogo");
        builder.setMessage(ganhador + " ganhou!");
        builder.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                JogoActivity.this.finish();
                ludo.setJogando(false);
            }
        });

        builder.create().show();
    }

    private void fazerPergunta(String ganhador) {
        int aleatorio = new Random().nextInt(180) + 1;
        pergunta = Pergunta.getPergunta(this, aleatorio);
        acertou = false;
        // OpcaoAdapter adaptador = new OpcaoAdapter(this, pergunta.getOpcoes());
        testaAcerto(0);
        View convertView = LayoutInflater.from(this).inflate(R.layout.popup_pergunta, null);
        TextView txtTempoVerbal = convertView.findViewById(R.id.txtTempoVerbal);
        TextView txtPergunta = convertView.findViewById(R.id.txtPergunta);
        ListView ltvOpcoes = convertView.findViewById(R.id.ltvOpcoes);

        txtTempoVerbal.setText(pergunta.getTempoVerbal());
        txtPergunta.setText(pergunta.getPergunta());
        OpcaoAdapter listAdapter = new OpcaoAdapter(this, pergunta.getOpcoes());
        //ltvOpcoes.setAdapter(listAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogStyle);
        // builder.setView(convertView);
        builder.setCancelable(false);
        TextView txtTitulo = new TextView(this);
        txtTitulo.setTextAppearance(this, R.style.CustomTitle);
        txtTitulo.setText(pergunta.getTempoVerbal() + "\n\n" + pergunta.getPergunta());
        builder.setCustomTitle(txtTitulo);
//        builder.setMessage(pergunta.getPergunta()); setMessage e SingleChoice não funcionam juntos
        builder.setPositiveButton("Responder", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //JogoActivity.this.finish();
                //ludo.setJogando(false);
            }
        });
        builder.setSingleChoiceItems(listAdapter, 0, this);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!acertou) {
                    passarAVez();
                }
            }
        });
        builder.create().show();
    }

    private void testaAcerto(int which) {
        if (which + 1 == pergunta.getCorreta()) {
            acertou = true;
        } else {
            acertou = false;
            // Toast.makeText(JogoActivity.this, "Você errou!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        testaAcerto(which);
    }
}

