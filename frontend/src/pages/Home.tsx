import { useEffect, useState } from "react";
import dayjs from "dayjs";

import '../styles/Home.css';
import Modal from "../components/Modal";
import Room from "../api/models/Room";
import { createReservationForRoom, deleteReservation, getAllReservationsForRoom, getAllRooms } from "../api/api";
import Reservation from "../api/models/Reservation";
import { MaterialSymbol } from "react-material-symbols";


const Home = () => {
  const [rooms, setRooms] = useState<Room[]>([]);
  const [reservations, setReservations] = useState<Reservation[]>([]);

  const [selectedRoom, setSelectedRoom] = useState<number>(1);
  const [selectedRoomObject, setSelectedRoomObject] = useState<Room | undefined>(undefined);
  const [title, setTitle] = useState<string>();
  const [startDate, setStartDate] = useState<string>();
  const [endDate, setEndDate] = useState<string>();
  const [titleError, setTitleError] = useState<string | null>(null);
  const [dateStartError, setDateStartError] = useState<string | null>(null);
  const [dateEndError, setDateEndError] = useState<string | null>(null);
  const [isDeleteModalOpen, setDeleteModalOpen] = useState<boolean>(false);
  const [reservationToDelete, setReservationToDelete] = useState<number | null>(null);
  const [reservationError, setReservationError] = useState<string | null>(null);

  const getRooms = async () => {
    const rooms = await getAllRooms();
    if ('data' in rooms) setRooms(rooms.data)

  }

  const getReservations = async () => {
    const reservations = await getAllReservationsForRoom(selectedRoom);
    if ('data' in reservations) setReservations(reservations.data)

  }

  const createReservation = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!title) {
      setTitleError("You must add a meeting title");
    } else {
      setTitleError("");
    }
    if (!startDate) {
      setDateStartError("You must choose start date and time");
    } else {
      setDateStartError("");
    }
    if (!endDate) {
      setDateEndError("You must choose end date and time");
    } else {
      setDateEndError("");
    }

    if (title && startDate && endDate && selectedRoom) {
      const res = await createReservationForRoom({ title, startAt: startDate, endAt: endDate, roomId: selectedRoom });
      if ('data' in res) {
        setTitleError(null);
        setDateStartError(null);
        setDateEndError(null);
        getReservations();
        (event.target as HTMLFormElement).reset();
      } else {
        if (res?.response?.data)
          setReservationError(res?.response?.data.toString())
      }
    }

  }

  const onDeleteReservation = (id: number) => {
    setReservationToDelete(id);
    setDeleteModalOpen(true);
  }

  const confirmDeleteReservation = async () => {
    if (reservationToDelete !== null) {
      const response = await deleteReservation(reservationToDelete);
      if ('data' in response) {
        getReservations();
      }
    }
    setReservationToDelete(null);
    setDeleteModalOpen(false);
  }

  const cancelDeleteReservation = () => {
    setReservationToDelete(null);
    setDeleteModalOpen(false);
  }

  useEffect(() => {
    getRooms();
  }, []);


  useEffect(() => {
    const room = rooms[0];
    if (room) {
      setSelectedRoom(room.id)
      setSelectedRoomObject(room)
    }
  }, [rooms]);

  useEffect(() => {
    getReservations();
  }, [selectedRoom]);



  return (
    <>
      {reservationError && <Modal>
        <div>
          <p style={{ marginBottom: "20px" }}>{reservationError}</p>
          <button onClick={() => setReservationError("")}>Ok</button>
        </div>
      </Modal>}
      <div className="home-container">
        <div className="list-container">
          <div className="list-header">
            <h1>Meetings</h1>
            <h2>Scheduled For {selectedRoomObject?.name}</h2>
          </div>
          {reservations && reservations.map((reservation) => <div className="reservation-item" key={reservation.id}>
            <div className="reservation-data">
              <h2>{reservation.title}</h2>
              <p><span className="bold-text">Starts </span>{dayjs(reservation.startAt).format('HH:mm D MMM YYYY')}</p>
              <p><span className="bold-text">Ends </span>{dayjs(reservation.endAt).format('HH:mm D MMM YYYY')}</p>
            </div>
            <div className="reservation-actions">
              <button onClick={() => onDeleteReservation(reservation.id)} className="delete-button">
                <MaterialSymbol icon="delete" size={24} grade={-25} />
              </button>
            </div>
          </div>)}
          {reservations.length === 0 && <h2 className="no-information-text">No meetings scheduled</h2>}
        </div>
        <div className="form-container">
          <form onSubmit={createReservation}>
            <label className="form-item" htmlFor="room">Room
              <select id="room" name="roomList" form="roomForm" onChange={(e) => {
                setSelectedRoom(Number(e.target.value));
                setSelectedRoomObject(rooms.find(room => room.id === Number(e.target.value)))
              }
              }>
                {
                  rooms && rooms.map((room) => <option key={room.id} value={room.id}>{room.name}</option>)
                }
              </select>
            </label>
            <label className="form-item">
              Reservation Title
              <input type="text" placeholder="Title" onChange={(e) => { setTitle(e.target.value) }} />
              {titleError && <small style={{ color: 'red' }}>{titleError}</small>}
            </label>
            <label className="form-item">
              Start Date
              <input type="datetime-local" min={dayjs(new Date()).format('YYYY-MM-DD HH:mm')} onChange={(e) => {
                setStartDate(e.target.value)
              }} />
              {dateStartError && <small style={{ color: 'red' }}>{dateStartError}</small>}
            </label>
            <label className="form-item">
              End Date
              <input type="datetime-local" min={dayjs(new Date()).format('YYYY-MM-DD HH:mm')} onChange={(e) => {
                setEndDate(e.target.value)
              }} />
              {dateEndError && <small style={{ color: 'red' }}>{dateEndError}</small>}
            </label>
            <input type="submit" value="Add" />
          </form>
        </div>
        {isDeleteModalOpen && (
          <Modal>
            <div>
              <p style={{ marginBottom: "20px" }}>Are you sure you want to delete this meeting?</p>
              <button style={{ marginRight: "10px" }} onClick={confirmDeleteReservation}>Yes</button>
              <button onClick={cancelDeleteReservation}>No</button>
            </div>
          </Modal>
        )}
      </div>
    </>
  );
}

export default Home
