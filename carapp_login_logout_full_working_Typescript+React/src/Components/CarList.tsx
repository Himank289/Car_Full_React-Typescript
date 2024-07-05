import React, { useEffect, useState } from 'react';
import CarItem from './CarItem';
import carService from '../Services/CarService';
import './CarList.css'
import Car from '../Types/Car';


const CarList:React.FC = () => {
    const [cars, setCars] = useState<Car[]>([]);

    useEffect(() => {
        carService.getAllCars().then(response => {
            setCars(response.data);
        });
    }, []);

    return (
        <div className='car-list-container'>
            <h1>Car List</h1>
            {cars.map(car => (
                <CarItem key={car.id} car={car} />
            ))}
        </div>
    );
};

export default CarList;
