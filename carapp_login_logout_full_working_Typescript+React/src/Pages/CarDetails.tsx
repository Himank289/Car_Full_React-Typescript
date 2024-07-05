import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import carService from '../Services/CarService';
import './CarDetails.css'
import Car from '../Types/Car';

// interface RouteParamsId {
//     id?: string;
//     [key: string]: string | undefined; 
// }

const CarDetails:React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const [car, setCar] = useState<Car|null>(null);

    useEffect(() => {
        carService.getCarById(id!).then(response => {
            setCar(response.data);
        });
    }, [id]);

    if (!car) {
        return <div>Loading...</div>;
    }

    return (
        <div className='car-details-container'>
            <h1>{car.name}</h1>
            <p>Brand: {car.brand}</p>
            <p>Color: {car.color}</p>
            <p>Type: {car.type}</p>
            <p>Description: {car.description}</p>
            <p>Price: {car.price}</p>
            <p>Year: {car.year}</p>
            <img src={car.image} alt={car.name} />
        </div>
    );
};

export default CarDetails;