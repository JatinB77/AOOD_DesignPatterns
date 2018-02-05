import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target(ElementType.METHOD)
public @interface UpdateMethod {
}

@Target(ElementType.CLASS)
public @interface Observer {
}

public class Manager {
    private ArrayList listeners;

    public void publish(Object data) {
        for (Object potentialListener : listeners) {
            Class<?> listenerClass = potentialListener.getClass();
            for (Method m : listenerClass.getDeclaredMethods()) {
                if (m.getAnnotation(UpdateMethod.class) != null) {
                    Class<?>[] parameters = m.getParameterTypes();
                    if (parameters.length == 1 && parameters[0].isAssignableFrom(data.getClass())) {
                        try {
                            m.setAccessible(true);
                            m.invoke(potentialListener, data);
                        } catch (InvocationTargetException ex) {
                            Throwable cause = x.getCause();
                            throw cause;
                        }
                    } 
                }
            }
        }
    }

    public void addListener(Object listener) {
        Class<?> listenerClass = listener.getClass();
        if (listenerClass.getAnnotation(Observer.class) != null) {
            // don't bother with check for valid update methods
            // objects without update methods will simply be passed over
            listeners.add(listener);
        } else {
            throw IllegalArgumentException("Listener object must have Observer annotation.");
        }

    }
}
