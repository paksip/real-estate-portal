import { Route } from '@angular/router';
import { realEstateRoute } from 'app/home/real-estate/real-estate.route';
import { HomeComponent } from 'app/home/home.component';

const REAL_ESTATE_ROUTES = [realEstateRoute];

export const HOME_ROUTE: Route = {
  path: '',
  data: {
    authorities: [],
    pageTitle: 'home.title'
  },
  children: [
    {
      path: '',
      component: HomeComponent
    },
    ...REAL_ESTATE_ROUTES
  ]
};
