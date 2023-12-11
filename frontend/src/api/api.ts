import axios, { AxiosError } from 'axios';
import Reservation from './models/Reservation';

export const getAllReservationsForRoom = async (roomId: number) => {
    try {
        const response = await axios.get<Reservation[]>(`/api/reservations/getAll/${roomId}`);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}

export const createReservationForRoom = async (params: Omit<Reservation, 'id'>) => {
    try {
        const response = await axios.post(`/api/reservations/create/${params.roomId}`, params);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}

export const deleteReservation = async (id: number) => {
    try {
        const response = await axios.delete(`/api/reservations/delete/${id}`);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}

export const getAllRooms = async () => {
    try {
        const response = await axios.get(`/api/rooms/getAll`);
        return response;
    } catch (error) {
        console.error(error);
        return error as AxiosError;
    }
}