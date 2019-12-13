package com.pi.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pi.ela.R;

import java.util.ArrayList;


public class Game extends SurfaceView implements Runnable {
    private boolean jogando;
    private Thread thread;
    private SurfaceHolder surfaceholder;
    private Paint paint;
    private ArrayList<Peca> amarelas;
    private ArrayList<Peca> verdes;
    private ArrayList<Peca> azuis;
    private ArrayList<Peca> vermelhas;
    private int larguraTela;
    private int jDaVez;

    public Game(Context context, int larguraTela) {
        super(context);
        this.jogando = false;
        this.jDaVez = 1;
        this.larguraTela = larguraTela;
        this.surfaceholder = getHolder();
        this.paint = new Paint();
        int x1 = 70;
        int y1 = 260;
        int x2 = 550;
        int y2 = 740;
        this.azuis = new ArrayList<>();
        this.azuis.add(new Peca(50, Ponto.CAZUL.getX() + Ponto.C1.getX(), Ponto.CAZUL.getY() + Ponto.C1.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true)));
        this.azuis.add(new Peca(50, Ponto.CAZUL.getX() + Ponto.C2.getX(), Ponto.CAZUL.getY() + Ponto.C2.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true)));
        this.azuis.add(new Peca(50, Ponto.CAZUL.getX() + Ponto.C3.getX(), Ponto.CAZUL.getY() + Ponto.C3.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true)));
        this.azuis.add(new Peca(50, Ponto.CAZUL.getX() + Ponto.C4.getX(), Ponto.CAZUL.getY() + Ponto.C4.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca3_shine), 400, 400, true)));

        this.vermelhas = new ArrayList<>();
        this.vermelhas.add(new Peca(50, Ponto.CVERMELHO.getX() + Ponto.C1.getX(), Ponto.CVERMELHO.getY() + Ponto.C1.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true)));
        this.vermelhas.add(new Peca(50, Ponto.CVERMELHO.getX() + Ponto.C2.getX(), Ponto.CVERMELHO.getY() + Ponto.C2.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true)));
        this.vermelhas.add(new Peca(50, Ponto.CVERMELHO.getX() + Ponto.C3.getX(), Ponto.CVERMELHO.getY() + Ponto.C3.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true)));
        this.vermelhas.add(new Peca(50, Ponto.CVERMELHO.getX() + Ponto.C4.getX(), Ponto.CVERMELHO.getY() + Ponto.C4.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca4_shine), 400, 400, true)));

        this.amarelas = new ArrayList<>();
        this.amarelas.add(new Peca(50, Ponto.CAMARELO.getX() + Ponto.C1.getX(), Ponto.CAMARELO.getY() + Ponto.C1.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true)));
        this.amarelas.add(new Peca(50, Ponto.CAMARELO.getX() + Ponto.C2.getX(), Ponto.CAMARELO.getY() + Ponto.C2.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true)));
        this.amarelas.add(new Peca(50, Ponto.CAMARELO.getX() + Ponto.C3.getX(), Ponto.CAMARELO.getY() + Ponto.C3.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true)));
        this.amarelas.add(new Peca(50, Ponto.CAMARELO.getX() + Ponto.C4.getX(), Ponto.CAMARELO.getY() + Ponto.C4.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca1_shine), 400, 400, true)));

        this.verdes = new ArrayList<>();
        this.verdes.add(new Peca(50, Ponto.CVERDE.getX() + Ponto.C1.getX(), Ponto.CVERDE.getY() + Ponto.C1.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true)));
        this.verdes.add(new Peca(50, Ponto.CVERDE.getX() + Ponto.C2.getX(), Ponto.CVERDE.getY() + Ponto.C2.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true)));
        this.verdes.add(new Peca(50, Ponto.CVERDE.getX() + Ponto.C3.getX(), Ponto.CVERDE.getY() + Ponto.C3.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true)));
        this.verdes.add(new Peca(50, Ponto.CVERDE.getX() + Ponto.C4.getX(), Ponto.CVERDE.getY() + Ponto.C4.getY(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.peca2_shine), 400, 400, true)));


    }

    public ArrayList<Peca> getAmarelas() {
        return amarelas;
    }

    public ArrayList<Peca> getVerdes() {
        return verdes;
    }

    public ArrayList<Peca> getAzuis() {
        return azuis;
    }

    public ArrayList<Peca> getVermelhas() {
        return vermelhas;
    }

    public void proximoDaVez() {
        jDaVez++;
        if (jDaVez == 5) {
            jDaVez = 1;
        }
    }

    public int getjDaVez() {
        return jDaVez;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    @Override
    public void run() {
        Bitmap tabuleiro = BitmapFactory.decodeResource(getResources(), R.mipmap.tabuleiro);
        Rect src = new Rect(0, 0, tabuleiro.getWidth() - 1, tabuleiro.getHeight() - 1);
        Rect dest = new Rect(10, 200, larguraTela - 1 + 10, larguraTela - 1 + 200);
        while (jogando) {
            if (!surfaceholder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = surfaceholder.lockCanvas();
            canvas.drawBitmap(tabuleiro, src, dest, null);
            //this.peca.desenhar(canvas);
            for (Peca peca : azuis) {
                peca.desenhar(canvas);
            }

            for (Peca peca : vermelhas) {
                peca.desenhar(canvas);
            }

            for (Peca peca : amarelas) {
                peca.desenhar(canvas);
            }

            for (Peca peca : verdes) {
                peca.desenhar(canvas);
            }
            surfaceholder.unlockCanvasAndPost(canvas);
        }
    }

    public void comecar() {
        this.jogando = true;
        this.thread = new Thread(this);
        this.thread.start();

    }

    //    public void moverCima() {
//        peca.moverCima();
//    }
//
//    public void moverBaixo() {
//        peca.moverBaixo();
//    }
//
//    public void moverDireita() {
//        peca.moverDireita();
//    }
//
//    public void moverEsquerda() {
//        peca.moverEsquerda();
//    }
    public void definirPosicao(Peca p, int x, int y) {
        p.definirPosicao(x, y);
    }

    public void definirPosicao(Peca p, Ponto quadrante, Ponto ponto) {
        p.definirPosicao(quadrante, ponto);
    }
}
