package it.crud.controllers;

import it.crud.entities.Car;
import it.crud.repositories.CarRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/add")
    public Car addCar(@RequestBody Car car){
        return carRepository.saveAndFlush(car);
    }


    @GetMapping("/lista")
    public List<Car> getCars(){
        return carRepository.findAll();
    }


    @GetMapping("/{id}")
    public Car getCarById(@PathVariable long id){
        Car car = carRepository.getReferenceById(id);
        return car;

    }


    @PutMapping("/{id}")
    public Car editCar(@PathVariable long id, @RequestParam String type){
        Car car = carRepository.getReferenceById(id);
            car.setType(type);
            car = carRepository.saveAndFlush(car);
        return car;
    }


    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable long id, HttpServletResponse response){
        if (carRepository.existsById(id))
            carRepository.deleteById(id);
        else
            response.setStatus(409);
    }


    @DeleteMapping("")
    public void deleteAllCars(){
        carRepository.deleteAll();
    }
}
