package com.pi.classes;


import java.util.ArrayList;

public class Ponto {
    private int x;
    private int y;


    public static final Ponto C1 = new Ponto(70, 70);
    public static final Ponto C2 = new Ponto(70, 140);
    public static final Ponto C3 = new Ponto(140, 70);
    public static final Ponto C4 = new Ponto(140, 140);

    public static final Ponto CAZUL = new Ponto(0, 190);
    public static final Ponto CVERMELHO = new Ponto(475, 190);
    public static final Ponto CVERDE = new Ponto(475, 660);
    public static final Ponto CAMARELO = new Ponto(0, 660);

    public static final Ponto P1 = new Ponto(27, 27);
    public static final Ponto P2 = new Ponto(108, 27);
    public static final Ponto P3 = new Ponto(189, 27);
    public static final Ponto P4 = new Ponto(27, 108);
    public static final Ponto P5 = new Ponto(108, 108);
    public static final Ponto P6 = new Ponto(189, 108);
    public static final Ponto P7 = new Ponto(27, 189);
    public static final Ponto P8 = new Ponto(108, 189);
    public static final Ponto P9 = new Ponto(189, 189);
    public static final Ponto QAzul = new Ponto(0, 423);
    public static final Ponto QVermelho = new Ponto(235, 190);
    public static final Ponto QVerde = new Ponto(475, 425);
    public static final Ponto QAmarelo = new Ponto(235, 660);

    public static final Ponto FAzul = new Ponto(270, 108);
    public static final Ponto FVermelho = new Ponto(108, 270);
    public static final Ponto FVerde = new Ponto(-54, 108);
    public static final Ponto FAmarelo = new Ponto(108, -54);


    public static final Ponto centroVerde = new Ponto(-54, 108);

    public static final ArrayList<Ponto> POSICOES = new ArrayList() {{
        add(P1);
        add(P2);
        add(P3);
        add(P4);
        add(P5);
        add(P6);
        add(P7);
        add(P8);
        add(P9);

    }};
    public static final ArrayList<Ponto> QUADRANTES = new ArrayList() {{
        add(QAzul);
        add(QVermelho);
        add(QVerde);
        add(QAmarelo);

    }};

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {

        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean finalizado() {
        if (this.equals(FAzul)) {
            return true;
        }
        if (this.equals(FVermelho)) {
            return true;
        }
        if (this.equals(FVerde)) {
            return true;
        }
        if (this.equals(FAmarelo)) {
            return true;
        }

        return false;
    }

    public boolean emCasa() {
        for (int i = 0; i < 9; i++) {
            Ponto p = POSICOES.get(i);
            if (this.equals(p)) {
                return false;
            }
        }
        return true;
    }

    public boolean ehPonto() {
        for (int i = 0; i < 9; i++) {
            if (this.equals(POSICOES.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean ehQuadrante() {
        for (int i = 0; i < 4; i++) {
            if (this.equals(QUADRANTES.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static Ponto[] getProximo(Ponto quadrante, Ponto ponto) {
        if (quadrante == QAzul && ponto == P3) {
            quadrante = QVermelho;
            ponto = P7;
        } else if (quadrante == QVermelho && ponto == P9) {
            quadrante = QVerde;
            ponto = P1;
        } else if (quadrante == QVerde && ponto == P7) {
            quadrante = QAmarelo;
            ponto = P3;
        } else if (quadrante == QAmarelo && ponto == P1) {
            quadrante = QAzul;
            ponto = P9;
        } else if (ponto == P1) {
            ponto = P2;
        } else if (ponto == P2) {
            ponto = P3;
        } else if (ponto == P3) {
            ponto = P6;
        } else if (ponto == P4) {
            ponto = P1;
        } else if (ponto == P5) {
            ponto = P6;
        } else if (ponto == P6) {
            ponto = P9;
        } else if (ponto == P7) {
            ponto = P4;
        } else if (ponto == P8) {
            ponto = P7;
        } else if (ponto == P9) {
            ponto = P8;
        }
        return new Ponto[]{
                quadrante,
                ponto
        };
    }

    @Override
    public boolean equals(Object obj) {
        Ponto p = (Ponto) obj;
        return (this.x == p.x && this.y == p.y);
    }
}
