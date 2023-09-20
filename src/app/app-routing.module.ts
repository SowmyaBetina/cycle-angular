import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CartComponent } from './cart/cart.component';
import { CyclesComponent } from './cycles/cycles.component';

const routes: Routes = [
  {path:'cycles', component:CyclesComponent},
  { path: 'api/cart', component: CartComponent },
  {path:'', redirectTo: '/cycles', pathMatch: 'full'}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
