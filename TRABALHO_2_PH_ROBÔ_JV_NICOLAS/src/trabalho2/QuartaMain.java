package trabalho2;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuartaMain {
    public static void main(String[] args) throws InterruptedException, MovimentoInvalidoException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Dimensão do plano cartesiano
        int tamanhoX = 3;
        int tamanhoY = 3;

        // Criando a matriz que representa o plano cartesiano
        int[][] area = new int[tamanhoY + 1][tamanhoX + 1];
        System.out.println("-------BEM-VINDO AO JOGO RANDÔMICO COM UM ROBÔ PADRÃO E UM INTELIGENTE COM OBSTÁCULOS-------");
        System.out.println("Para iniciar, escolha a cor de cada robô");

        
        System.out.println("");
        System.out.println("(VERMELHO)");
        System.out.println("(ROSA)");
        System.out.println("(CINZA)");
        System.out.println("(PRETO)");
        System.out.println("");

        // Instancia o primeiro robô e pede a cor
        System.out.print("Digite a cor do primeiro robô (padrão): ");
        String cor1 = scanner.nextLine();
        Robo robo1 = new Robo(cor1);
        robo1.verificarCor(cor1);
        
        System.out.println(" ");

        // Instancia o segundo robô e pede a cor
        System.out.print("Digite a cor do segundo robô (inteligente): ");
        String cor2 = scanner.nextLine();
        Robo robo2 = new Robo(cor2);
        robo2.verificarCor(cor2);
        System.out.println(" ");

        // Posição do alimento
        System.out.println("Informe a posição do alimento (x, y): ");
        System.out.print("POSIÇÃO X: ");
        int alimentoX = scanner.nextInt();
        System.out.println(" ");
        

        System.out.print("POSIÇÃO Y: ");
        int alimentoY = scanner.nextInt();
        System.out.println(" ");
        
        robo1.tratarPosicaoAlimento(alimentoX, alimentoY);
        
        // Configuração de obstáculos
        ArrayList<int[]> rochas = new ArrayList<>();
        ArrayList<int[]> bombas = new ArrayList<>();
        String tipoObstaculos = "";

        while (!tipoObstaculos.equals("bomba") && !tipoObstaculos.equals("rocha") && !tipoObstaculos.equals("ambos")) {
            System.out.print("Deseja incluir bombas, rochas ou ambos? (bomba/rocha/ambos): ");
            tipoObstaculos = scanner.next().toLowerCase();
            if (!tipoObstaculos.equals("bomba") && !tipoObstaculos.equals("rocha") && !tipoObstaculos.equals("ambos")) {
                System.out.println("Entrada inválida. Digite novamente.");
            }
        }

        // Adiciona bombas ou rochas conforme a escolha
        if (tipoObstaculos.contains("bomba")|| tipoObstaculos.contains("ambos")) {
            System.out.print("Quantas bombas deseja adicionar? ");
            int numBombas = Math.min(scanner.nextInt(), 3);
            adicionarObstaculos(scanner, bombas, numBombas, "bomba", tamanhoX, tamanhoY, alimentoX, alimentoY);
        }
        if (tipoObstaculos.contains("rocha")|| tipoObstaculos.contains("ambos")) {
            System.out.print("Quantas rochas deseja adicionar? ");
            int numRochas = Math.min(scanner.nextInt(), 3);
            adicionarObstaculos(scanner, rochas, numRochas, "rocha", tamanhoX, tamanhoY, alimentoX, alimentoY);
        }

        // Posições iniciais dos robôs
        robo1.setRoboX(0);
        robo1.setRoboY(0);
        robo2.setRoboX(0);
        robo2.setRoboY(0);

        // Início do jogo
        boolean encontrouAlimento = false;
        boolean robo1Explodiu = false;
        boolean robo2Explodiu = false;
        System.out.println("INÍCIO DA PARTIDA");
        exibirArea(area, robo1.getRoboX(), robo1.getRoboY(), robo2.getRoboX(), robo2.getRoboY(), alimentoX, alimentoY, rochas, bombas, tamanhoX, tamanhoY, cor1, cor2);
        System.out.println("-------------------------------------");

        while (!encontrouAlimento) {
            // Exibe a vez do robô padrão (robo1), se não explodiu
            if (!robo1Explodiu) {

                System.out.println("Vez do Robô Padrão"+" ("+robo1.getCor()+")"+ ":");

                // Movimento do robô 1 (Robô Padrão)
                movimentarRobo(robo1, random, tamanhoX, tamanhoY, rochas, bombas, "Robô padrão (" + cor1 + ")");
                if (verificarExplosao(robo1, bombas)) {
                    robo1Explodiu = true;
                    System.out.println("Robô padrão (" + cor1 + ") explodiu em uma bomba!");
                }

                // Exibe a área do jogo
                exibirArea(area, robo1.getRoboX(), robo1.getRoboY(), robo2.getRoboX(), robo2.getRoboY(), alimentoX, alimentoY, rochas, bombas, tamanhoX, tamanhoY, cor1, cor2);
                System.out.println(" ");

                if (robo1.verificarPosicaoAlimento(alimentoX, alimentoY)) {
                    System.out.println("Robô padrão (" + cor1 + ") encontrou o alimento!");
                    encontrouAlimento = true;
                    break;
                }
            }

            // Exibe a vez do robô inteligente (robo2), se não explodiu
            if (!robo2Explodiu) {

                System.out.println("Vez do Robô inteligente"+" ("+robo2.getCor()+")"+":");

                // Movimento do robô 2 (Robô Inteligente)
                movimentarRobo(robo2, random, tamanhoX, tamanhoY, rochas, bombas, "Robô inteligente (" + cor2 + ")");
                if (verificarExplosao(robo2, bombas)) {
                    robo2Explodiu = true;
                    System.out.println("Robô inteligente (" + cor2 + ") explodiu em uma bomba!");
                }

                // Exibe a área do jogo
                exibirArea(area, robo1.getRoboX(), robo1.getRoboY(), robo2.getRoboX(), robo2.getRoboY(), alimentoX, alimentoY, rochas, bombas, tamanhoX, tamanhoY, cor1, cor2);
                System.out.println("-------------------------------------");

                if (robo2.verificarPosicaoAlimento(alimentoX, alimentoY)) {
                    System.out.println("Robô inteligente (" + cor2 + ") encontrou o alimento!");
                    encontrouAlimento = true;
                }
            }

            // Se ambos os robôs explodiram antes de encontrar o alimento, encerra o jogo
            if (robo1Explodiu && robo2Explodiu) {
                System.out.println("Ambos os robôs explodiram antes de encontrar o alimento!");
                break;
            }
        }
        System.out.println("------------------------------------");
        System.out.println("ESTATÍSTICAS - ROBÔ PADRÃO "+ "("+robo1.getCor() + ")" +": ");
        System.out.println("MOVIMENTOS VÁLIDOS = " + robo1.getMovimentosValidos());
        System.out.println("MOVIMENTOS INVÁLIDOS = " + robo1.getMovimentosInvalidos());
        System.out.println("MOVIMENTOS TOTAIS = "+ robo1.getMovimentosTotais());

        System.out.println("------------------------------------");
        System.out.println("ESTATÍSTICAS - ROBÔ INTELIGENTE " + "("+ robo2.getCor() + ")" + ": " );
        System.out.println("MOVIMENTOS VÁLIDOS = " + robo2.getMovimentosValidos());
        System.out.println("MOVIMENTOS INVÁLIDOS = " + robo2.getMovimentosInvalidos());
        System.out.println("MOVIMENTOS TOTAIS = "+ robo2.getMovimentosTotais());
        System.out.println("------------------------------------");


        System.out.println("Jogo finalizado!");
        scanner.close();
    }

    private static void adicionarObstaculos(Scanner scanner, ArrayList<int[]> lista, int quantidade, String tipo, int tamanhoX, int tamanhoY, int alimentoX, int alimentoY) {
        for (int i = 0; i < quantidade; i++) {
            int obstaculoX, obstaculoY;
            do {
                System.out.println("Informe a posição da " + tipo + " " + (i + 1) + " (x, y) entre 0 e 3: ");
                System.out.print("POSIÇÃO X: ");
                obstaculoX = scanner.nextInt();
                System.out.print("POSIÇÃO Y: ");
                obstaculoY = scanner.nextInt();
                

                if(obstaculoX == alimentoX && obstaculoY == alimentoY) {
                	System.out.println("Não é permitido adicionar obstáculos na posição do alimento, tente novamente ");
                }else if(obstaculoX < 0 || obstaculoY < 0 || obstaculoX > 3 || obstaculoY > 3) {
                	System.out.println("Não é permitido adicionar obstáculos fora da área permitida, tente novamente ");
                }else if(obstaculoX==0 && obstaculoY==0) {
                	System.out.println("Não é permitido adicionar obstáculos na posição inicial dos robôs, tente novamente ");
                }else {
                	System.out.println("Posições adicionadas com sucesso!");

                }
            } while ((obstaculoX == alimentoX && obstaculoY == alimentoY) || obstaculoX < 0 || obstaculoY < 0 || obstaculoX > 3 || obstaculoY > 3 || (obstaculoX==0 && obstaculoY ==0));
            
            lista.add(new int[]{obstaculoX, obstaculoY});
        }
    }

    private static void exibirArea(int[][] area, int robo1X, int robo1Y, int robo2X, int robo2Y, int alimentoX, int alimentoY, ArrayList<int[]> rochas, ArrayList<int[]> bombas, int tamanhoX, int tamanhoY, String cor1, String cor2) {
        for (int i = 0; i <= tamanhoY; i++) {
            for (int j = 0; j <= tamanhoX; j++) {
                area[i][j] = 0;
            }
        }
        area[robo1Y][robo1X] = 1;
        area[robo2Y][robo2X] = 2;
        area[alimentoY][alimentoX] = 3;

        for (int[] rocha : rochas) area[rocha[1]][rocha[0]] = 4;
        for (int[] bomba : bombas) area[bomba[1]][bomba[0]] = 5;

        for (int i = tamanhoY; i >= 0; i--) {
            for (int j = 0; j <= tamanhoX; j++) {
                switch (area[i][j]) {
                    case 1 -> System.out.print("Rp" + " ");
                    case 2 -> System.out.print("Ri" + " ");
                    case 3 -> System.out.print(" A ");
                    case 4 -> System.out.print(" @ ");
                    case 5 -> System.out.print(" # ");
                    default -> System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    private static void movimentarRobo(Robo robo, Random random, int tamanhoX, int tamanhoY, ArrayList<int[]> rochas, ArrayList<int[]> bombas, String nomeRobo) throws MovimentoInvalidoException, InterruptedException {
        int movimento = random.nextInt(4) + 1;

        // Armazenar as posições iniciais
        int posicaoInicialX = robo.getRoboX();
        int posicaoInicialY = robo.getRoboY();

        // Tenta mover o robô
        robo.mover(movimento);

        // Verificar se a nova posição está dentro dos limites do plano
        if (robo.getRoboX() < 0 || robo.getRoboY() < 0 || robo.getRoboX() > tamanhoX || robo.getRoboY() > tamanhoY) {
            // Se o movimento for inválido (fora dos limites), o robô retorna para a posição inicial
            System.out.println(nomeRobo + " tentou sair do plano! Retornando à posição anterior.");
            robo.setRoboX(posicaoInicialX);
            robo.setRoboY(posicaoInicialY);
        }

        // Verificar colisões com rochas
        for (int[] rocha : rochas) {
            if (robo.getRoboX() == rocha[0] && robo.getRoboY() == rocha[1]) {
                // Se o robô colidir com uma rocha, ele retorna à posição inicial
                System.out.println(nomeRobo + " colidiu com uma rocha! Retornando à posição anterior.");
                robo.setRoboX(posicaoInicialX);
                robo.setRoboY(posicaoInicialY);
                return;
            }
        }

        // Atraso de 1 segundo após o movimento
        Thread.sleep(1000);
    }


    private static boolean verificarExplosao(Robo robo, ArrayList<int[]> bombas) {
        for (int[] bomba : bombas) {
            if (robo.getRoboX() == bomba[0] && robo.getRoboY() == bomba[1]) {
                robo.setParado(true); // Marca o robô como parado
                System.out.println("Robô " + (robo.getCor()) + " bateu na bomba e está parado.");
                bombas.remove(bomba); // Remove a bomba do jogo
                return true; // Retorna verdadeiro quando uma explosão ocorre
            }
        }
        return false; // Retorna falso se o robô não bateu em nenhuma bomba
    }
}