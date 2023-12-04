import BaseEntity from "./BaseEntity";

export default interface Reservation extends BaseEntity {
    title: string;
    startAt: string;
    endAt: string;
    roomId: number;
}