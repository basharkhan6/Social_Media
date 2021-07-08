import { Injectable } from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {HttpEvent, HttpHandler, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService {

  constructor(private auth: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // console.log("Intercepting...")

    if (sessionStorage.getItem('cred')) {
      // console.log("Applying Intercept header..")

      const xhr = req.clone({
        // withCredentials: true,
        headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
          // @ts-ignore
          .set('authorization', sessionStorage.getItem('cred'))
      });

      // console.log(xhr)
      return next.handle(xhr);
    }
    else {
      // console.log("No Credential")
      return next.handle(req);
    }
  }
}
