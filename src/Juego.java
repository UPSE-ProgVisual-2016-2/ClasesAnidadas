import java.util.ArrayList;
import java.util.List;

public class Juego {

	static class PersonajeJuego{
		private String nombrePersonaje;
		private int defensa;
		private int poder;
		
		PersonajeJuego(String nombrePersonaje, int defensa, int poder)
		{
			this.nombrePersonaje = nombrePersonaje;
			this.defensa = defensa;
			this.poder = poder;
		}
	}
	

	
	private List<Juego.PersonajeJuego> personajesDisponibles = new ArrayList<Juego.PersonajeJuego>();
	

}
