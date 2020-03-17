import { Injectable } from '@angular/core';
import { Cliente } from './cliente.class';
import { CLIENTES } from './clientes.json';

@Injectable()
export class ClienteService {

  constructor() { }

  getClientes(): Cliente[]{ return CLIENTES;
  
  }
}