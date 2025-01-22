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
        /*Enquanto o movimento for válido, ele tenta executar o método mover da classe Robo,
    	chamando junto com seu construtor, nisso, sendo uma direção válida, o controlador passa a ser true
    	então o catch não é chamado e ele encerra o while*/
        while (!movimentoValido) {
            try {
            	//Essa chamada super.mover(direcao) chama o método mover da classe mãe aprovitando a 
            	 //lógica para não ficar repetitiva as condições de movimento e, como está dentro de um método sobreescrito
            	 // é possível colocar as condições particulares para a movimentação do Robô Inteligente/
                super.mover(direcao); // Tenta mover
                movimentoValido = true; // Movimento válido, sai do loop
            } catch (MovimentoInvalidoException erro) {
                // Escolhe uma nova direção se o movimento for inválido
                direcao = gerarNovaDirecao(direcao);
            }
        }
        return true;
    }
    
    /*Esse método é private pq nenhuma outra classe vai usá-lo diretamente, pois ele é retornado 
    dentro da classe sobreescrita mover, que é pública
    Caso uma exceção seja encontrada do método sobreescrito mover(String), ele vai receber o valor da 
     * direção posta no parâmetro acima no lugar da direcaoAnterior. Em seguida, ele receberá o valor aleaórtio 
     * para a novaDirecao até que chegue ao While e a novaDirecao seja diferente da direcaoAnterior,
     *  resultando em um false dentro do while e parando o looping. Caso a novaDirecao e a direcaoAnterior sejam iguais
     *  o while continua e retorna o valor da novaDirecao ao do e continua o looping*/
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