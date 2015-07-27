package pl.spring.demo.aop;


import org.springframework.aop.MethodBeforeAdvice;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

import java.lang.reflect.Method;

public class BookDaoAdvisor implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

        if (hasAnnotation(method, o, NullableId.class)) {
            checkNotNullId(objects[0]);
        }
    }

    private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }

    private boolean hasAnnotation (Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

        if (!hasAnnotation && o != null) {
            hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
        }
        return hasAnnotation;
    }
}
