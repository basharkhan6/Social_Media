import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {finalize} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {User} from "../model/user.model";

const API_BASE_URL = environment.apiBaseUrl;
const API_LOGOUT_URL = environment.apiBaseUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  isAuthenticated(): boolean {
    return sessionStorage.getItem('cred') && sessionStorage.getItem('user') ? true : false;
  }

  authenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    // console.log(credentials ? {
    //   authorization : 'Basic ' + credentials.username + ':' + credentials.password} : "No Credential");

    this.http.get(API_BASE_URL + '/userInfo', {headers: headers}).subscribe((response: any) => {
        // console.log("Request sent")
        if (response['email'] == credentials.username) {
          // console.log("Authenticated");
          sessionStorage.setItem('cred', 'Basic ' + btoa(credentials.username + ':' + credentials.password));
          sessionStorage.setItem('user', JSON.stringify(response));
        } else {
          // console.log("Can not Authenticate!");
        }

        return callback && callback();
      },
      error => {
        alert('Invalid Login');
      });
  }

  logout(): void {
    this.http.post(API_LOGOUT_URL, {}).pipe(
      finalize(() => {
        sessionStorage.clear();
        this.router.navigate(['/login']);
      })).subscribe();
  }

  getLocalUser(): User | null {
    let jsonObj = sessionStorage.getItem('user');
    if (jsonObj != null) {
      return JSON.parse(jsonObj) as User;
    }

    return null;
  }

  setLocalUser(user: User): void {
    sessionStorage.setItem('user', JSON.stringify(user));
  }
}
