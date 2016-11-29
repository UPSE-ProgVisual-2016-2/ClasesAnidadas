import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
	
	class Jugador{
		private String nombreJugador;
		private int puntajeJugador = 100;
		private Juego.PersonajeJuego personajeElegido;
		
		public Jugador(String nombreJugador, Juego.PersonajeJuego personajeElegido)
		{
			this.nombreJugador = nombreJugador;
			this.personajeElegido = personajeElegido;
		}
		
		public String getNombreJugador() {
			return nombreJugador;
		}

		public void setNombreJugador(String nombreJugador) {
			this.nombreJugador = nombreJugador;
		}

		public int getPuntajeJugador() {
			return puntajeJugador;
		}

		public void setPuntajeJugador(int puntajeJugador) {
			this.puntajeJugador = puntajeJugador;
		}

		public Juego.PersonajeJuego getPersonajeElegido() {
			return personajeElegido;
		}

		public void setPersonajeElegido(Juego.PersonajeJuego personajeElegido) {
			this.personajeElegido = personajeElegido;
		}
	
	}
	
	private List<Juego.PersonajeJuego> personajesDisponibles = new ArrayList<Juego.PersonajeJuego>();
	private Jugador jugadorA;
	private Jugador jugadorB;
	private Jugador ganador;
	private LocalDateTime fechaPartida;
	
	Juego(Jugador jA, Jugador jB)
	{
		this.jugadorA = jA;
		this.jugadorB = jB;
		this.fechaPartida = LocalDateTime.now();
	}
	
	
	
	private Jugador Jugar()
	{
		Jugador ganador = null;
		
		Random r = new Random();
		while(this.jugadorA.getPuntajeJugador()>0 && this.jugadorB.getPuntajeJugador()>0)
		{
			int turnoActual = r.nextInt(2)+1;
			
			switch (turnoActual) {
			case 1:
				System.out.println("Jugador A: "+ jugadorA.nombreJugador + "Ataca");
				int poderA = r.nextInt(jugadorA.getPersonajeElegido().poder);
				System.out.println("El poder del ataque lanzado por A es: " + poderA);
				int defensaB = r.nextInt(jugadorB.getPersonajeElegido().defensa);
				System.out.println("El nivel de defensa del jugador B es: " + defensaB);
				int danoB = ((poderA - defensaB>0)?poderA-defensaB:0);				
				jugadorB.setPuntajeJugador(jugadorB.getPuntajeJugador()-danoB);
				System.out.println("El jugadorB recibio un dano de: " + danoB + ". Los puntos de vida del jugadorB son: " + jugadorB.getPuntajeJugador());
				
				if(jugadorB.getPuntajeJugador()<=0)
				{
					System.out.println("El jugador B " + jugadorB.getNombreJugador() + " ha sido derrotado");
					ganador = jugadorA;
				}
				break;
			
			case 2:
				System.out.println("Jugador B: "+ jugadorB.nombreJugador + "Ataca");
				int poderB = r.nextInt(jugadorB.getPersonajeElegido().poder);
				System.out.println("El poder del ataque lanzado por B es: " + poderB);
				int defensaA = r.nextInt(jugadorA.getPersonajeElegido().defensa);
				System.out.println("El nivel de defensa del jugador A es: " + defensaA);
				int danoA = ((poderB - defensaA>0)?poderB-defensaA:0);
				jugadorB.setPuntajeJugador(jugadorA.getPuntajeJugador()-danoA);
				System.out.println("El jugadorA recibio un dano de: " + danoA + ". Los puntos de vida del jugadorB son: " + jugadorA.getPuntajeJugador());
				
				
				if(jugadorA.getPuntajeJugador()<=0)
				{
					System.out.println("El jugador A " + jugadorA.getNombreJugador() + " ha sido derrotado");
					ganador = jugadorB;
				}
				break;

			default:
				break;
			}
			
		}
		return ganador;
	}

	public void agregarPersonajeAlJuego(PersonajeJuego pj)
	{
		this.personajesDisponibles.add(pj);
	}

	public List<Juego.PersonajeJuego> getPersonajesDisponibles() {
		return personajesDisponibles;
	}



	public void setPersonajesDisponibles(List<Juego.PersonajeJuego> personajesDisponibles) {
		this.personajesDisponibles = personajesDisponibles;
	}



	public Jugador getJugadorA() {
		return jugadorA;
	}



	public void setJugadorA(Jugador jugadorA) {
		this.jugadorA = jugadorA;
	}



	public Jugador getJugadorB() {
		return jugadorB;
	}



	public void setJugadorB(Jugador jugadorB) {
		this.jugadorB = jugadorB;
	}



	public Jugador getGanador() {
		return ganador;
	}



	public LocalDateTime getFechaPartida() {
		return fechaPartida;
	}

}
