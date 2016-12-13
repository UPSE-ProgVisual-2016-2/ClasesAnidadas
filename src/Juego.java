import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Juego {

	public interface Enemigo{
		public void atacarJugador(Jugador j);
		public int getPoderEnemigo();
	}
	
	public interface Asistente{
		public void reforzarPoder(Jugador j);
		public void atacarContrincante(Jugador j);
	}
	
	public static final String consola = "SuperNintendo";
	
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

		@Override
		public String toString() {
			return "PersonajeJuego [nombrePersonaje=" + nombrePersonaje + ", defensa=" + defensa + ", poder=" + poder
					+ "]";
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

		@Override
		public String toString() {
			return "Jugador [nombreJugador=" + nombreJugador + ", puntajeJugador=" + puntajeJugador
					+ ", personajeElegido=" + personajeElegido + "]";
		}
	}
	
	private List<Juego.PersonajeJuego> personajesDisponibles = new ArrayList<Juego.PersonajeJuego>();
	private Jugador jugadorA;
	private Jugador jugadorB;
	private Jugador ganador;
	private LocalDateTime fechaPartida;
	
	Juego()
	{
		this.fechaPartida = LocalDateTime.now();
	}
	
	public Jugador Jugar()
	{
		System.out.println("---------Que comienzen los juegos del Hambre----------\n");
		Jugador ganador = null;
		
		Random r = new Random();
		
		//Ponerle etiqueta al ciclo donde los jugadores viven para poder romperlo.
		jugadoresVivos:
		while(this.jugadorA.getPuntajeJugador()>0 && this.jugadorB.getPuntajeJugador()>0)
		{
			int turnoActual = r.nextInt(3)+1;
			
			switch (turnoActual) {
			case 1:
				System.out.println("***********Le toca al Jugador A***********");
				System.out.println("Jugador A: "+ jugadorA.nombreJugador + " Ataca");
				int poderA = r.nextInt(jugadorA.getPersonajeElegido().poder);
				System.out.println("El poder del ataque lanzado por A " + jugadorA.getNombreJugador() + " es: " + poderA);
				int defensaB = r.nextInt(jugadorB.getPersonajeElegido().defensa);
				System.out.println("El nivel de defensa del jugador B " + jugadorB.getNombreJugador() + " es: " + defensaB);
				int danoB = ((poderA - defensaB>0)?poderA-defensaB:0);				
				jugadorB.setPuntajeJugador(jugadorB.getPuntajeJugador()-danoB);
				System.out.println("El jugadorB " + jugadorB.getNombreJugador() + " recibio un dano de: " + danoB + ". Los puntos de vida del jugadorB son: " + jugadorB.getPuntajeJugador());
				
				if(jugadorB.getPuntajeJugador()<=0)
				{
					System.out.println("El jugador B " + jugadorB.getNombreJugador() + " ha sido derrotado");
					ganador = jugadorA;
					break jugadoresVivos;
				}
				System.out.println("\n");
				break;
			
			case 2:
				System.out.println("***********Le toca al Jugador B***********");
				System.out.println("Jugador B: "+ jugadorB.nombreJugador + " Ataca");
				int poderB = r.nextInt(jugadorB.getPersonajeElegido().poder);
				System.out.println("El poder del ataque lanzado por B " + jugadorB.getNombreJugador() + " es: "  + poderB);
				int defensaA = r.nextInt(jugadorA.getPersonajeElegido().defensa);
				System.out.println("El nivel de defensa del jugador A " + jugadorA.getNombreJugador() + " es: " + defensaA);
				int danoA = ((poderB - defensaA>0)?poderB-defensaA:0);
				jugadorA.setPuntajeJugador(jugadorA.getPuntajeJugador()-danoA);
				System.out.println("El jugadorA " + jugadorA.getNombreJugador() + " recibio un dano de: " + danoA + ". Los puntos de vida del jugadorB son: " + jugadorA.getPuntajeJugador());
				
				
				if(jugadorA.getPuntajeJugador()<=0)
				{
					System.out.println("El jugador A " + jugadorA.getNombreJugador() + " ha sido derrotado");
					ganador = jugadorB;
					break;
				}
				System.out.println("\n");
				break;
				
			case 3:
				Enemigo e1 = generadorEnemigo(); 
				
				System.out.println("***********ENEMIGO SALVAJE APARECE***********");
				System.out.println("Enemigo dice: quieres probar mi poder!");
				System.out.println("El enemigo ataca con un poder de " + e1.getPoderEnemigo() + " al jugador " + jugadorA.getNombreJugador());
				e1.atacarJugador(jugadorA);
				System.out.println("Los puntos del jugador " + jugadorA.getNombreJugador() + " ahora son " + jugadorA.getPuntajeJugador());
				if(jugadorA.getPuntajeJugador()<=0)
				{
					ganador = jugadorB;
					break jugadoresVivos;
				}
				break;
				
			case 4:
				Enemigo e2 = generadorEnemigo(); 
				
				System.out.println("***********ENEMIGO SALVAJE APARECE***********");
				System.out.println("Enemigo dice: quieres probar mi poder!");
				System.out.println("El enemigo ataca con un poder de " + e2.getPoderEnemigo() + " al jugador " + jugadorB.getNombreJugador());
				e2.atacarJugador(jugadorB);
				System.out.println("Los puntos del jugador " + jugadorB.getNombreJugador() + " ahora son " + jugadorB.getPuntajeJugador());
				if(jugadorB.getPuntajeJugador()<=0)
				{
					ganador = jugadorA;
					break jugadoresVivos;
				}
				break;

			default:
				break;
			}
			
			System.out.println("############Fin de Turno################\n");
			
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

	@Override
	public String toString() {
		return "Juego [ganador=" + ganador + ", fechaPartida=" + fechaPartida + "]";
	}
	
	public Asistente generadorAsistente()
	{
		class AsistenteSexy implements Asistente{
			int poderRecuperacion;
			int poderAtaque;
			
			public AsistenteSexy(int poderRecuperacion, int poderAtaque)
			{
				this.poderRecuperacion = poderRecuperacion;
				this.poderAtaque = poderAtaque;
			}

			@Override
			public void reforzarPoder(Jugador j) {
				System.out.println("Con su sensualidad tu asistente te ha recuperado.");
				j.setPuntajeJugador(j.getPuntajeJugador()+poderRecuperacion);;
			}

			@Override
			public void atacarContrincante(Jugador j) {
				System.out.println("Asistente sensual sedujo al enemigo y lo dejo sin poder");
				j.setPuntajeJugador(j.getPuntajeJugador()-poderAtaque);
			}
			
		}
		
		Asistente asistenteTemporal = new AsistenteSexy(10, 6);
		return asistenteTemporal;
	}
	
	public Enemigo generadorEnemigo()
	{
		class EnemigoSimplon implements Enemigo{
			int poder;
			
			public EnemigoSimplon(int poder) {
				this.poder = poder;
			}
			
			@Override
			public void atacarJugador(Jugador j) {
				System.out.println("Ataque Simplon: " + poder);
				j.setPuntajeJugador(j.getPuntajeJugador()-poder);
			}

			@Override
			public int getPoderEnemigo() {
				return poder;
			}
			
		}
		
		Random r = new Random();
		int suerte = r.nextInt(4);
		
		Enemigo e = new EnemigoSimplon(0);
		switch (suerte) {
		case 0:	e = new EnemigoSimplon(2);
			break;
		
		case 1: e = new Enemigo(){
			int poder = 100;
			@Override
			public void atacarJugador(Jugador j) {
				System.out.println("Ataque Maldito: " + poder);
				j.setPuntajeJugador(j.getPuntajeJugador()-poder);
			}

			@Override
			public int getPoderEnemigo() {
				// TODO Auto-generated method stub
				return poder;
			}
			
		};
		break;
		
		case 2: e = new Enemigo(){

			public int poder = 30;
			
			@Override
			public void atacarJugador(Jugador j) {
				System.out.println("Enemigo Intermedio");
				j.setPuntajeJugador(j.getPuntajeJugador()-poder);
			}

			@Override
			public int getPoderEnemigo() {
				// TODO Auto-generated method stub
				return poder;
			}			
		};
		
		case 3: e = new Enemigo(){
			public int poder = -5;
			@Override
			public void atacarJugador(Jugador j) {
				System.out.println("Enemigo Pendejo, te suma puntos");
				j.setPuntajeJugador(j.getPuntajeJugador()-poder);
			}

			@Override
			public int getPoderEnemigo() {
				// TODO Auto-generated method stub
				return poder;
			}};
		

		default:
			e = new EnemigoSimplon(0);
			break;
		}
		
		return e;
	}

}
