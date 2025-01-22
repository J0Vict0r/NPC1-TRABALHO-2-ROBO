package trabalho2;
abstract class Obstaculo {
    protected int id;

    public Obstaculo(int id) {
        this.id = id;
    }
    public abstract void bater(Robo robo);
    
}