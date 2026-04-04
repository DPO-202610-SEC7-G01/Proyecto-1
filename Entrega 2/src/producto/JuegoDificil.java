package producto;

public class JuegoDificil extends Juego{
	
	
	
	public JuegoDificil(int id, int precio, String nombre, int anioPublicacion, String empresMatriz, int numJugadores,
			String restriccionEdad, String categoria) {
		super(id, precio, nombre, anioPublicacion, empresMatriz, numJugadores, restriccionEdad, categoria);
	}
		
	//Métodos

	@Override
	public boolean requiereInstructor() {
		return true;
	}
	
}
