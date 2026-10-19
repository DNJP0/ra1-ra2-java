public class circular {
    private int[] dados;
    private int inicio;
    private int quantidade;

    public circular(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva");
        }
        dados = new int[capacidade];
        inicio = 0;
        quantidade = 0;
    }

    public boolean cheia() {
        return quantidade == dados.length;
    }

    public boolean vazia() {
        return quantidade == 0;
    }

    public void insere(int elemento) {
        if (cheia()) {
            throw new IllegalStateException("Fila cheia");
        }
        int fim = (inicio + quantidade) % dados.length;
        dados[fim] = elemento;
        quantidade++;
    }

    public int remove() {
        if (vazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        int elemento = dados[inicio];
        inicio = (inicio + 1) % dados.length;
        quantidade--;
        return elemento;
    }

    public int primeiro() {
        if (vazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        return dados[inicio];
    }

    public int ultimo() {
        if (vazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        int indice = (inicio + quantidade - 1) % dados.length;
        return dados[indice];
    }

    public static circular merge(circular a, circular b) {
        int capacidade = a.quantidade + b.quantidade;
        if (capacidade == 0) {
            capacidade = 1;
        }
        circular r = new circular(capacidade);
        while (!a.vazia() && !b.vazia()) {
            if (a.primeiro() <= b.primeiro()) {
                r.insere(a.remove());
            } else {
                r.insere(b.remove());
            }
        }
        while (!a.vazia()) {
            r.insere(a.remove());
        }
        while (!b.vazia()) {
            r.insere(b.remove());
        }
        return r;
    }
}
