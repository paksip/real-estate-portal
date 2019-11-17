import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RealEstateComponent } from 'app/real-estate/real-estate.component';
import { NewRealEstateComponent } from 'app/real-estate/new-real-estate/new-real-estate.component';
import { MyRealEstateComponent } from 'app/real-estate/my-real-estate/my-real-estate.component';
import { RealEstatePortalSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { REAL_ESTATE_ROUTE } from 'app/real-estate/real-estate.route';

@NgModule({
  declarations: [RealEstateComponent, NewRealEstateComponent, MyRealEstateComponent],
  imports: [CommonModule, RealEstatePortalSharedModule, RouterModule.forChild([REAL_ESTATE_ROUTE])]
})
export class RealEstateModule {}
