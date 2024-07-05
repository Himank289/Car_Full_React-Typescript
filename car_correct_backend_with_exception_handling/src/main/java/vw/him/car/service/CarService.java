package vw.him.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vw.him.car.configuration.JwtProvider;
import vw.him.car.entity.Car;
import vw.him.car.entity.User;
import vw.him.car.exception.NotAuthorizedException;
import vw.him.car.exception.UserNotFoundException;
import vw.him.car.repository.CarRepo;
import vw.him.car.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepo carrepository;

    @Autowired
    UserRepository userRepository;

    public List<Car> getAllCars(){
        return carrepository.findAll();

    }

    public Optional<Car> getCarById(Long id) {
        return carrepository.findById(id);
    }

    public Car createCar(String jwt,Car c) {
        if (isAdmin(jwt)) {
            return carrepository.save(c);
        }
        throw new NotAuthorizedException("not authorized");

    }

    public Optional<Car> updateCar(String jwt,Long id, Car carDetails) {

        if (isAdmin(jwt)) {
            Optional<Car> optionalCar = carrepository.findById(id);
            if (optionalCar.isPresent()) {
                Car existingCar = optionalCar.get();
//            existingCar.setId(carDetails.getId());
                existingCar.setBrand(carDetails.getBrand());
                existingCar.setColor(carDetails.getColor());
                existingCar.setDescription(carDetails.getDescription());
                existingCar.setImage(carDetails.getImage());
                existingCar.setName(carDetails.getName());
                existingCar.setPrice(carDetails.getPrice());
                existingCar.setType(carDetails.getType());
                existingCar.setYear(carDetails.getYear());

                carrepository.save(existingCar);
                return Optional.of(existingCar);
            } else {
                return Optional.empty();
            }
        }
        throw new NotAuthorizedException("not authorized");

    }


    public boolean deleteCar(String jwt,Long id) {
        if (isAdmin(jwt)) {
            Optional<Car> c = carrepository.findById(id);
            if (c.isPresent()) {
                carrepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }

        throw new NotAuthorizedException("not authorized");
    }

    public User getUserProfile(String jwt)
    {
        User user=new User();

        String email= JwtProvider.getEmailFromJwtTone(jwt);
        User fetchedUser=userRepository.findByEmail(email);
        if(fetchedUser!=null) {
            return userRepository.findByEmail(email);}
        throw new UserNotFoundException("user not found");

    }

    public boolean isAdmin(String jwt){
        User jwtUser=getUserProfile(jwt);
        String role = jwtUser.getRole();
        if(role != null && role.equals("admin")){
            return true;
        }
        return false;
    }

}
