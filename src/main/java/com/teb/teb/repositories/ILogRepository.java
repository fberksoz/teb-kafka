package com.teb.teb.repositories;

import com.teb.teb.objects.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogRepository extends CrudRepository<Log, Long> {
}
