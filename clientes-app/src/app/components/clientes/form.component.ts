import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente.class';
import { ClienteService } from './cliente.service';
import { Router, ActivatedRoute } from '@angular/router'
import swal from 'sweetalert2'

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  cliente: Cliente = new Cliente()
  titulo:string = "Crear Cliente"

  constructor(private cliente_service: ClienteService, private router: Router, private activate_route: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargar_cliente();
  }

  cargar_cliente(): void{
    this.activate_route.params.subscribe( params => {
      let id = params['id']
      if(id){
        this.cliente_service.getCliente(id).subscribe( (cliente) => this.cliente = cliente)
      }
    })
  }

  public create(): void{
    this.cliente_service.create(this.cliente)
    .subscribe(json => {
      this.router.navigate(['/clientes'])
      swal.fire(  'Cliente guardado',  `Cliente ${ json.cliente.nombre } se ha creado con exito`,  'success');
    }
      /* response => this.router.navigate(['/clientes']) */
    )
  }

  update(): void{
    this.cliente_service.update(this.cliente)
    .subscribe( json => {
      this.router.navigate(['/clientes'])
      swal.fire('Cliente Actualizado', `Cliente ${json.cliente.nombre} actualizado con exito!`, 'success')
    })
  }

}
