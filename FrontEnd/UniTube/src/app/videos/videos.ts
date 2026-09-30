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

  videos: any[] = [];

  videoEditandoId: number | null = null;

  private url = "http://localhost:8080/videos";

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
  ) {}

  ngOnInit(): void {

    this.criarFormulario();

    this.videoForm.valueChanges
      .pipe(debounceTime(400))
      .subscribe((res) => {
        console.log(res);
      });

    this.buscarTodos();

  }

  private criarFormulario(): void {

    this.videoForm = this.formBuilder.group({
      titulo: ["", Validators.required],
      curso: ["", Validators.required],
      professor: ["", Validators.required],
      data: [null, Validators.required],
      duracao: [null, Validators.required]
    });
  }

  public buscarTodos(): void {
    this.httpClient.get<any[]>(this.url).subscribe((res) => {
        this.videos = res;
        console.log(this.videos);
      });
  }

  public criarVideo(): void {

    if (this.videoForm.invalid) {
      return;
    }

    if (this.videoEditandoId === null) {

      this.httpClient.post<any>(this.url,this.videoForm.getRawValue()).subscribe((videoCriado) => {

        console.log("Vídeo criado!");

        this.videos = [...this.videos,videoCriado];
        this.videoForm.reset();
      });

    }

    else {

      this.httpClient.put<any>(`${this.url}/${this.videoEditandoId}`,this.videoForm.getRawValue()).subscribe((videoAtualizado) => {
        
        console.log("Vídeo atualizado!");

        this.videos = this.videos.map(video =>video.id === videoAtualizado.id? videoAtualizado: video);
        this.videoEditandoId = null;
        this.videoForm.reset();
      });
    }
  }

  public editarVideo(video: any): void {

    this.videoEditandoId = video.id;

    this.videoForm.patchValue({
      titulo: video.titulo,
      curso: video.curso,
      professor: video.professor,
      data: video.data,
      duracao: video.duracao
    });
  }

  public deletarVideo(id: number): void {

    this.httpClient.delete(`${this.url}/${id}`).subscribe(() => {

      console.log("Vídeo deletado!");
      this.videos = this.videos.filter(video => video.id !== id);
      this.videoForm.reset();

    });
  }

  public cancelarEdicao(): void {

    this.videoEditandoId = null;
    this.videoForm.reset();
  }

}
