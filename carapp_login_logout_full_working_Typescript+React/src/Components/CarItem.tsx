import React from 'react';
import { Link } from 'react-router-dom';
import "./CarItem.css"
import Car from '../Types/Car';

interface CarItemProps {
    car: Car;
}

const CarItem:React.FC<CarItemProps> = ({ car }) => {
   
    const role :string|null = localStorage.getItem("jwtid");

    return (
        <div className='car-item'>
          
            <h2>{car.name}</h2>
            <p>{car.description}</p>
            <Link to={`/cars/${car.id}`}>Details</Link>

           { (role==="ROLE_admin" || role==="admin") && <Link to={`/edit-car/${car.id}`}>Edit</Link>}
           { (role==="ROLE_admin" || role==="admin") && <Link to={`/delete-car/${car.id}`}>Delete</Link>}
        </div>
    );
};

export default CarItem;
