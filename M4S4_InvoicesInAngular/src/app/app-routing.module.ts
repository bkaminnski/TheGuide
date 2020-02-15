import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InvoicesComponent } from './invoices/invoices.component';


const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'invoices', component: InvoicesComponent },
  { path: '**', redirectTo: '/dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
