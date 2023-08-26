package se.ivankrizsan.restexample.services;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import se.ivankrizsan.restexample.domain.Rectangle;
import se.ivankrizsan.restexample.repositories.RectangleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service exposing operations on rectangles.
 *
 * @author Ivan Krizsan
 */
@Service
@Observed(name = "RectangleService")
public class RectangleService extends AbstractServiceBasePlain<Rectangle> {

    /**
     * Creates a service instance that will use the supplied repository
     * for entity persistence.
     *
     * @param inRepository Rectangle repository.
     */
    public RectangleService(final RectangleRepository inRepository) {
        super(inRepository);
    }

    /*
     * In addition to the @Observed annotation on this (repository) interface also
     * need to include declarations of all the methods for which a span
     * is to be created by Micrometer.
     */

    @Override
    public Rectangle save(Rectangle inEntity) {
        return super.save(inEntity);
    }

    @Override
    public Rectangle update(Rectangle inEntity) {
        return super.update(inEntity);
    }

    @Override
    public Optional<Rectangle> find(Long inEntityId) {
        return super.find(inEntityId);
    }

    @Override
    public List<Rectangle> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Long inId) {
        super.delete(inId);
    }

    @Override
    public void deleteAll() {
        super.deleteAll();
    }
}
