import { Component, OnInit } from '@angular/core';
import { InvoicesService } from './invoices.service';
import { Invoice } from './invoice.model';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.css']
})
export class InvoicesComponent implements OnInit {
  invoices: Invoice[];

  constructor(private invoicesService: InvoicesService) { }

  ngOnInit() {
    this.invoicesService.findAll().subscribe(invoices => this.invoices = invoices);
  }

}
