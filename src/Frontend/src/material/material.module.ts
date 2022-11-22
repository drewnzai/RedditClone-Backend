import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from 'src/app/header/header.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'
import { MatToolbarModule } from '@angular/material/toolbar'; 
import {MatButtonModule} from '@angular/material/button'


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatProgressSpinnerModule,
    MatToolbarModule,
    MatButtonModule
  ],
  exports: [
    CommonModule,
    MatProgressSpinnerModule,
    MatToolbarModule,
    MatButtonModule
  ]
})
export class MaterialModule { }
