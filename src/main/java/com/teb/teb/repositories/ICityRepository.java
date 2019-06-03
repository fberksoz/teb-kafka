package com.teb.teb.repositories;

import com.teb.teb.objects.City;
import org.springframework.data.repository.CrudRepository;

public interface ICityRepository extends CrudRepository<City, Long> {

    public City getCityByName(String name);

}
