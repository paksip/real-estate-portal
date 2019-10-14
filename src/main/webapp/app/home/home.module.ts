import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RealEstatePortalSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { RealEstateComponent } from './real-estate/real-estate.component';

@NgModule({
  imports: [RealEstatePortalSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent, RealEstateComponent]
})
export class RealEstatePortalHomeModule {}
