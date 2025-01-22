//OFICIAL
package trabalho2;

import java.util.Random;

public class RoboInteligente extends Robo{
	
	public RoboInteligente(String cor) {
		super(cor);
	}
    //Sobreescrita do método mover(String)
    @Override
    public boolean mover(String direcao) throws MovimentoInvalidoException {
        
    	//Controlador do looping do while
    	boolean movimentoValido = false;
        
        while (!movimentoValido) {
            try {
            	
                super.mover(direcao); // Tenta mover
                movimentoValido = true; // Movimento válido, sai do loop
            } catch (MovimentoInvalidoException erro) {
                // Escolhe uma nova direção se o movimento for inválido
                direcao = gerarNovaDirecao(direcao);
            }
        }
        return true;
    }
    
    
    private String gerarNovaDirecao(String direcaoAnterior) {
        String[] direcoes = { "up", "down", "left", "right" };
        Random random = new Random();
        String novaDirecao;

        do {
            novaDirecao = direcoes[random.nextInt(direcoes.length)];
        } while (novaDirecao.equalsIgnoreCase(direcaoAnterior));

        return novaDirecao;
    }

    @Override
    public boolean mover(int num) throws MovimentoInvalidoException {
        //Controlador do looping do while
    	boolean movimentoValido = false;
        
        while (!movimentoValido) {
            try {
            	super.mover(num);
                movimentoValido = true; // Movimento válido, sai do loop
            } catch (MovimentoInvalidoException erro) {
                // Escolhe uma nova direção se o movimento for inválido
                num = gerarNovoComando(num);
            }
        }
        return true;
    }
    
    private int gerarNovoComando(int comandoAnterior) {
        int[] comandos = { 1, 2, 3, 4 };
        Random random = new Random();
        int novoComando;

        do {
            novoComando = comandos[random.nextInt(comandos.length)];
        } while (novoComando==comandoAnterior);

        return novoComando;
    }
}
