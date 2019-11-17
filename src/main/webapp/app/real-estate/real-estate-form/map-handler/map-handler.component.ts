import { Component, Input, OnInit } from '@angular/core';
import { MapLocation } from 'app/real-estate/models/mapLocation';

@Component({
  selector: 'jhi-map-handler',
  templateUrl: './map-handler.component.html',
  styleUrls: ['./map-handler.component.scss']
})
export class MapHandlerComponent implements OnInit {
  @Input() location: MapLocation;
  zoom = 15;
  constructor() {}

  ngOnInit() {}
}
