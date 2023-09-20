import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CyclesComponent } from './cycles/cycles.component';
import { HttpClientModule } from '@angular/common/http';
import { BorrowedCyclesComponent } from './borrowed-cycles/borrowed-cycles.component';
import { CartComponent } from './cart/cart.component';
import { DataService } from './data.service';

@NgModule({
  declarations: [
    AppComponent,
    CyclesComponent,
    BorrowedCyclesComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
