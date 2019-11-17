export interface Reservation {
  id: number;
  /**
   * The start of the reservation interval.
   */
  from: Date;
  /**
   * The end of the reservation interval.
   */
  to: Date;
  isFree: boolean;
}
