package vw.him.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vw.him.car.entity.Car;
import vw.him.car.exception.CarNotFoundException;
import vw.him.car.exception.NotAuthorizedException;
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
        public ResponseEntity<List<Car>> getAllCars(){
            try{
                return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
            }
            catch (CarNotFoundException ex){
                throw new CarNotFoundException("Cars not found");
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Car> getCarById(@PathVariable Long id) {
            try{
                return new ResponseEntity<>(carService.getCarById(id).get(), HttpStatus.OK);
            }
            catch (CarNotFoundException ex) {
                throw new CarNotFoundException("Car not found");
            }

        }

        @PostMapping("/")
        public ResponseEntity<Car> createCar(@RequestHeader("Authorization") String jwt,@RequestBody Car c) {
            try {
                Car savedCar = carService.createCar(jwt, c);
                return ResponseEntity.ok(savedCar);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }

        @PutMapping("/{id}")
        public ResponseEntity<Car> updateCar(@RequestHeader("Authorization") String jwt,@PathVariable Long id, @RequestBody Car carDetails) {
            try {
                Optional<Car> updatedCar = carService.updateCar(jwt, id, carDetails);
                if (updatedCar.isPresent()) {
                    return ResponseEntity.ok(updatedCar.get());
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Boolean> deleteCar(@RequestHeader("Authorization") String jwt,@PathVariable Long id) {
            try{
                Boolean message= carService.deleteCar(jwt, id);
                return ResponseEntity.ok(message);
            }
            catch (NotAuthorizedException ex) {
                throw new NotAuthorizedException("Unauthorized operation");
            }

        }

    }



