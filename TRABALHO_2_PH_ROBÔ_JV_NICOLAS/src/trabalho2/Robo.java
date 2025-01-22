package trabalho2;

import java.util.Scanner;

public class Robo {
    protected int roboX;
    protected int roboY;
    protected String cor;
    protected int movimentosValidos;
    protected int movimentosInvalidos;
    protected int movimentosTotais;
    private int antigoX, antigoY;

    private static String[] coresDisponiveis = new String[2]; // O TAMANHO DO VETOR CORRESPONDE AO NÚMERO DE ROBÔS NA PARTIDA

    // Construtor
    public Robo(String cor) {
        this.cor = cor;
        this.roboX = 0;
        this.roboY = 0;
        this.movimentosValidos = 0;
        this.movimentosInvalidos = 0;
        this.movimentosTotais = 0;
        
        this.antigoX = roboX;
        this.antigoY = roboY;
    }

    // Método para mover o robô (usando direção como String)
    public boolean mover(String direcao) throws MovimentoInvalidoException {
        antigoX = roboX;
        antigoY = roboY;
        
        int novoX = roboX;
        int novoY = roboY;

        // Calcula a nova posição baseada na direção
        switch (direcao.toLowerCase()) {
            case "up":
                novoY++;
                break;
            case "down":
                novoY--;
                break;
            case "right":
                novoX++;
                break;
            case "left":
                novoX--;
                break;
            default:
                // Se direção inválida, incrementa movimentos inválidos
                movimentosInvalidos++;
                System.out.println("Movimento inválido! Digite a direção novamente");
                throw new MovimentoInvalidoException("Direção inválida: " + direcao);
        }

        // Verifica se a nova posição está dentro dos limites
        if (novoX < 0 || novoX > 3 || novoY < 0 || novoY > 3) {
            movimentosInvalidos++;  // Incrementa movimentos inválidos
            System.out.println("O movimento está fora dos limites! Tentando outra direção...");
            return false; // Não move, pois ultrapassou os limites
        }

        // Se dentro dos limites, atualiza a posição
        roboX = novoX;
        roboY = novoY;

        // Incrementa o contador de movimentos válidos
        movimentosValidos++;
        System.out.println("Robô na posição: (" + roboX + ", " + roboY + ")");
        return true;
    }

    // Sobrecarga do método mover (usando números inteiros)
    public boolean mover(int num) throws MovimentoInvalidoException {
        String direcao;

        // Mapeia o número para a direção correspondente
        switch (num) {
            case 1:
                direcao = "up";
                break;
            case 2:
                direcao = "down";
                break;
            case 3:
                direcao = "right";
                break;
            case 4:
                direcao = "left";
                break;
            default:
                // Se número inválido, incrementa movimentos inválidos
                movimentosInvalidos++;
                System.out.println("Movimento inválido! Digite a direção corretamente (1, 2, 3 ou 4)");
                throw new MovimentoInvalidoException("Direção inválida: " + num);
        }

        // Chama o método mover(String direcao) para realizar o movimento
        return mover(direcao);
    }

