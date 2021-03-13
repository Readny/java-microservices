package pl.redny.album.infrastructure.controller;

public class ControllerException extends Exception {

    private static final long serialVersionUID = 4529908930732130434L;

    public ControllerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ControllerException(final String message) {
        super(message);
    }

    public ControllerException(final Throwable cause) {
        super(cause);
    }
}
