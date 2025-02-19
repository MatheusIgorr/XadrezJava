
import java.util.Scanner;

public class JogoDeXadrez {

    public static final int TAMANHO_TABULEIRO = 8;
    public static String[][] tabuleiro = new String[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

    public static void main(String[] args) {
        inicializarTabuleiro();
        imprimirTabuleiro();

        // Usando try-with-resources para garantir que o Scanner seja fechado
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nDigite o movimento (exemplo: 'e2 e4') ou 'sair' para encerrar: ");
                String movimento = scanner.nextLine();
                if (movimento.equalsIgnoreCase("sair")) {
                    break;
                }
                moverPeca(movimento);
                imprimirTabuleiro();
            }
        }
    }

    // Inicializa as peças no tabuleiro
    public static void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = "  "; // Posições vazias
            }
        }

        // Peças brancas
        tabuleiro[0][0] = tabuleiro[0][7] = "♖"; // Torres
        tabuleiro[0][1] = tabuleiro[0][6] = "♘"; // Cavalos
        tabuleiro[0][2] = tabuleiro[0][5] = "♗"; // Bispos
        tabuleiro[0][3] = "♕"; // Dama
        tabuleiro[0][4] = "♔"; // Rei
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            tabuleiro[1][i] = "♙"; // Peões
        }

        // Peças pretas
        tabuleiro[7][0] = tabuleiro[7][7] = "♖"; // Torres
        tabuleiro[7][1] = tabuleiro[7][6] = "♘"; // Cavalos
        tabuleiro[7][2] = tabuleiro[7][5] = "♗"; // Bispos
        tabuleiro[7][3] = "♕"; // Dama
        tabuleiro[7][4] = "♔"; // Rei
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            tabuleiro[6][i] = "♙"; // Peões
        }
    }

    // Imprime o tabuleiro de xadrez
    public static void imprimirTabuleiro() {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Faz o movimento de uma peça
    public static void moverPeca(String movimento) {
        String[] partes = movimento.split(" ");
        if (partes.length != 2) {
            System.out.println("Movimento inválido! Use o formato 'e2 e4'.");
            return;
        }

        String posicaoInicial = partes[0];
        String posicaoFinal = partes[1];

        // Converte as posições de 'a1', 'e4', etc., para índices do array
        int colunaInicial = posicaoInicial.charAt(0) - 'a'; // 'a' -> 0, 'b' -> 1, ...
        int linhaInicial = 8 - Character.getNumericValue(posicaoInicial.charAt(1)); // '1' -> 7, '2' -> 6, ...

        int colunaFinal = posicaoFinal.charAt(0) - 'a'; // 'a' -> 0, 'b' -> 1, ...
        int linhaFinal = 8 - Character.getNumericValue(posicaoFinal.charAt(1)); // '1' -> 7, '2' -> 6, ...

        // Verifica se a posição está dentro dos limites do tabuleiro
        if (linhaInicial < 0 || linhaInicial >= TAMANHO_TABULEIRO || colunaInicial < 0 || colunaInicial >= TAMANHO_TABULEIRO
                || linhaFinal < 0 || linhaFinal >= TAMANHO_TABULEIRO || colunaFinal < 0 || colunaFinal >= TAMANHO_TABULEIRO) {
            System.out.println("Posição inválida! Tente novamente.");
            return;
        }

        // Verifica se há uma peça na posição inicial
        if (tabuleiro[linhaInicial][colunaInicial].equals("  ")) {
            System.out.println("Não há peça na posição inicial!");
            return;
        }

        // Verifica se a peça na posição inicial é a que deve ser movida
        String pecaMovida = tabuleiro[linhaInicial][colunaInicial];

        // Move a peça
        tabuleiro[linhaFinal][colunaFinal] = pecaMovida;
        tabuleiro[linhaInicial][colunaInicial] = "  ";
    }
}
