import { Component, Input, OnInit } from '@angular/core';
import { MapLocation } from 'app/real-estate/models/mapLocation';

@Component({
  selector: 'jhi-map-handler',
  templateUrl: './map-handler.component.html',
  styleUrls: ['./map-handler.component.scss']
})
export class MapHandlerComponent implements OnInit {
  @Input() location: MapLocation;
  texto = 'Wenceslau Braz - Cuidado com as cargas';
  lat = -23.8779431;
  lng = -49.8046873;
  zoom = 15;
  constructor() {}

  ngOnInit() {}
}
