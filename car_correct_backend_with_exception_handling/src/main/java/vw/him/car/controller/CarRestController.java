package vw.him.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vw.him.car.entity.Car;
import vw.him.car.service.CarService;

import java.util.List;
import java.util.Optional;

    @CrossOrigin(origins = "http://localhost:3000")
    @RestController
    @RequestMapping("/cars")
    public class CarRestController {

        @Autowired
        private CarService carService;

        @GetMapping("/")

        public List<Car> getAllCars() {
            return carService.getAllCars();
        }

        @GetMapping("/{id}")

        public Car getCarById(@PathVariable Long id) {
            Optional<Car> c = carService.getCarById(id);
            if (c.isPresent()) {
                return c.get();
            } else {
                return null;
            }

        }

        @PostMapping("/")

        public Car createCar(@RequestHeader("Authorization") String jwt,@RequestBody Car c) {
            Car savedCar = carService.createCar(jwt ,c);
            System.out.println("Post called:");
            return savedCar;
        }

        @PutMapping("/{id}")

        public Car updateCar(@RequestHeader("Authorization") String jwt,@PathVariable Long id, @RequestBody Car carDetails) {
            Optional<Car> updatedCar = carService.updateCar(jwt,id, carDetails);
            if (updatedCar.isPresent()) {
                return updatedCar.get();
            } else {
                return null;
            }
        }

        @DeleteMapping("/{id}")

        public void deleteCar(@RequestHeader("Authorization") String jwt,@PathVariable Long id) {
            boolean deleted = carService.deleteCar(jwt,id);
        }

    }