    // Método para verificar a cor do robô
    public String verificarCor(String cor) {
        boolean corValida = cor.equalsIgnoreCase("vermelho") || cor.equalsIgnoreCase("preto") ||
                             cor.equalsIgnoreCase("cinza") || cor.equalsIgnoreCase("rosa");
        
        boolean corRepetida = false;

        // Verifica se a cor foi escolhida anteriormente
        for (String corEscolhida : coresDisponiveis) {
            if (cor != null && corEscolhida != null && corEscolhida.equalsIgnoreCase(cor)) {
                corRepetida = true;
                break;
            }
        }

        // Verifica se a cor é válida e se não é repetida
        while (!corValida || corRepetida) {
            if (!corValida) {
                System.out.println("A cor " + cor + " não é válida. Escolha entre vermelho, preto, cinza ou rosa.");
            } else {
                System.out.println("A cor " + cor + " já foi escolhida por outro robô. Escolha outra cor.");
            }
            cor = new java.util.Scanner(System.in).nextLine();  // Solicita nova cor
            corValida = cor.equalsIgnoreCase("vermelho") || cor.equalsIgnoreCase("preto") ||
                        cor.equalsIgnoreCase("cinza") || cor.equalsIgnoreCase("rosa");

            corRepetida = false;
            for (String corEscolhida : coresDisponiveis) {
                if (cor != null && corEscolhida != null && corEscolhida.equalsIgnoreCase(cor)) {
                    corRepetida = true;
                    break;
                }
            }
        }

        // Armazena a cor escolhida
        for (int i = 0; i < coresDisponiveis.length; i++) {
            if (coresDisponiveis[i] == null) {
                coresDisponiveis[i] = cor;
                System.out.println("A cor " + cor + " foi adicionada com sucesso!");
                break;
            }
        }
        return cor;
    }

    
    // Métodos para verificar posição
    public boolean tratarPosicaoAlimento(int alimentoX, int alimentoY) {
    	while (alimentoX < 0 || alimentoX > 3 || alimentoY < 0 || alimentoY > 3) {
            System.out.println("Posição inválida! O alimento não pode estar fora da área de (0,0) a (3,3).");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Informe novamente a posição do alimento: ");
            System.out.print("Posição X: ");
            alimentoX = scanner.nextInt();
            System.out.print("Posição Y: ");
            alimentoY = scanner.nextInt();
        }
        System.out.println("Posição do alimento adicionada com sucesso!");       
        return true;
    }
    
    public boolean verificarPosicaoAlimento(int alimentoX, int alimentoY) {
        return roboX == alimentoX && roboY == alimentoY;
    }

    // Método para armazenar posição do robô
    public String armazenarPosicaoRobo() {
        return "(" + this.roboX + ", " + this.roboY + ")";
    }
    
    public boolean verificarPosicaoRobo(int x, int y, String cor) {
        boolean verificador;
        int anteriorX, anteriorY;
        anteriorX = x;
        anteriorY = y;
        if (x < 0 || x > 3 || y < 0 || y > 3) {
            System.out.println("O robô " + cor + " está saindo do plano");
            verificador = true;
        } else {
            System.out.println("Robô voltando à posição do plano ");
            x = anteriorX;
            y = anteriorY;
            verificador = false;
        }
        return verificador;
    }

    public boolean verificarPosicaoRoboRocha(int rochaX, int rochaY) {
        if (roboX == rochaX && roboY == rochaY) {
            System.out.println("O robô " + cor + " bateu na rocha");
            return true;
        } else {
            System.out.println("\n");
            return false;
        }
    }

    // Método para explodir o Robô
    public void voltarPosicaoAnterior() {
        if (verificarPosicaoRoboRocha(roboX, roboY)) {
            // Retorna à posição anterior
            roboX = antigoX;
            roboY = antigoY;
            System.out.println("O robô " + cor + " retornou para a posição " + "(" + roboX + ", " + roboY + ")");
        }
    }
    
    public boolean verificarPosicaoRoboBomba(int bombaX, int bombaY) {
        if (roboX == bombaX && roboY == bombaY) {
            System.out.println("O robô " + cor + " bateu na bomba");
            return true;
        } else {
            return false;
        }
    }

    public boolean explodir() {
        if (verificarPosicaoRoboBomba(roboX, roboY)) {
            System.out.println("O robô explodiu");
            return true; // Marca que o robô explodiu
        }
        return false;
    }
    private boolean explodiu = false;

    public boolean getExplodiu() {
        return explodiu;
    }

    public void setExplodiu(boolean explodiu) {
        this.explodiu = explodiu;
    }

    // Getters para contagem de movimentos
    public int getMovimentosValidos() {
        return movimentosValidos;
    }

    public int getMovimentosInvalidos() {
        return movimentosInvalidos;
    }

    public int getMovimentosTotais() {
        return movimentosInvalidos + movimentosValidos;
    }

    public int getRoboX() {
        return this.roboX;
    }
    public void setRoboX(int robox) {
    	this.roboX=robox;
    }
    public int getRoboY() {
        return this.roboY;
    }
    public void setRoboY(int roboY) {
    	this.roboY=roboY;
    }
    public String getCor() {
    	return this.cor;
    }
    public void setCor(String cor) {
    	this.cor=cor;
    }
    private boolean parado;
    public boolean isParado() {
        return parado;
    }

    public void setParado(boolean parado) {
        this.parado = parado;
    }
}