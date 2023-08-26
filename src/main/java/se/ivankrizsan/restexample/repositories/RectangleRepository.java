package se.ivankrizsan.restexample.repositories;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import se.ivankrizsan.restexample.domain.Rectangle;
import se.ivankrizsan.restexample.repositories.customisation.JpaRepositoryCustomisations;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Spring Data JPA mRepository for rectangles.
 *
 * @author Ivan Krizsan
 */
@Observed(name = "RectangleRepository")
public interface RectangleRepository extends JpaRepositoryCustomisations<Rectangle> {
    /*
     * In addition to the @Observed annotation on this (repository) interface also
     * need to include declarations of all the methods for which a span
     * is to be created by Micrometer.
     */
    @Override
    Rectangle persist(Rectangle inEntity);

    @Override
    void flush();

    @Override
    <S extends Rectangle> S saveAndFlush(S entity);

    @Override
    <S extends Rectangle> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<Rectangle> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    Rectangle getOne(Long aLong);

    @Override
    Rectangle getById(Long aLong);

    @Override
    Rectangle getReferenceById(Long aLong);

    @Override
    <S extends Rectangle> List<S> findAll(Example<S> example);

    @Override
    <S extends Rectangle> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Rectangle> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Rectangle> findAll();

    @Override
    List<Rectangle> findAllById(Iterable<Long> longs);

    @Override
    <S extends Rectangle> S save(S entity);

    @Override
    Optional<Rectangle> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Rectangle entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Rectangle> entities);

    @Override
    void deleteAll();

    @Override
    List<Rectangle> findAll(Sort sort);

    @Override
    Page<Rectangle> findAll(Pageable pageable);

    @Override
    <S extends Rectangle> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Rectangle> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Rectangle> long count(Example<S> example);

    @Override
    <S extends Rectangle> boolean exists(Example<S> example);

    @Override
    <S extends Rectangle, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
