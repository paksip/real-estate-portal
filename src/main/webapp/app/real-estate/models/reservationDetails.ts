export interface ReservationDetails {
  /**
   * The start of the reservation interval.
   */
  from: Date;
  /**
   * The end of the reservation interval.
   */
  to: Date;
  emailAddress: string;
  phoneNumber: string;
  message?: string;
  userName: string;
}
