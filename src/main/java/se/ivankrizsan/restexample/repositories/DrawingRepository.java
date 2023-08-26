package se.ivankrizsan.restexample.repositories;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import se.ivankrizsan.restexample.domain.Drawing;
import se.ivankrizsan.restexample.repositories.customisation.JpaRepositoryCustomisations;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Spring Data JPA mRepository for drawings.
 *
 * @author Ivan Krizsan
 */
@Observed(name = "DrawingRepository")
public interface DrawingRepository extends JpaRepositoryCustomisations<Drawing> {
    /*
     * In addition to the @Observed annotation on this (repository) interface also
     * need to include declarations of all the methods for which a span
     * is to be created by Micrometer.
     */
    @Override
    Drawing persist(Drawing inEntity);

    @Override
    void flush();

    @Override
    <S extends Drawing> S saveAndFlush(S entity);

    @Override
    <S extends Drawing> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<Drawing> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    Drawing getOne(Long aLong);

    @Override
    Drawing getById(Long aLong);

    @Override
    Drawing getReferenceById(Long aLong);

    @Override
    <S extends Drawing> List<S> findAll(Example<S> example);

    @Override
    <S extends Drawing> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Drawing> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Drawing> findAll();

    @Override
    List<Drawing> findAllById(Iterable<Long> longs);

    @Override
    <S extends Drawing> S save(S entity);

    @Override
    Optional<Drawing> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Drawing entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Drawing> entities);

    @Override
    void deleteAll();

    @Override
    List<Drawing> findAll(Sort sort);

    @Override
    Page<Drawing> findAll(Pageable pageable);

    @Override
    <S extends Drawing> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Drawing> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Drawing> long count(Example<S> example);

    @Override
    <S extends Drawing> boolean exists(Example<S> example);

    @Override
    <S extends Drawing, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
