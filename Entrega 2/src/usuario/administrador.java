package usuario;

import cafe.Cafe;

public class administrador extends Usuario {
	private Cafe cafe;
	public administrador(int id, String login, String password, String nombre, Cafe cafe) {
		super(id, login, password, nombre);
		this.cafe = cafe;
	}
	

}
