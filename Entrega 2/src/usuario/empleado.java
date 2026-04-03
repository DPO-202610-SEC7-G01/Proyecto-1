package usuario;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
public class empleado extends Usuario {
	private List <Calendar> turno;
	public empleado(int id, String login, String password, String nombre) {
		super(id, login, password, nombre);
		ArrayList<Calendar> turno = new ArrayList<>();
		this.turno = turno;
	}

}
