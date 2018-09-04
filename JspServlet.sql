create table users
(
	name varchar,
	email varchar not null,
	password varchar not null,
	id serial not null
		constraint user_id_pk
			primary key,
	phone varchar,
	create_at timestamp default now()
)
;

create unique index user_id_uindex
	on users (id)
;

create table role
(
	name varchar not null,
	id serial not null
		constraint role_id_pk
			primary key
)
;

create unique index role_id_uindex
	on role (id)
;

create table user_role
(
	id serial not null
		constraint user_role_pkey
			primary key,
	user_id integer not null
		constraint user_role_user_id_fk
			references users,
	role_id integer not null
		constraint user_role_role_id_fk
			references role
)
;

create unique index user_role_id_uindex
	on user_role (id)
;

create table user_log
(
	id serial not null
		constraint user_log_pkey
			primary key,
	username varchar not null,
	time timestamp not null,
	type varchar not null
)
;

create unique index user_log_id_uindex
	on user_log (id)
;

create table product_log
(
	id serial not null
		constraint sanpham_log_pkey
			primary key,
	type varchar not null,
	time timestamp not null,
	username varchar not null
)
;

create unique index sanpham_log_id_uindex
	on product_log (id)
;

create table type
(
	id serial not null
		constraint type_pkey
			primary key,
	name varchar not null,
	create_at timestamp default now()
)
;

create unique index type_id_uindex
	on type (id)
;

create table picture
(
	id serial not null
		constraint picture_pkey
			primary key,
	url varchar,
	create_at timestamp default now()
)
;

create table product
(
	id serial not null
		constraint sanpham_pkey
			primary key,
	user_id integer not null
		constraint sanpham_user_id_fk
			references users,
	price numeric not null,
	name varchar not null,
	type_id integer not null
		constraint product_type_id_fk
			references type,
	create_at timestamp default now(),
	image_id integer
		constraint product_picture_id_fk
			references picture
)
;

create unique index sanpham_id_uindex
	on product (id)
;

create unique index picture_id_uindex
	on picture (id)
;

