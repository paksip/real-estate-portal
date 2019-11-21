import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewRealEstate } from 'app/real-estate/models/newRealEstate';
import { RealEstateDetails } from 'app/real-estate/models/realEstateDetails';
import { RealEstate } from 'app/real-estate/models/realEstate';

@Injectable({
  providedIn: 'root'
})
export class RealEstateService {
  configUrl = 'api/realestates';

  constructor(private http: HttpClient) {}

  create(body: NewRealEstate): Observable<void> {
    return this.http.post<void>(`${this.configUrl}/`, body);
  }

  update(id: number, body: NewRealEstate): Observable<void> {
    return this.http.put<void>(`${this.configUrl}/${id}`, body);
  }

  getAll(page: number, offset: number, specs: any): Observable<any> {
    if (!specs) {
      specs = 'id>-1';
    }
    return this.http.get<any>(`${this.configUrl}/?page=${page}&offset=${offset}&search=${specs}`);
  }

  get(id: number): Observable<RealEstateDetails> {
    return this.http.get<RealEstateDetails>(`${this.configUrl}/${id}`);
  }

  getOwn(page: number, offset: number): Observable<any> {
    return this.http.get<any>(`${this.configUrl}/ownrealestates/?page=${page}&offset=${offset}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.configUrl}/${id}`);
  }
}
