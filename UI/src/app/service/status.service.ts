import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {FormGroup} from "@angular/forms";

const API_BASE_URL = environment.apiBaseUrl;

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  private url = API_BASE_URL + '/status';

  constructor(private httpClient: HttpClient) { }

  getStatus(id: number): Observable<any> {
    return this.httpClient.get(this.url + '/' + id);
  }

  getAllStatus(): Observable<any> {
    return this.httpClient.get(this.url);
  }

  getAllStatusByUserId(userId: number): Observable<any> {
    return this.httpClient.get(this.url + '/findByUser?id=' + userId);
  }

  postStatus(statusForm: FormGroup): Observable<any> {
    return this.httpClient.post(this.url, statusForm.getRawValue());
  }
}
