import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewReservation } from 'app/real-estate/models/newReservation';
import { AvailableReservationTime } from 'app/real-estate/models/availableReservationTime';
import { Reservation } from 'app/real-estate/models/reservation';
import { ReservationDetails } from 'app/real-estate/models/reservationDetails';

@Injectable({
  providedIn: 'root'
})
export class ReservationHandlerService {
  configUrl = 'api/realestates';

  constructor(private http: HttpClient) {}

  create(realEstateId: number, body: NewReservation): Observable<AvailableReservationTime> {
    return this.http.post<AvailableReservationTime>(`${this.configUrl}/${realEstateId}/reservations`, body);
  }

  update(realEstateId: number, reservationId: number, body: AvailableReservationTime): Observable<AvailableReservationTime> {
    return this.http.put<AvailableReservationTime>(`${this.configUrl}/${realEstateId}/reservations/${reservationId}`, body);
  }

  getAll(realEstateId: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.configUrl}/${realEstateId}/reservations`);
  }

  get(realEstateId: number, reservationId: number): Observable<ReservationDetails> {
    return this.http.get<ReservationDetails>(`${this.configUrl}/${realEstateId}/reservations/${reservationId}`);
  }

  delete(realEstateId: number, reservationId: number): Observable<void> {
    return this.http.delete<void>(`${this.configUrl}/${realEstateId}/reservations/${reservationId}`);
  }

  reserve(realEstateId: number, reservationId: number, body: NewReservation): Observable<void> {
    return this.http.patch<void>(`${this.configUrl}/${realEstateId}/reservations/${reservationId}`, body);
  }
}
