import { Component, OnInit } from '@angular/core';
import { RealEstateService } from 'app/real-estate/real-estate.service';
import { RealEstate } from 'app/real-estate/models/realEstate';
import { AccountService } from 'app/core/auth/account.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'jhi-real-estate',
  templateUrl: './real-estate.component.html',
  styleUrls: ['./real-estate.component.scss']
})
export class RealEstateComponent implements OnInit {
  realEstates: RealEstate[];

  constructor(
    private realEstateService: RealEstateService,
    private accountService: AccountService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.realEstateService.getAll(0, 20, null).subscribe(result => {
      this.realEstates = result.content;
    });
  }

  onView(id: number) {
    this.router.navigate([`get/${id}`], { relativeTo: this.activatedRoute });
  }

  onEdit(id: number) {
    this.router.navigate([`update/${id}`], { relativeTo: this.activatedRoute });
  }

  onCreate() {
    this.router.navigate(['create'], { relativeTo: this.activatedRoute });
  }

  onDelete(id: number) {
    this.realEstateService.delete(id).subscribe();
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }
}
