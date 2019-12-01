import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {RealEstateService} from 'app/real-estate/real-estate.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {FormMode} from 'app/real-estate/models/formMode';
import {CategoryEnum} from 'app/real-estate/models/category';
import {RealEstateDetails} from 'app/real-estate/models/realEstateDetails';
import {MapLocation} from 'app/real-estate/models/mapLocation';
import {NewRealEstate} from 'app/real-estate/models/newRealEstate';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'jhi-real-estate-form',
  templateUrl: './real-estate-form.component.html',
  styleUrls: ['./real-estate-form.component.scss']
})
export class RealEstateFormComponent implements OnInit {
  @ViewChild('file', { static: false }) file: ElementRef;
  model: RealEstateDetails;
  modelId: number;
  location: MapLocation;
  mode: FormMode;
  CategoryEnum = CategoryEnum;
  FormMode = FormMode;
  form: FormGroup;

  constructor(private realEstateService: RealEstateService, private activatedRoute: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.mode = this.activatedRoute.snapshot.data['formMode'];
      if (params['id']) {
        this.modelId = params['id'];
        this.getData();
      } else {
        this.initForm();
      }
    });
  }

  getData() {
    this.realEstateService.get(this.modelId).subscribe(result => {
      this.model = result;
      this.initForm();
    });
  }

  initForm() {
    this.form = new FormGroup({
      name: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      category: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      description: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      squareMeter: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      price: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      numberOfRooms: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      hasBalcony: new FormControl({value: false, disabled: this.mode === FormMode.GET}),
      hasAirCondition: new FormControl({value: false, disabled: this.mode === FormMode.GET}),
      ownerPhoneNumber: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      lon: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      lat: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required),
      locationName: new FormControl({value: '', disabled: this.mode === FormMode.GET}, Validators.required)
    });

    if (this.model) {
      this.form.get('name').patchValue(this.model.name);
      this.form.get('category').patchValue(this.model.category);
      this.form.get('description').patchValue(this.model.description);
      this.form.get('squareMeter').patchValue(this.model.squareMeter);
      this.form.get('price').patchValue(this.model.price);
      this.form.get('numberOfRooms').patchValue(this.model.numberOfRooms);
      this.form.get('hasBalcony').patchValue(this.model.hasBalcony === true);
      this.form.get('hasAirCondition').patchValue(this.model.hasAirCondition === true);
      this.form.get('ownerPhoneNumber').patchValue(this.model.ownerPhoneNumber);
      this.form.get('lon').patchValue(this.model.location.lon);
      this.form.get('lat').patchValue(this.model.location.lat);
      this.form.get('locationName').patchValue(this.model.location.locationName);
    }
  }

  onSubmit() {
    this.form.markAllAsTouched();
    // eslint-disable-next-line no-console
    console.log(this.form.value);
    if (this.mode === FormMode.CREATE) {
      this.realEstateService.create(this.buildDto()).subscribe(() => this.navigateToListPage());
    } else {
      this.realEstateService.update(this.modelId, this.buildDto()).subscribe(() => this.navigateToListPage());
    }
  }

  buildDto(): NewRealEstate {
    return {
      name: this.form.get('name').value,
      category: this.form.get('category').value,
      description: this.form.get('description').value,
      squareMeter: this.form.get('squareMeter').value,
      price: this.form.get('price').value,
      numberOfRooms: this.form.get('numberOfRooms').value,
      hasBalncony: this.form.get('hasBalcony').value,
      hasAirCondition: this.form.get('hasAirCondition').value,
      ownerPhoneNumber: this.form.get('name').value,
      location: {
        lon: this.form.get('lon').value,
        lat: this.form.get('lat').value,
        locationName: this.form.get('locationName').value
      }
    };
  }

  navigateToListPage() {
    this.router.navigate(['/real-estate']);
  }

  locationChanged($event: Event) {
    // eslint-disable-next-line no-console
    console.log('asd');
    this.location = { lon: this.form.get('lon').value, lat: this.form.get('lat').value };
  }
}
