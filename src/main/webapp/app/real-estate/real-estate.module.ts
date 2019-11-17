import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RealEstateComponent } from 'app/real-estate/real-estate.component';
import { MyRealEstateComponent } from 'app/real-estate/my-real-estate/my-real-estate.component';
import { RealEstatePortalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { REAL_ESTATE_ROUTE } from 'app/real-estate/real-estate.route';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { RealEstateFormComponent } from './real-estate-form/real-estate-form.component';
import { ImageHandlerComponent } from './real-estate-form/image-handler/image-handler.component';
import { ReservationHandlerComponent } from './real-estate-form/reservation-handler/reservation-handler.component';

@NgModule({
  declarations: [RealEstateComponent, MyRealEstateComponent, RealEstateFormComponent, ImageHandlerComponent, ReservationHandlerComponent],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    RealEstatePortalSharedModule,
    RouterModule.forChild([REAL_ESTATE_ROUTE]),
    BrowserModule,
    HttpClientModule,
    MatCardModule,
    MatDialogModule
  ],
  entryComponents: [RealEstateFormComponent]
})
export class RealEstateModule {}
