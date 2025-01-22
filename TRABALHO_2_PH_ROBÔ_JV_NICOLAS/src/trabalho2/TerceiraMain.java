//OFICIAL
/*Primeiro: suspeito de que o robô inteligente não está cumprindo sua função, que é ir é ultrapassar o limite uma única vez
 * */

//FUNCIONANDO (21/01/25)
package trabalho2;

import java.util.Random;
import java.util.Scanner;

public class TerceiraMain {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Dimensão do plano cartesiano (apenas valores positivos)
        int tamanhoX = 3; // Comprimento do eixo X
        int tamanhoY = 3; // Altura do eixo Y

        // Criando a matriz que representa o plano cartesiano
        int[][] area = new int[tamanhoY + 1][tamanhoX + 1];
        System.out.println("-------BEM-VINDO AO JOGO RANDÔMICO COM UM ROBÔ PADRÃO E UM INTELIGENTE------");
        System.out.println("Para iniciar, escolha a cor de cada robô");
        
        System.out.println("");
        System.out.println("(VERMELHO)");
        System.out.println("(ROSA)");
        System.out.println("(CINZA)");
        System.out.println("(PRETO)");
        System.out.println("");

        // Instancia o primeiro robô e pede a cor
        System.out.print("Digite a cor do primeiro robô: ");
        String cor1 = scanner.nextLine();
        Robo robo1 = new Robo(cor1);
        robo1.verificarCor(cor1);

        // Instancia o segundo robô e pede a cor
        System.out.print("Digite a cor do segundo robô: ");
        String cor2 = scanner.nextLine();
        RoboInteligente robo2 = new RoboInteligente(cor2);
        robo2.verificarCor(cor2);

        // Pede a posição do alimento
        System.out.println("Informe a posição do alimento");
        System.out.print("Posição X: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Posição Y: ");
        int alimentoY = scanner.nextInt();

        // Verifica se o alimento está dentro da área
        robo1.tratarPosicaoAlimento(alimentoX, alimentoY);

        // Define posição inicial dos robôs
        robo1.setRoboX(0);
        robo1.setRoboY(0);
        robo2.setRoboX(0);
        robo2.setRoboY(0);

        System.out.println("INÍCIO DA PARTIDA");

        // Exibe o plano inicial com ambos os robôs
        exibirArea(area, robo1.getRoboX(), robo1.getRoboY(), robo2.getRoboX(), robo2.getRoboY(), alimentoX, alimentoY, tamanhoX, tamanhoY, cor1, cor2);
        System.out.println("-------------------------------------");

        boolean encontrouAlimento = false;
        while (!encontrouAlimento) {
            // Movimentação alternada entre os robôs

            System.out.println("Vez do Robô 1 (" + cor1 + ") jogar.");
            int movimento1 = random.nextInt(4) + 1; // Movimento aleatório para o robô 1

            try {
                robo1.mover(movimento1);
                if (robo1.getRoboX() < 0 || robo1.getRoboY() < 0 || robo1.getRoboX() > tamanhoX || robo1.getRoboY() > tamanhoY) {
                    throw new MovimentoInvalidoException("Robô 1 tentou sair do plano!");
                }
            } catch (MovimentoInvalidoException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Robô 1 tentou sair do plano!");
                robo1.setRoboX(Math.max(0, Math.min(robo1.getRoboX(), tamanhoX)));
                robo1.setRoboY(Math.max(0, Math.min(robo1.getRoboY(), tamanhoY)));
            }

            // Exibe a área após o movimento do robô 1
            exibirArea(area, robo1.getRoboX(), robo1.getRoboY(), robo2.getRoboX(), robo2.getRoboY(), alimentoX, alimentoY, tamanhoX, tamanhoY, cor1, cor2);
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println(" ");
            
            System.out.println("Vez do Robô Inteligente (" + cor2 + ") jogar.");
            int movimento2 = random.nextInt(4) + 1; // Movimento aleatório para o robô Inteligente

            try {
                robo2.mover(movimento2);
                if (robo2.getRoboX() < 0 || robo2.getRoboY() < 0 || robo2.getRoboX() > tamanhoX || robo2.getRoboY() > tamanhoY) {
                    throw new MovimentoInvalidoException("Robô Inteligente tentou sair do plano!");
                }
            } catch (MovimentoInvalidoException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Robô Inteligente tentou sair do plano!");
                robo2.setRoboX(Math.max(0, Math.min(robo2.getRoboX(), tamanhoX)));
                robo2.setRoboY(Math.max(0, Math.min(robo2.getRoboY(), tamanhoY)));
            }

            // Exibe a área após o movimento do robô Inteligente
            exibirArea(area, robo1.getRoboX(), robo1.getRoboY(), robo2.getRoboX(), robo2.getRoboY(), alimentoX, alimentoY, tamanhoX, tamanhoY, cor1, cor2);
            Thread.sleep(1000); // Pausa de 1 segundo
            System.out.println("-------------------------------------");


            // Verifica se algum dos robôs encontrou o alimento
            if (robo1.verificarPosicaoAlimento(alimentoX, alimentoY)) {
                System.out.println("Robô 1 (" + cor1 + ") encontrou o alimento!");
                encontrouAlimento = true;
            } else if (robo2.verificarPosicaoAlimento(alimentoX, alimentoY)) {
                System.out.println("Robô Inteligente (" + cor2 + ") encontrou o alimento!");
                encontrouAlimento = true;
            }
        }

