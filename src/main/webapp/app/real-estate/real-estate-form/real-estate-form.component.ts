import { Component, ElementRef, Inject, OnInit, Optional, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { RealEstateService } from 'app/real-estate/real-estate.service';
import { FormControl, FormGroup } from '@angular/forms';
import { FormMode } from 'app/real-estate/models/formMode';
import { CategoryEnum } from 'app/real-estate/models/category';
import { RealEstateDetails } from 'app/real-estate/models/realEstateDetails';
import { MapLocation } from 'app/real-estate/models/mapLocation';

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
    this.form = new FormGroup({
      name: new FormControl(''),
      category: new FormControl(''),
      description: new FormControl(''),
      squareMeter: new FormControl(''),
      price: new FormControl(''),
      numberOfRooms: new FormControl(''),
      hasBalcony: new FormControl(false),
      hasAirCondition: new FormControl(false),
      ownerPhoneNumber: new FormControl('')
    });

    if (this.modalData.id) {
      this.realEstateService.get(this.modalData.id).subscribe(result => {
        this.model = result;
        this.form.patchValue(result);
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
      this.realEstateService.create(this.form.value).subscribe();
    } else {
      this.realEstateService.update(this.modalData.id, this.form.value).subscribe();
    }
  }

  changeCategory(e) {
    this.form.get('category').setValue(e.target.value, {
      onlySelf: true
    });
  }
}
