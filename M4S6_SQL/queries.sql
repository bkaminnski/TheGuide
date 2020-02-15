select * from invoice;

select * from invoice_line;

select * from invoice i left join invoice_line il on i.id = il.invoice_id;

select sum(total), invoice_id from invoice_line group by invoice_id;

INSERT INTO public.invoice_line(
	id, description, total, invoice_id)
	VALUES (nextval('invoice_line_id_seq'), 'Fixed Rate', 50.00, (select id from invoice where number = 'INV/001'));
	
select i.total as invoice_total, sum(il.total) as invoice_lines_total, invoice_id from invoice i join invoice_line il on i.id = il.invoice_id group by invoice_id, i.total;