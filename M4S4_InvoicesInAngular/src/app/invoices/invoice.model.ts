import { InvoiceLine } from './invoice-line.model';

export class Invoice {
  totalAmount: number;
  invoiceLines: InvoiceLine[];
}
