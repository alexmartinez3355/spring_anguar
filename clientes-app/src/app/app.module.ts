import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

/* Componentes */
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DirectivaComponent } from './components/directiva/directiva.component';
import { ClientesComponent } from './components/clientes/clientes.component'
/* Servicios */
import { ClienteService } from './components/clientes/cliente.service';
/* Rutas */
import { APP_ROUTING } from './app.routes'; 

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent
  ],
  imports: [
    BrowserModule,
    APP_ROUTING
  ],
  providers: [ClienteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
