import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RealEstateDetails } from 'app/real-estate/models/realEstateDetails';

@Injectable({
  providedIn: 'root'
})
export class ImageHandlerService {
  configUrl = 'api/realestates';

  constructor(private http: HttpClient) {}

  download(fileName: string): Observable<any> {
    return this.http.get<any>(`${this.configUrl}/files/${fileName}`);
  }

  upload(id: string, file: Blob): Observable<any> {
    return this.http.post<any>(`${this.configUrl}/${id}/files`, file);
  }
}
