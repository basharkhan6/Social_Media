import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const API_BASE_URL = environment.apiBaseUrl;


@Injectable({
  providedIn: 'root'
})
export class StatusService {

  constructor(private httpClient: HttpClient) { }

  getAllStatus(): Observable<any> {
    return this.httpClient.get(API_BASE_URL + '/status');
  }
}
