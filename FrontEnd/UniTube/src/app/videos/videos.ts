import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { debounceTime } from 'rxjs';

@Component({
  selector: 'app-videos',
  imports: [
    MatIconModule, 
    MatDividerModule, 
    MatButtonModule, 
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule
  ],
  templateUrl: './videos.html',
  styleUrl: './videos.css',
})
export class Videos implements OnInit {

  videoForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
  ){}

  ngOnInit(): void {
    this.criarFormulario();

    this.videoForm.valueChanges.pipe(debounceTime(400)).subscribe((res) => {
      console.log(res);
    })

    this.httpClient.get("http://localhost:8080/videos").subscribe((res) => {
      console.log(res);
      
    })
  }

  private criarFormulario(): void {
    this.videoForm = this.formBuilder.group ({
      titulo: [ "" , Validators.required ],
      curso: [ "" , Validators.required ], 
      professor: [ "" , Validators.required ],
      data: [ null , Validators.required ], 
      duracao: [ null , Validators.required ]
    });
  }

  public enviarDados() {
    console.log(this.videoForm.valid);
    console.log(this.videoForm.getRawValue());

    if (this.videoForm.valid) {
      console.log("Envie para o BackEnd.")
      this.httpClient.post("http://localhost:8080/videos", this.videoForm.getRawValue()).subscribe(() => { 
      
      })
    }
  }
}
