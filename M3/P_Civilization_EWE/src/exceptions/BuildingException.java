package exceptions;

public class BuildingException extends Exception {
	
	//En esta excepcion crearemos un constructor al que le pasaremos el mensaje de error, este ha de enviar un mensaje a dc_gui para mostrar el
	//mensaje de la excepcion por pantalla

	public BuildingException(String string) {
		super(string);
	}
}
