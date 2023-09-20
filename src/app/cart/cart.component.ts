import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cycles } from '../cycles';
import { CyclesComponent } from '../cycles/cycles.component';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  // Default value for the number of days
  cycles: Cycles[] = [];
  title = "Cart";
  rentednew : Cycles[] = [];
  subPrice: number = 0;
  stock: number = 5;
  constructor(private http: HttpClient, private dataService: DataService) {}
  
  // ngOnInit() {
  //   // Fetch the list of rented cycles from your Spring Boot API.
  //   //this.loadRentedCycles();
  //   this.showRentedCycles();
  // }
  // showRentedCycles() {
  //   // Make an HTTP request to get the rented cycles data.
  //   // Update this URL to match your API endpoint for rented cycles.
  //   this.http.get<Cycles[]>('http://localhost:8080/api/cart').subscribe((cycles) => {
  //     this.rentednew = cycles;
  //     this.calculateTotalPrice();
  //   });
  // }
  ngOnInit() {
    this.dataService.getRentedCycles().subscribe(rentedCycles => {
      console.log("performing ngOnInit");
      this.rentednew.push(JSON.parse(localStorage.getItem('cart') as string));
      console.log(rentedCycles);
      this.calculateTotalPrice();
    });
  }
  
  
  calculateTotalPrice() {
    this.subPrice = this.stock * 50;
  }
  onNumberOfDaysChange() {
    this.calculateTotalPrice();
  }
  openCart(id: number): void {
    const selectedCycle = this.cycles.find(cycle => cycle.id === id);
    if (selectedCycle) {
      this.rentednew.push(selectedCycle);
      this.dataService.updateRentedCycles(selectedCycle);
    }
}
}

