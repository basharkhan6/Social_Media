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

  public registrationForm: FormGroup;
  public showError: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.setupForm();
  }

  setupForm(): void {
    this.registrationForm = this.formBuilder.group({
      'name': '',
      'email': '',
      'password': ''
    });
  }

  submitRegistrationForm() {
    this.userService.registerNewUser(this.registrationForm).subscribe(
      () => this.router.navigate(['login']),
    () => this.showError = true
    );
  }

}
