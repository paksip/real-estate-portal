import { RealEstateComponent } from 'app/real-estate/real-estate.component';
import { Route } from '@angular/router';
import { MyRealEstateComponent } from 'app/real-estate/my-real-estate/my-real-estate.component';

export const REAL_ESTATE_ROUTE: Route = {
  path: 'real-estate',
  data: {
    authorities: []
  },
  children: [
    {
      path: 'list',
      component: RealEstateComponent,
      data: {
        authorities: []
      }
    },
    {
      path: 'my',
      component: MyRealEstateComponent,
      data: {
        authorities: []
      }
    }
  ]
};
