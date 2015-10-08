package tp.pr5;

import java.lang.String;
import java.util.Scanner;

import tp.pr5.control.*;
import tp.pr5.logica.*;
import tp.pr5.vistas.VistaConsola;
import tp.pr5.vistas.VistaGUI;

import org.apache.commons.cli.*;

/**
 * Clase Main
 * 
 * @author Abel Coronado y Maria Castañeda
 *
 */
public class Main {

	/**
	 * Partida actual
	 */
	private static Partida p;

	/**
	 * Factoria actual
	 */
	private static FactoriaTipoJuego f;

	/**
	 * Opciones de la librería Commons
	 */
	private static Options opcion = new Options();
	
	/**
	 * Tipo de juego
	 */
	private static TipoJuego tipo;
	
	/**
	 * Función Main
	 * 
	 * @param args
	 * @throws MovimientoInvalido
	 */
	public static void main(java.lang.String[] args) {

		analisisArg();
		CommandLineParser parseo = new BasicParser();

		try {
			CommandLine cmd;
			cmd = parseo.parse(opcion, args);

			if (cmd.getOptions().length == 0)
				partidaPorDefectoc4();
			
			else if (cmd.hasOption("h")) {
				String comando = "tp.pr5.Main [-g <game>] [-h] [-u <tipo>] [-x <columnNumber>] [-y <rowNumber>] ";
				new HelpFormatter().printHelp(comando, opcion);

			} else {
				if (cmd.getOptionValue("g") == null)
					throw new ParseException("Juego vacío.");

				else if (cmd.getOptionValue("g").equalsIgnoreCase("gr")) {
					GR(cmd);

				} else if (cmd.getOptionValue("g").equalsIgnoreCase("c4")) {
					C4(cmd);

				} else if (cmd.getOptionValue("g").equalsIgnoreCase("rv")) {
					RV(cmd);

				} else if (cmd.getOptionValue("g").equalsIgnoreCase("co"))
					CO(cmd);

				else
					throw new ParseException("Juego '"
							+ cmd.getOptionValue("g") + "' incorrecto.");

				if (cmd.hasOption("u"))
					consoleWindow(cmd);

				else
					inicializacionPartidaConsola();
			}

		} catch (ParseException e) {

			System.err.println("Uso incorrecto: " + e.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			System.exit(1);
		}
	}

	/**
	 * Inicializa Partida sin el argumento [-u]
	 */
	public static void inicializacionPartidaConsola() {

		Scanner in = new Scanner(System.in);

		ControladorConsola c = new ControladorConsola(f, p, in);

		new VistaConsola(c);

		c.run();
	}

	/**
	 * Crea Partida por defecto [C4 / Console]
	 */
	public static void partidaPorDefectoc4() {

		f = new FactoriaConecta4();
		p = new Partida(f.creaReglas());
		Scanner in = new Scanner(System.in);

		ControladorConsola c = new ControladorConsola(f, p, in);
		new VistaConsola(c);

		c.run();
	}

	/**
	 * Elige console o window
	 * 
	 * @param cmd
	 * @throws ParseException
	 */
	public static void consoleWindow(CommandLine cmd) throws ParseException {

		if (cmd.getOptionValue("u") == null)
			throw new ParseException("Vista vacía.");

		else if (cmd.getOptionValue("u").equalsIgnoreCase("window")) {

			ControladorGui c = new ControladorGui(p, f, tipo);
			new VistaGUI(c, tipo);

		} else if (cmd.getOptionValue("u").equalsIgnoreCase("console")) {

			Scanner in = new Scanner(System.in);
			ControladorConsola c = new ControladorConsola(f, p, in);
			new VistaConsola(c);
			c.run();

		} else

			throw new ParseException("Comando '" + cmd.getOptionValue("u")
					+ "' incorrecto.");
	}

	/**
	 * Análisis de los argumentos de entrada
	 */
	public static void analisisArg() {
		// Ayuda [-h, --help]
		Option help = new Option("h", "help", false, "Muestra esta ayuda.");

		// Juego [-g <game>]
		OptionBuilder.withArgName("game");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(1);
		OptionBuilder.withLongOpt("game");
		OptionBuilder
				.withDescription("Tipo de juego (c4, co, gr, rv). Por defecto, c4.");
		Option game = OptionBuilder.create("g");

		// Tamaño Columna [-x <columNumber>]
		OptionBuilder.withArgName("columnNumber");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(1);
		OptionBuilder.withLongOpt("tamX");
		OptionBuilder
				.withDescription("Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
		Option tamX = OptionBuilder.create("x");

		// Tamaño Fila [-y <rowNumber>]
		OptionBuilder.withArgName("rowNumber");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(1);
		OptionBuilder.withLongOpt("tamY");
		OptionBuilder
				.withDescription("Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
		Option tamY = OptionBuilder.create("y");

		// Tipo de interfaz [-u <tipo>]
		OptionBuilder.withArgName("tipo");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(1);
		OptionBuilder.withLongOpt("ui");
		OptionBuilder
				.withDescription("Tipo de interfaz (console, window). Por defecto, console.");
		Option ui = OptionBuilder.create("u");

		// Añadir Comandos al array
		opcion.addOption(ui);
		opcion.addOption(game);
		opcion.addOption(help);
		opcion.addOption(tamX);
		opcion.addOption(tamY);
	}

	/**
	 * Comando GR
	 * 
	 * @param cmd
	 * @param in
	 * @throws ParseException
	 * @throws MovimientoInvalido
	 */
	public static void GR(CommandLine cmd) throws ParseException {

		String valorX, valorY;
		int tamX, tamY;
		tipo = TipoJuego.GR;
		if (cmd.hasOption("x") && cmd.hasOption("y")) {

			valorX = cmd.getOptionValue("x");
			valorY = cmd.getOptionValue("y");

			if (valorX == null) {
				throw new ParseException(
						"Uso incorrecto: Missing argument for option: x");

			} else if (valorY == null) {
				throw new ParseException(
						"Uso incorrecto: Missing argument for option: y");

			} else {

				isNumeric(valorX);
				isNumeric(valorY);

				tamX = Integer.parseInt(valorX);
				tamY = Integer.parseInt(valorY);
			}
			
		} else { // Tamaño por defecto

			tamX = 10;
			tamY = 10;
		}

		f = new FactoriaGravity(tamX, tamY);
		p = new Partida(f.creaReglas());
	}

	/**
	 * Comando C4
	 * 
	 * @param cmd
	 * @param in
	 * @throws ParseException
	 */
	public static void C4(CommandLine cmd) throws ParseException {

		tipo = TipoJuego.C4;
		
		if (cmd.getOptionValue("g").equalsIgnoreCase("c4")
				&& cmd.getArgs().length == 0) {

			f = new FactoriaConecta4();
			p = new Partida(f.creaReglas());

		} else {

			String v[] = cmd.getArgs();
			String cadena = "";

			for (int i = 0; i < cmd.getArgs().length; i++)

				cadena += " " + v[i];

			throw new ParseException("Argumentos no entendidos:" + cadena);
		}
	}

	/**
	 * Comando CO
	 * 
	 * @param cmd
	 * @param in
	 * @throws ParseException
	 */
	public static void CO(CommandLine cmd) throws ParseException {

		tipo = TipoJuego.CO;
		
		if (cmd.getOptionValue("g").equalsIgnoreCase("co")
				&& cmd.getArgs().length == 0) {

			f = new FactoriaComplica();
			p = new Partida(f.creaReglas());

		} else {

			String v[] = cmd.getArgs();
			String cadena = "";

			for (int i = 0; i < cmd.getArgs().length; i++)

				cadena += v[i] + " ";

			throw new ParseException("Argumentos no entendidos: " + cadena);
		}
	}

	/**
	 * Comando RV
	 * 
	 * @param cmd
	 * @throws ParseException
	 */
	public static void RV(CommandLine cmd) throws ParseException {

		tipo = TipoJuego.RV;
		
		if (cmd.getOptionValue("g").equalsIgnoreCase("rv")
				&& cmd.getArgs().length == 0) {

			f = new FactoriaReversi();
			p = new Partida(f.creaReglas());

		} else {

			String v[] = cmd.getArgs();
			String cadena = "";

			for (int i = 0; i < cmd.getArgs().length; i++)

				cadena += v[i] + " ";

			throw new ParseException("Argumentos no entendidos: " + cadena);
		}
	}

	public static void isNumeric(String cadena) throws ParseException {

		try {
			Integer.parseInt(cadena);

		} catch (NumberFormatException nfe) {
			throw new ParseException("Parámetros no enteros");
		}
	}
}
