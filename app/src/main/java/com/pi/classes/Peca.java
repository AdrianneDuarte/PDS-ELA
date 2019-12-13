package com.pi.classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Peca extends Paint {
    private int tamanho;
    private int x;
    private int y;
    private Bitmap imagem;
    private Rect src;
    private Rect dest;
    private Ponto ponto;
    private Ponto quadrante;

    public Peca(int tamanho) {
        this.tamanho = tamanho;
    }

    public Peca(int tamanho, int x, int y, Bitmap imagem) {
        this.tamanho = tamanho;
        this.x = x;
        this.y = y;
        this.ponto = new Ponto(x, y);
        this.quadrante = new Ponto(0, 0);
        this.imagem = imagem;

        src = new Rect(0, 0, this.imagem.getWidth() - 1, this.imagem.getHeight() - 1);
        dest = new Rect(this.x, this.y, this.x + this.tamanho, this.y + this.tamanho);
    }

    public void desenhar(Canvas canvas) {
        canvas.drawBitmap(imagem, this.src, this.dest, this);
    }

    public void moverCima() {
        y -= tamanho;
    }

    public void moverBaixo() {
        y += tamanho;
    }

    public void moverDireita() {
        x += tamanho;
    }

    public void moverEsquerda() {
        x -= tamanho;
    }

    public void definirPosicao(int x, int y) {
        this.x = x;
        this.y = y;
        dest.left = x;
        dest.top = y;
        dest.right = this.x + this.tamanho;
        dest.bottom = this.y + this.tamanho;
    }

    public void definirPosicao(Ponto quadrante, Ponto ponto) {
        this.ponto = ponto;
        this.quadrante = quadrante;
        this.x = quadrante.getX() + ponto.getX();
        this.y = quadrante.getY() + ponto.getY();
        dest.left = quadrante.getX() + ponto.getX();
        dest.top = quadrante.getY() + ponto.getY();
        dest.right = quadrante.getX() + ponto.getX() + this.tamanho;
        dest.bottom = quadrante.getY() + ponto.getY() + this.tamanho;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public Ponto getQuadrante() {
        return quadrante;
    }

    public void getProximo() {
        Ponto.getProximo(this.quadrante, this.ponto);
    }

    @Override
    public boolean equals(Object obj) {
        Ponto p = (Ponto) obj;
        if (p.getX() >= dest.left && p.getX() <= dest.right && p.getY() >= dest.top && p.getY() <= dest.bottom) {
            return true;
        }
        return false;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }
}
