import { RealEstateComponent } from 'app/home/real-estate/real-estate.component';
import { Route } from '@angular/router';
import { NewRealEstateComponent } from 'app/home/real-estate/new-real-estate/new-real-estate.component';
import { MyRealEstateComponent } from 'app/home/real-estate/my-real-estate/my-real-estate.component';

export const realEstateRoute: Route[] = [
  {
    path: 'real-estate/list',
    component: RealEstateComponent,
    data: {
      authorities: []
    }
  },
  {
    path: 'real-estate/new',
    component: NewRealEstateComponent,
    data: {
      authorities: []
    }
  },
  {
    path: 'real-estate/my',
    component: MyRealEstateComponent,
    data: {
      authorities: []
    }
  }
];
