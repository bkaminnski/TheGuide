CREATE TABLE public.invoice
(
    id bigint NOT NULL,
    "number" character varying(255) NOT NULL,
    total numeric(11, 2),
    PRIMARY KEY (id),
    UNIQUE ("number")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.invoice
    OWNER to alice;

CREATE TABLE public.invoice_line
(
    id bigint NOT NULL,
    description character varying NOT NULL,
    total numeric(11, 2),
    invoice_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (invoice_id)
        REFERENCES public.invoice (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.invoice_line
    OWNER to alice;
	
CREATE SEQUENCE public.invoice_id_seq;

ALTER SEQUENCE public.invoice_id_seq
    OWNER TO alice;
	
CREATE SEQUENCE public.invoice_line_id_seq;

ALTER SEQUENCE public.invoice_line_id_seq
    OWNER TO alice;
	
INSERT INTO public.invoice(
	id, "number", total)
	VALUES (nextval('invoice_id_seq'), 'INV/001', 100.01);
	
INSERT INTO public.invoice(
	id, "number", total)
	VALUES (nextval('invoice_id_seq'), 'INV/002', 200.02);
	
INSERT INTO public.invoice(
	id, "number", total)
	VALUES (nextval('invoice_id_seq'), 'INV/003', 300.03);

INSERT INTO public.invoice_line(
	id, description, total, invoice_id)
	VALUES (nextval('invoice_line_id_seq'), 'Day Rate', 70.01, (select id from invoice where number = 'INV/001'));
	
INSERT INTO public.invoice_line(
	id, description, total, invoice_id)
	VALUES (nextval('invoice_line_id_seq'), 'Night Rate', 30.00, (select id from invoice where number = 'INV/001'));
	
INSERT INTO public.invoice_line(
	id, description, total, invoice_id)
	VALUES (nextval('invoice_line_id_seq'), 'Stable Rate', 200.02, (select id from invoice where number = 'INV/002'));


