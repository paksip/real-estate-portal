import { Component, ElementRef, Inject, OnInit, Optional, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormMode } from 'app/real-estate/real-estate.component';
import { RealEstateService } from 'app/real-estate/real-estate.service';

@Component({
  selector: 'jhi-real-estate-form',
  templateUrl: './real-estate-form.component.html',
  styleUrls: ['./real-estate-form.component.scss']
})
export class RealEstateFormComponent implements OnInit {
  @ViewChild('file', { static: false }) file: ElementRef;
  FormMode = FormMode;
  images: string[];

  constructor(
    private realEstateService: RealEstateService,
    @Optional() public dialogRef: MatDialogRef<RealEstateFormComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public modalData: { id: number; mode: FormMode }
  ) {}

  ngOnInit() {}

  onFileChange() {}

  onFileClicked() {
    this.file.nativeElement.value = null;
  }
}
