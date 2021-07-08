import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import {RouterModule, Routes} from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptorService} from "./config/auth-interceptor.service";
import { HomepageComponent } from './pages/homepage/homepage.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { StatusComponent } from './pages/status/status.component';
import { ProfileComponent } from './pages/profile/profile.component';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'home', component: HomepageComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'profile/:id', component: ProfileComponent},
  {path: 'status', component: StatusComponent},
  {path: 'status/:id', component: StatusComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    RegistrationComponent,
    StatusComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
