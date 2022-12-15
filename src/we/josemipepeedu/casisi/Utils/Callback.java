package we.josemipepeedu.casisi.Utils;

public interface Callback<T> {

    void done(T result, Exception exception);
}
