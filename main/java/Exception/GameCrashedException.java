package Exception;

public class GameCrashedException extends RuntimeException {
        public GameCrashedException(String msg, Throwable cause) {
            super(msg, cause);
        }

        public GameCrashedException(Throwable cause) {
            super(cause);
        }
}
