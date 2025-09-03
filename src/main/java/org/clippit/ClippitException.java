package org.clippit;

public class ClippitException extends RuntimeException {
    public static final class LackOfArgumentException extends RuntimeException {
        public LackOfArgumentException(String message) {
            super(message);
        }
    }

    public ClippitException(String message) {
        super(message);
    }
}
