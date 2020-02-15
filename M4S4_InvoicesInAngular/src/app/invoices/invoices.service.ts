import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Invoice } from './invoice.model';

@Injectable({
  providedIn: 'root'
})
export class InvoicesService {

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Invoice[]> {
    return this.httpClient.get<Invoice[]>('/api/invoices');
  }
}
