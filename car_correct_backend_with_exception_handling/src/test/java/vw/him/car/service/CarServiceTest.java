    package vw.him.car.service;

    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.mockito.junit.jupiter.MockitoSettings;
    import org.mockito.quality.Strictness;
    import vw.him.car.entity.Car;
    import vw.him.car.repository.CarRepo;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.verify;
    import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    @MockitoSettings(strictness = Strictness.LENIENT)
    class CarServiceTest {

        @Mock
        private CarRepo carrepository;

        @InjectMocks
        private CarService carservice;

    //    @Test
    //    void testCreateCar(){
    //        Car c=new Car();
    //
    //        when(carrepository.save(c)).thenReturn(c);
    //
    //         Car savedCar=carservice.createCar(c);
    //         assertNotNull(savedCar);
    //          verify(carrepository).save(c);
    //    }

        @Test
        void testGetAllCars(){
            List<Car> lst=new ArrayList<>();
            lst.add(new Car());
            lst.add(new Car());

            when(carrepository.findAll()).thenReturn(lst);

            List<Car>carList=carservice.getAllCars();
            assertNotNull(carList);
            assertEquals(lst.size(),carList.size());
            assertEquals(lst,carList);
        }

        @Test
        void testGetCarById(){
            Long id=1L;
            Car c =new Car();
            when(carrepository.findById(id)).thenReturn(Optional.of(c));

            Optional<Car> foundcar=carservice.getCarById(id);
            assertNotNull(foundcar);
            verify(carrepository).findById(id);

        }

    //    @Test
    //    void testUpdateCar(){
    //        String brand="Volkswagen";
    //    Car originalCar=new Car();
    //    originalCar.setBrand(brand);
    //    Long id=1L;
    //
    //    when(carrepository.findByBrand(brand)).thenReturn((originalCar));
    //   when(carrepository.save(originalCar)).thenReturn(originalCar);
    //
    //   Car updatedCar=new Car();
    //   updatedCar.setBrand(brand);
    //
    //  Optional <Car> result=carservice.updateCar(id,updatedCar);
    //
    //  verify(carrepository).findById(id);
    //
    //  assertNotNull(result);
    //}


    //    @Test
    //    void testDeleteCar(){


    //        Long id=1L;
    //       when(carrepository.findById(id)).thenReturn(Optional.of(new Car()));
    //
    //        carservice.deleteCar(id);
    //        verify(carrepository).deleteById(id);
    //    }



    }