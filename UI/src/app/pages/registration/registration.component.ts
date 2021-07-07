import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public registrationFormGroup: FormGroup;
  public showError: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.registrationFormGroup = this.formBuilder.group({
      'name': '',
      'email': '',
      'password': ''
    });
  }

  submitRegistrationForm() {
    this.userService.registerNewUser(this.registrationFormGroup.getRawValue()).subscribe(
      () => this.router.navigateByUrl('/login'),
      () => this.showError = true
    );
  }

}
