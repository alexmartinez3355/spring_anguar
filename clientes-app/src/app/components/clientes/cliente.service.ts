import { Injectable } from '@angular/core';
import { Cliente } from './cliente.class';
import { CLIENTES } from './clientes.json';
import { of, Observable } from 'rxjs';

@Injectable()
export class ClienteService {

  constructor() { }

  getClientes(): Observable<Cliente[]> { 
    return of(CLIENTES);
  }
}