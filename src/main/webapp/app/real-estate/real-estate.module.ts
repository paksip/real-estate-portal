import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RealEstateComponent } from 'app/real-estate/real-estate.component';
import { RealEstatePortalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { REAL_ESTATE_ROUTE } from 'app/real-estate/real-estate.route';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import { RealEstateFormComponent } from './real-estate-form/real-estate-form.component';
import { ImageHandlerComponent } from './real-estate-form/image-handler/image-handler.component';
import { ReservationHandlerComponent } from './real-estate-form/reservation-handler/reservation-handler.component';
import { RealEstateService } from 'app/real-estate/real-estate.service';
import { ReservationHandlerService } from 'app/real-estate/real-estate-form/reservation-handler/reservation-handler.service';
import { ImageHandlerService } from 'app/real-estate/real-estate-form/image-handler/image-handler.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MapHandlerComponent } from './real-estate-form/map-handler/map-handler.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { AgmCoreModule } from '@agm/core';
import { MatRadioModule } from '@angular/material/radio';

@NgModule({
  declarations: [
    RealEstateComponent,
    RealEstateFormComponent,
    ImageHandlerComponent,
    ReservationHandlerComponent,
    MapHandlerComponent
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    RealEstatePortalSharedModule,
    RouterModule.forChild([REAL_ESTATE_ROUTE]),
    BrowserModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyB06-nDzQzU4X3crMreB0qYZnsYtmkexFs'
    }),
    HttpClientModule,
    MatCardModule,
    MatCheckboxModule,
    MatRadioModule
  ],
  entryComponents: [RealEstateFormComponent],
  providers: [RealEstateService, ReservationHandlerService, ImageHandlerService]
})
export class RealEstateModule {}
