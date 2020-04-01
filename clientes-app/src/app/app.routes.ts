import { Routes, RouterModule } from '@angular/router';

import { DirectivaComponent } from './components/directiva/directiva.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { FormComponent } from './components/clientes/form.component';

const APP_ROUTES: Routes = [
    
    {path: 'directivas', component: DirectivaComponent},
    {path: 'clientes', component: ClientesComponent},
    {path: 'clientes/form', component: FormComponent},
    {path: 'clientes/form/:id', component: FormComponent},
    {path: '', redirectTo: '/clientes', pathMatch: 'full'}
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);