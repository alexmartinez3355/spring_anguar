import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente.class';
import { ClienteService } from './cliente.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  cliente: Cliente = new Cliente()
  titulo:string = "Crear Cliente"

  constructor(private cliente_service: ClienteService, private router: Router) { }

  ngOnInit(): void {
  }

  public create(): void{
    this.cliente_service.create(this.cliente).subscribe(
      response => this.router.navigate(['/clientes'])
    )
  }

}
