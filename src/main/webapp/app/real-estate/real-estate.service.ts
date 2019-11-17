import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RealEstate } from '../../../../../target/generated-sources/openapi/src/main/webapp';
import { NewRealEstate } from 'app/real-estate/models/newRealEstate';
import { RealEstateDetails } from 'app/real-estate/models/realEstateDetails';

@Injectable({
  providedIn: 'root'
})
export class RealEstateService {
  configUrl = 'api/realestates';

  constructor(private http: HttpClient) {}

  create(body: NewRealEstate): Observable<RealEstateDetails> {
    return this.http.post<RealEstateDetails>(`${this.configUrl}`, body);
  }

  update(id: number, body: NewRealEstate): Observable<RealEstateDetails> {
    return this.http.put<RealEstateDetails>(`${this.configUrl}/${id}`, body);
  }

  getAll(page: number, offset: number, specs: any): Observable<RealEstate[]> {
    return this.http.get<RealEstate[]>(`${this.configUrl}/?page=${page}&offset=${offset}&specs=${specs}`);
  }

  get(id: number): Observable<RealEstateDetails[]> {
    return this.http.get<RealEstateDetails[]>(`${this.configUrl}/${id}`);
  }

  getOwn(): Observable<RealEstate[]> {
    return this.http.get<RealEstate[]>(`${this.configUrl}/ownrealestates`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.configUrl}/${id}`);
  }
}
