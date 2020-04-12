import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

/* Module */
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'

/* Componentes */
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DirectivaComponent } from './components/directiva/directiva.component';
import { ClientesComponent } from './components/clientes/clientes.component'
import { FormComponent } from './components/clientes/form.component';

/* Servicios */
import { ClienteService } from './components/clientes/cliente.service';
/* Rutas */
import { APP_ROUTING } from './app.routes';

/* Otros */
import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es-MX';
registerLocaleData(localeES, 'es-MX');


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    APP_ROUTING
  ],
  providers: [ClienteService, {provide: LOCALE_ID, useValue: 'es-MX'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
