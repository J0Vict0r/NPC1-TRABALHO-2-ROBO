//OFICIAL

package trabalho2;
import java.util.Scanner;

public class PrimeiraMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dimensão do plano cartesiano (apenas valores positivos)
        int tamanhoX = 3; // Comprimento do eixo X
        int tamanhoY = 3; // Altura do eixo Y

        // Criando a matriz que representa o plano cartesiano
        int[][] area = new int[tamanhoY + 1][tamanhoX + 1];
        System.out.println("-------BEM-VINDO AO ROBÔ DE CONTROLE MANUAL-------");
        System.out.println("Para iniciar, escolha a cor do seu robô ");

        // Instancia o robô e pede a cor
        System.out.println("");
        System.out.println("(VERMELHO)");
        System.out.println("(ROSA)");
        System.out.println("(CINZA)");
        System.out.println("(PRETO)");
        System.out.println("");

        System.out.print("Digite a cor do robô: ");
        String cor = scanner.nextLine();
        Robo robo = new Robo(cor);
        robo.verificarCor(cor); 

        // Pede a posição do alimento
        System.out.println("Informe a posição do alimento ");
        System.out.print("Posição X: ");
        int alimentoX = scanner.nextInt();  
        System.out.print("Posição Y: ");
        int alimentoY = scanner.nextInt(); 

        // Verifica se o alimento está dentro da área
        robo.tratarPosicaoAlimento(alimentoX, alimentoY);
        
        
        // Inicia o loop de movimento do robô
        System.out.println("INÍCIO DA PARTIDA");
        
        robo.armazenarPosicaoRobo();
        exibirArea(area, robo.getRoboX(), robo.getRoboY(), alimentoX, alimentoY, tamanhoX, tamanhoY, cor);
        System.out.println("-------------------------------------");

        System.out.println(" ");
        System.out.print("Mova seu robô " + robo.getCor() + " de acordo com os comandos abaixo ");

        boolean encontrouAlimento = false;
        while (!encontrouAlimento) {
            System.out.print("Movimento disponível: up, down, left, right ou, seus respectivos, 1,2,3 e 4: " );
            String movimento = scanner.next();

            try {
                // Se o movimento for numérico, converte para inteiro
                if (movimento.matches("[1-4]")) {
                    int movimentoInt = Integer.parseInt(movimento);
                    // Chama o método sobrecarregado que aceita int
                    robo.mover(movimentoInt);
                } else {
                    // Caso contrário, usa o método com a string
                    robo.mover(movimento);
                }

                // Verifica se encontrou o alimento
                encontrouAlimento = robo.verificarPosicaoAlimento(alimentoX, alimentoY);

                // Exibe a área após o movimento
                exibirArea(area, robo.getRoboX(), robo.getRoboY(), alimentoX, alimentoY, tamanhoX, tamanhoY, cor);
                System.out.println("-------------------------------------");

            } catch (MovimentoInvalidoException e) {
                // Exceção de movimento inválido
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException erro) {
                // Trata a exceção se o robô sair dos limites
                robo.verificarPosicaoRobo(robo.getRoboX(), robo.getRoboY(), cor);
                System.out.println(erro.getMessage());
            }
        }

        // Finaliza o jogo
        System.out.println("------------------------------------");
        System.out.println("ESTATÍSTICAS - ROBÔ" + " ("+robo.getCor()+")" +": ");
        System.out.println("MOVIMENTOS VÁLIDOS = " + robo.getMovimentosValidos());
        System.out.println("MOVIMENTOS INVÁLIDOS = " + robo.getMovimentosInvalidos());
        System.out.println("MOVIMENTOS TOTAIS = "+ robo.getMovimentosTotais());
        System.out.println("------------------------------------");
        System.out.println("Jogo Finalizado! ");
        // Fechar o scanner ao final do programa
        scanner.close();
    }

    // Método para exibir a área
    public static void exibirArea(int[][] area, int roboX, int roboY, int x, int y, int tamanhoX, int tamanhoY, String cor) {
        // Preenche a área com espaços em branco
        for (int i = 0; i <= tamanhoY; i++) {
            for (int j = 0; j <= tamanhoX; j++) {
                area[i][j] = 0; // Inicializa com 0 (sem robô ou alimento)
            }
        }

        // Coloca o robô na posição
        area[roboY][roboX] = 1; // 1 representa o robô

        // Coloca o alimento na posição
        area[y][x] = 2; // 2 representa o alimento
        
        
        // Exibe o plano cartesiano
        for (int i = tamanhoY; i >= 0; i--) { // Começa do topo (maior Y) até 0
            for (int j = 0; j <= tamanhoX; j++) {
                if (area[i][j] == 1) {
                    System.out.print("Rp" + " "); // Exibe o robô
                } else if (area[i][j] == 2) {
                    System.out.print(" A "); // Exibe o alimento
                } else {
                    System.out.print(" . "); // Espaço vazio
                }
            }
            System.out.println(); // Quebra de linha
        }
    }
}