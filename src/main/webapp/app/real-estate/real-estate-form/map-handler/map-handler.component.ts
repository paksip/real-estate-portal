import { Component, Input, OnInit } from '@angular/core';
import { MapLocation } from 'app/real-estate/models/mapLocation';

@Component({
  selector: 'jhi-map-handler',
  templateUrl: './map-handler.component.html',
  styleUrls: ['./map-handler.component.scss']
})
export class MapHandlerComponent implements OnInit {
  _location: MapLocation;

  @Input() set location(value) {
    if (value) {
      this._location = value;
    }
  }

  get location(): MapLocation {
    return this._location;
  }
  zoom = 15;
  constructor() {}

  ngOnInit() {}
}
