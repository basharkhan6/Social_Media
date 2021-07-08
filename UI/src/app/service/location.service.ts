import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

const API_BASE_URL = environment.apiBaseUrl;

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private url = API_BASE_URL + '/location';

  constructor(private httpClient: HttpClient) { }

  getAllLocation(): Observable<any> {
    return this.httpClient.get(this.url);
  }

}
