import { RealEstateComponent } from 'app/real-estate/real-estate.component';
import { Route } from '@angular/router';
import { RealEstateFormComponent } from 'app/real-estate/real-estate-form/real-estate-form.component';
import { FormMode } from 'app/real-estate/models/formMode';

export const REAL_ESTATE_ROUTE: Route = {
  path: 'real-estate',
  data: {
    authorities: []
  },
  children: [
    {
      path: '',
      component: RealEstateComponent,
      data: {
        authorities: []
      }
    },
    {
      path: 'create',
      component: RealEstateFormComponent,
      data: {
        formMode: FormMode.CREATE,
        authorities: []
      }
    },
    {
      path: 'update/:id',
      component: RealEstateFormComponent,
      data: {
        formMode: FormMode.UPDATE,
        authorities: []
      }
    },
    {
      path: 'get/:id',
      component: RealEstateFormComponent,
      data: {
        formMode: FormMode.GET,
        authorities: []
      }
    }
  ]
};
