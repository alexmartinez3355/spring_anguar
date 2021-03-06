import { Injectable } from '@angular/core';
import { formatDate, DatePipe, registerLocaleData } from '@angular/common';
import { Cliente } from './cliente.class';
import { CLIENTES } from './clientes.json';
import { of, Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';

import { Router } from '@angular/router';

@Injectable()
export class ClienteService {

  private urlEndPoint: string = 'http://localhost:8080/api/clientes';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  constructor(private http: HttpClient, private router: Router) { }

  getClientes(page: number): Observable<any> {
    //return of(CLIENTES);
    return this.http.get<Cliente[]>(this.urlEndPoint+'/page/'+page).pipe(
      tap((response: any) => {
        /* console.log('ClienteService: tap 1'); */
        (response.content as Cliente[]).forEach(cliente => {
          /* console.log(cliente.nombre); */
        }
        )
      }),
      map((response: any) => {
        (response.content as Cliente[]).map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();

          let datePipe = new DatePipe('es-MX')
          /* cliente.fecha = formatDate(cliente.fecha, 'dd-MM-yyyy', 'en-Us'); */
          /* cliente.fecha = datePipe.transform(cliente.fecha, 'EEE dd, MMM yyyy '); */
          return cliente;
        });
        return response;
      }),
      tap(response => {
       /*  console.log('ClienteService: tap 2'); */
        (response.content as Cliente[]).forEach( cliente => {
          /* console.log(cliente.nombre); */
        }         
        )
      })
    );
  }



  getCliente(id): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/clientes']);
        console.error(e.error.mensaje);
        swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  create(cliente: Cliente): Observable<any> {
    return this.http.post<any>(this.urlEndPoint, cliente, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError(e);
        }
        console.error(e.error.mensaje);
        swal.fire('Error al crear al cliente', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  update(cliente: Cliente): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${cliente.cliente_id}`, cliente, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError(e);
        }
        console.error(e.error.mensaje);
        swal.fire('Error al editar al cliente', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  delete(id: number): Observable<Cliente> {
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        swal.fire('Error al eliminar al cliente', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
}