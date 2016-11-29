
public class SimuladorClasesAnidadas {

	public SimuladorClasesAnidadas() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Juego.PersonajeJuego ryu = new Juego.PersonajeJuego("ryu", 75, 93);
		Juego.PersonajeJuego ken = new Juego.PersonajeJuego("Ken", 85, 78);
		Juego.PersonajeJuego Iori = new Juego.PersonajeJuego("Iori", 95, 56);
		
		Juego streetFighter = new Juego();
		Juego.Jugador chicoLoco = streetFighter.new Jugador("ChicoLoco", Iori);
		Juego.Jugador blackDread = streetFighter.new Jugador("BlackDread", ken);
		
		streetFighter.setJugadorA(chicoLoco);
		streetFighter.setJugadorB(blackDread);
		
	 	Juego.Jugador ganador = streetFighter.Jugar();
	 	
	 	System.out.println("\n");
	 	System.out.println("\n");
	 	System.out.println("*******GANADOR***********");
	 	System.out.println("El ganador fue: " + ganador.getNombreJugador());
	 	
	 	System.out.println("Status");
	 	System.out.println(chicoLoco);
	 	System.out.println(blackDread);
	 	/*
		System.out.println(ryu);
		System.out.println(ken);*/
	}
	

}
