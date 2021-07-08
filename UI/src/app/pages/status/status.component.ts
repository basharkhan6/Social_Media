import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {StatusService} from "../../service/status.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Status} from "../../model/status.model";
import {Location} from "../../model/location.model";
import {PrivacyEnum} from "../../model/enumeration/privacy-enum";
import {LocationService} from "../../service/location.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  public showLogin: boolean;
  public showNavMenu: boolean;
  public showError = false;
  public showSuccess = false;

  public privacyEnum = PrivacyEnum;
  public locationList: Location[];

  public statusId: number;
  private status: Status;

  public statusForm: FormGroup;

  constructor(
    private authService: AuthenticationService,
    private statusService: StatusService,
    private locationService: LocationService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.checkAuthentication();
    this.getStatus();
    this.setupNav();
    this.resetForm();
    this.getAllLocation();
  }

  getStatusId(): void {
    this.route.params.subscribe(params => {
      const paramName = 'id';
      this.statusId = +params[paramName]; // (+) converts string 'id' to a number
    });
  }

  getStatus(): void {
    this.getStatusId();
    if (this.statusId) {
      this.statusService.getStatus(this.statusId).subscribe(
        data => {
          this.status = data;
          this.resetForm();
        },
        error => console.log(error)
      );
    }
  }

  getAllLocation(): void {
    this.locationService.getAllLocation().subscribe(
      data => this.locationList = data,
      error => console.log(error)
    );
  }

  submitStatus(): void {
    this.showError = false;
    this.showSuccess = false;
    if (this.statusForm.valid) {
      this.statusService.postStatus(this.statusForm).subscribe(
        () => {
          this.router.navigate(['/profile']);
          this.showSuccess = true;
        },
        ()=> this.showError = true
      );
    } else {
      alert('Please fillup all the required fields');
    }
  }

  resetForm(): void {
    if (this.status) {
      this.statusForm = this.formBuilder.group({
        'id': this.status.id,
        'details': [this.status.details, Validators.required],
        'privacy': [this.status.privacy, Validators.required],
        'location': this.formBuilder.group({
          'id': [this.status.location.id, Validators.required],
          'location': this.status.location.location
        })
      });
    } else {
      this.statusForm = this.formBuilder.group({
        'details': ['', Validators.required],
        'privacy': [PrivacyEnum.PUBLIC, Validators.required],
        'location': this.formBuilder.group({
          'id': ['', Validators.required],
          'location': ''
        })
      });
    }
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
