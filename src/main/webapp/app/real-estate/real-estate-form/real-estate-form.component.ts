import { Component, ElementRef, Inject, OnInit, Optional, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { RealEstateService } from 'app/real-estate/real-estate.service';
import { FormControl, FormGroup } from '@angular/forms';
import { FormMode } from 'app/real-estate/models/formMode';
import { CategoryEnum } from 'app/real-estate/models/category';
import { RealEstateDetails } from 'app/real-estate/models/realEstateDetails';
import { MapLocation } from 'app/real-estate/models/mapLocation';
import { NewRealEstate } from 'app/real-estate/models/newRealEstate';

@Component({
  selector: 'jhi-real-estate-form',
  templateUrl: './real-estate-form.component.html',
  styleUrls: ['./real-estate-form.component.scss']
})
export class RealEstateFormComponent implements OnInit {
  @ViewChild('file', { static: false }) file: ElementRef;
  model: RealEstateDetails;
  location: MapLocation;
  CategoryEnum = CategoryEnum;
  FormMode = FormMode;
  form: FormGroup;

  constructor(
    private realEstateService: RealEstateService,
    @Optional() public dialogRef: MatDialogRef<RealEstateFormComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public modalData: { id: number; mode: FormMode }
  ) {
    this.location = {
      lon: -23.8779431,
      lat: -49.8046873
    };
  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.form = new FormGroup({
      name: new FormControl(''),
      category: new FormControl(''),
      description: new FormControl(''),
      squareMeter: new FormControl(''),
      price: new FormControl(''),
      numberOfRooms: new FormControl(''),
      hasBalcony: new FormControl(false),
      hasAirCondition: new FormControl(false),
      ownerPhoneNumber: new FormControl(''),
      lon: new FormControl(''),
      lat: new FormControl(''),
      locationName: new FormControl('')
    });

    if (this.modalData.id) {
      this.realEstateService.get(this.modalData.id).subscribe(result => {
        this.model = result;

        this.form.get('name').patchValue(result.name);
        this.form.get('category').patchValue(result.category);
        this.form.get('description').patchValue(result.description);
        this.form.get('squareMeter').patchValue(result.squareMeter);
        this.form.get('price').patchValue(result.price);
        this.form.get('numberOfRooms').patchValue(result.numberOfRooms);
        this.form.get('hasBalcony').patchValue(result.hasBalcony);
        this.form.get('hasAirCondition').patchValue(result.hasAirCondition);
        this.form.get('ownerPhoneNumber').patchValue(result.ownerPhoneNumber);
        this.form.get('lon').patchValue(result.location.lon);
        this.form.get('lat').patchValue(result.location.lat);
        this.form.get('locationName').patchValue(result.location.locationName);
      });
    }
  }

  categoryKeys(): Array<string> {
    return Object.keys(this.CategoryEnum);
  }

  onSubmit() {
    // eslint-disable-next-line no-console
    console.log(this.form.value);
    if (this.modalData.mode === FormMode.CREATE) {
      this.realEstateService.create(this.buildDto()).subscribe();
    } else {
      this.realEstateService.update(this.modalData.id, this.buildDto()).subscribe();
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
      hasBalcony: this.form.get('hasBalcony').value,
      hasAirCondition: this.form.get('hasAirCondition').value,
      ownerPhoneNumber: this.form.get('name').value,
      location: {
        lon: this.form.get('lon').value,
        lat: this.form.get('lat').value,
        locationName: this.form.get('locationName').value
      }
    };
  }

  changeCategory(e) {
    this.form.get('category').setValue(e.target.value, {
      onlySelf: true
    });
  }
}
