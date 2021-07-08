import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user.model";
import {FormGroup} from "@angular/forms";

const API_BASE_URL = environment.apiBaseUrl;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  registerNewUser(registrationForm: FormGroup): Observable<any> {
    return this.httpClient.post(API_BASE_URL + '/registration', registrationForm.getRawValue());
  }

  getUser(): Observable<any> {
    return this.httpClient.get(API_BASE_URL + '/userInfo');
  }

  updateUser(userUpdateDto: User): Observable<any> {
    return this.httpClient.put(API_BASE_URL + '/userInfo', userUpdateDto);
  }

  changePassword(passwordForm: FormGroup): Observable<any> {
    return this.httpClient.post(API_BASE_URL + '/registration', passwordForm.getRawValue());
  }


}
