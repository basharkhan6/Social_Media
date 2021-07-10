import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../model/user.model";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Status} from "../../model/status.model";
import {StatusService} from "../../service/status.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public showLogin: boolean;
  public showNavMenu: boolean;
  public showUpdate: boolean;
  public showUpdateError: boolean;
  public showPasswordError: boolean;
  public showUpdateSuccess: boolean;
  public showPasswordSuccess: boolean;
  public profileOwner: boolean;

  public user: User;
  public userId: number;
  public passwordForm: FormGroup;
  public statusList: Status[];

  ngOnInit(): void {
    this.checkAuthentication();
    this.setupNav();
    this.loadProfileByCheckingOwner();
    this.resetPasswordForm();
  }

  constructor(
    private authService: AuthenticationService,
    private statusService: StatusService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  loadProfileByCheckingOwner(): void {
    this.getUserId();
    this.getLocalUserIfFound();

    if (this.userId && this.userId != this.user.id) {
      this.profileOwner = false;
      this.getAllStatusByUser(this.userId);
    } else {
      this.profileOwner = true;
      this.getAllStatusByUser();
    }
  }

  getUserId(): void {
    this.route.params.subscribe(params => {
      const paramName = 'id';
      this.userId = +params[paramName]; // (+) converts string 'id' to a number
    });
  }

  getLocalUserIfFound(): void {
    let obj = this.authService.getLocalUser();
    if (obj) {
      this.user = obj;
    }
  }

  updateUser(): void {
    this.showUpdateError = false;
    this.showUpdateSuccess = false;
    this.userService.updateUser(this.user).subscribe(
      () => {
        this.authService.setLocalUser(this.user);
        this.showUpdateSuccess = true;
      },
      ()=> this.showUpdateError = true
    );
  }

  getAllStatusByUser(userId?: number): void {
    if (userId) {
      this.statusService.getAllPublicStatusByUserId(userId).subscribe(
        data => {
          this.statusList = data;
        },
        error => console.log(error)
      );
    } else {
      this.statusService.getAllStatusByUser().subscribe(
        data => {
          this.statusList = data;
        },
        error => console.log(error)
      );
    }
  }


  changePassword(): void {
    this.showPasswordError = false;
    this.showPasswordSuccess = false;

    if (this.passwordForm.valid) {
      this.userService.changePassword(this.passwordForm).subscribe(
        () => this.showPasswordSuccess = true,
        ()=> this.showPasswordError = true
      );
    } else {
      alert('Please fillup all the required fields');
    }
  }

  resetPasswordForm(): void {
    this.passwordForm = this.formBuilder.group({
      'oldPassword': ['', Validators.required],
      'newPassword': ['', Validators.required]
    });
  }

  checkAuthentication(): void {
    if (!this.authService.isAuthenticated())
      this.router.navigate(['login'], { queryParams: { redirectUrl: this.router.url }});
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
