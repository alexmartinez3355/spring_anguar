import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente.class';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[] = [
    {cliente_id: 1, nombre: 'Alex', apellido: 'Martínez', email: 'alex@gmail.com', fecha: '2020'},
    {cliente_id: 2, nombre: 'Araceli', apellido: 'Campos', email: 'araceli@gmail.com', fecha: '2020'},
    {cliente_id: 3, nombre: 'Valentina', apellido: 'Martínez', email: 'vale_m@gmail.com', fecha: '2020'},
    {cliente_id: 4, nombre: 'Efren', apellido: 'Martínez', email: 'efren@gmail.com', fecha: '2020'}
  ];
  constructor() { }

  ngOnInit(): void {
  }

}
