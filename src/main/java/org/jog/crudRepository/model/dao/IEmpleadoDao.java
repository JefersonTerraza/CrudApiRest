package org.jog.crudRepository.model.dao;

import org.jog.crudRepository.model.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoDao extends JpaRepository<Empleado, Long> {
}
