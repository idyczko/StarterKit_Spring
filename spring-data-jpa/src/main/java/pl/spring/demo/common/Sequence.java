package pl.spring.demo.common;

import pl.spring.demo.to.IdAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import java.util.Collection;

@Component
public class Sequence {

    public long nextValue(Collection<? extends IdAware> existingIds) {
        long result = 1;
        for (IdAware nextExistingId : existingIds) {
            if (Long.compare(nextExistingId.getId(), result) > 0) {
                result = nextExistingId.getId();
            }
        }
        return result;
    }
}
