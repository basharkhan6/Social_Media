import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {finalize} from "rxjs/operators";
import {environment} from "../../environments/environment";

const API_BASE_URL = environment.apiBaseUrl;
const API_LOGOUT_URL = environment.apiBaseUrl;


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  authenticated = false;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  authenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    // console.log("Sending HTTP Get request.");
    // console.log(credentials ? {
    //   authorization : 'Basic ' + credentials.username + ':' + credentials.password} : "No Credential");

    this.http.get(API_BASE_URL + '/userInfo', {headers: headers}).subscribe((response: any) => {
        // console.log("Request sent")
        if (response['name']) {
          this.authenticated = true;
          sessionStorage.setItem('cred', 'Basic ' + btoa(credentials.username + ':' + credentials.password));
          // console.log("Authenticated");
        } else {
          this.authenticated = false;
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
        this.authenticated = false;
        sessionStorage.clear();
        this.router.navigateByUrl('/login');
      })).subscribe();
  }

}
