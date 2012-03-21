package cl.buildersoft.framework.exception;

public class NestedException extends RuntimeException {

    static Throwable throwable;

    public NestedException(Throwable t) {
      this.throwable = t;
    }

    /** Wraps another exeception in a RuntimeException. */
    public static RuntimeException wrap(Throwable t) {
      if (t instanceof Exception) 
        return (RuntimeException) t;
      return new NestedException(t);
    }

    public Throwable getCause() {
      return this.throwable;
    }

    public void printStackTrace() {
      this.throwable.printStackTrace();
    }

  }