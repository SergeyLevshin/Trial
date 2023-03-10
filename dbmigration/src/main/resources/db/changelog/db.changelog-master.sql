create table customer (id int8 not null, discount1 int4, discount2 int4, name varchar(255), primary key (id));
create table discount (id int8 not null, end_time timestamp, start_time timestamp, value int4, product_id int8, primary key (id));
create table item (id int8 not null, amount int4, final_discount int4, final_price numeric(19, 2), initial_price numeric(19, 2), product_id int8, primary key (id));
create table product (id int8 not null, description varchar(255), name varchar(255), price numeric(19, 2), primary key (id));
create table product_scores (product_id int8 not null, scores_id int8 not null);
create table purchase (id int8 not null, purchase_date timestamp, receipt varchar(255), customer_id int8, primary key (id));
create table purchase_items (purchase_id int8 not null, items_id int8 not null);
create table receipt (id int8 not null, number varchar(255), update_time timestamp, primary key (id));
create table score (id int8 not null, value int4, customer_id int8, product_id int8, primary key (id));
create table statistic (id int8 not null, receipts_count int4 not null, total_discount numeric(19, 2), total_sum numeric(19, 2), customer_id int8, product_id int8, primary key (id));
alter table if exists product_scores add constraint UK_product_scores_scores_id unique (scores_id);
alter table if exists discount add constraint FK_discount_product foreign key (product_id) references product;
alter table if exists item add constraint FK_item_product foreign key (product_id) references product;
alter table if exists product_scores add constraint FK_product_scores_score foreign key (scores_id) references score;
alter table if exists product_scores add constraint FK_product_scores_product foreign key (product_id) references product;
alter table if exists purchase add constraint FK_purchase_customer foreign key (customer_id) references customer;
alter table if exists purchase_items add constraint FKd_purchase_items_item foreign key (items_id) references item;
alter table if exists purchase_items add constraint FKd_purchase_items_purchase foreign key (purchase_id) references purchase;
alter table if exists score add constraint FK_score_customer foreign key (customer_id) references customer;
alter table if exists score add constraint FK_score_product foreign key (product_id) references product;
alter table if exists statistic add constraint FK_statistic_customer foreign key (customer_id) references customer;
alter table if exists statistic add constraint FK_statistic_product foreign key (product_id) references product;