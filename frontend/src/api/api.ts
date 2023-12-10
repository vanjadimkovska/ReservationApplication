import axios, { AxiosError } from 'axios';
import Reservation from './models/Reservation';

const API_URL = import.meta.env.VITE_APP_URL;

export const getAllReservationsForRoom = async (roomId: number) => {
    try {
        const response = await axios.get<Reservation[]>(`${API_URL}/api/reservations/getAll/${roomId}`);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}

export const createReservationForRoom = async (params: Omit<Reservation, 'id'>) => {
    try {
        const response = await axios.post(`${API_URL}/api/reservations/create/${params.roomId}`, params);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}

export const deleteReservation = async (id: number) => {
    try {
        const response = await axios.delete(`${API_URL}/api/reservations/delete/${id}`);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}

export const getAllRooms = async () => {
    try {
        const response = await axios.get(`${API_URL}/api/rooms/getAll`);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}