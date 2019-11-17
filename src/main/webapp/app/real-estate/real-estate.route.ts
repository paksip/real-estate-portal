import { RealEstateComponent } from 'app/real-estate/real-estate.component';
import { Route } from '@angular/router';
import { NewRealEstateComponent } from 'app/real-estate/new-real-estate/new-real-estate.component';
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
      path: 'new',
      component: NewRealEstateComponent,
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
