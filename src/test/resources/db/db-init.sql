CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--CREATE ROLE payment WITH LOGIN ENCRYPTED PASSWORD 'user';
--CREATE SCHEMA payment AUTHORIZATION payment;
--ALTER USER payment SET search_path TO payment

CREATE OR REPLACE FUNCTION public.naturalsort(text)
 RETURNS bytea
 LANGUAGE sql
 IMMUTABLE STRICT
AS $function$
  select string_agg(convert_to(coalesce(r[2],
                                        length(length(r[1])::text) || length(r[1])::text || r[1]),
                               'SQL_ASCII'),'\x00')
    from regexp_matches($1, '0*([0-9]+)|([^0-9]+)', 'g') r;
$function$
;