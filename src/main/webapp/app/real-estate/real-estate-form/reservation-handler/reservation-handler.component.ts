import { Component, Input, OnInit } from '@angular/core';
import { FormMode } from 'app/real-estate/models/formMode';
import { ReservationHandlerService } from 'app/real-estate/real-estate-form/reservation-handler/reservation-handler.service';
import { Reservation } from 'app/real-estate/models/reservation';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'jhi-reservation-handler',
  templateUrl: './reservation-handler.component.html',
  styleUrls: ['./reservation-handler.component.scss']
})
export class ReservationHandlerComponent implements OnInit {
  _mode: FormMode;
  reservations: Reservation[];
  FormMode = FormMode;
  formCreate: FormGroup;
  formReserve: FormGroup;

  @Input() realEstateId: number;
  @Input() set mode(value: FormMode) {
    this._mode = value;
    this.formInit();
    this.load();
  }

  get mode(): FormMode {
    return this._mode;
  }

  constructor(private reservationHandlerService: ReservationHandlerService) {}

  ngOnInit() {
    this.formInit();
    this.load();
  }

  formInit() {
    if (this.mode === FormMode.GET) {
      this.formReserve = new FormGroup({
        reservationId: new FormControl('', Validators.required),
        email: new FormControl('', Validators.required),
        phoneNumber: new FormControl('', Validators.required),
        message: new FormControl(''),
        userName: new FormControl('')
      });
    } else {
      this.formCreate = new FormGroup({
        from: new FormControl('', Validators.required),
        to: new FormControl('', Validators.required)
      });
    }
  }

  load() {
    this.reservationHandlerService.getAll(this.realEstateId).subscribe(result => {
      this.reservations = this.mode === FormMode.GET ? result.filter(r => r.isFree) : result;
    });
  }

  onCreate() {
    this.formCreate.markAllAsTouched();
    this.formCreate.markAllAsTouched();
    this.reservationHandlerService
      .create(this.realEstateId, {
        from: this.formCreate.get('from').value,
        to: this.formCreate.get('to').value
      })
      .subscribe(() => {
        this.load();
        this.formCreate.reset();
      });
  }

  onReserve() {
    this.formReserve.markAllAsTouched();
    this.reservationHandlerService
      .reserve(this.realEstateId, this.formReserve.get('reservationId').value, {
        email: this.formReserve.get('email').value,
        phoneNumber: this.formReserve.get('phoneNumber').value,
        message: this.formReserve.get('message').value,
        userName: this.formReserve.get('userName').value
      })
      .subscribe(() => {
        this.load();
        this.formReserve.reset();
      });
  }

  remove(reservation: Reservation) {
    this.reservationHandlerService.delete(this.realEstateId, reservation.id).subscribe(() => this.load());
  }
}
