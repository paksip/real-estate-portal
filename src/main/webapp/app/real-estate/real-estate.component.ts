import { Component, OnInit } from '@angular/core';
import { RealEstateService } from 'app/real-estate/real-estate.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { RealEstateFormComponent } from 'app/real-estate/real-estate-form/real-estate-form.component';
import { RealEstate } from 'app/real-estate/models/realEstate';
import { FormMode } from 'app/real-estate/models/formMode';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-real-estate',
  templateUrl: './real-estate.component.html',
  styleUrls: ['./real-estate.component.scss']
})
export class RealEstateComponent implements OnInit {
  realEstates: RealEstate[];

  constructor(private realEstateService: RealEstateService, private dialog: MatDialog, private accountService: AccountService) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.realEstateService.getAll(0, 1, {}).subscribe(result => {
      this.realEstates = result;
    });
  }

  onView(id: number) {
    const dialogRef = this.dialog.open(RealEstateFormComponent, {
      width: '90vw',
      data: { id, mode: FormMode.VIEW }
    });

    dialogRef.afterClosed().subscribe(result => {});
  }

  onEdit(id: number) {
    const dialogRef = this.dialog.open(RealEstateFormComponent, {
      width: '90vw',
      data: { id, mode: FormMode.EDIT }
    });

    dialogRef.afterClosed().subscribe(result => {});
  }

  onCreate() {
    const dialogRef = this.dialog.open(RealEstateFormComponent, {
      autoFocus: true,
      position: {
        top: '',
        bottom: '',
        left: '',
        right: ''
      },
      width: '90vw',
      data: { id: null, mode: FormMode.CREATE }
    });

    dialogRef.afterClosed().subscribe(result => {});
  }

  onDelete(id: number) {
    this.realEstateService.delete(id).subscribe();
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }
}
