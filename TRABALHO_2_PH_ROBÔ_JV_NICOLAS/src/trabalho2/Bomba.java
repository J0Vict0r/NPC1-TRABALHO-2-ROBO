package trabalho2;



class Bomba extends Obstaculo {
    public Bomba(int id) {
        super(id);
    }

    @Override
    public void bater(Robo robo) {
        System.out.println("Robô explodiu ao bater em uma bomba!");
        robo.explodir(); // Marca o robô como fora de jogo
    }
}