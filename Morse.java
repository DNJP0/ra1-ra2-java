import java.util.Scanner;

class Nodo {
    char caractere;
    Nodo esquerda;
    Nodo direita;

    Nodo(char caractere) {
        this.caractere = caractere;
    }
}

class ArvoreBinariaMorse {
    private Nodo raiz;

    public ArvoreBinariaMorse() {
        inicializar();
    }

    public void inicializar() {
        raiz = new Nodo('\0');
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String[] codigos = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};
        for (int i = 0; i < letras.length; i++) {
            inserir(codigos[i], letras[i]);
        }
    }

    public void inserir(String codigo, char caractere) {
        Nodo atual = raiz;
        Scanner sinais = new Scanner(codigo);
        sinais.useDelimiter("");
        while (sinais.hasNext()) {
            switch (sinais.next()) {
                case ".":
                    if (atual.esquerda == null) {
                        atual.esquerda = new Nodo('\0');
                    }
                    atual = atual.esquerda;
                    break;
                case "-":
                    if (atual.direita == null) {
                        atual.direita = new Nodo('\0');
                    }
                    atual = atual.direita;
                    break;
                default:
                    sinais.close();
                    return;
            }
        }
        atual.caractere = caractere;
        sinais.close();
    }

    private char lerNodo(Nodo no) {
        if (no == null || no.caractere == '\0') {
            return '?';
        }
        return no.caractere;
    }

    public String buscar(String mensagem) {
        Scanner sinais = new Scanner(mensagem);
        sinais.useDelimiter("");
        Nodo atual = raiz;
        String resultado = "";
        boolean emCodigo = false;
        while (sinais.hasNext()) {
            switch (sinais.next()) {
                case ".":
                    if (atual != null) {
                        atual = atual.esquerda;
                    }
                    emCodigo = true;
                    break;
                case "-":
                    if (atual != null) {
                        atual = atual.direita;
                    }
                    emCodigo = true;
                    break;
                case " ": case "\t": case "\n": case "\r":
                    if (emCodigo) {
                        resultado += lerNodo(atual);
                        atual = raiz;
                        emCodigo = false;
                    }
                    break;
                case "/":
                    if (emCodigo) {
                        resultado += lerNodo(atual);
                    }
                    resultado += " ";
                    atual = raiz;
                    emCodigo = false;
                    break;
                default:
                    atual = null;
                    emCodigo = true;
                    break;
            }
        }
        if (emCodigo) {
            resultado += lerNodo(atual);
        }
        sinais.close();
        return resultado;
    }

    public String buscar(char caractere) {
        return buscarCodigo(raiz, caractere, "");
    }

    private String buscarCodigo(Nodo no, char caractere, String caminho) {
        if (no == null) {
            return null;
        }
        if (no.caractere == caractere) {
            return caminho;
        }
        String encontrado = buscarCodigo(no.esquerda, caractere, caminho + ".");
        if (encontrado != null) {
            return encontrado;
        }
        return buscarCodigo(no.direita, caractere, caminho + "-");
    }

    private char converterLetra(String texto) {
        switch (texto) {
            case "A": case "a": return 'A';
            case "B": case "b": return 'B';
            case "C": case "c": return 'C';
            case "D": case "d": return 'D';
            case "E": case "e": return 'E';
            case "F": case "f": return 'F';
            case "G": case "g": return 'G';
            case "H": case "h": return 'H';
            case "I": case "i": return 'I';
            case "J": case "j": return 'J';
            case "K": case "k": return 'K';
            case "L": case "l": return 'L';
            case "M": case "m": return 'M';
            case "N": case "n": return 'N';
            case "O": case "o": return 'O';
            case "P": case "p": return 'P';
            case "Q": case "q": return 'Q';
            case "R": case "r": return 'R';
            case "S": case "s": return 'S';
            case "T": case "t": return 'T';
            case "U": case "u": return 'U';
            case "V": case "v": return 'V';
            case "W": case "w": return 'W';
            case "X": case "x": return 'X';
            case "Y": case "y": return 'Y';
            case "Z": case "z": return 'Z';
            case "0": return '0';
            case "1": return '1';
            case "2": return '2';
            case "3": return '3';
            case "4": return '4';
            case "5": return '5';
            case "6": return '6';
            case "7": return '7';
            case "8": return '8';
            case "9": return '9';
            case " ": case "\t": case "\n": case "\r": return ' ';
            default: return '\0';
        }
    }

    public String codificar(String mensagem) {
        Scanner letras = new Scanner(mensagem);
        letras.useDelimiter("");
        String resultado = "";
        while (letras.hasNext()) {
            char letra = converterLetra(letras.next());
            if (resultado.length() > 0) {
                resultado += " ";
            }
            if (letra == ' ') {
                resultado += "/";
            } else if (letra == '\0') {
                resultado += "?";
            } else {
                resultado += buscar(letra);
            }
        }
        letras.close();
        return resultado;
    }

    public void exibir() {
        exibir(raiz, "", "raiz: ");
    }

    private void exibir(Nodo no, String recuo, String ramo) {
        if (no == null) {
            return;
        }
        char rotulo = no.caractere == '\0' ? '*' : no.caractere;
        System.out.println(recuo + ramo + rotulo);
        exibir(no.esquerda, recuo + "    ", ".: ");
        exibir(no.direita, recuo + "    ", "-: ");
    }
}

public class Morse {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        while (true) {
            System.out.println("1 - Texto para Morse");
            System.out.println("2 - Morse para texto");
            System.out.println("3 - Exibir arvore");
            System.out.println("0 - Sair");
            if (!entrada.hasNextLine()) {
                entrada.close();
                return;
            }
            switch (entrada.nextLine()) {
                case "1":
                    System.out.println("Digite letras A-Z, numeros ou espacos:");
                    if (entrada.hasNextLine()) {
                        System.out.println(arvore.codificar(entrada.nextLine()));
                    }
                    break;
                case "2":
                    System.out.println("Digite Morse: espaco entre letras e / entre palavras:");
                    if (entrada.hasNextLine()) {
                        System.out.println(arvore.buscar(entrada.nextLine()));
                    }
                    break;
                case "3":
                    arvore.exibir();
                    break;
                case "0":
                    entrada.close();
                    return;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }
}
