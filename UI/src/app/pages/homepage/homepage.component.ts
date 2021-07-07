import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {StatusService} from "../../service/status.service";
import {Status} from "../../model/status.model";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  public showLogin: boolean;
  public showNavMenu: boolean;

  public statusList: Status[];

  constructor(
    private authService: AuthenticationService,
    private statusService: StatusService
  ) { }

  ngOnInit(): void {
    this.showNavMenu = false;
    if (!this.authService.authenticated)
      this.showLogin = true;
    else
      this.showLogin = true;

    this.loadAllStatus();
  }

  loadAllStatus() {
    this.statusService.getAllStatus().subscribe(
      data => {
        this.statusList = data;
      },
      error => console.log(error)
    );
  }

  logout(): boolean {
    this.authService.logout();
    return false;
  }


  toggleNav(): void {
    this.showNavMenu=!this.showNavMenu;
  }
}
