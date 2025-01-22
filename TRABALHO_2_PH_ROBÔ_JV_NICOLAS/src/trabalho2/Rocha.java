package trabalho2;

class Rocha extends Obstaculo {
    public Rocha(int id) {
        super(id);
    }

    @Override
    public void bater(Robo robo) {
        System.out.println("Robô bateu em uma rocha e voltou à posição anterior.");
        robo.voltarPosicaoAnterior();
    }    
}