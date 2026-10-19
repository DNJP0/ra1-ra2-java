public class Main {
    public static void main(String[] args) {
        System.out.println("RA1 A - Fila circular");
        circular fila = new circular(3);
        System.out.println("Vazia inicialmente: " + fila.vazia());
        fila.insere(12);
        fila.insere(35);
        fila.insere(52);
        System.out.println("Cheia: " + fila.cheia());
        System.out.println("Removido: " + fila.remove());
        fila.insere(64);
        System.out.println("Primeiro: " + fila.primeiro());
        System.out.println("Ultimo: " + fila.ultimo());
        while (!fila.vazia()) {
            System.out.println(fila.remove());
        }
        System.out.println("Vazia no final: " + fila.vazia());

        System.out.println("RA1 B - Merge");
        circular a = new circular(4);
        circular b = new circular(5);
        a.insere(12);
        a.insere(35);
        a.insere(52);
        a.insere(64);
        b.insere(5);
        b.insere(15);
        b.insere(23);
        b.insere(55);
        b.insere(75);
        circular r = circular.merge(a, b);
        System.out.print("Resultado: ");
        while (!r.vazia()) {
            System.out.print(r.remove() + " ");
        }
        System.out.println();
        System.out.println("A vazia: " + a.vazia());
        System.out.println("B vazia: " + b.vazia());
    }
}
