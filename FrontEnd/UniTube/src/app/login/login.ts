import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
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
  }

  public enviarDados() {
    console.log(this.loginForm.getRawValue());
  }
}
