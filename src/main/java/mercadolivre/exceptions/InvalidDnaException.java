package mercadolivre.exceptions;

import mercadolivre.messages.errors.ErrorMessages;

public class InvalidDnaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidDnaException() {
		 super(ErrorMessages.DNA_INVALIDO);
	}

	public InvalidDnaException(String message) {
		super(message);
	}
}
