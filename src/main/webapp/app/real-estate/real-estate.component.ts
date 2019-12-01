import {Component, OnInit} from '@angular/core';
import {RealEstateService} from 'app/real-estate/real-estate.service';
import {RealEstate} from 'app/real-estate/models/realEstate';
import {AccountService} from 'app/core/auth/account.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormMode} from "app/real-estate/models/formMode";
import {FormControl, FormGroup} from "@angular/forms";
import {CategoryEnum} from "app/real-estate/models/category";

@Component({
  selector: 'jhi-real-estate',
  templateUrl: './real-estate.component.html',
  styleUrls: ['./real-estate.component.scss']
})
export class RealEstateComponent implements OnInit {
  realEstates: RealEstate[];
  showsOwn = false;
  FormMode = FormMode;
  CategoryEnum = CategoryEnum;
  form: FormGroup;

  constructor(
    private realEstateService: RealEstateService,
    private accountService: AccountService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.initForm();
    this.load();
  }

  initForm() {
    this.form = new FormGroup({
      name: new FormControl(''),
      squareMeterMin: new FormControl(''),
      squareMeterMax: new FormControl(''),
      priceMin: new FormControl(''),
      priceMax: new FormControl(''),
      numberOfRooms: new FormControl(''),
    });
  }

  onFilter() {
    let specs = '';
    if (this.form.get('name').value) {
      specs += `name:*${this.form.get('name').value}* AND `;
    }
    if (this.form.get('squareMeterMin').value) {
      specs += `squareMeter>${this.form.get('squareMeterMin').value}  AND `;
    }
    if (this.form.get('squareMeterMax').value) {
      specs += `squareMeter<${this.form.get('squareMeterMax').value}  AND `;
    }
    if (this.form.get('priceMin').value) {
      specs += `price>${this.form.get('priceMin').value}  AND `;
    }
    if (this.form.get('priceMax').value) {
      specs += `price<${this.form.get('priceMax').value}  AND `;
    }
    if (this.form.get('numberOfRooms').value) {
      specs += `numberOfRooms:${this.form.get('numberOfRooms').value}  AND `;
    }

    this.realEstateService.getAll(0, 20, specs).subscribe(result => {
      this.realEstates = result.content;
      this.showsOwn = false;
    });
  }

  load() {
    this.realEstateService.getAll(0, 20, null).subscribe(result => {
      this.realEstates = result.content;
      this.showsOwn = false;
    });
  }

  onView(id: number) {
    this.router.navigate([`get/${id}`], {relativeTo: this.activatedRoute});
  }

  onEdit(id: number) {
    this.router.navigate([`update/${id}`], {relativeTo: this.activatedRoute});
  }

  onCreate() {
    this.router.navigate(['create'], {relativeTo: this.activatedRoute});
  }

  onDelete(id: number) {
    this.realEstateService.delete(id).subscribe(() => this.onMyRealEstates());
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  onMyRealEstates() {
    this.realEstateService.getOwn(0, 20).subscribe(result => {
      this.realEstates = result.content;
      this.showsOwn = true;
    });
  }

  onClearFilter() {
    this.form.reset();
    this.onFilter();
  }
}
