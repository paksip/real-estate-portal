import { Route } from '@angular/router';
import { RealEstateComponent } from 'app/home/real-estate/real-estate.component';

export const realEstateRoute: Route = {
  path: 'real-estate',
  component: RealEstateComponent,
  data: {
    authorities: []
  }
};
