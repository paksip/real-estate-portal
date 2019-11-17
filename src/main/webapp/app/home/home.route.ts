import { Route } from '@angular/router';

import { HomeComponent } from './home.component';
import { realEstateRoute } from 'app/home/real-estate/real-estate.route';

const REAL_ESTATE_ROUTES = [...realEstateRoute];

export const HOME_ROUTE: Route = {
  path: '',
  component: HomeComponent,
  data: {
    authorities: []
  },
  children: [
    {
      path: '',
      component: HomeComponent
    },
    ...REAL_ESTATE_ROUTES
  ]
};
