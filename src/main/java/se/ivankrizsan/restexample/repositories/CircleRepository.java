package se.ivankrizsan.restexample.repositories;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.repositories.customisation.JpaRepositoryCustomisations;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Spring Data JPA mRepository for circles.
 *
 * @author Ivan Krizsan
 */
@Observed(name = "CircleRepository")
public interface CircleRepository extends JpaRepositoryCustomisations<Circle> {
    /*
     * In addition to the @Observed annotation on this (repository) interface also
     * need to include declarations of all the methods for which a span
     * is to be created by Micrometer.
     */
    @Override
    Circle persist(Circle inEntity);

    @Override
    void flush();

    @Override
    <S extends Circle> S saveAndFlush(S entity);

    @Override
    <S extends Circle> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<Circle> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    void deleteAllInBatch();

    @Override
    Circle getOne(Long inLong);

    @Override
    Circle getById(Long inLong);

    @Override
    Circle getReferenceById(Long inLong);

    @Override
    <S extends Circle> List<S> findAll(Example<S> example);

    @Override
    <S extends Circle> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Circle> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Circle> findAll();

    @Override
    List<Circle> findAllById(Iterable<Long> ids);

    @Override
    <S extends Circle> S save(S entity);

    @Override
    Optional<Circle> findById(Long inLong);

    @Override
    boolean existsById(Long inLong);

    @Override
    long count();

    @Override
    void deleteById(Long inLong);

    @Override
    void delete(Circle entity);

    @Override
    void deleteAllById(Iterable<? extends Long> ids);

    @Override
    void deleteAll(Iterable<? extends Circle> entities);

    @Override
    void deleteAll();

    @Override
    List<Circle> findAll(Sort sort);

    @Override
    Page<Circle> findAll(Pageable pageable);

    @Override
    <S extends Circle> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Circle> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Circle> long count(Example<S> example);

    @Override
    <S extends Circle> boolean exists(Example<S> example);

    @Override
    <S extends Circle, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
