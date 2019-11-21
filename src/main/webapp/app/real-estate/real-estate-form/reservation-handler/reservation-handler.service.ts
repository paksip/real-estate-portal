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

  create(realEstateId: number, body: AvailableReservationTime): Observable<void> {
    return this.http.post<void>(`${this.configUrl}/${realEstateId}/reservations`, body);
  }

  update(realEstateId: number, reservationId: number, body: AvailableReservationTime): Observable<void> {
    return this.http.put<void>(`${this.configUrl}/${realEstateId}/reservations/${reservationId}`, body);
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
    return this.http.put<void>(`${this.configUrl}/${realEstateId}/reservations/${reservationId}`, body);
  }
}
