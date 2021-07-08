import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {StatusService} from "../../service/status.service";
import {Status} from "../../model/status.model";
import {User} from "../../model/user.model";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  public showLogin: boolean;
  public showNavMenu: boolean;

  public statusList: Status[];
  public user: User;

  constructor(
    private authService: AuthenticationService,
    private statusService: StatusService
  ) { }

  ngOnInit(): void {
    this.setupNav();
    this.getLocalUserIfFound();
    this.getAllStatus();
  }

  getLocalUserIfFound(): void {
    let obj = this.authService.getLocalUser();
    if (obj) {
      this.user = obj;
    }
  }

  getAllStatus():void {
    this.statusService.getAllStatus().subscribe(
      data => {
        this.statusList = data;
      },
      error => console.log(error)
    );
  }

  setupNav(): void {
    this.showNavMenu = false;
    this.showLogin = !this.authService.isAuthenticated();
  }

  toggleNav(): void {
    this.showNavMenu=!this.showNavMenu;
  }

  logout(): boolean {
    this.authService.logout();
    return false;
  }
}
