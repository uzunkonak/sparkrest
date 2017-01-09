package com.uzunkonak.sparkapp.model;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Caner Uzunkonak on 09.01.2017.
 */
@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {
}
