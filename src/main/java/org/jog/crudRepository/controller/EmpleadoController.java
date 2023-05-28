package org.jog.crudRepository.controller;

import org.jog.crudRepository.model.dao.IEmpleadoDao;
import org.jog.crudRepository.model.entity.Empleado;
import org.jog.crudRepository.model.excepcion.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmpleadoController {

    @Autowired
    private IEmpleadoDao empleadoDao;


    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {

        return empleadoDao.findAll();
    }


    @PostMapping("/empleados")
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoDao.save(empleado);

        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);

    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        Empleado empleado = empleadoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El empleado no se encuentra con el id " + id));
        return ResponseEntity.ok(empleado);
    }

    //Actualizar empleados
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> editarEmpleado(@PathVariable long id, @RequestBody Empleado detalleEmpleado) {
        Empleado empleado1 = empleadoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede editar ya que no existe en la base de datos."));
        empleado1.setNombre(detalleEmpleado.getNombre());
        empleado1.setApellido(detalleEmpleado.getApellido());
        empleado1.setEmail(detalleEmpleado.getEmail());

        Empleado actualizado = empleadoDao.save(empleado1);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar, id no existe"));

        empleadoDao.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }


}
