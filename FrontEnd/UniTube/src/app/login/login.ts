import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-login',
  imports: [
    MatIconModule, 
    MatDividerModule, 
    MatButtonModule, 
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css',
  standalone: true
})
export class Login implements OnInit {

  loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
  ){}

  ngOnInit(): void {
    this.criarFormulario();
  }

  private criarFormulario(): void {
    this.loginForm = this.formBuilder.group ({
      login: [ "" , Validators.required ],
      password: [ "" , Validators.required ] 
    });
    console.log("loginForm", this.loginForm);
    console.log("olá");
  }

  public enviarDados() {
    console.log(this.loginForm.getRawValue());
  }
}