        // Finaliza o jogo
        System.out.println("------------------------------------");
        System.out.println("ESTATÍSTICAS - ROBÔ PADRÃO "+ "("+robo1.getCor()+")" +": ");
        System.out.println("MOVIMENTOS VÁLIDOS = " + robo1.getMovimentosValidos());
        System.out.println("MOVIMENTOS INVÁLIDOS = " + robo1.getMovimentosInvalidos());
        System.out.println("MOVIMENTOS TOTAIS = "+ robo1.getMovimentosTotais());

        System.out.println("------------------------------------");
        System.out.println("ESTATÍSTICAS - ROBÔ INTELIGENTE " + "("+robo2.getCor()+")" + ": " );
        System.out.println("MOVIMENTOS VÁLIDOS = " + robo2.getMovimentosValidos());
        System.out.println("MOVIMENTOS INVÁLIDOS = " + robo2.getMovimentosInvalidos());
        System.out.println("MOVIMENTOS TOTAIS = "+ robo2.getMovimentosTotais());
        System.out.println("------------------------------------");
        // Fecha o scanner
        System.out.println("Jogo Finalizado! ");
        scanner.close();
    }

    // Método para exibir a área
    public static void exibirArea(int[][] area, int robo1X, int robo1Y, int robo2X, int robo2Y, int alimentoX, int alimentoY, int tamanhoX, int tamanhoY, String cor1, String cor2) {
        // Preenche a área com espaços em branco
        for (int i = 0; i <= tamanhoY; i++) {
            for (int j = 0; j <= tamanhoX; j++) {
                area[i][j] = 0; // Inicializa com 0 (sem robôs ou alimento)
            }
        }

        // Coloca os robôs na posição
        area[robo1Y][robo1X] = 1; // 1 representa o robô 1
        area[robo2Y][robo2X] = 2; // 2 representa o robô Inteligente

        // Coloca o alimento na posição
        area[alimentoY][alimentoX] = 3; // 3 representa o alimento

        // Exibe o plano cartesiano
        for (int i = tamanhoY; i >= 0; i--) { // Começa do topo (maior Y) até 0
            for (int j = 0; j <= tamanhoX; j++) {
                if (area[i][j] == 1) {
                    System.out.print("Rp" + " "); // Exibe o robô 1
                } else if (area[i][j] == 2) {
                    System.out.print("Ri" + " "); // Exibe o robô Inteligente
                } else if (area[i][j] == 3) {
                    System.out.print(" A "); // Exibe o alimento
                } else {
                    System.out.print(" . "); // Espaço vazio
                }
            }
            System.out.println(); // Quebra de linha
        }
    }
}